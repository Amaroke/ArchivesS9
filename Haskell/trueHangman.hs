import System.IO

hangman :: IO ()
hangman = do putStrLn "Think of a word: "
             word <- sgetLine
             putStrLn "Try to guess it:"
             play word []

sgetLine :: IO String
sgetLine = do x <- getCh
              if x == '\n' then
                  do putChar x
                     return []
              else
                  do putChar '-'
                     xs <- sgetLine
                     return (x:xs)

getCh :: IO Char
getCh = do hSetEcho stdin False
           x <- getChar
           hSetEcho stdin True
           return x

play :: String -> String -> IO ()
play word letters = do  putStr "? "
                        guess <- getChar
                        mem <- return (guess:letters)
                        if (match word mem) == word then
                            putStrLn "You got it!"
                        else
                            do  putStrLn letters
                                play word (match word mem)
                        
  
match :: String -> String -> String
match word guess =
 [if elem x guess then x else '-' | x <- word]                        