# [Silver V] 소수369 - 1011 

[문제 링크](https://jungol.co.kr/problem/1011) 

### 성능 요약

메모리: 1.6 MB, 시간: 4 ms, 코드길이: 1011 Bytes

### 분류

수학, 정수론, 소수 판정, 에라토스테네스의 체

### 제출 일자

2026년 7월 28일 13:31:03

### 문제 설명

<p style="text-align: justify">원래 기존의 369게임은 원으로 둘러앉아서 순서대로 <math-inline>1</math-inline>부터 순서대로 숫자를 부르면서 <math-inline>3, 6, 9</math-inline>가 들어가는 숫자를 부를 경우 숫자 대신 박수를 쳐야하는 게임이다.</p>
<p style="text-align: justify">소수369게임은 박수를 치는 수를 <math-inline>3, 6, 9</math-inline>가 들어가는 숫자 대신 <strong>소수</strong>(1이외에 자기 자신으로만 나눠지는 2이상의 숫자)로 바꿔서 진행하는 게임이다.</p>
<p style="text-align: justify">플레이어의 수와 마지막에 부른 번호가 주어졌을 때 특정 사람이 박수를 몇 번 쳤는지 알아보는 프로그램을 작성하라.</p>

### 입력 

<p>첫 줄에 학생 수 <math-inline>N</math-inline>, 마지막에 부른 숫자 <math-inline>M</math-inline>, 박수를 몇 번 쳤는지 알고 싶은 사람의 번호 <math-inline>K</math-inline>가 주어진다.</p>
<p>첫 번째 숫자를 부르는 학생은 <math-inline>0</math-inline>번이고, 마지막 학생은 <math-inline>N-1</math-inline>번이며 <math-inline>N-1</math-inline>번 학생 다음에는 <math-inline>0</math-inline>번 학생의 차례가 오게 된다.​</p>
<p></p>
<p>[제한]</p>
<ul>
<li><p><math-inline>2≤N≤1,000</math-inline></p></li>
<li><p><math-inline>1≤M≤1,000,000</math-inline></p></li>
<li><p><math-inline>0≤K＜N</math-inline></p></li>
</ul>

### 출력 

<p>해당 번호의 사람이 박수를 치는 횟수를 출력하라.</p>

### 예제 입력

<pre>3 9 1</pre>

### 예제 출력

<pre>2</pre>

> 출처: JUNGOL, https://jungol.co.kr
