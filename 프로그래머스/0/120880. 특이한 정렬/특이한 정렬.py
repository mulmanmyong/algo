def solution(numlist, n):
    answer = sorted(numlist, reverse=True)
    answer = sorted(answer, key=lambda x: abs(x - n))
    return answer