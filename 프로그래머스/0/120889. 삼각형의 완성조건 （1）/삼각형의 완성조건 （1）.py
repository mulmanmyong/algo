def solution(sides):
    sides = sorted(sides)
    answer = 1 if sides[-1] < sides[0] + sides[1] else 2
    return answer