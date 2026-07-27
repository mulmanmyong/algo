#include <iostream>

using namespace std;

// 윤년판별
bool isLeapYear(int year) {
    // 400의 배수는 모두 윤년
    if (year % 400 == 0) {
        return true;
    }
    // 4의 배수이며 100의 배수가 아니면 윤년
    else if (year % 4 == 0 && year % 100 != 0) {
        return true;
    }

    return false;
}

// 해당 월의 마지막 날짜 반환
int getLastDay(int year, int month) {
    // 각 달의 마지막 일
    int monthDay[12] = {
        31, 28, 31, 30, 31, 30,
        31, 31, 30, 31, 30, 31
    };

    // 윤년의 2월은 29일까지
    if (month == 2 && isLeapYear(year)) {
        return 29;
    }

    return monthDay[month - 1];
}

// 해당 날짜의 요일 반환
// 0: 일요일, 1: 월요일, 2: 화요일, 3: 수요일, 4: 목요일, 5: 금요일, 6: 토요일
int getWeekDay(int year, int month, int day) {
    // 2000년 1월 1일부터 지난 날짜 수
    int totalDay = 0;

    // 이전 년도 계산
    for (int y = 2000; y < year; y++) {
        if (isLeapYear(y)) {
            totalDay += 366;
        }
        else {
            totalDay += 365;
        }
    }

    // 이전 월 계산
    for (int m = 1; m < month; m++) {
        totalDay += getLastDay(year, m);
    }

    // 현재 달 계산
    totalDay += day - 1;

    // 2000년 1월 1일은 토요일
    int startWeek = 6;

    return (startWeek + totalDay) % 7;
}

int main() {
    // 원하는 년 월 일을 입력받아 해당 년 월의 달력,
    // 해당일의 요일을 출력하는 프로그램 작성

    int year, month, day;

    // 올바른 입력이 들어올 때까지 반복
    while (true) {
        cin >> year >> month >> day;

        // 년 검사
        if (year < 2000 || year > 2010) {
            cout << "INPUT ERROR!\n";
        }
        // 월 검사
        else if (month < 1 || month > 12) {
            cout << "INPUT ERROR!\n";
        }
        // 일 검사
        else if (day < 1 || day > getLastDay(year, month)) {
            cout << "INPUT ERROR!\n";
        }
        // 모든 조건을 만족하면 반복 종료
        else {
            break;
        }
    }

    // 해당 월의 첫 번째 요일
    int firstWeek = getWeekDay(year, month, 1);

    // 해당 월의 마지막 날짜
    int lastDay = getLastDay(year, month);

    // 달력 출력
    cout << year << ". " << month << endl;
    cout << "sun mon tue wed thu fri sat" << endl;

    // 첫 번째 날짜 전까지 공백 출력
    for (int i = 0; i < firstWeek; i++) {
        cout << "    ";
    }

    // 날짜 출력
    for (int d = 1; d <= lastDay; d++) {

        // 한 자리 숫자는 앞에 공백 2칸
        if (d < 10) {
            cout << "  " << d << " ";
        }
        // 두 자리 숫자는 앞에 공백 1칸
        else {
            cout << " " << d << " ";
        }

        // 토요일이면 줄바꿈
        if ((firstWeek + d) % 7 == 0) {
            cout << endl;
        }
    }

    // 마지막 줄이 토요일이 아니면 줄바꿈
    if ((firstWeek + lastDay) % 7 != 0) {
        cout << endl;
    }

    // 요일 이름 저장
    string week[7] = {
        "Sunday",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday"
    };

    // 해당 날짜의 요일 출력
    cout << week[getWeekDay(year, month, day)] << endl;

    return 0;
}
