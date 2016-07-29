
rotate :: String -> String 
rotate x = (tail x) ++ [head x]

genRotations :: String -> Int ->  [String]
genRotations x 0 = [x]
genRotations x n = let rotX = rotate x in 
	[x] ++ (genRotations rotX (n-1)) 

solveRow :: String -> String
solveRow x = unwords $  genRotations (rotate x) ((length x) -1)

main = do
	n <- readLn :: IO Int
	s <- getContents
	putStrLn $ unlines $ map solveRow $  take n $ lines s
