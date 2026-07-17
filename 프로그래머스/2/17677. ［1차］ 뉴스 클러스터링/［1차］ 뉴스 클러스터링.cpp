#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(string str1, string str2) {
    // 대소문자를 구분 안하니깐 전부 소문자로 변환하고
    transform(str1.begin(), str1.end(), str1.begin(), ::tolower);
    transform(str2.begin(), str2.end(), str2.begin(), ::tolower);

    // 두 글자씩 끊어서 다중 집합의 원소로 만들기
    vector<string> vec1, vec2;

    // 오직 영문자로만 된 글자 쌍만 유효 (숫자, 기호, 띄어쓰기 등 다 무시)
    // 다중집합 만들기
    for (int i = 0; i < str1.length() - 1; i++) {
        // 현재 문자와 다음 문자가 알파벳인지 확인
        if (isalpha(str1[i]) && isalpha(str1[i + 1])) {
            // 알파벳이면 2글자 끊어서 담기
            vec1.push_back(str1.substr(i, 2));
        }
    }

    for (int i = 0; i < str2.length() - 1; i++) {
        // 현재 문자와 다음 문자가 알파벳인지 확인
        if (isalpha(str2[i]) && isalpha(str2[i + 1])) {
            // 알파벳이면 2글자 끊어서 담기
            vec2.push_back(str2.substr(i, 2));
        }
    }

    // 같은 문자열끼리 쉽게 비교하기 위해 정렬
    sort(vec1.begin(), vec1.end());
    sort(vec2.begin(), vec2.end());

    // 교집합 원소 개수
    int intersectionCount = 0;
    // 합집합 원소 개수
    int unionCount = 0;

    // 투 포인터를 이용해서 집합을 앞에서 부터 비교하면서 진행
    // 어느한쪽이 끝까지 도달하면 종료
    int index1 = 0;
    int index2 = 0;
    while (index1 < vec1.size() && index2 < vec2.size()) {
        // 같은 문자열이면 교집합과 합집합에 모두 포함
        if (vec1[index1] == vec2[index2]) {
            intersectionCount++;
            unionCount++;

            // 포인터 같이 증가
            index1++;
            index2++;
        }
        // vec1의 문자열이 더 작으면 vec1에만 존재하는 원소
        else if (vec1[index1] < vec2[index2]) {
            unionCount++;
            index1++;
        }

        // vec2의 문자열이 더 작으면 vec2에만 존재하는 원소
        else {
            unionCount++;
            index2++;
        }
    }

    // 아직 비교하지 못한 남은 원소들은 모두 합집합에 포함
    unionCount += (vec1.size() - index1);
    unionCount += (vec2.size() - index2);

    int answer = 0;

    // 두 집합이 모두 공집합인 경우 자카드 유사도를 1로 계산
    if (unionCount == 0) {
        answer = 65536;
    }
    else {
        answer = intersectionCount * 65536 / unionCount;
    }

    return answer;
}