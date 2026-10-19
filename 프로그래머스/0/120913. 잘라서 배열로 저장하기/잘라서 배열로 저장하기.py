def solution(my_str, n):
    answer = []
    for num in range(0, len(my_str), n):
        answer.append(my_str[num:num + n])
    return answer