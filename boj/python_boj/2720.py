t = int(input())

arr = [25, 10, 5, 1]
for _ in range(t) :
    m = int(input())
    change = [0, 0, 0, 0]

    for i in range(len(arr)) :
        change[i] = m//arr[i]
        m %= arr[i]
    for i in change :
        print(i, end= " ")