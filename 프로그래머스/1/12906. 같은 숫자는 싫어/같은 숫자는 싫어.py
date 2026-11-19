def solution(arr):
    answer = []
    # 반복문을 돌며 answer가 비어있거나, 제일 뒤에 숫자랑 다르면 삽입
    for i in arr:
        if not answer or answer[-1] != i:
            answer.append(i)
    return answer