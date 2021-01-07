import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q16985 {
    static int[][][] cube = new int[5][5][5];
    static int[][][] src = new int[5][][];
    static boolean[] check = new boolean[5];
    static int[][] delta = {{1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}};
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int k = 0; k < 5; k++) {
                    cube[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        System.arraycopy(cube, 0, src, 0, 5);

        go(0);

        if (min == Integer.MAX_VALUE) {
            System.out.print(-1);
        } else {
            System.out.print(min);
        }
    }

    static void go(int index) {

        if (index == 5) {
            go2(0);
            return;
        }

        for (int i = 0; i < 5; i++) {
            if (!check[i]) {
                check[i] = true;
                cube[index] = src[i];
                go(index+1);
                cube[index] = src[index];
                check[i] = false;
            }
        }
    }

    static void go2(int index) {

        if (index == 5) {
            if (cube[0][0][0] == 0 || cube[4][4][4] == 0) {
                return;
            }

            LinkedList<int[]> q = new LinkedList<>();
            int[][][] visited = new int[5][5][5];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 5; k++) {
                        visited[i][j][k] = -1;
                    }
                }
            }

            visited[0][0][0] = 0;
            q.add(new int[]{0, 0, 0});
            while (!q.isEmpty()) {
                int[] p = q.poll();
                int h = p[0], x = p[1], y = p[2];

                if (h == 4 && x == 4 && y == 4) {
                    min = Math.min(min, visited[h][x][y]);
                    return;
                }

                for (int i = 0; i < 6; i++) {
                    int nh = h + delta[i][0];
                    int nx = x + delta[i][1];
                    int ny = y + delta[i][2];
                    if (nh >= 0 && nx >= 0 && ny >= 0 && nh < 5 && nx < 5 && ny < 5) {
                        if (visited[nh][nx][ny] == -1 && cube[nh][nx][ny] == 1) {
                            visited[nh][nx][ny] = visited[h][x][y] + 1;
                            q.add(new int[]{nh, nx, ny});
                        }
                    }
                }
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            go2(index+1);
            rotate(cube[index]);
        }
    }

    static void rotate(int[][] facet) {
        int[][] tmp_facet = new int[5][5];
        copyFacet(facet, tmp_facet);
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                facet[row][4-col] = tmp_facet[col][row];
            }
        }
    }

    static void copyFacet(int[][] src, int[][] dst) {
        for (int i = 0; i < 5; i++) {
            System.arraycopy(src[i], 0, dst[i], 0, 5);
        }
    }
}
