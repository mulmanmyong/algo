#include <iostream>

using namespace std;

// postorder로 방문 결과 출력
void postorder() {
    // preorder로 입력됨
    // preorder는 중앙 좌 우 지만
    // postorder는 좌 우 중앙이다
    // 제일먼저 입력된 값이 제일 뒤로가면 되니깐?
    // -1이 나올때까지 타고 들어가고, -1이 나오면 백트래킹
    // -1 -1이 나오면 좌 우 없는 것이니 출력하는 형식으로 하면
    // 좌 우 중앙의 순이 될 것
    int node; 
    cin >> node;

    // -1이 나오면 백트래킹
    if (node == -1) {
        return;
    }

    // 좌 우 순회
    postorder();
    postorder();

    // -1이 모두 나와서 출력할 차례
    cout << node << ' ';
}

int main() {
    // preorder 형식으로 입력
    // child없으면 -1 
    postorder();
    return 0;
}