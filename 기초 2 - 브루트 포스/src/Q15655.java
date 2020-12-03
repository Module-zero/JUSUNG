import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q15655 {

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

        go(0, n, m, 0);
        System.out.print(sb);
    }

    public static void go(int index, int n, int m, int start) {

        if (index == m) {
            for (int i = 0; i < m; i++) {
                sb.append(answer[i] + " ");
            } sb.append("\n");
            return;
        }

        for (int i = start; i < n; i++) {
            if (check[i] == true) {
                continue;
            }
            answer[index] = nums[i];
            check[i] = true;
            go(index+1, n, m, i+1);
            check[i] = false;
        }
    }
}
