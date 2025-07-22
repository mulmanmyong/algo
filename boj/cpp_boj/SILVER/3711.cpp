// 3711. 학번 -> 실버 5
#include <iostream>
#include <vector>
#include <unordered_set>

using namespace std;

int main()
{
  ios::sync_with_stdio(0);
  cin.tie(0);

  int n;
  cin >> n;

  while (n--)
  {
    int g;
    cin >> g;

    vector<int> students(g); // 학번을 담음
    for (int i = 0; i < g; i++)
    {
      cin >> students[i];
    }

    int div = 1; // 이걸로 나눔
    while (true)
    {
      unordered_set<int> remainders; ; // 중복없는 집합에 나머지를 담음

      for (int s : students)
      {
        remainders.insert(s % div); // 나머지를 담음
      }

      if (remainders.size() == g) // 중복은 담기지 않기 때문에 중복이 생기는 순간 사이즈는 g가 아니게 됨
      {
        cout << div << '\n';
        break;
      }

      div++;
    }
  }

  return 0;
}
