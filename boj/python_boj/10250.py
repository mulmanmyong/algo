t = int(input())

for i in range(t) :
    h, w, n = map(int, input().split())
    a = (n // h) + 1
    b = n % h
    if n % h == 0 :
        a = n // h
        b = h
    print((b * 100) + a)