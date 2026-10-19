def solution(num, k):
    num = list(str(num))
    answer = -1
    if str(k) in num:
        answer = num.index(str(k)) + 1
    return answer