def solution(my_string):
    answer = 0
    num = 0
    for s in my_string:
        if s.isdigit():
            num *= 10
            num += int(s)
        else:
            answer += num
            num = 0
    answer += num
    return answer