matrix = [[0] * 15 for _ in range(5)]
for i in range(5) :
    tmp = list(input())
    for j in range(len(tmp)) :
        matrix[i][j] = tmp[j]

for i in range(15) :
    for j in range(5) :
        if (matrix[j][i] == 0) :
            continue
        else :
            print(matrix[j][i], end= "")