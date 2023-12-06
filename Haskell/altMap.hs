altMap :: (a -> b) -> (a -> b) -> [a] -> [b]
altMap p1 p2 [] = [] 
altMap p1 p2 (x:xs) = p1 x : altMap p2 p1 xs 