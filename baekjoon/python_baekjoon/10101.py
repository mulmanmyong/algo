angle = []
for _ in range(3) :
    angle.append(int(input()))

if (sum(angle) != 180) :
    print("Error")
else :
    if (angle[0] == angle[1] == angle[2]) :
        print("Equilateral")
    elif (angle[0] == angle[1] or angle[1] == angle[2] or angle[2] == angle[0]) :
        print("Isosceles")
    else :
        print("Scalene")