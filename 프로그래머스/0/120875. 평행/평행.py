def solution(dots):
    answer = 0

    # 네 점을 두 개씩 연결하는 3가지 경우
    cases = [
        (0, 1, 2, 3),
        (0, 2, 1, 3),
        (0, 3, 1, 2)
    ]

    for a, b, c, d in cases:
        # 첫 번째 직선의 x, y 변화량
        dx1 = dots[b][0] - dots[a][0]
        dy1 = dots[b][1] - dots[a][1]

        # 두 번째 직선의 x, y 변화량
        dx2 = dots[d][0] - dots[c][0]
        dy2 = dots[d][1] - dots[c][1]

        # 두 직선의 기울기가 같으면 평행
        if dy1 * dx2 == dy2 * dx1:
            answer = 1
            break

    return answer