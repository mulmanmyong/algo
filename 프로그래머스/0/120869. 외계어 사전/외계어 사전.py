def solution(spell, dic):
    answer = 2
    for s in dic:
        flag = 0
        for i in range(0, len(spell)):
            if s.count(spell[i]) != 1:
                break
            flag += 1
        if flag == len(spell):
            answer = 1
            break
    return answer