#include <iostream>
#include <vector>
#include <algorithm>
#include <map>

using namespace std;

int solution(vector<int> nums)
{
    int answer = 0;
    map<int, int> mp;
    int nums_len = nums.size();
    for (int i = 0; i < nums_len; i++) {
        mp[nums[i]] += 1;
    }

    // 최빈값을 찾는 것이 아닌 N개면 N/2개의 종류를 최대로 선택할 수 있음. 하지만 N/2보다 더 적은 수가 최대가 될 수 있음
    // 그래서 맵을 이용하는 것 -> 맵을 쓰면 중복되는 것 없이 만들어 줌
    nums_len = min(nums_len/2, (int)mp.size());
    answer = nums_len;

    return answer;
}

int main() {

    vector<int> tmp;
    tmp.push_back(3);
    tmp.push_back(2);
    tmp.push_back(1);
    tmp.push_back(3);

    cout << solution(tmp) << endl;

    return 0;
}