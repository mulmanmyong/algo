def solution(clothes):
    answer = 0

    # 의상 종류별 개수를 저장하는 딕셔너리
    category_counts = {}
    for clothing_name, category in clothes:
        category_counts[category] = category_counts.get(category, 0) + 1

    # 종류별로 의상을 선택하거나 입지 않는 경우를 계산
    combinations = 1
    for count in category_counts.values():
        combinations *= (count + 1) # count + 1은 입지 않는 경우를 추가한 것

    # 모든 의상을 입지 않는 경우 제외
    answer = combinations - 1

    return answer