import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 0, 1 로 이루어진 수열을 구하는 문제

// 재귀를 사용한 브루트 포스로 수열을 구함

// 수 하나하나가 가로 혹은 세로에 반드시 포함된다는 아이디어를 떠올리지 못함
// 인덱스를 가로, 세로로 탐색하는 것을 생각해내지 못함

public class Q14391 {

    static int N;
    static int M;
    static int[][] paper;
    static int[] answer;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine(),  " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        paper = new int[N][M];
        answer = new int[N*M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                paper[i][j] = s.charAt(j) - '0';
            }
        }

        go(0);
        System.out.print(max);
    }

    static void go(int index) {

        if (index == N*M) {
            solve();
            return;
        }

        for (int i = 0; i <= 1; i++) {
            answer[index] = i;
            go(index + 1);
        }
    }

    static void solve() {
        int allSum = 0;
        int sum = 0;

        // 1 은 가로
        // 인덱스를 가로로 탐색
        for (int i = 0; i < N; i++) {
            sum = 0;
            for (int j = 0; j < M; j++) {
                if (answer[i*M + j] == 1) {
                    // 한자리수를 이어붙여 여러자리수의 수로 바꾸는 방법
                    sum = sum*10 + paper[i][j];
                }
                else {
                    allSum += sum;
                    sum = 0;
                }
            }
            allSum += sum;
        }

        // 0 은 세로
        // 인덱스를 세로로 탐색
        for (int i = 0; i < M; i++) {
            sum = 0;
            for (int j = 0; j < N; j++) {
                if (answer[j*M + i] == 0) {
                    sum = sum*10 + paper[j][i];
                }
                else {
                    allSum += sum;
                    sum = 0;
                }
            }
            allSum += sum;
        }

        if (max < allSum) {
            max = allSum;
        }
    }
}
