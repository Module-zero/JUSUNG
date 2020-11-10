import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1062 {

    static int N;
    static int K;
    static boolean[] check;
    static String[] words;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        check = new boolean[26];
        words = new String[N];

        if (K < 5) {
            System.out.print(0);
            return;
        }

        if (K == 26) {
            System.out.print(N);
            return;
        }

        check['a'-'a'] = true;
        check['c'-'a'] = true;
        check['i'-'a'] = true;
        check['n'-'a'] = true;
        check['t'-'a'] = true;

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            s = s.replace("a", "");
            s = s.replace("c", "");
            s = s.replace("i", "");
            s = s.replace("n", "");
            s = s.replace("t", "");
            words[i] = s;
        }

        go(0, 0);
        System.out.print(max);
    }

    static void go(int depth, int start) {

        // 사용가능한 알파벳이 check 배열에 체크되어있음
        if (depth == K-5) {
            int count = 0;
            for (int i = 0; i < words.length; i++) {
                boolean flag = true;
                for (int j = 0; j < words[i].length(); j++) {
                    if (check[words[i].charAt(j)-'a'] == false) {
                        flag = false;
                    }
                }
                if (flag) { count++; }
            }
            max = Math.max(max, count);
            return;
        }

        for (int i = start; i < 26; i++) {
            if (check[i]) { continue; }
            check[i] = true;
            go(depth + 1, i + 1);
            check[i] = false;
        }
    }
}
