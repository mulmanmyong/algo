arr = []
temp = []
for i in range(28) :
    arr.append(int(input()))
for i in range(1, 31) :
    temp.append(i)
for i in range(28) :
    temp.remove(arr[i])
print(min(temp))
print(max(temp))