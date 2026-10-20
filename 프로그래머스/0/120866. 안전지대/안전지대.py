def solution(board):
    n = len(board)
    answer = 0

    # 상하좌우 대각선 4방향을 포함한 총 8방향
    deltaRow = [-1, 1, 0, 0, -1, -1, 1, 1]
    deltaCol = [0, 0, -1, 1, -1, 1, -1, 1]

    # 지뢰가 있는 칸을 기준으로 주변 8방향 확인
    for row in range(n):
        for col in range(n):
            if board[row][col] == 1:
                for d in range(8):
                    nextRow = row + deltaRow[d]
                    nextCol = col + deltaCol[d]

                    # 범위를 벗어나지 않고, 안전한 칸이면 위험 지역으로 변경
                    if 0 <= nextRow < n and 0 <= nextCol < n:
                        if board[nextRow][nextCol] == 0:
                            board[nextRow][nextCol] = 2

    # 위험 지역이 아닌 안전한 칸의 개수
    for row in board:
        answer += row.count(0)

    return answer