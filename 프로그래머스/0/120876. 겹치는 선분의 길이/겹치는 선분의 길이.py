def solution(lines):
    answer = 0
    lines.sort()

    # 두 선분씩 겹치는 길이 계산
    answer += max(0, min(lines[0][1], lines[1][1]) - lines[1][0])
    answer += max(0, min(lines[1][1], lines[2][1]) - lines[2][0])
    answer += max(0, min(lines[0][1], lines[2][1]) - lines[2][0])

    # 세 선분이 동시에 겹치는 길이 중복 제거
    answer -= 2 * max(0, min(lines[0][1], lines[1][1], lines[2][1]) - lines[2][0])

    return answer