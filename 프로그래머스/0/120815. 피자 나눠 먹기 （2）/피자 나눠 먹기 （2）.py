import math

def solution(n):
    lcm = math.lcm(6, n)
    answer = lcm // 6
    return answer