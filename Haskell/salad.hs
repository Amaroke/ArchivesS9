import Data.Char

-- Auxiliary functions
let2int :: Char -> Int
let2int a = ord a - ord 'a'

int2let :: Int -> Char
int2let x = chr (ord 'a' + x)

rotate :: Int -> [a] -> [a]
rotate x xs = drop x xs ++ take x xs

-- Encode strings
encode :: Int -> String -> String
encode x str = [if(ord a == ord ' ') then ' ' else int2let(let2int a + x) | a <- str]

-- Code cracker
table :: [Float]
table = [8.2, 1.5, 2.8, 4.3, 12.7, 2.2, 2.0, 6.1, 7.0, 0.2, 0.8, 4.0, 2.4, 6.7, 7.5, 1.9, 0.1, 6.0, 6.3, 9.1, 2.8, 1.0, 2.4, 0.2, 2.0, 0.1]

percent :: Int -> Int -> Float
percent x y = (fromIntegral x / fromIntegral y) * 100

lowers :: String -> Int
lowers xs = sum [1 | x <- xs, x >= 'a' && x <= 'z']

positions :: Eq a => a -> [a] -> [Int]
positions x xs = [i | (x', i) <- zip xs [0..], x == x']

count :: Char -> String -> Int
count a str = length [x | x <- str, a == x]

freqs :: String -> [Float]
freqs str = [percent (count x str) n | x <- ['a'..'z']]
    where n = lowers str

chisqr :: [Float] -> [Float] -> Float
chisqr os es = sum [(x-y)^2 / y | (x, y) <- zip os es]

crack :: String -> String
crack xs = encode (-factor) xs
  where
    factor = head (positions (minimum chitab) chitab)
    chitab = [chisqr (rotate n freqstab) table | n <- [0..25]]
    freqstab = freqs xs