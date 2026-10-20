def solution(score):
    answer = []
    average_score = []
    for english_score, math_score in score:
        average_score.append((english_score + math_score) / 2)
    
    sorted_score = sorted(average_score, reverse=True)
    for average in average_score:
        answer.append(sorted_score.index(average) + 1)
        
    return answer