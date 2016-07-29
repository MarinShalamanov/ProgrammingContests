touples :: [Int] -> (Int, Int)
touples [a, b] = (a, b)
touples _ = error "array doenst consist of two elements"

readPoint :: String -> (Int, Int)
readPoint line = touples $ map (read :: String->Int) $ words line

distance :: (Int, Int) -> (Int, Int) -> Double
distance (a, b) (c, d) = sqrt (fromIntegral $ (c-a)*(c-a) + (d-b)*(d-b))

len :: [(Int, Int)] -> Double
len [(a, b)] = 0.0
len x = distance (head x) (x !! 1) + len (tail x) 

main =  do
	n <- readLn :: IO Int
	s <- getContents
	let points = map readPoint (take n $ lines s)	

	putStrLn $ show $len ((last points) : points)
	


