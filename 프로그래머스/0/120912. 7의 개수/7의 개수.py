def solution(array):
    answer = 0
    for num in array:
        num = str(num)
        answer += num.count("7")
    return answer