def solution(array, n):
    answer = 0
    difference = float('inf')
    for num in array:
        if abs(num - n) < difference:
            answer = num
            difference = abs(num - n)
        elif abs(num - n) == difference:
            answer = min(answer, num)
    return answer