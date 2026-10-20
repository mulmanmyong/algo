from collections import deque

def solution(num, total):
    if num == 1:
        return [total]
    
    answer = deque(range(num))
    current_sum = sum(answer)

    # 합계가 total과 같아질 때까지 윈도우 이동
    while current_sum != total:
        if current_sum > total:
            # 합계가 크면 오른쪽 숫자를 제거하고 왼쪽에 작은 숫자 추가
            current_sum -= answer.pop()
            answer.appendleft(answer[0] - 1)
            current_sum += answer[0]
        else:
            # 합계가 작으면 왼쪽 숫자를 제거하고 오른쪽에 큰 숫자 추가
            current_sum -= answer.popleft()
            answer.append(answer[-1] + 1)
            current_sum += answer[-1]

    answer = list(answer)
    return answer