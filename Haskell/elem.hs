elem' :: Eq a => a -> [a] -> Bool
elem' x [] = False
elem' x (y:ys) = if x==y then True else elem' x ys