A, B, V = map(int, input().split())

if (V - B) % (A - B) != 0 :
    #print('up')
    print((V - B) // (A - B) + 1)
else :
    #print('down')
    print((V - B) // (A - B))