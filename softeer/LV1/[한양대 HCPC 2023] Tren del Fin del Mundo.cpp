// 주어진 입력값으로 정렬가능성 문제?
// 소요시간 : 10분
#include<iostream>
#include <algorithm>
#include <vector>

using namespace std;

bool compare(pair<int, int> a, pair<int, int> b) {
    if (a.second < b.second) { // 유일한 점, y좌표만 비교
        return true;
    }

    return false;
}

int main(int argc, char** argv)
{

    int n;
    vector<pair<int, int>> vec;
    int x, y;

    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> x >> y;
        vec.push_back(make_pair(x, y));
    }

    sort(vec.begin(), vec.end(), compare);

    cout << vec[0].first << " " << vec[0].second << endl; 
    
    return 0;
}