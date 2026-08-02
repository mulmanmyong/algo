# [Silver V] 에라토스테네스의 체 - 1232 

[문제 링크](https://jungol.co.kr/problem/1232) 

### 성능 요약

메모리: -, 시간: 1 ms, 코드길이: 798 Bytes

### 제출 일자

2026년 8월 2일 16:13:17

### 문제 설명

<p align="justify" style="text-align: justify;">에라토스테네스의 체는 어떤 수 N까지의 소수를 구하는 유명한 알고리즘이다. 그 알고리즘은 아래와 같다.</p><p align="justify" style="text-align: justify;">&nbsp;</p><p align="justify" style="text-align: justify;">1. 2와 N을 포함하여 그사이의 수들을 차례로 적는다.
2. 지우지 않은 수들 중에서 가장 작은 수를 찾는다. 그 수는 소수이고 그 수를 P라고 하자.
3. P를 지우고 아직 지워지지 않은 P의 배수들을 지운다.
4. 아직 지워지지 않은 수가 있다면 2번으로 돌아가서 다시 시작한다.</p><p align="justify" style="text-align: justify;">&nbsp;</p><p align="justify" style="text-align: justify;">두수 N과 K가 주어질 때, K번째로 지워지는 정수를 출력하시오.</p>

### 입력

<p>두수 N과 K의 범위는 2≤K＜N≤1,000 이다.</p>

### 출력

<p>K번째로 지워지는 수를 출력하시오.</p>

### 예제 입력 1

<pre>7 3</pre>

### 예제 출력 1

<pre>6</pre>

### 예제 입력 2

<pre>15 12</pre>

### 예제 출력 2

<pre>7</pre>

### 예제 입력 3

<pre>10 7</pre>

### 예제 출력 3

<pre>9</pre>

### 출처

COCI 2008/2009 contest2 2
