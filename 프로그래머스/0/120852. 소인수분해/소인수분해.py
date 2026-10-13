def solution(n):
    answer = []
    div = 2
    while n != 1:
        if n % div == 0:
            answer.append(div)
            while n % div == 0:
                n //= div
        div += 1
    return answer