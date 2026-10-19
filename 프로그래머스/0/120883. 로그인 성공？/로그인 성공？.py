def solution(id_pw, db):
    answer = 'fail'
    for id, pw in db:
        if id == id_pw[0]:
            if pw == id_pw[1]:
                answer = 'login'
            else:
                answer = 'wrong pw'
            break
    return answer