import sys

while(1) :
    a, b = map(int, sys.stdin.readline().split())
    if a or b != 0 :
        print(a + b)
    else :
        break