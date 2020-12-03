import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 17298번을 풀면 아주 쉽게 풀 수 있음
public class Q17299 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());

        int[] A = new int[count];
        int[] NGE = new int[count];
        int[] F = new int[1000001];
        Stack<Integer> stack = new Stack<>();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < count; i++) {
            int num = Integer.parseInt(st.nextToken());
            A[i] = num;
            F[num]++;
        }

        int cur = 0;
        while (true) {
            if (stack.empty()) {
                stack.push(cur++);
            }
            if (cur == A.length) {
                break;
            }

            int topNum = A[stack.peek()];
            int curNum = A[cur];
            if (F[topNum] < F[curNum]) {
                NGE[stack.pop()] = A[cur];
            }
            else {
                stack.push(cur++);
            }
        }

        while (!stack.empty()) {
            NGE[stack.pop()] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < NGE.length; i++) {
            sb.append(NGE[i] + " ");
        }
        System.out.println(sb);
    }
}
