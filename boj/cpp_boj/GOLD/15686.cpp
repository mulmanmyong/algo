// 15686. 치킨 배달 -> 골드 5

#include <iostream>
#include <queue>
#include <algorithm>
#include <cmath>
#include <vector>

using namespace std;

int n, m, ans = 2147483647;

vector< pair<int, int> > chickens;
vector< pair<int, int> > houses;
vector<int> survived_chicken;

int calc_distance() {
    int total_distance = 0;

    // 모든 집에 대해 계산
    for (auto house : houses) {
        int hx = house.first;
        int hy = house.second;
        int min_dist = 2147483647;

        // 해당 집에 대해 생존한 치킨집과의 최소거리 찾기
        for (int index : survived_chicken) {
            int cx = chickens[index].first;
            int cy = chickens[index].second;

            int dist = abs(hx - cx) + abs(hy - cy);
            min_dist = min(min_dist, dist);
        }

        total_distance += min_dist;
    }

    return total_distance;
}

void even_chicken(int index, int count) {

    if (m == count) {
        int chicken_dist = calc_distance();
        ans = min(ans, chicken_dist);
        return;
    }

    for (int i = index; i < chickens.size(); i++) {
        survived_chicken.push_back(i); // 현재 인덱스 보관
        even_chicken(i+1, count+1); // 재귀
        survived_chicken.pop_back(); // 원복
    }

}

int main() {

    cin >> n >> m;

    int a;
    // 치킨 좌표와 집 좌표 기록
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> a;
            if (a == 2) {
                chickens.push_back(make_pair(i, j));
            }
            else if (a == 1) {
                houses.push_back(make_pair(i, j));
            }
        }
    }

    // 폐업 시킬 치킨집 찾기
    // 생존한 치킨집 대상으로 최소거리 찾아서 더하기

    even_chicken(0, 0);

    cout << ans << endl;

    return 0;
}