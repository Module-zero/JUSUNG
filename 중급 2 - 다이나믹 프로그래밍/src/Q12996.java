import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q12996 {
    static int s, a, b, c;
    static int mod = 1000000007;
    static long[][][][] d = new long[51][51][51][51];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        s = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 51; i++) {
            for (int j = 0; j < 51; j++) {
                for (int k = 0; k < 51; k++) {
                    Arrays.fill(d[i][j][k], -1);
                }
            }
        }

        System.out.print(go(s, a, b, c));
    }

    static long go(int s, int x, int y, int z) {

        if (s == 0) {
            if (x == 0 && y == 0 && z == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        if (x < 0 || y < 0 || z < 0) {
            return 0;
        }

        if (d[s][x][y][z] != -1) {
            return d[s][x][y][z];
        }

        long answer = 0;
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 1; j++) {
                for (int k = 0; k <= 1; k++) {
                    if (i + j + k > 0) {
                        answer += go(s - 1, x - i, y - j, z - k);
                    }
                }
            }
        }

        return (d[s][x][y][z] = answer % mod);
    }
}