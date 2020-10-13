import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 해당 문제에 접근하는 방식 중 가장 중요한 포인트는 결국 스택에 있는 모든 수를 꺼내서 수열로 나열한다는 것이다.
// 만약 1~n 까지의 수를 수열로 나열한다면 스택에 반드시 push 도 n번, pop 도 n번 하게된다.
// 따라서 전체적으로는 우선 루프를 돌면서 push 를 n번 진행하고 그 과정에서 필요시 pop 을 하면된다.

// 해당 문제에서 내가 부족했던 점
// 1. 너무 복잡한 인덱스 계산을 사용하려고 했음(자바의 컬렉션을 잘 활용하면 인덱스 계산을 줄일 수 있음)
// 2. 불필요하게 경우의 수를 나누어 생각하려고 했음(이런 명료한 자료구조를 사용할 때는 경우의 수 보다는 자료구조의 성질을 제대로 활용하는 것이 중요한 듯.
// 애초에 좀 복잡하게 경우의 수가 나뉘는 경우는 잘못된 접근인 경우가 많음)
// 3. StringBuilder 를 좀 더 잘 활용하면 좋을듯

public class Q1874 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }

        for (int i = 1; i <= n; i++) {
            stack.push(i);
            sb.append("+\n");

            while (!stack.empty() && stack.peek().equals(list.get(0))) {
                stack.pop();
                sb.append("-\n");
                list.remove(0);
            }
        }

        if (!stack.empty()) {
            sb.setLength(0);
            sb.append("NO\n");
        }
        System.out.println(sb);
    }
}
