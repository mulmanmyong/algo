X = int(input())

#group 수에 일치하게 group내의 수 개수 존재 ex) group1 1개, group2 2개.....
#홀수 group에는 분모가 1씩 늘어남, 짝수 group에는 분자가 1씩 늘어남
group = 0
end_index = 0
while X > end_index :
    group += 1
    end_index += group
#group 수에 따라서 분자와 분모의 합 결정. 예를 들면 group5이면 분모와 분자의 합은 6
#print(group, end_index)
find_index = X - (end_index - group) #group에서의 index
#print(find_index)
#print(group%2)
if group % 2 == 0 : #짝수 group
    print('%d/%d'%(find_index, group + 1 - find_index))
else : #홀수 group
    print('%d/%d'%(group + 1 - find_index, find_index))