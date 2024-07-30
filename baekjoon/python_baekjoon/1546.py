N = int(input())
N_list = list(map(int, input().split()))
new = []

for i in range(N) :
    new.append(N_list[i]/max(N_list)*100)
print(sum(new)/N)