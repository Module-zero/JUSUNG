import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Q1918 {

    static StringBuilder answer = null;

    public static void func(StringBuilder exp) {

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) == '+' || exp.charAt(i) == '-' || exp.charAt(i) == '*' || exp.charAt(i) == '/') {
                if (stack.isEmpty()) {
                    stack.push(exp.charAt(i));
                }
                else {
                    if (exp.charAt(i) == '*' || exp.charAt(i) == '/') {
                        while (!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/')) {
                            answer.append(stack.pop());
                        }
                        stack.push(exp.charAt(i));
                    }
                    else {
                        while (!stack.isEmpty()) {
                            answer.append(stack.pop());
                        }
                        stack.push(exp.charAt(i));
                    }
                }
            }
            else if (exp.charAt(i) == '(') {
                StringBuilder subExp = new StringBuilder();
                int cur = i + 1;

                Stack<Character> tmpStack = new Stack<>();
                tmpStack.push('(');
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
        answer = new StringBuilder();
        func(exp);
        System.out.println(answer);
    }
}
