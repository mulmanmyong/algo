// 1406. 에디터 -> 실버 2

#include <bits/stdc++.h>

using namespace std;

const int mx = 1000005; // 초기 최대는 100000개, 추가로 최대 입력되는 개수 500000, 따라서 최소 600000의 공간은 필요함
char dat[mx];
int pre[mx];
int nxt[mx];
int unused = 1;

void P(int cursor, char c) {
  // 새로운 값을 생성
  // 삽입하고자 하는 값의 이전위치에 현위치 연결, 
  // 삽입하고자 하는 다음 위치의 기존 다음 위치 연결
  // 기존의 다음위치의 이전이 삽입하는 것을 가르키도록 연결
  dat[unused] = c;
  pre[unused] = cursor;
  nxt[unused] = nxt[cursor];
  if (nxt[cursor] != -1) pre[nxt[cursor]] = unused; // 맨 뒤에 삽입하는 경우에는 -1일 수도 있기에
  nxt[cursor] = unused;
  unused++;
}

void B(int cursor) {
  // 이전의 넥스트를 현재의 넥스트와 연결
  // 넥스트의 이전을 현재의 이전과 연결
  nxt[pre[cursor]] = nxt[cursor];
  if (nxt[cursor] != -1) pre[nxt[cursor]] = pre[cursor]; // 맨 뒤의 것을 지운다면 다음의 것이 -1일 수도 있기에
}

void print(void) {
  int cur = nxt[0];
  while (cur != -1) {
    cout << dat[cur];
    cur = nxt[cur];
  }
}

void func1(void) {
  int cur = 0;

  fill(pre, pre+mx, -1);
  fill(nxt, nxt+mx, -1);

  string s;
  cin >> s;
  for (char c : s) {
    P(cur, c);
    cur++;
  }

  int i;
  cin >> i;
  while(i--) {
    char cmd;
    cin >> cmd;

    if (cmd == 'L' && pre[cur] != -1) { // 커서가 맨 앞에 있으면 더 앞으로 안감
      cur = pre[cur];
    }
    else if (cmd == 'D' && nxt[cur] != -1) { // 커서가 맨 뒤에 있으면 더 뒤로 안감
      cur = nxt[cur];
    }
    else if (cmd == 'B' && pre[cur] != -1) { 
      // 커서가 맨 앞인데 지우면 지우는 건 없지만, 
      // 커서만 더 앞으로 갈 수도 있기에(0번 인덱스에 -1 존재) 
      // pre가 -1 이면 해당 커맨드도 지울 것도 없으니 명령 수행안하게 함
      B(cur);
      cur = pre[cur];
    }
    else if (cmd == 'P') {
      char in;
      cin >> in;
      P(cur, in);
      cur = nxt[cur];
    }
  }
  print();
}

void func2(void) {
  // list 사용한 방식
  string s;
  cin >> s;

  list<char> l;
  for(char c : s) l.push_back(c);
  auto cur = l.end();

  int i;
  cin >> i;
  while(i--) {
    char cmd;
    cin >> cmd;

    if (cmd == 'L') {
      if (cur != l.begin()) cur--;
    }
    else if (cmd == 'D') { 
      if (cur != l.end()) cur++;
    }
    else if (cmd == 'B') { 
      if (cur != l.begin()) {
        cur--;
        l.erase(cur);
      }
    }
    else if (cmd == 'P') {
      char in;
      cin >> in;
      l.insert(cur, in);
    }
  }

  for (auto c : l)  cout << c;
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
} 