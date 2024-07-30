B, N = input().split()
arr = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"

B = B[::-1]
N = int(N)

result = 0
for i in range(len(B)) :
    result += (N**i)*(arr.index(B[i]))
print(result)