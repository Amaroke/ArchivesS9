all' :: (a -> Bool) -> [a] -> Bool
all' p (x:xs) = (p x) && (all' p xs)