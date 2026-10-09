def solution(box, n):
    answer = 1

    for length in box:
        answer *= length // n

    return answer