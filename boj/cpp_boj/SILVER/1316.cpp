// 1316. 그룹 단어 체커 -> 실버 5

#include <iostream>
#include <string>


using namespace std;

int main() {


    string str;
    int test_case, sum = 0;
    cin >> test_case;

    for (int t = 0; t < test_case; t++) {

        cin >> str;
        int check = 1;

        // 나왔던 단어가 연속해서가 아니라 끊겨서 나오면 그룹단어가 아님
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < str.length(); j++) { // 다음 글자부터 비교

                if (str[i] == str[j] && str[i] != str[j-1]) {
                    // abca 가 있으면 i = 0일경우
                    // j가 3이되었을 때 a와 a로 같지만, a와 c로 다르므로 연속되어서 나온것이 아니라는 것을 확인
                    // 그러므로 그룹단어가 아님
                    check = 0;
                    break;
                }
            }

            if (check == 0) {
                break; // 이미 그룹단어가 아님
            }
        }

        if (check == 1) {
            sum++; // 그룹단어이면 그룹단어의 개수 증가
        }
    }

    cout << sum << endl;

    return 0;
}