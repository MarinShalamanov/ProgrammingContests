
data AVL = EmptyNode | Node 
	{ val :: Int
	, size :: Int
	, left :: AVL
	, right :: AVL
	} deriving (Show)

createTree :: Int -> AVL
createTree x = Node x 1 EmptyNode EmptyNode

insert :: Int -> AVL -> AVL
insert x EmptyNode = createTree x
insert x (Node a s left right) 
	| x >= a =
		let newRight = (insertAVL x right)
                    newHeight = max (1 + height left) (height newRight) 
                    in Node a newHeight left newRight
	| x < a = 
		let newLeft = (insertAVL x left)
		    newHeight = max (1 + height right) (height newLeft)
		    in Node a newHeight newLeft right

balance :: AVL -> AVL
balance EmptyNode = EmptyNode
balance (Node a s l r) = 
	let factor = (height l) - (height r)
	in if factor > 1 
		then let h2 = 1 + max (height $ right l) (height r)
			 h = 1 + max h2  (height $ left l)
		         in Node (val l) h (left l) (Node a h2 (right l) r) 
		else if factor < -1
			then let h2 = 1 + max (height l) (height $ left r)
				 h = 1 + max h2 (height $ right r)
			         in Node (val r) h (Node a h2 l (left r)) (right r)
			else (Node a s l r) 
insertAVL :: Int -> AVL -> AVL
insertAVL x tree = balance $ insert x tree

height :: AVL -> Int 
height EmptyNode = 0
height tree = 1 + max (height $ left tree) (height $ right tree) 

insertMultiple :: AVL -> [Int] -> AVL
insertMultiple tree els = foldr insertAVL tree els

treeFromElements :: [Int] -> AVL
treeFromElements x = insertMultiple EmptyNode x

toString :: AVL -> String
toString EmptyNode = ""
toString tree = "(" ++ (toString $ left tree) ++ ") " ++ (show $ val tree) ++ 
	" (" ++ (toString $ right tree) ++ ")"

main = 
	putStrLn . show . height . treeFromElements $ [1 .. 1024]








