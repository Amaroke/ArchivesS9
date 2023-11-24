reverse' [] = []
reverse' l = reverse' (tail l) ++ head l