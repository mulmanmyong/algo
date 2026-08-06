#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<vector<int>> routes) {
    // 우선 진입 시점을 기준으로 오름차순 정렬
    sort(routes.begin(), routes.end());
    
    // 각 경로가 끝나는 지점에 카메라를 설치하는 것이 합리적 (겹치거나 그 안에 자동차가 들어와서 주행 중이면 겹치니깐)
    // 그러면 조건을 세워서 카메라의 위치를 변경하고, 개수 증가
    // 초기 설치 위치는 첫번째 진입 차량의 끝나는 지점
    int answer = 1;
    int cameraPosition = routes[0][1];
    
    // 다음 진입 차량 부터 판단
    for (int i = 1; i < routes.size(); i++) {
        // 우선 카메라의 위치가 변경되는 상황
        // 1. 현재 카메라 위치보다 다음 진입 차량의 시점이 더 클 경우 -> 카메라의 위치 변경하고 개수 증가
        // 2. 현재 카메라 위치보다 다음 진입 차량의 진입과 탈출이 빠른 경우 -> 카메라의 위치 변경
        
        // 1. 현재 카메라 위치보다 다음 진입 차량의 시점이 더 클 경우 
        // -> 카메라의 위치를 다음 종료시점으로 변경하고 개수 증가
        if (cameraPosition < routes[i][0]) {
            cameraPosition = routes[i][1];
            answer++;
        }
        // 2. 현재 카메라 위치보다 다음 진입 차량의 진입과 탈출이 빠른 경우 
        // -> 카메라의 위치만 다음 진입차량 탈출시점으로 변경 (동시 촬영)
        else if (cameraPosition > routes[i][1]) {
            cameraPosition = routes[i][1];
        }
    }
    
    return answer;
}