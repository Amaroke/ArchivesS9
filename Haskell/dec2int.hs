dec2int :: [Int] -> Int
dec2int = foldl (\y x -> x + (10 * y)) 0