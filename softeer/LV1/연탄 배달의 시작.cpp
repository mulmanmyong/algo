// vector와 map의 이용
// 소요시간 : 10분
// 고려사항 : 이렇게 풀면 시간복잡도가 너무 쓰레기 -> 개선방안 생각

#include<iostream>
#include <map>
#include <vector>


using namespace std;

int main(int argc, char** argv)
{


    int n, a;
    vector<int> loc;
    map<int, int> dist;

    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> a;
        loc.push_back(a);
        
    }

    for (int i = 1; i <= n; i++) {
        dist[loc[i] - loc[i-1]] += 1; // 거리를 배열로 개수를 추가, map은 오름차순 정렬이고, += 1로 있으면 해당값 증가
    }

    int index = 0;
    while (1) {
        if (dist[index] > 0) {
            cout << dist[index] << endl;
            break;
        }

        index++;
    }
    

   return 0;
}