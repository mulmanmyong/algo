// 1076. 저항 -> 브론즈 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main1076 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 색깔들의 배열 0~9 
		String[] colors = {"black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white"};

		// Map Collections을 이용해서 입력값을 키로 하고, 그에 해당하는 인덱스를 value 
		// Map을 입력하여 깔끔하게 구현하는 것 O(N)
		Map<String, Integer> colorMap = new HashMap<>(); 
		for (int index = 0; index < colors.length; index++) {
			colorMap.put(colors[index], index);
		}
		
		// 입력받음과 동시에 키로 이용하여 value가져오기 
		int firstNum = colorMap.get(br.readLine());
		int secondNum = colorMap.get(br.readLine());
		int multiplier = colorMap.get(br.readLine());
		
		/*// 반복을 하며 해당 컬러와 일치하는 것 찾는 것 O(N)
		String[] inputColor = new String[3];
		for (int inputIndex = 0; inputIndex < 3; inputIndex++) {
			inputColor[inputIndex] = br.readLine();
		}
		
		// 첫번째와 두번째는 값, 세번쨰는 곱(각 인덱스에 해당하는 수의 0의 개수)
		int firstNum = -1, secondNum = -1, multiplier = -1;
		for (int index = 0; index < colors.length; index++) {
			if (colors[index].equals(inputColor[0])) {
				firstNum = index;
			}
			if (colors[index].equals(inputColor[1])) {
				secondNum = index;
			}
			if (colors[index].equals(inputColor[2])) {
				multiplier = index;
			}
		}*/
		
		// System.out.println((firstNum*10L + secondNum) * (long)Math.pow(10, multiplier));
		long ans = (firstNum * 10L + secondNum);
		for (int i = 0; i < multiplier; i++) { // pow는 double return, 따라서 long으로의 강제 형변환 
			// long에서 직접 계산 
			ans *= 10;
		}
		System.out.println(ans);
		
		// 색깔을 일치하는 것을 찾는데에 둘 다 O(N)이라는 것을 알 수 있고,
		// 각 인덱스에 맞는 숫자를 찾으면 그것을 계산 하는 과정에서 Math.pow가 double을 return함으로써 형변환에 의한 시간만 더 소요되는 듯?
	}
}
