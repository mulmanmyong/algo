import math

def solution(a, b):
    answer = 1

    # 최대공약수를 이용해서 기약분수로 변환
    gcd = math.gcd(a, b)
    b //= gcd

    # 분모에서 2와 5를 모두 제거
    while b % 2 == 0:
        b //= 2
    while b % 5 == 0:
        b //= 5

    # 다른 소인수가 남아있으면 무한소수
    if b != 1:
        answer = 2

    return answer