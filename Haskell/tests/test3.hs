-- Hugo Mathieu Steinbach, voisin de droite Guillaume Zimol et aucun voisin de gauche.

{-
A Collatz sequence is defined as follows: 

Start with any natural number. 
If the number is 1, stop. 
If the number is even, divide it by 2. 
If the number is odd, multiply it by 3 and add 1. 
Repeat the algorithm with the resulting number. 
This results in a chain of numbers which normally finishes at the number 1. For example, for 10 we get the sequence 10,5,16,8,4,2,1 (of length 7) and for 15 we get 15,46,23,70,35,106,53,160,80,40,20,10,5,16,8,4,2,1 (of length 18). 

Define the function 

numLongChains :: Int -> Int 

which, for all starting numbers between 1 and n (taken as argument), computes how many Collatz chains have a length strictly greater than 15. You shouldn’t use list comprehensions.
For example:

> numLongChains 10

2

The resulting 2 corresponds to the chains for 7 and 9.


Hint: Define a function that computes the sequence for a given input integer and apply it to the integers between 1 and n.
-}

collatz :: Int -> Int
collatz 1 = 1
collatz x
    | even x    = 1 + collatz (x `div` 2)
    | otherwise = 1 + collatz (1 + 3 * x)

numLongChains :: Int -> Int
numLongChains x = length (filter (\y -> y > 15) (map collatz [1..x]))