#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(int cacheSize, vector<string> cities) {
    // LRU 알고리즘를 통해 캐시 교체 알고리즘 사용
    // hit면 실행시간은 1, miss면 5

    // LRU를 위한 캐시 배열
    vector<string> cache;

    int answer = 0;

    // cacheSize가 0이면 전부다 miss
    if (cacheSize == 0) {
        return cities.size() * 5;
    }

    // 실행 시간 계산 진행
    for (string city : cities) {
        // 대소문자 구분 X
        transform(city.begin(), city.end(), city.begin(), ::tolower);

        auto it = find(cache.begin(), cache.end(), city);

        // cache hit
        if (it != cache.end()) {
            answer += 1;

            // 가장 최근 사용한 것으로 갱신
            cache.erase(it);
            cache.push_back(city);
        }
        // cache miss
        else {
            answer += 5;

            // 캐시가 꽉 찼다면 가장 오래된 데이터 제거
            if (cache.size() == cacheSize)
                cache.erase(cache.begin());

            cache.push_back(city);
        }
    }

    return answer;
}