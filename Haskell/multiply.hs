mult n m = if m == 0 then 0 else n + (mult n (if m < 0 then m+1 else m-1))