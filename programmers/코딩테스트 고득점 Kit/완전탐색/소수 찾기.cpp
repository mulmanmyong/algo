#include <string>
#include <vector>
#include <cmath>
#include <algorithm>
#include <set>

using namespace std;

bool isPrime(int num) {
    if (num < 2) {
        return false;
    }
    for (int i = 2; i <= sqrt(num); i++) {
        if (num % i == 0) {
            return false;
        } 
    }
    
    return true;
}

int solution(string numbers) {
    set<int> ans;
    
    sort (numbers.begin(), numbers.end());
    do {
        string num = "";
        for (int i = 0; i < numbers.size(); ++i) {
            num += numbers[i];
            if (isPrime(stoi(num))) {
                ans.insert(stoi(num));
            }
        }
    } while (next_permutation(numbers.begin(), numbers.end()));
    
    return ans.size();
}