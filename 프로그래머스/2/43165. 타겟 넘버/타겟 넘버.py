'''
dfs를 이용해서 -일 경우 +일 경우 각각 들어가며, 제일 끝까지 도달했을 때 target에 일치하면 answer를 증가하는 형식으로
'''

answer = 0

def dfs(depth, score, numbers, target):
    global answer

    if depth == len(numbers):
        if score == target:
            answer += 1
        return

    dfs(depth + 1, score + numbers[depth], numbers, target)
    dfs(depth + 1, score - numbers[depth], numbers, target)

def solution(numbers, target):
    global answer
    answer = 0
    # 현재 depth와 더해진 값을 가지고 들어가면 될듯
    dfs(0, 0, numbers, target)
    return answer