def solution(my_string):
    aeiou = "aeiou"
    answer = ''
    for s in my_string:
        if s not in aeiou:
            answer += s
    return answer