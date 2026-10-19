def solution(numbers):
    numbers = sorted(numbers)
    answer = max(numbers[0] * numbers[1], numbers[-1] * numbers[-2])
    return answer