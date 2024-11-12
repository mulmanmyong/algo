s = input()
spe = ['c=', 'c-', 'dz=', 'd-', 'lj', 'nj', 's=', 'z=']

for i in range(len(s)) :
    for j in spe :
        if j in s :
            s = s.replace(j, '0')
print(len(s))