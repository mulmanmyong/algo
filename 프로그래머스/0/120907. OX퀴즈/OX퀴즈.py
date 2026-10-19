def solution(quiz):
    answer = []
    for q in quiz:
        q = q.split()
        flag = False
        if q[1] == '+':
            if int(q[0]) + int(q[2]) == int(q[4]):
                flag = True
        elif q[1] == '-':
            if int(q[0]) - int(q[2]) == int(q[4]):
                flag = True
                
        if flag == True:
            answer.append("O")
        elif flag == False:
            answer.append("X")
            
    return answer