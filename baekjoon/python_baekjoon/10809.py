s_list = list(map(str, input()))
alphabet = 'abcdefghijklmnopqrstuvwxyz'

for i in alphabet :
    if i in s_list :
        print(s_list.index(i), end = ' ')
    else :
        print('-1', end = ' ')