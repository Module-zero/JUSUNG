import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Q3111 {
    static int n, m;
    static String A, T;
    static boolean[] used = new boolean[300001];
    static Stack<int[]> stack = new Stack<>();
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine();
        T = br.readLine();
        n = T.length();
        m = A.length();

        left();

        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                answer.append(T.charAt(i));
            }
        }
        System.out.print(answer);
    }

    static boolean left() {
        for (int i = 0; i < n; i++) {
            if (used[i]) {
                continue;
            }
            char cur = T.charAt(i);
            if (cur == A.charAt(0)) {
                stack.push(new int[]{i, 0});
            } else {
                if (!stack.isEmpty()) {
                    int nIdx = stack.peek()[1] + 1;
                    if (cur == A.charAt(nIdx)) {
                        System.out.println(nIdx);
                        stack.push(new int[]{i, nIdx});
                        if (nIdx == m - 1) {
                            System.out.println("c");
                            for (int j = 0; j < m; j++) {
                                int[] pair = stack.pop();
                                int idx = pair[0];
                                used[idx] = true;
                            }
                            while (!stack.isEmpty()) {
                                stack.pop();
                            }
                            // A를 찾음
                            return true;
                        }
                    }
                }
            }
        }
        // A를 못찾음
        return false;
    }
}
