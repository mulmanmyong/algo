def solution(polynomial):
    x = 0
    num = 0
    answer = ''

    for s in polynomial.split(' + '):
        if 'x' in s:
            if s == 'x':
                x += 1
            else:
                x += int(s[:-1])
        else:
            num += int(s)

    if x > 0:
        answer += 'x' if x == 1 else str(x) + 'x'

    if num > 0:
        if answer:
            answer += ' + '
        answer += str(num)

    return answer