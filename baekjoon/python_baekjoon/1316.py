t = int(input())

for i in range(t) :
    N = input()
    for j in range(len(N) - 1) :
        if N[j] == N[j + 1] :
            pass
        elif N[j] in N[j + 1 :] :
            t -= 1
            break
print(t)