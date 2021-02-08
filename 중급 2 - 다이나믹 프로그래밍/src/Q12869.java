import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q12869 {
    static int n;
    static int[] l = new int[3];
    static int[][][] d = new int[61][61][61];
    static int[][] a = {{1, 3, 9}, {1, 9, 3}, {3, 1, 9}, {3, 9, 1}, {9, 1, 3}, {9, 3, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            l[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 61; i++) {
            for (int j = 0; j < 61; j++) {
                for (int k = 0; k < 61; k++) {
                    d[i][j][k] = -1;
                }
            }
        }

        d[l[0]][l[1]][l[2]] = 0;
        go(l[0], l[1], l[2]);
        System.out.print(d[0][0][0]);
    }

    static void go(int x, int y, int z) {

        if (x == 0 && y == 0 && z == 0) {
            return;
        }

        for (int i = 0; i < 6; i++) {
            int nx = Math.max(x - a[i][0], 0);
            int ny = Math.max(y - a[i][1], 0);
            int nz = Math.max(z - a[i][2], 0);

            if (d[nx][ny][nz] != -1) {
                if (d[nx][ny][nz] <= d[x][y][z] + 1) {
                    continue;
                }
            }

            d[nx][ny][nz] = d[x][y][z] + 1;
            go(nx, ny, nz);
        }
    }
}