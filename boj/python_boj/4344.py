import math as m

C = int(input())

def roundTraditional(val, digits):
    return round(val+10**(-len(str(val))-1), digits)
"""
부동소수에 의하여 다음과 같은 함수를 이용하여서 사사오입 또는 오사오입을 고려해주어야한다.
파이썬에서는 그런 사사오입과 오사오입을 고려하지 않기 때문에 기존의 코드를 이용하여도 에러가 출력이 되었던 것이다.
그래서 이러한 반올림을 이용하는 문제가 나왔을 경우에는 부동소수점에서 이러한 반올림이 제대로 일어날 수 있도록 고려를 해주어야 한다."""

for _ in range(C) :
    N = map(int, input().split())
    N = list(N)

    avg = (sum(N) - N[0]) / N[0]
    cnt = 0
    for i in range(1, len(N)) :
        if(avg < N[i]) :
            cnt += 1
    tmp = cnt/N[0]*100
    rr = roundTraditional(tmp, 3)
    print("{0:0.3f}%".format(rr))