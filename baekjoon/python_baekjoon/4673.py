numbers = set(range(1,10000))
remove_set = set()

for i in numbers :
    for j in str(i) :
        i += int(j)
    remove_set.add(i)

self_number = numbers - remove_set
for self_num in sorted(self_number) :
    print(self_num)