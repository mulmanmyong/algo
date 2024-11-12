arr = []
cnt = 1

for i in range(9) :
    arr.append(int(input()))
print(max(arr))
for i in range(9) :
    if (arr[i] == max(arr)) :
        break
    else :
        cnt += 1
print(cnt)