takeWhile' :: (a -> Bool) -> [a] -> [a]
takeWhile' p xs = foldr (\x xs -> if p x then (x:xs) else []) []