touples :: [Int] -> (Int, Int)
touples [a, b] = (a, b)
touples _ = error "array doenst consist of two elements"

readPoint :: String -> (Int, Int)
readPoint line = touples $ map (read :: String->Int) $ words line

distance :: (Int, Int) -> (Int, Int) -> Double
distance (a, b) (c, d) = sqrt (fromIntegral $ (c-a)*(c-a) + (d-b)*(d-b))

ar :: (Int, Int) -> (Int, Int) -> Double
ar (x1, y1) (x2, y2) = fromIntegral( (x1 + x2) * (y2 - y1)) / 2.0; 

triangleArea :: (Int, Int) -> (Int, Int) -> (Int, Int) -> Double
triangleArea a b c = ar a b + ar b c + ar c a

stripArea :: (Int, Int) -> [(Int, Int)] -> Double
stripArea anch [x] = 0.0
stripArea anch x = stripArea anch (tail x) + triangleArea anch (head x) (x !! 1)
	 

main =  do
	n <- readLn :: IO Int
	s <- getContents
	let points = map readPoint (take n $ lines s)	

	putStrLn $ show $ abs ( stripArea (head points) (tail points))
	


