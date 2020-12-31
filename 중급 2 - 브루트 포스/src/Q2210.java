import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Q2210 {
    static int[][] arr = new int[5][5];
    static TreeSet<String> set = new TreeSet<>();
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 5; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                dfs(i, j, 0, String.valueOf(arr[i][j]));
            }
        }

        System.out.print(set.size());
    }

    static void dfs(int x, int y, int depth, String s) {

        if (depth == 5) {
            set.add(s);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < 5 && ny < 5) {
                dfs(nx, ny, depth+1, s+arr[nx][ny]);
            }
        }
    }
}
