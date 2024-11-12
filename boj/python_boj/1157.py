s = input().lower()
s_list = list(set(s))

temp = []
for i in s_list :
    temp.append(s.count(i))
if temp.count(max(temp)) >= 2 :
    print('?')
else :
    print(s_list[temp.index(max(temp))].upper())