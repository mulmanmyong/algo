N = int(input())

if (N == 1) :
    pass
else :
    k = 2
    while (1) :
        if (N % k == 0) :
            N //= k
            print(k)
        else :
            k += 1
        
        if (N == 1) :
            break

#나중에 속도 개선 해보기