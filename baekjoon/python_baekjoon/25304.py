P = int(input())
t = int(input())

result = 0
for i in range(t) :
    a, b = map(int, input().split())
    result += a * b
if (P == result) :
    print("Yes")
else :
    print("No")