// 2477. 참외 밭 -> 실버 2

#include <bits/stdc++.h>

using namespace std;

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);

  int k;
  cin >> k;

  // 순서대로 방향배열
  int dir[6] = {0};
  // 길이 배열
  int moveLength[6] = {0};

  // 큰 변의 길이 에서 작은 사각형을 뺴면됨
  // 큰변의 길이의 맞은 편에 짧은 것 존재, 하지만 제일 짧은 것이 잘라내는 사각형이 아님
  // 구조상 제일 긴 나온 변 나오고 3번 뒤에 를 보면 각각 잘라낼 변임을 알 수 있었음

  // index를 구해줘야함
  int maxCol = -1, maxRow = -1;
  int maxColIdx = -1, maxRowIdx = -1;
  for (int i = 0; i < 6; i++)
  {
    cin >> dir[i] >> moveLength[i];

    if (dir[i] == 1 || dir[i] == 2)
    {
      if (maxCol < moveLength[i])
      {
        maxCol = moveLength[i];
        maxColIdx = i;
      }
    }
    else if (dir[i] == 3 || dir[i] == 4)
    {
      if (maxRow < moveLength[i])
      {
        maxRow = moveLength[i];
        maxRowIdx = i;
      }
    }
  }

  int bigArea = maxCol * maxRow;
  int cutArea = moveLength[(maxColIdx + 3) % 6] * moveLength[(maxRowIdx + 3) % 6];

  cout << (bigArea - cutArea) * k;

  return 0;
}