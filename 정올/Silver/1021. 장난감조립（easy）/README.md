# [Silver II] 장난감조립(easy) - 1021 

[문제 링크](https://jungol.co.kr/problem/1021) 

### 성능 요약

메모리: -, 시간: 1 ms, 코드길이: 1688 Bytes

### 분류

백트래킹

### 제출 일자

2026년 8월 8일 18:03:42

### 문제 설명

<p style="text-align: justify;">우리는 어떤 장난감을 여러 가지 부품으로 조립하여 만들려고 한다.&nbsp;</p><p style="text-align: justify;">이 장난감을 만드는데는 기본 부품과 그 기본 부품으로 조립하여 만든 중간 부품이 사용된다.&nbsp;</p><p style="text-align: justify;">기본 부품은 다른 부품을 사용하여 조립될수 없는 부품이다.&nbsp;</p><p style="text-align: justify;">중간 부품은 또 다른 중간 부품이나 기본 부품을 이용하여 만들어지는 부품이다.</p><p style="text-align: justify;">&nbsp;</p><p style="text-align: justify;">예를 들어보자. 기본 부품으로서 1, 2, 3, 4가 있다.&nbsp;</p><p style="text-align: justify;">중간 부품 5는 2개의 기본 부품 1과 2개의 기본 부품 2로 만들어진다.&nbsp;</p><p style="text-align: justify;">그리고 중간 부품 6은 2개의 중간 부품 5, 3개의 기본 부품 3과 4개의 기본 부품 4로 만들어진다.&nbsp;</p><p style="text-align: justify;">마지막으로 장난감 완제품 7은 2개의 중간 부품 5, 3개의 중간 부품 6과 5개의 기본 부품 4로 만들어진다.&nbsp;</p><p style="text-align: justify;">이런 경우에 장난감 완제품 7을 만드는데 필요한 기본 부품의 개수는 1번 16개, 2번 16개, 3번 9개, 4번 17개 이다.</p><p style="text-align: justify;">&nbsp;</p><p style="text-align: justify;">이와 같이 어떤 장난감 완제품과 그에 필요한 부품들 사이의 관계가 주어져 있을 때&nbsp;</p><p style="text-align: justify;">하나의 장난감 완제품을 조립하기 위하여 필요한 기본 부품의 종류별 개수를 계산하는 프로그램을 작성하시오.</p>

### 입력

<p>입력 파일의 첫째 줄에는 정수 N(3≤N≤100)이 주어지는데 1부터 N-1까지는 기본 부품이나 중간 부품의 번호를 나타내고 N은 완제품의 번호를 나타낸다.</p><p>그리고 그 다음 줄에는 정수 M(3≤M≤100)이 주어지고 그 다음 M개의 줄에는 어떤 부품을 완성하는데 필요한 부품들 간의 관계가 3개의 정수 X Y K로 주어진다.&nbsp;</p><p>이 뜻은 "중간 부품이나 완제품 X를 만드는데 필요한 중간 부품 혹은 기본 부품 Y가 K개 필요하다"는 뜻이다.</p>

### 출력

<p>하나의 완제품을 조립하는데 필요한 기본 부품의 수를 한 줄에 하나씩 출력하되(중간 부품은 출력하지 않음) 반드시 기본 부품의 번호가 작은 것부터 큰 순서가 되도록 한다.</p><p>각 줄에는 기본 부품의 번호와 소요 개수를 출력한다.</p><p>이 때, 모든 기본 부품의 소요 개수의 합은 10,000을 넘지 않음이 보장된다.(easy)</p>

### 예제 입력 1

<pre>7
8
5 1 2
5 2 2
7 5 2
6 5 2
6 3 3
6 4 4
7 6 3
7 4 5</pre>

### 예제 출력 1

<pre>1 16
2 16
3 9
4 17</pre>

### 예제 입력 2

<pre>5
6
1 3 4
1 4 2
3 4 1
5 3 2
5 1 3
3 2 3</pre>

### 예제 출력 2

<pre>2 42
4 20</pre>

<figure><img crossorigin="anonymous" src="https://s.jungol.co.kr/board/77747/1Qr-ZJovBgD7YsNWHtsLj4/파일"></figure>

### 출처

KOI 전국 2000 중1 수정
