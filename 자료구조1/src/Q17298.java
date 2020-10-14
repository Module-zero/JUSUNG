import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 정말 아무리 풀어도 시간초과가 나서 너무 힘든 문제였다...
// 출력할 때 StringBuilder 꼭 쓰자... 무조건 쓰자....
// 하긴 100만개의 숫자를 하나하나 찍으면 당연히 시간초과 날만 하지...

// 이 문제의 접근 방법은
// 1. 스택을 사용해야하고
// 2. 스택에 값이 아닌 인덱스를 저장하는 방식을 사용해야함(값은 중복될 수 있으므로)
public class Q17298 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());

        int []A = new int[count];
        int []NGE = new int[count];
        Stack<Integer> stack = new Stack<>();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < count; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < count; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
            }
            while (!stack.isEmpty() && A[stack.peek()] < A[i]) {
                NGE[stack.pop()] = A[i];
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            NGE[stack.pop()] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < NGE.length; i++) {
            sb.append(NGE[i] + " ");
        }
        System.out.println(sb);
    }
}
