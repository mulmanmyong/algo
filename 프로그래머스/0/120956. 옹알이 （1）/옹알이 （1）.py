def solution(babbling):
    answer = 0
    checklist = ["aya", "ye", "woo", "ma"]

    for bb in babbling:
        for word in checklist:
            bb = bb.replace(word, " ", 1) # 최대 한번만 공백으로 변경하라

        if bb.strip() == "": # strip()으로 양옆 공백을 제거 -> 암것도 없다? 다 대체한 것
            answer += 1

    return answer