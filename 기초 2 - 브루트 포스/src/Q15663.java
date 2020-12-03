import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q15663 {

    static int[] answer;
    static boolean[] check;
    static int[] nums;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        answer = new int[m];
        check = new boolean[n];
        nums = new int[n];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        go(0, n, m);
        System.out.print(sb);
    }

    public static void go(int index, int n, int m) {

        if (index == m) {
            for (int i = 0; i < m; i++) {
                sb.append(answer[i] + " ");
            } sb.append("\n");
            return;
        }

        // 중복되는 수열은 출력하지 않기위해 재귀함수마다 별도의 배열을 생성
        // 하나의 함수 호출이 정답 배열의 하나의 인덱스를 담당한다는 사실이 중요함
        boolean[] used = new boolean[10001];

        for (int i = 0; i < n; i++) {

            if (check[i] == true || used[nums[i]] == true) {
                continue;
            }

            answer[index] = nums[i];
            used[nums[i]] = true;
            check[i] = true;
            go(index+1, n, m);
            check[i] = false;
        }
    }
}
