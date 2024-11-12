N = int(input())

for i in range(N) :
    N_list = list(input())
    score = 0
    temp = 0
    for j in N_list :
        if j == "O" :
            score += 1
            temp += score
        else :
            score = 0
    print(temp)