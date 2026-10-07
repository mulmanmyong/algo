def solution(rsp):
    rsp_answer = {'0':'5', '2':'0', '5':'2'}
    answer = ''
    for s in rsp:
        answer += rsp_answer.get(s)
    return answer