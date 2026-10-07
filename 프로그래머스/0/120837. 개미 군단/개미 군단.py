def solution(hp):
    answer = 0
    hp_list = [5, 3, 1]
    for i in range(3):
        answer += hp // hp_list[i]
        hp %= hp_list[i]
    return answer