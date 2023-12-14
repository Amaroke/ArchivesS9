data Tree a = Leaf a | Node (Tree a) a (Tree a)

treeSize (Leaf _) = 1
treeSize (Node left _ right) = 1 + treeSize left + treeSize right 

treeOccurs (Leaf a) x = (a == x)
treeOccurs (Node left a right) x = (a == x) || treeOccurs left x || treeOccurs right x

treeFlatten (Leaf a) = [a]
treeFlatten (Node left a right) = treeFlatten left ++ [a] ++ treeFlatten right

treeOccursSearch (Leaf a) x = (a == x)
treeOccursSearch (Node left a right) x
    | a == x = True
    | a < x = treeOccursSearch right x
    | otherwise = treeOccursSearch left x