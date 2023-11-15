luhn :: Int -> Int -> Int -> Int -> Bool
luhn a b c d = mod (d + f c + b + f a) 10 == 0
    where f x = if (x*2 < 10) then x*2 else x*2-9