// 25206. 너의 평점은 -> 실버 5

#include <iostream>

using namespace std;

int main() {

    string sub, grade;
    float flt = 0.0f, sum = 0.0f, score = 0.0f;
    int pass = 0;

    for (int i = 0; i < 20; i++) {

        cin >> sub >> flt >> grade;

        score += flt;

        if (grade == "A+") {
            sum += (4.5 * flt);
        }
        else if (grade == "A0") {
            sum += (4.0 * flt);
        }
        else if (grade == "B+") {
            sum += (3.5 * flt);
        }
        else if (grade == "B0") {
            sum += (3.0 * flt);
        }
        else if (grade == "C+") {
            sum += (2.5 * flt);
        }
        else if (grade == "C0") {
            sum += (2.0 * flt);
        }
        else if (grade == "D+") {
            sum += (1.5 * flt);
        }
        else if (grade == "D0") {
            sum += (1.0 * flt);
        }
        else if (grade == "F") {
            sum += 0.0f;
        }
        else {
            score -= flt;
        }

    }

    cout << sum / score << endl;

    return 0;
}