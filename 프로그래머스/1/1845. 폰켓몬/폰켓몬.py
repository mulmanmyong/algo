def solution(nums):
    answer = 0
    # 딕셔너리를 이용해서 중복제거(해시)
    ponkemon = {}
    
    for n in nums:
        ponkemon[n] = ponkemon.get(n, 0) + 1
        
    # 최대 고를 수 있는 폰켓몬 종류의 수를 고르는 것
    answer = min(len(nums) // 2, len(ponkemon))
    return answer