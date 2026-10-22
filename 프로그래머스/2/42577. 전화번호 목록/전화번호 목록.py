def solution(phone_book):
    answer = True

    # 값 in list -> O(N)
    # 값 in set -> 평균 O(1)
    # 빠른 탐색을 위해 list를 set으로 변환
    phone_set = set(phone_book)
    for phone in phone_book:
        for i in range(1, len(phone)):
            if phone[:i] in phone_set:
                return False
    return answer