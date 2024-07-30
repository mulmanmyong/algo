n = int(input())

x = []
y = []
for _ in range(n) :
    a, b = map(int, input().split())
    x.append(a)
    y.append(b)

if (n == 1) :
    print(0) 
else :   
    h = max(x) - min(x)
    w = max(y) - min(y)

    print(w*h)