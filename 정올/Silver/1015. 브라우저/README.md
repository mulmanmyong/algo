# [Silver I] 브라우저 - 1015 

[문제 링크](https://jungol.co.kr/problem/1015) 

### 성능 요약

메모리: 1.5 MB, 시간: 2 ms, 코드길이: 1267 Bytes

### 분류

스택, 구현, 자료 구조

### 제출 일자

2026년 8월 5일 13:33:44

### 문제 설명

<p>표준 웹브라우저는 방문한 페이지들 내에서 이전 이후 페이지를 방문하는 기능이 있다.&nbsp;</p><p>이를 구현하는 방법으로 두 개의 스택을 이용하는 방법이 있다.&nbsp;</p><p>​</p><p>입력으로 아래의 명령들이 들어온다.</p><p>&nbsp;</p><p><b>BACK </b>: 현재 페이지를 forward stack에 push, backward stack에서 pop하여 현재 페이지로 설정한다.&nbsp;</p><p>(backward stack이 ​비었다면&nbsp;​명령을 무시한다.)</p><p>&nbsp;</p><p><b>FORWARD </b>: 현재 페이지를 backward stack에 push, forward stack에서 pop하여 현재 페이지로 설정한다.&nbsp;</p><p>(forward stack이 비었다면 명령은 무시한다​.)</p><p>&nbsp;</p><p><b>VISIT</b> : 현재 페이지를 backward stack에 push, 입력된 URL을 현재 페이지로 설정. forward stack은 비운다.&nbsp;</p><p>&nbsp;</p><p><b>QUIT&nbsp;</b>: 프로그램 종료.</p><p>&nbsp;</p><p>초기 웹페이지는 <span style="color: rgb(58, 50, 195);"><b>http://www.acm.org/</b></span>로 설정되었다고 가정한다.​&nbsp;</p>

### 입력

<p>각 줄에 명령이 입력된다.
URL은 70글자 이하이다.</p><p>Stack은 100개를 넘게 쌓이지 않는다고 가정한다.</p>

### 출력

각 줄마다 현재 페이지를 출력한다.
만약 명령이 무시되면 "Ignored"를 출력한다.

### 예제 입력

<pre>VISIT http://acm.ashland.edu/ 
VISIT http://acm.baylor.edu/acmicpc/ 
BACK 
BACK 
BACK 
FORWARD 
VISIT http://www.ibm.com/ 
BACK 
BACK 
FORWARD 
FORWARD 
FORWARD 
QUIT</pre>

### 예제 출력

<pre>http://acm.ashland.edu/ 
http://acm.baylor.edu/acmicpc/ 
http://acm.ashland.edu/ 
http://www.acm.org/ 
Ignored 
http://acm.ashland.edu/ 
http://www.ibm.com/ 
http://acm.ashland.edu/ 
http://www.acm.org/ 
http://acm.ashland.edu/ 
http://www.ibm.com/ 
Ignored</pre>

### 출처

East Central North America 2001, poj 1028
