#include <iostream>

using namespace std;

int solution(int n, int a, int b)
{
    int answer = 0;
    
    // N 명이 참가, 토너먼트 형식으로 진행
    // 참가자들은 1부터 N번을 차례대로 배정 받음
    // N은 짝수이고, 1 2 / 3 4 / 5 6 이런 식으로 배정 받음
    // 그리고 이긴 사람들은 다시 1부터 N/2까지 차례대로 배정 받음
    
    // 그러면 A번참가자는 B번 참가자랑 몇번째 라운드에서 만나냐?
    // a와 b의 숫자가 같아지면, 이전 라운드에서 만나서 싸운 것
    while (a != b) {
        answer++; // 숫자가 다르니깐 해당라운드 진행
        
        // 다음 라운드 숫자 배정
        a = (a + 1) / 2;
        b = (b + 1) / 2;
    }
    
    // answer가 같아지기 전 라운드
    return answer;
}