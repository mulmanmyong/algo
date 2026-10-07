def solution(age):
    answer = ''
    alpha = "abcdefghij"
    
    for num in str(age):
        answer += alpha[int(num)]
    
    return answer