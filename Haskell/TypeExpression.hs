data Expr = Val Int
    | Add Expr Expr
    | Mul Expr Expr

size :: Expr -> Int
size (Val n) = 1
size (Add x y) = size x + size y
size (Mul x y) = size x + size y

eval :: Expr -> Int
eval (Val n) = n 
eval (Add x y) = eval x + eval y
eval (Mul x y) = eval x * eval y

folde :: (Int -> a) -> (a -> a -> a) -> (a -> a -> a) -> Expr -> a
folde f _ _ (Val n) = f n
folde f g h (Add x y) = g (folde f g h x) (folde f g h y)
folde f g h (Mul x y) = h (folde f g h x) (folde f g h y)

eval' :: Expr -> Int
eval' = folde id (+) (*)

size' :: Expr -> Int
size' = folde (\_ ->1) (+) (+)