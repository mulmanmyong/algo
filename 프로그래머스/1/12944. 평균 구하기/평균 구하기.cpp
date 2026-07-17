#include <string>
#include <vector>

using namespace std;

double solution(vector<int> arr) {
    double answer = 0;
    for (int num : arr) {
        answer += (double) num;
    }
    answer /= arr.size();
    return answer;
}