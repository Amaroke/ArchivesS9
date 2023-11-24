insert' :: Ord a => a -> [a] -> [a]
insert' x [] = x:[]
insert' x (y:ys) = if x<=y then (x:(y:ys)) else y: (insert' x ys)

isort :: Ord a => [a] -> [a]
isort [] = []
isort (x:xs) = insert' x (isort xs)