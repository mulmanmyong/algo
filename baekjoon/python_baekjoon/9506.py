while (1) :
    n = int(input())
    if (n == -1) :
        break
    arr= []
    for i in range(1, n) :
        if (n % i == 0) :
            arr.append(i)
    if (n == sum(arr)) :
        print(n, "= ", end= "")
        print(*arr, sep= " + ") #sep는 문자열 사이에 해당 구문을 사이에 넣어서 출력하는 것이다.
    else :
        print(n, "is NOT perfect.")