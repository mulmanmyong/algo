def solution(sides):
    sides = sorted(sides)
    answer = sides[0] - 1 # sides에 없는 나머지 한 변이 가장 긴 변일 경우
    # sides에서 가장 큰 값이 가장 긴 변일 경우 
    answer += sides[0]
    return answer