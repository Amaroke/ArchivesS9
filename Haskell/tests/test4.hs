-- Hugo Mathieu Steinbach, voisin de droite Paul Gsell et voisin de gauche Anthony Briot.

{-
— Permutations —

Using foldr, define the function

mypermutations :: [a] -> [[a]] 

that generates all the possible permutations of a list. For example, 

> mypermutations [1..3]

[[1,2,3],[2,1,3],[2,3,1],[1,3,2],[3,1,2],[3,2,1]] 

You should obtain the same lists as the function permutations from Data.List (accessible with import Data.List) but not necessarily in the same order.



Hint: you might want to define a function insert :: a -> [a] -> [[a]] that generates the lists obtained by inserting an element to all positions in a given list.
-}

insert :: a -> [a] -> [[a]]
insert y [] = [[y]]
insert y (x:xs) = [y:x:xs] ++ [x:y:xs] ++ insert y xs

mypermutations :: [a] -> [[a]]
mypermutations [] = [[]]
mypermutations (x:xs) = 
    filter (\y -> length y == length (x:xs)) 
    (foldr (\y z -> insert x y ++ z) [] (mypermutations xs))

-- On utilise filter pour retirer les listes qui sont pas de la bonne taille.
-- Ne fonctionne pas ne renvoi pas toutes les permutations.