def solution(emergency):
    answer = []
    sorted_emergency = sorted(emergency, reverse=True)

    for e in emergency:
        answer.append(sorted_emergency.index(e) + 1)

    return answer