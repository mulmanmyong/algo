# [Silver I] 2진수를 10진수로... - 1274 

[문제 링크](https://jungol.co.kr/problem/1274) 

### 성능 요약

메모리: 1.5 MB, 시간: 11 ms, 코드길이: 708 Bytes

### 제출 일자

2026년 7월 30일 11:05:18

### 문제 설명

<p style="text-align: justify">2진수를 입력받아 10진수로 바꾸어주는 프로그램을 생각해보자.</p>
<p style="text-align: justify"></p>
<p style="text-align: justify">주어지는&nbsp; 2진수는 8비트로 구성되어있으며 최상위비트는 부호비트이다. 최상위 비트의 값이 0이면 양수, 1이면 음수이며&nbsp;</p>
<p style="text-align: justify">음수의 경우 2의 보수로 나타낸다.</p>
<p style="text-align: justify"></p>
<p style="text-align: justify">[ <strong><span style="color: rgb(255, 0, 0)">1</span></strong>의 보수 만들기 ]&nbsp;</p>
<p style="text-align: justify">각 비트의 값이 1인 경우 0으로, 0인 경우 1로 바꾸면 된다.</p>
<p style="text-align: justify">(예 )&nbsp;</p>
<p style="text-align: justify"><strong>11010001</strong> 을 1의 보수로 바꾸면</p>
<p style="text-align: justify"><strong>00101110</strong> 가 된다. &nbsp;</p>
<p style="text-align: justify"></p>
<p style="text-align: justify">[&nbsp;<strong><span style="color: rgb(255, 0, 0)">2</span></strong>의 보수만들기 ] ​</p>
<p style="text-align: justify">2의 보수는 1의 보수의 마지막 비트에 1을 더한다.&nbsp;</p>
<p style="text-align: justify">(예)</p>
<p style="text-align: justify"><strong>00000001</strong>&nbsp;(10진수 1)을 1의 보수로 바꾸면</p>
<p style="text-align: justify"><strong>1111111<span style="color: rgb(0, 158, 37)">0</span></strong>&nbsp;가 되고 여기에 1을 더하여 2의 보수로 나타내면</p>
<p style="text-align: justify"><strong>1111111<span style="color: rgb(0, 158, 37)">1&nbsp;</span></strong>가 된다.</p>
<p style="text-align: justify">음수의 경우 2의 보수로 나타내므로 <strong>1111111<span style="color: rgb(0, 158, 37)">1</span></strong>​ 는 십진수로 -1이된다.</p>
<p style="text-align: justify"></p>
<p style="text-align: justify">여기서&nbsp;<strong>11111111</strong>​ 는 10진수로 -1인데&nbsp;</p>
<p style="text-align: justify">이는&nbsp;<strong>01111111</strong>​ (10진수 127)과 <strong>10000000​​</strong>​를 더한 결과이다.</p>
<p style="text-align: justify"></p>
<p style="text-align: justify">따라서 아래와 같은 식을 세울수 있다.</p>
<p style="text-align: justify"><strong>11111111</strong>​ =&nbsp;<strong>01111111 +&nbsp;10000000​​&nbsp;</strong></p>
<p style="text-align: justify"><strong>11111111</strong>​ -&nbsp;<strong>01111111 =&nbsp;10000000​​&nbsp;</strong></p>
<p style="text-align: justify">이제 (<strong>11111111</strong>) - (<strong>01111111</strong>) 는 10진수로 (-1) - (127) = -128이므로</p>
<p style="text-align: justify"><strong>10000000​​ </strong>는 ​-128이라는 것을 알수 있다.</p>
<p style="text-align: justify"></p>
<p style="text-align: justify">부호있는 8비트 2진수를 입력받아 10진수로 출력하는 프로그램을 작성하시오.</p>
<p style="text-align: justify"></p>

### 입력 

<p>입력의 첫 줄에 <math-inline>8</math-inline>자리의 <math-inline>2</math-inline>진수가 들어온다.</p>
<p></p>

### 출력 

<p>출력의 첫 줄에 <math-inline>10</math-inline>진수로 변환한 값을 출력한다.</p>
<p></p>

### 예제 입력 1

<pre>00000101</pre>

### 예제 출력 1

<pre>5</pre>

### 예제 입력 2

<pre>10011000</pre>

### 예제 출력 2

<pre>-104</pre>

### 힌트

<p>[<strong>보수의 정의</strong>]</p>
<p>1. <math-inline>N</math-inline>자리 <math-inline>A</math-inline>진법 수 <math-inline>K</math-inline>가 있을 때 <math-inline>A</math-inline>의 보수는 : <math-inline>A^N - K</math-inline>, 또는 <math-inline>A-1</math-inline>의 보수를 구하고 1을 더한다.&nbsp;</p>
<p>2. <math-inline>N</math-inline>자리 <math-inline>A</math-inline>진법 수 <math-inline>K</math-inline>가 있을 때 <math-inline>A-1</math-inline>의 보수는 : <math-inline>A^N - 1 - K</math-inline>&nbsp;</p>
<p></p>
<p>[<a target="blank" rel="noopener noreferrer nofollow" href="https://en.wikipedia.org/wiki/Horner%27s_method"> <strong><u>Horner's Method </u></strong></a>]&nbsp;</p>
<p><math-inline>2</math-inline>진수 <math-inline>1101</math-inline>을 자리수별 가중치를 주어 <math-inline>10</math-inline>진수로 나타내면 아래와 같다.&nbsp;</p>
<p><math-inline>1101 = 0 * 2^4 + 1 * 2^3 + 1 * 2^2 + 0 * 2^1 + 1</math-inline> 이므로&nbsp;</p>
<p>&nbsp; &nbsp; &nbsp;=<math-inline> (((0*2 + 1) * 2 + 1) * 2 + 0) * 2 + 1</math-inline> 과 같다.</p>

> 출처: JUNGOL, https://jungol.co.kr
