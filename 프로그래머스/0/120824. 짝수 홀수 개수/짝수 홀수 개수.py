def solution(num_list):
    count = 0
    for num in num_list:
        if num % 2 == 0:
            count += 1
    answer = [count, len(num_list)-count]
    return answer