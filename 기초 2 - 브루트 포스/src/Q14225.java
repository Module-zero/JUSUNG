import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 수열이 주어졌을 때 부분수열, 즉 부분집합을 구하는 문제이다

// 부분집합을 구하는 문제이므로 비트마스크를 사용한다

// 집합에 직접 값을 넣지 않고 인덱스 값을 넣는다는 사실 중요

public class Q14225 {

    static int N;
    static int[] S;
    static boolean[] check = new boolean[2000001];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < (1<<N); i++) {
            int sum = 0;
            for (int k = 0; k < N; k++) {
                // 부분집합에 k 가 존재함
                if ((i & (1<<k)) != 0) {
                    sum += S[k];
                }
            }
            check[sum] = true;
        }

        for (int i = 1; i < check.length; i++) {
            if (check[i] == false) {
                System.out.print(i);
                break;
            }
        }
    }
}
