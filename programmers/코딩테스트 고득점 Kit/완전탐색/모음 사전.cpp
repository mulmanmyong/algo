#include <string>
#include <vector>

using namespace std;

string aeiou = "AEIOU";
int answer, cnt;

void dfs(string str, string word) {
    if (answer == cnt) {
        return;
    }
    
    if (str == word) {
        answer = cnt;
        return;
    }
    
    if (str.length() >= 5) {
        return;
    }

    for (int i = 0; i < 5; i++) {
        cnt++;
        dfs(str + aeiou[i], word);
    }
}

int solution(string word) {
    answer = -1; cnt = 0;
    dfs("", word);
    return answer;
}
