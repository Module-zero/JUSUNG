import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 해당 문제에서 나는 계속 시간초과가 나왔다. 그 이유는 무엇인가?
// 바로 불필요한 경우에 push, pop 을 진행했기 때문이다
// 문자가 태그안에 있는지 없는지 구분하기위해서 나는 '<'를 스택에 넣고
// '<'가 스택안에 있으면 문자가 태그안에 있다고 구분하였다.
// 그러나 태그안에 있는지 없는지는 플래그변수로도 충분히 구분이 가능하다.
// 앞으로도 불필요한 연산은 최대한 줄이면서 코드를 짜보자
public class Q17413 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder str = new StringBuilder(br.readLine());
        str.append('\n');

        StringBuilder answer = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        boolean tag = false;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '<') {
                while (!stack.empty()) {
                    answer.append(stack.pop());
                }
                answer.append('<');
                tag = true;
            }
            else {
                if (tag) {
                    answer.append(ch);
                    if (ch == '>') {
                        tag = false;
                    }
                }
                else {
                    if (ch == ' ' || ch == '\n') {
                        while (!stack.empty()) {
                            answer.append(stack.pop());
                        } answer.append(ch);
                    }
                    else {
                        stack.push(ch);
                    }
                }
            }
        }
        System.out.print(answer);
    }
}
