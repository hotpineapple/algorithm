import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class SWEA_1224_계산기3 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int len = 0;
		
		for (int tc = 1; tc <= 10; tc++) {
			len = Integer.parseInt(br.readLine());
			char[] infix = br.readLine().toCharArray();
			
      // 1. 후위표기식으로 변환
			Stack<Character> ops = new Stack<>();
			StringBuilder postfix = new StringBuilder();
			for (int i = 0; i < len; i++) {
				if(infix[i]>='0' && infix[i]<='9') { // 숫자는 특별한 처리 없이 바로 연산식에 이용
					postfix.append(infix[i]);
					continue;
				}
				// 여는 괄호 : 그냥 넣음
				// 닫는 괄호 : 여는괄호 나올 때까지 pop, 여는괄호는 버림
				// 연산자 : 스택의 top이 자신보다 우선순위 높은 연산자이면 pop 하고 자신은 push, 아니면 그냥 push
				if(infix[i] == '(') {
					ops.push(infix[i]);
				}else if(infix[i] == ')') {
					while(true) {
						char op = ops.pop();
						if(op == '(') break;
						postfix.append(op);
					}
				}else {
					if(infix[i] == '*' || infix[i] == '/') {
						ops.push(infix[i]);
					}else if (infix[i] == '+' || infix[i] == '-') {
						while(true) {
							char op = ops.peek();	
							if(op == '+' || op == '-' || op == '(') break;
								
							postfix.append(ops.pop());
						}	
						ops.push(infix[i]);
					}
				}
			}
			while(!ops.isEmpty()) postfix.append(ops.pop());
//			System.out.println(postfix.toString());
			
      // 2. 계산
			Stack<Integer> nums = new Stack<>();
			char[] chPostfix = postfix.toString().toCharArray();
			for (int i = 0, size = chPostfix.length; i < size; i++) {
				if(chPostfix[i]>='0' && chPostfix[i]<='9') { // 숫자는 스택에 넣음
					nums.push(chPostfix[i]-'0');
					continue;
				}
				
				// 연산자는 스택의 [두번째 top ? 첫번째 top] 형식에 맞게 연산 후 push
				int num2 = nums.pop();
				int num1 = nums.pop();
				
				switch(chPostfix[i]) {
				case '+' :
					nums.push(num1+num2);
					break;
				case '-' :
					nums.push(num1-num2);
					break;
				case '*' :
					nums.push(num1*num2);
					break;
				case '/' :
					nums.push(num1/num2);
					break;
				
				}
				
			}
			sb.append("#").append(tc).append(" ").append(nums.pop()).append("\n");
			
		}
		System.out.println(sb.toString());
	}

}
