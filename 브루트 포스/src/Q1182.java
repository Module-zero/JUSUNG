import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 비트마스크를 활용한 집합 표현

// 결국 수열에서 추출 가능한 모든 부분수열을 구하는 문제임
// 여러가지 방법으로 해결가능하나 해당 문제에서 모든 수열은 곧 모든 부분집합임
// 따라서 비트마스크를 활용하여 부분 집합을 통해 해결할 수 있음
// 비트마스크를 활용할 때는 집합에 있는 수들을 그대로 비트마스크로 표현하기보다는
// 주로 {1, 2, 3, ... , n} 인 집합을 사용하여 해당 집합의 원소들을 인덱스로 활용하는 경우가 많음

// 문제 이해력이 아직 부족한 것 같음

public class Q1182 {

    static int N;
    static int S;
    static boolean[] check;
    static int[] list;
    static int count = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        check = new boolean[N];
        list = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        // 만약 N=5 라면 {0, 1, 2, 3, 4} 라는 임의의 집합을 가정
        // 집합의 원소들은 배열의 인덱스가 됨
        // i -> 1 ~ 31(00001 ~ 11111)
        // 즉 i 라는 수 자체가 곧 부분집합이됨
        for (int i = 1; i < (1<<N); i++) {
            int sum = 0;
            // k -> 0 ~ N-1(0~4)
            // k(배열의 인덱스)가 부분집합에 속해있는지만 체크하면됨
            for (int k = 0; k < N; k++) {
                if ((i & (1<<k)) != 0) {
                    sum += list[k];
                }
            }
            if (sum == S) {
                count++;
            }
        }
        System.out.print(count);
    }
}
