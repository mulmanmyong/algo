def solution(s):
    answer = 0
    before = 0
    for n in s.split():
        if n == "Z":
            answer -= before
            before = 0
        else:
            answer += int(n)
            before = int(n)
    return answer