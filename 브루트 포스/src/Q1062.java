import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// K개의 글자를 가르칠 때, 학생들이 읽을 수 있는 단어 개수의 최댓값을 출력
// -> '최대값' 을 구해야 하므로 '모든 경우' 를 돌며 최대값을 갱신해야함
// -> 그렇다면 '모든 경우' 는 무엇인가?
// -> 기본적으로 'a, c, i, n, t' 5개의 기본 글자는 반드시 포함되어야 단어를 읽을 수 있음.
// -> 글자는 k 개가 주어지므로 k-5 개의 글자를 21 개의 글자에서 중복없이 뽑은 뒤, 5개의 기본 글자와 합치는 것이 모든 경우임

// 재귀를 사용하여 dfs 로 모든 경우를 탐색

// 재귀를 사용할 때는 종료조건의 설정이 아주 중요함
// 나는 재귀가 끝나면 추출된 k-5 개의 글자를 기본문자와 어떻게 적절히 합칠지를 생각해내지 못함
// 그냥 check 배열에 체크해두면 되는 것이였음
// 결국 check 배열에 k 개의 글자를 모두 체크한 뒤 주어진 단어들을 체크함

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

        // K개의 사용가능한 알파벳이 check 배열에 체크되어있음
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
