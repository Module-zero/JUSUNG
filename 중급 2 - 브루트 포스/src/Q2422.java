import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2422 {
    static int n, m;
    static int cnt = 0;
    static int[] selected = new int[3];
    static boolean[] visited = new boolean[201];
    static boolean[][] check = new boolean[201][201];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            check[v1][v2] = check[v2][v1] = true;
        }

        go(0, 1);
        System.out.print(cnt);
    }

    static void go(int index, int start) {

        if (index == 3) {
            for (int i = 0; i < 3; i++) {
                int idx1 = selected[(i+1)%3];
                int idx2 = selected[(i+2)%3];
                if (check[idx1][idx2]) {
                    return;
                }
            }
            cnt++;
            return;
        }

        for (int i = start; i <= n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selected[index] = i;
                go(index+1, i+1);
                visited[i] = false;
            }
        }
    }
}
