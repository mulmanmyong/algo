def solution(array):
    max_count = 0
    answer = -1
    
    for number in array:
        count = 0
        
        for compare in array:
            if number == compare:
                count += 1
        
        # 최대값 갱신
        if count > max_count:
            max_count = count
            answer = number
        
        # 등장 횟수가 같고 값은 다르면 최빈값이 여러 개
        elif count == max_count and answer != number:
            answer = -1
    
    return answer