// 14891. 톱니바퀴 -> 골드 5

#include <iostream>
#include <algorithm>
#include <vector>
#include <deque>
#include <cstring>
#include <cmath>

#define SIZE 8

using namespace std;

// deque로 벡터 만들 수 있음
vector< deque<int> > gear(5);
int rotate_arr[5]; // 회전방향 배열
int check[5]; // 중복확인 제거 위함
int k;

void setRotation(int a, int b) {
    rotate_arr[a] = b;
    check[a] = 1;

    // 왼쪽확인
    if (1 <= a - 1 && check[a - 1] == 0) {
        // 현재 톱니 회전안하면 -> 왼쪽도 회전안함
        if (b == 0) {
            setRotation(a - 1, 0);
        }
        else if (gear[a][6] == gear[a - 1][2]) {
            setRotation(a - 1, 0); // 현재 기어는 회전하지만, 같은 극이어서 회전 안함
        }
        else {
            setRotation(a - 1, -b); // 현재 기어가 회전하고, 다른 극이므로 반대 방향으로 회전
        }
    }
    // 오른쪽환인
    if (a + 1 <= 4 && check[a + 1] == 0) {
        // 현재 톱니 회전안하면 -> 오른쪽도 회전안함
        if (b == 0) {
            setRotation(a + 1 , 0);
        }
        else if (gear[a][2] == gear[a + 1][6]) {
            setRotation(a + 1, 0); // 현재 기어는 회전하지만, 같은 극이어서 회전 안함
        }
        else {
            setRotation(a + 1, -b); // 현재 기어가 회전하고, 다른 극이므로 반대 방향으로 회전
        }
    }
}

void Rotation() {

    // 모든 기어 판단
    for (int i = 1; i <= 4; i++) {
        // 시계방향 회전
        if (rotate_arr[i] == 1) {
            // 시계방향 회전이니깐 젤 뒤에꺼가 제일 앞으로 오고 pop
            int tmp = gear[i].back();
            gear[i].pop_back();
            gear[i].push_front(tmp);
        }
        // 반시계방향 회전
        if (rotate_arr[i] == -1) {
            // 반시계방향 회전이니깐 젤 앞에꺼가 제일 뒤로 가고 pop
            int tmp = gear[i].front();
            gear[i].pop_front();
            gear[i].push_back(tmp);
        }
    }
}

int main() {

    // gear를 입력받음
    char c;
    for (int i = 1; i <= 4; i++) {
        for (int j = 0; j < SIZE; j++) {
            cin >> c;
            gear[i].push_back(c - '0');
        }
    }

    cin >> k;
    int a, b;
    for (int i = 0; i < k; i++) {
        cin >> a >> b;

        memset(check, 0, sizeof(check)); // 중복확인제거위함
        // 회전방향정하기
        setRotation(a, b);
        // 회전 방향 정했으면 회전방향을 기준으로 회전시키기
        Rotation();
    }

    // k번 회전 이후에 4개의 톱니 점수 계산
    // 전부 12시 기준
    // 1번 N 0 , S 1=2^0 / 2번 N 0 , S 2=2^1 / 3번 N 0 , S 4=2^2 / 4번 N 0 , S 8=2^3
    int ans = 0;
    for (int i = 1; i <= 4; i++) {
        ans += (gear[i].front() * pow(2, i - 1));
    }

    cout << ans << endl;


    return 0;
}