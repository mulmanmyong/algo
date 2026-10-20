def solution(n):
    answer = 0
    for num in range(n):
        answer += 1
        while str(answer).count("3") > 0 or answer % 3 == 0:
            answer += 1
    return answer