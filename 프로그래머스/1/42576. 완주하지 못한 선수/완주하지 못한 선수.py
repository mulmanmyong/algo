def solution(participant, completion):
    answer = ''
    hashmap = {}

    # 참가자별 등장 횟수 저장
    for name in participant:
        hashmap[name] = hashmap.get(name, 0) + 1 # 존재하면 hashmap[name]의 값, 없으면 0 => get의 사용법

    # 완주한 선수의 등장 횟수 차감
    for name in completion:
        hashmap[name] -= 1

    # 등장 횟수가 남아 있는 선수 찾기 -> 한 사람만 완주 못함
    for name, count in hashmap.items():
        if count > 0:
            answer = name
            break

    return answer