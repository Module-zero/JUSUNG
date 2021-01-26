import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q10942 {
    static int n, m;
    static int[] arr = new int[2021];
    static int[][] d = new int[2021][2021];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 2021; i++) {
            for (int j = 0; j < 2021; j++) {
                d[i][j] = i == j ? 1 : -1;
            }
        }

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            System.out.println(go(x, y));
        }
    }

    static int go(int x, int y) {

        if (d[x][y] != -1) {
            return d[x][y];
        }

        if (x + 1 == y) {
            return d[x][y] = (arr[x] == arr[y] ? 1 : 0);
        }

        d[x][y] = 0;
        if (go(x+1, y-1) == 1) {
            if (arr[x] == arr[y]) {
                d[x][y] = 1;
            }
        }

        return d[x][y];
    }
}
