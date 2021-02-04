import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q12869 {
    static int n;
    static int min = Integer.MAX_VALUE;
    static int[] life = new int[4];
    static int[][][] d = new int[61][61][61];
    static int[][] a = {{1, 3, 9}, {1, 9, 3}, {3, 1, 9}, {3, 9, 1}, {9, 1, 3}, {9, 3, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            life[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 61; i++) {
            for (int j = 0; j < 61; j++) {
                for (int k = 0; k < 61; k++) {
                    d[i][j][k] = -1;
                }
            }
        }

        go(life[1], life[2], life[3]);
        System.out.print(min);
    }

    static void go(int x, int y, int z) {

        System.out.println(x+","+y+","+z);

        if (x == 0 && y == 0 && z == 0) {
            min = Math.min(min, d[x][y][z]);
            return;
        }

        if (d[x][y][z] != -1) {
            return;
        }

        for (int i = 0; i < 6; i++) {
            int nx = Math.max(x - a[i][0], 0);
            int ny = Math.max(y - a[i][1], 0);
            int nz = Math.max(z - a[i][2], 0);

            if (d[nx][ny][nz] == -1) {
                d[nx][ny][nz] = d[x][y][z] + 1;
            } else {
                d[nx][ny][nz] = Math.min(d[nx][ny][nz], d[x][y][z] + 1);
            }

            go(nx, ny, nz);
        }
    }
}
