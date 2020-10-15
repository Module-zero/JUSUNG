import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 고수의 코드와 내 코드의 비교점
// 1. 고수의 코드는 재귀를 사용하지 않았다. 내가 재귀를 사용한 이유는 괄호안의 연산식 처리 때문이었다.
// 재귀를 사용한 단점은 그것으로 인해 인덱스 계산을 부자연스럽게 할 수 밖에 없었다는 점과 속도가 느려진다는 점이다.
// 스택을 제대로 사용하려면 괄호까지 스택에 넣었어야했다. 열린 괄호가 나오는대로 무조건 스택에 넣었다면
// 닫힌 괄호를 만나면 스택에서 열린 괄호가 나올때까지 꺼내기만하면 되는 것이었다. 스택의 성질을 따르면
// 어차피 가장 먼저 만나는 열린괄호가 닫힌 괄호와 짝일 수 밖에 없을 것이기 때문이다
// 괄호 처리를 위한 재귀 사용은 실패에 가까운것 같다
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
