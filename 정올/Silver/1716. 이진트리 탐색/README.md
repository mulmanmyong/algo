# [Silver V] 이진트리 탐색 - 1716 

[문제 링크](https://jungol.co.kr/problem/1716) 

### 성능 요약

메모리: 1.3 MB, 시간: 4 ms, 코드길이: 584 Bytes

### 제출 일자

2026년 7월 29일 13:07:14

### 문제 설명

<p>이진 트리를 postorder 방문 형식으로 방문해보자. 아래와 같은 트리가 주어지는 경우</p>
<figure><img crossorigin="anonymous" src="https://u.jungol.co.kr/problem/1716/8eec27fa-154f-42b4-bddc-6082b3b3bcfb.png"></figure>
<p>입력은 다음과 같이 preorder 형식으로 입력된다. (child를 가지지 않을 때는 -1)</p>
<ul>
<li><p>5 3 11 7 -1 -1 2 -1 -1 -1 8 13 -1 -1 4 -1 1 -1 -1</p></li>
</ul>
<p>postorder로 방문한 결과를 출력하는 프로그램을 작성하시오.</p>

### 입력 

<p>같은 번호를 가진 노드 번호는 입력되지 않는다. 노드 번호의 최대는 <math-inline>20</math-inline> 이고 자연수이다.</p>

### 출력 

<p>postorder로 방문한 결과를 출력한다.</p>

### 예제 입력

<pre>5 3 11 7 -1 -1 2 -1 -1 -1 8 13 -1 -1 4 -1 1 -1 -1</pre>

### 예제 출력

<pre>7 2 11 3 13 1 4 8 5</pre>

> 출처: JUNGOL, https://jungol.co.kr
