import math

def solution(numer1, denom1, numer2, denom2):
    numer = (numer1 * denom2) + (numer2 * denom1)
    denom = denom1 * denom2
    
    # 최대공약수 어캐 찾지 -> math에 gcd 있음
    gcd = math.gcd(numer, denom)  
    answer = [numer / gcd, denom / gcd]
    return answer