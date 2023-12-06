-- Hugo Mathieu Steinbach, voisin de droite Aucun et voisin de gauche Paul Gsell.

{-
Define a recursive function mergeSort that implements merge sort, which can be specified by the following two rules: 

lists of length ≤ 1 are already sorted;
other lists can be sorted by sorting the two halves and merging the resulting lists.
Recall: The recursive function that merges two sorted lists of values to give a single sorted list:

merge :: Ord a ⇒ [a] → [a] → [a]

merge [] xs = xs

merge xs [] = xs

merge (x:xs) (y:ys) | x<y = x:merge xs (y:ys) 

                                 | otherwise = y:merge (x:xs) ys
-}

merge :: Ord a => [a] -> [a] -> [a]
merge [] xs = xs
merge xs [] = xs
merge (x:xs) (y:ys)
    | x < y = x : merge xs (y:ys)
    | otherwise = y : merge (x:xs) ys

mergeSort :: Ord a => [a] -> [a]
mergeSort [] = []
mergeSort [x] = [x]
mergeSort xs = merge (take (div (length xs) 2) xs)  (take (div (length xs) 2) (reverse xs))