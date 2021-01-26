import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q10942 {
    static int n, m;
    static int[] a = new int[2021];
    static int[][] d = new int[2021][2021];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = (go(x, y) == 1 ? 1 : 0);
            sb.append(k).append("\n");
        }

        System.out.print(sb);
    }

    // 0 : 방문 안함
    // -1 : 방문 했는데 팰린드롬 아님
    // 1 : 방문 했는데 팰린드롬 맞음
    static int go(int x, int y) {

        if (x == y) {
           return d[x][y] = 1;
        }

        if (d[x][y] != 0) {
            return d[x][y];
        }

        if (x + 1 == y) {
            return d[x][y] = (a[x] == a[y] ? 1 : -1);
        }

        d[x][y] = -1;
        if (go(x+1, y-1) == 1) {
            if (a[x] == a[y]) {
                d[x][y] = 1;
            }
        }

        return d[x][y];
    }
}
