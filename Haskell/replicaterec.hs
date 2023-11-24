replicate' :: Int -> a -> [a]
replicate' 0 a = []
replicate' x a = a:(replicate' (x-1) a)