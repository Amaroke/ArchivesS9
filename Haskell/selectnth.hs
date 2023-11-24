selectnth (x:_) 0 = x
selectnth (x:xs) n = selectnth xs (n-1)