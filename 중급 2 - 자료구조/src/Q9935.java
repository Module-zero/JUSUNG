import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Q9935 {
    static Stack<Pair> stack = new Stack<>();
    static boolean[] erased = new boolean[1000001];
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String p = br.readLine();
        int n = s.length();
        int m = p.length();

        if (m == 1) {
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == p.charAt(0)) {
                    erased[i] = true;
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                char cur = s.charAt(i);
                if (cur == p.charAt(0)) {
                    stack.add(new Pair(i, 0));
                } else {
                    if (!stack.isEmpty()) {
                        int nIdx = stack.peek().pIdx + 1;
                        if (cur == p.charAt(nIdx)) {
                            stack.add(new Pair(i, nIdx));
                            if (nIdx == m-1) {
                                for (int j = 0; j < m; j++) {
                                    int idx = stack.pop().sIdx;
                                    erased[idx] = true;
                                }
                            }
                        } else {
                            while (!stack.isEmpty()) {
                                stack.pop();
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (!erased[i]) {
                answer.append(s.charAt(i));
            }
        }

        if (answer.toString().equals("")) {
            System.out.print("FRULA");
        } else {
            System.out.print(answer);
        }
    }

    static class Pair {
        int sIdx, pIdx;
        public Pair(int sIdx, int pIdx) {
            this.sIdx = sIdx;
            this.pIdx = pIdx;
        }
    }
}
