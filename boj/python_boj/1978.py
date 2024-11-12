N = int(input())

arr = list(map(int, input().split()))
cnt = 0
for j in arr :
    for i in range(2, j + 1) :
        if (j % i == 0) :
            if (j == i) :
                cnt += 1
            break
print(cnt)