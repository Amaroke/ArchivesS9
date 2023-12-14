rmdups :: Eq a => [a] -> [a]
rmdups [] = []
rmdups (x:xs) = x : filter (/=x) (rmdups xs)

--rmdups' :: Eq a => [a] -> [a]
--rmdups' = foldl (\x y -> if y == x then x else x ++ [y]) []

--group :: Eq a => [a] -> [[a]]

--rmdups'' :: Ord a => [a] -> [a]

--bools :: Int -> [[Bool]]


data Prop = Var Char | Not Prop | And Prop Prop | Imply Prop Prop

data Subst = Subst [(Char, Bool)]

evalProp :: Subst -> Prop -> Bool
evalProp (Subst []) _ = False
evalProp (Subst ((x, y):xs)) (Var z) = if x == z then y else evalProp (Subst xs) (Var z)
evalProp x (Not p) = not (evalProp x p)
evalProp x (And p1 p2) = evalProp x p1 && evalProp x p2
evalProp x (Imply p1 p2) = not (evalProp x p1) || evalProp x p2