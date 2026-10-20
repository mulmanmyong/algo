def solution(common):
    answer = 0
    # 등차
    if common[1] - common[0] == common[2] - common[1]:
        answer = common[-1] + common[1] - common[0]
    # 등비
    elif common[1] // common[0] == common[2] // common[1]:
        answer = common[-1] * (common[1] // common[0])
    return answer