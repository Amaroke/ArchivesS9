length' :: [a] -> Int 
length' [] = 0
length' l = 1 + length' (tail l)