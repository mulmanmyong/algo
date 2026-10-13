def solution(my_string):
    answer = sorted(int(s) for s in my_string if s.isdigit())
    return answer