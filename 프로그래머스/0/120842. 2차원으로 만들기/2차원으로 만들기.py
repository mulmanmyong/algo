def solution(num_list, n):
    # n개씩 슬라이싱 하면 될 듯?
    answer = []
    for i in range(0, len(num_list), n):
        answer.append(num_list[i:i+n])

    return answer