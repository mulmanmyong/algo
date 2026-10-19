def solution(n):
    answer = 2
    num = 1
    while num * num <= n:
        if num * num == n:
            answer = 1
            break
        num += 1
    return answer