import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Q1918 {

    // 정답인 후위 연산식을 저장할 빌더
    static StringBuilder answer = new StringBuilder();

    public static void func(StringBuilder exp) {

        // 연산자를 저장할 스택
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) == '+' || exp.charAt(i) == '-' || exp.charAt(i) == '*' || exp.charAt(i) == '/') {
                if (!stack.isEmpty()) {
                    if (exp.charAt(i) == '*' || exp.charAt(i) == '/') {
                        while (!stack.isEmpty()) {
                            // 자신('*', '/')보다 낮은 우선순위의 연산자가 나오면 pop 을 멈춤
                            if (stack.peek() == '+' || stack.peek() == '-') {
                                break;
                            }
                            answer.append(stack.pop());
                        }
                    }
                    else {
                        // 자신('+', '-')보다 낮은 우선순위의 연산자가 없으므로 모두 pop
                        while (!stack.isEmpty()) {
                            answer.append(stack.pop());
                        }
                    }
                }
                // 연산자는 일단 무조건 스택에 들어가긴함
                stack.push(exp.charAt(i));
            }
            else if (exp.charAt(i) == '(') {
                StringBuilder subExp = new StringBuilder(); // 괄호안에 있는 표현식을 저장할 빌더
                Stack<Character> tmpStack = new Stack<>();  // 괄호안에 있는 표현식의 연산자를 저장할 스택

                int cur = i + 1;
                tmpStack.push('(');

                // 괄호안의 표현식을 추출
                while (true) {
                    if (exp.charAt(cur) == '(') {
                        tmpStack.push('(');
                    }
                    if (exp.charAt(cur) == ')') {
                        tmpStack.pop();
                    }
                    if (tmpStack.isEmpty()) {
                        break;
                    }
                    subExp.append(exp.charAt(cur++));
                }
                func(subExp);
                // 처리된 부분의 인덱스는 넘어감
                i += 1 + subExp.length();
            }
            else if (exp.charAt(i) >= 'A' && exp.charAt(i) <= 'Z') {
                answer.append(exp.charAt(i));
            }
        }
        while (!stack.isEmpty()) {
            answer.append(stack.pop());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder exp = new StringBuilder(br.readLine());
        func(exp);
        System.out.println(answer);
    }
}
