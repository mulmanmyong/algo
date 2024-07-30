N = int(input())
temp = N

cnt = 1
while (1) :
    a = temp // 10
    b = temp % 10
    temp = (b * 10) + ((a + b) % 10)

    if (temp == N) :
        break
    cnt += 1
print(cnt)