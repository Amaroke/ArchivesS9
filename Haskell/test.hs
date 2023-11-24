-- Hugo Mathieu Steinbach, voisin de droite Arthur Moitrier et voisin de gauche Anthony Briot.

{-
A positive integer is perfect if it equals the sum of all of its factors, excluding the number itself.  Define a function perfects that returns the list of all perfect numbers up to a given limit.  For example:

> perfects 500

[6,28,496]

Recall: The function that maps a positive integer to its list of factors:
-}

factors :: Int -> [Int]
factors n = [x | x <- [1..n], n `mod` x == 0]

perfects :: Int -> [Int]
perfects n = [y | y <- [1..n], sum (factors y) - y == y]

