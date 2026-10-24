'''
좌측상단에서 우측하단으로 가기
1은 지나갈 수 있는 길, 0은 못지나가는 길 bfs로 가보자고
'''
from collections import deque

deltaRow = [1, -1, 0, 0]
deltaCol = [0, 0, 1, -1]

def solution(maps):
    answer = -1
    rowCount = len(maps)
    colCount = len(maps[0])
    visited = [[False] * colCount for _ in range(rowCount)] # 2차원 초기화

    # row col 이동횟수
    queue = deque([(0, 0, 1)])
    visited[0][0] = True

    while queue:
        currentRow, currentCol, currentMove = queue.popleft()

        # 도달했으면 종료
        if currentRow == rowCount - 1 and currentCol == colCount - 1:
            answer = currentMove
            break

        for direction in range(4):
            nextRow = currentRow + deltaRow[direction]
            nextCol = currentCol + deltaCol[direction]

            # 범위 벗어났거나 이미 갔으면
            if nextRow < 0 or nextRow >= rowCount or nextCol < 0 or nextCol >= colCount:
                continue
            if visited[nextRow][nextCol]:
                continue
            if maps[nextRow][nextCol] == 0:
                continue

            # 그렇지 않으면 탐색
            visited[nextRow][nextCol] = True
            queue.append([nextRow, nextCol, currentMove + 1])

    return answer