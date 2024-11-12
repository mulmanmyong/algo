n = int(input())
arr = [[0]*100 for _ in range(100)] #100x100 array

for _ in range(n) :
    a, b = list(map(int, input().split()))

    for i in range(a, a+10) : #색종이 하나가 10x10 이므로 시작 지점부터 10까지 1로 바꾸는 것으로 함.
        for j in range(b, b+10) :
            arr[i][j] = 1
        
result = 0
for i in arr :
    result += i.count(1) #그리고 각 1로 된 부분이 문제에서 제시된 검정색 부분이므로 중복된 부분도 다 1로 되어 있으므로 arr를 분석하여서 1로 된 부분을 카운트하면
                            #그 카운트가 된 부분들이 넓이 이다.

print(result)