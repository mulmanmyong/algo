#include <iostream>
#include <stack>

using namespace std;

int main() {
    // 2개의 스택을 이용해서 
    // 입력으로 명령어 들어옴

    stack<string> forward;
    stack<string> backward;
    string currentPage = "http://www.acm.org/";

    string cmd;
    while(true) {
        cin >> cmd;
        // QUIT 명령어 들어오면 종료
        if (cmd == "QUIT") {
            break;
        }

        // VISIT 명령어 현재 페이지 backward push
        if (cmd == "VISIT") {
            backward.push(currentPage);
            cin >> currentPage;

            // 그리고 forward 스택 비우기
            while (!forward.empty()) forward.pop();
        }
        else if (cmd == "BACK") {
            // 근데 backward 비어있으면 무시
            if (backward.empty()) {
                cout << "Ignored\n";
                continue;
            }

            forward.push(currentPage);
            currentPage = backward.top();
            backward.pop();
        }
        else if (cmd == "FORWARD") {
            // 근데 forward 비어있으면 무시
            if (forward.empty()) {
                cout << "Ignored\n";
                continue;
            }

            backward.push(currentPage);
            currentPage = forward.top();
            forward.pop();
        }

        cout << currentPage << '\n';
    }

    return 0;
}