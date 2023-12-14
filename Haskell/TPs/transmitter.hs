import Data.Char

type Bit = Int

iterate' :: (a -> a) -> a -> [a]
iterate' p x = x : iterate p (p x)

fill :: [a] -> a -> Int -> [a]
fill xs v n = xs ++ take (n - length xs) (repeat v)

chop :: Int -> [a] -> [[a]]
chop n [] = []
chop n xs = take n xs : chop n (drop n xs)

bin2int :: [Bit] -> Int
bin2int xs = sum [x * y | (x, y) <- zip (iterate (*2) 1) (reverse xs)]

bin2int' :: [Bit] -> Int
bin2int' = foldl (\y x -> x + (2 * y)) 0

int2bit :: Int -> [Int]
int2bit n = reverse (fill (int2bitNot8Bytes n) 0 8)
  where
    int2bitNot8Bytes 0 = []
    int2bitNot8Bytes xs = xs `mod` 2 : int2bitNot8Bytes (xs `div` 2)

encode' :: [Char] -> [Bit]
encode' [] = []
encode' (x:xs) = int2bit (ord x) ++ [if odd (sum (int2bit (ord x))) then 1 else 0] ++ encode' xs

decode :: [Bit] -> [Char]
decode [] = []
decode xs
  | (if odd (sum (take 8 xs)) then 1 else 0) /= xs!!8 = error "Erreur de parité"
  | otherwise = chr (bin2int (take 8 xs)) : decode (drop 9 xs)

transmit :: String -> String
transmit = decode . channel . encode'

channel :: [Bit] -> [Bit]
channel = id

unfold :: (t -> Bool) -> (t -> a) -> (t -> t) -> t -> [a]
unfold p h t x
 | p x = []
 | otherwise = h x : unfold p h t (t x)

int2bitunfold xs = reverse (unfold (== 0) (`mod` 2) (`div` 2) xs)

chopunfold n xs = unfold (== 0) (take n) (drop n) xs