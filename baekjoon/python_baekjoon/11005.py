N, B = map(int, input().split())
arr = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"

result = ""
while (N) :
    result += str(arr[N%B])
    N //= B
print(result[::-1])