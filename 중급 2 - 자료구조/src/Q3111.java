import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Q3111 {
    static int n, m;
    static String A, T;
    static boolean[] used = new boolean[300001];
    static Stack<int[]> stack1 = new Stack<>();
    static Stack<int[]> stack2 = new Stack<>();
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine();
        T = br.readLine();
        n = T.length();
        m = A.length();

        while (true) {
            if (!left()) {
                break;
            }
            if (!right()) {
                break;
            }
        }

        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                answer.append(T.charAt(i));
            }
        }
        System.out.print(answer);
    }

    static boolean left() {
        int start = 0;
        if (!stack1.isEmpty()) {
            start = stack1.peek()[0] + 1;
        }

        for (int i = start; i < n; i++) {
            if (used[i]) {
                continue;
            }
            char cur = T.charAt(i);
            if (stack1.isEmpty()) {
                if (cur == A.charAt(0)) {
                    stack1.push(new int[]{i, 0});
                }
            } else {
                int[] p = stack1.peek();
                int nIdx = p[1] + 1;
                if (cur == A.charAt(nIdx)) {
                    stack1.push(new int[]{i, nIdx});
                    if (nIdx == m - 1) {
                        for (int j = 0; j < m; j++) {
                            p = stack1.pop();
                            used[p[0]] = true;
                        }
                        // A 를 찾아서 삭제
                        return true;
                    }
                } else {
                    while (!stack1.isEmpty()) {
                        stack1.pop();
                    }
                    if (cur == A.charAt(0)) {
                        stack1.push(new int[]{i, 0});
                    }
                }
            }
        }
        // A 가 없음
        return false;
    }

    static boolean right() {
        int start = n-1;
        if (!stack2.isEmpty()) {
            start = stack2.peek()[0] - 1;
        }

        for (int i = start; i >= 0; i--) {
            if (used[i]) {
                continue;
            }
            char cur = T.charAt(i);
            if (stack2.isEmpty()) {
                if (cur == A.charAt(m-1)) {
                    System.out.println("c");
                    stack2.push(new int[]{i, m-1});
                }
            } else {
                int[] p = stack2.peek();
                int nIdx = p[1] - 1;
                if (cur == A.charAt(nIdx)) {
                    System.out.println("c1");
                    stack2.push(new int[]{i, nIdx});
                    if (nIdx == 0) {
                        for (int j = 0; j < m; j++) {
                            p = stack2.pop();
                            used[p[0]] = true;
                        }
                        // A 를 찾아서 삭제
                        return true;
                    }
                } else {
                    while (!stack2.isEmpty()) {
                        stack2.pop();
                    }
                    if (cur == A.charAt(m-1)) {
                        stack2.push(new int[]{i, m-1});
                    }
                }
            }
        }
        // A 가 없음
        return false;
    }
}
