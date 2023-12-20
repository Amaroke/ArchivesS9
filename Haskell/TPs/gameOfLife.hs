import System.IO

cls :: IO ()
cls = putStr "\ESC[2J"

type Pos = (Int, Int)

goto :: Pos -> IO ()
goto (x, y) = putStr ("\ESC[" ++ show (y+1) ++ ";" ++ show (x+1) ++ "H")

writeat :: Pos -> String -> IO ()
writeat p xs = do
                goto p
                putStr xs
                

type Board = ([Pos], (Int, Int))

showcells :: Board -> IO ()
showcells board = do
                    cls
                    sequence_ [writeat (x,y) "O" | (x,y) <- fst board]
                    goto (0, snd (snd board))

-- Test : showcells ([(1,2),(5,5)],(5,5)) 

isAlive :: Board -> Pos -> Bool
isAlive board (x, y) = or [(x,y) == (x',y') | (x', y') <- fst board]


isEmpty :: Board -> Pos -> Bool
isEmpty board (x, y) = and [(x,y) /= (x',y') | (x', y') <- fst board]

-- isAlive ([(1, 2)],(5,5)) (1,2) 

neighbours :: Board -> Pos -> [Pos]
neighbours (_, (w, l)) (x,y) =
        [((x+1) `mod` w, y `mod` l),
        ((x+1) `mod` w, (y-1) `mod` l),
        (x `mod` w, (y-1) `mod` l),
        ((x-1) `mod` w, (y-1) `mod` l),
        ((x-1) `mod` w, y `mod` l),
        (x `mod` w, (y+1) `mod` l),
        ((x+1) `mod` w, (y+1) `mod` l),
        ((x-1) `mod` w, (y+1) `mod` l)] 

-- showcells (neighbours ([],(10,10)) (0, 0), (10,10))

livingneighbours :: Board -> Pos -> Int
livingneighbours board = length . filter (isAlive board) . (neighbours board)

survivors :: Board -> Board
survivors board = ([(x,y) | (x,y) <- fst board, (livingneighbours board (x,y)) `elem` [2, 3]], snd board)

-- survivors ([(1, 2), (2,2), (2,3)],(5,5))

births :: Board -> Board
births board = ([(x, y) | x <- [0..(fst $ snd board)], y <- [0..(snd $ snd board)], isEmpty board (x,y), (livingneighbours board (x,y)) >= 3], snd board)

-- births ([(1, 2), (2,2), (2,3)],(80,80))

nextgen :: Board -> Board
nextgen board = (fst (survivors board) ++ (fst (births board)), snd board)

wait :: Int -> IO ()
wait n = sequence_ [return () | _ <- [1..n*1000] ]

life :: Board -> IO ()
life b = do
  cls
  showcells b
  wait 500
  life (nextgen b)

glider :: Board
glider = ([(4,2), (2,3), (4,3), (3,4), (4,4)], (80,80))
clock :: Board
clock = ([(2,1), (3,2), (4,2), (1,3), (2,3), (3,4)], (80,80))
pentadecathlon :: Board
pentadecathlon = ([(1,2),(2,1),(2,2),(2,3),(7,1),(7,2),(7,3),(8,2)], (80,80))
form :: Board
form = ([(1,1),(1,2),(2,2),(2,3),(1,3),(5,1),(5,2),(4,2),(4,1),(5,3)], (80,80))