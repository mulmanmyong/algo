P = int(input())

for i in range(P) :
    s_list = list(map(str, input()))
    #print(s_list, len(s_list))
    for j in range(2, len(s_list)) :
        print(s_list[j] * int(s_list[0]), end = '')
        if j == len(s_list)-1 :
            print(end = '\n')