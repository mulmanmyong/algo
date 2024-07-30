matrix = []
for _ in range(9) :
    matrix.append(list(map(int, input().split())))
    
mat = 0
for i in range(9) :
    for j in range(9) :
        if (matrix[i][j] >= mat) :
            mat = matrix[i][j]
            row = i + 1
            col = j + 1
print(mat)
print("{} {}".format(row, col))