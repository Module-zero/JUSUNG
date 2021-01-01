import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Q16638 {
    static int n;
    static char[] exp;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        exp = br.readLine().toCharArray();
        go(0, "", "");
        System.out.print(max);
    }

    static void go(int index, String op, String s) {

        if (index >= n) {
            s = postfix(s);
            int res = calPostFix(s);
            max = Math.max(max, res);
            return;
        }

        s += op;
        String next_op = index+1 < n ? String.valueOf(exp[index+1]) : "";
        // 괄호를 추가하지 않음
        go(index+2, next_op, s+exp[index]);

        if (index <= n-2) {
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            for (int i = index; i < index+3; i++) {
                sb.append(exp[i]);
            }
            sb.append(")");
            next_op = (index >= n-3) ? "" : String.valueOf(exp[index+3]);
            // 괄호를 추가함
            go(index+4, next_op, s+sb);
        }
    }

    // 문자열을 postfix 로 변환
    static String postfix(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        char[] exp = s.toCharArray();
        for (char c : exp) {
            if ((c >= '0' && c <= '9')) {
                sb.append(c);
            } else if (c == ')') {
                while (!stack.isEmpty()) {
                    char pop = stack.pop();
                    if (pop == '(') {
                        break;
                    }
                    sb.append(pop);
                }
            } else if (c == '+' || c == '-') {
                while (!stack.isEmpty()) {
                    char peek = stack.peek();
                    if (peek == '*' || peek == '+' || peek == '-') {
                        sb.append(stack.pop());
                    } else {
                        break;
                    }
                }
                stack.push(c);
            } else {
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    // postfix 식의 결과값을 리턴
    static int calPostFix(String s) {
        Stack<Integer> stack = new Stack<>();
        char[] exp = s.toCharArray();
        for (char c : exp) {
            if (c >= '0' && c <= '9') {
                stack.push(c-'0');
            } else {
                int n2 = stack.pop();
                int n1 = stack.pop();
                if (c == '*') {
                    stack.push(n1*n2);
                } else if (c == '+') {
                    stack.push(n1+n2);
                } else {
                    stack.push(n1-n2);
                }
            }
        }
        return stack.pop();
    }
}
