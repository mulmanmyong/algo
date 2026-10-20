def solution(chicken):
    answer = 0
    remain_coupon = chicken

    # 쿠폰 10장당 서비스 치킨 1마리
    # 서비스 치킨에서도 쿠폰 1장을 받을 수 있음
    
    # 남은 쿠폰이 10장 미만이 될 때까지
    while remain_coupon >= 10:
        service = remain_coupon // 10
        answer += service

        # 사용하고 남은 쿠폰 + 서비스 치킨에서 받은 쿠폰
        remain_coupon = remain_coupon % 10 + service

    return answer