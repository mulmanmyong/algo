// PCCP 기출문제 1번 붕대감기 - lv1

#include <string>
#include <vector>

using namespace std;

int solution(vector<int> bandage, int health, vector<vector<int>> attacks)
{
    int t = bandage[0];
    int x = bandage[1];
    int y = bandage[2];

    int time = 0;
    int success = 0;
    int attack_index = 0;
    int max_health = health;
    int answer = 0;

    while (1)
    {
        time++; // 시간 증가

        if (time == attacks[attack_index][0])
        {
            health -= attacks[attack_index][1];
            attack_index++;
            success = 0;
        }
        else
        {
            success++; // 성공횟수

            health += x;
            if (success == t) // 연속성공
            {
                health += y;
                success = 0;
            }

            // 최초에 실패했던 이유
            // 그냥 무작정 최대 체력이 넘으면 회복도 안되게 함.
            // 아래처럼 일단 회복시키고, 최대 회복이 넘었을 경우에만 최대 체력까지 회복되게 변경
            if (health > max_health)
            {
                health = max_health;
            }
        }

        if (health <= 0)
        {
            answer = -1;
            break;
        }
        else if (time == attacks[attacks.size() - 1][0])
        {
            answer = health;
            break;
        }
    }

    return answer;
}