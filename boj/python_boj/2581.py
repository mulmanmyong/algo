M = int(input())
N = int(input())

arr = []
for x in range(M, N+1) :
    for i in range(2, x + 1) :
        if (x % i == 0) :
            if (x == i) :
                arr.append(x)
            break

if (arr == []) :
    print(-1)
else :
    print(sum(arr))
    print(arr[0])