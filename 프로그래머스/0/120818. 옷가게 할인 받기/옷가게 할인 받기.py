def solution(price):
    answer = price
    if price >= 500000:
        answer = price * 0.80
    elif price >= 300000:
        answer = price * 0.90
    elif price >= 100000:
        answer = price * 0.95
    return int(answer)