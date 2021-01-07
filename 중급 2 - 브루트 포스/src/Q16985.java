import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q16985 {
    static int[][][] cube = new int[5][5][5];
    static boolean[] check = new boolean[5];
    static int[] order = new int[5];
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

        go(0);

        if (min == Integer.MAX_VALUE) {
            System.out.print(-1);
        } else {
            System.out.print(min);
        }
    }

    static void go(int index) {

        if (index == 5) {
            int[][][] tmp_cube = new int[5][5][5];
            copyCube(cube, tmp_cube);
            go2(0, tmp_cube);
            return;
        }

        for (int i = 0; i < 5; i++) {
            if (!check[i]) {
                check[i] = true;
                order[index] = i;
                go(index+1);
                check[i] = false;
            }
        }
    }

    static void go2(int index, int[][][] tmp_cube) {

        if (index == 5) {
            if (tmp_cube[0][0][0] == 0 || tmp_cube[4][4][4] == 0) {
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
                        if (visited[nh][nx][ny] == -1 && tmp_cube[nh][nx][ny] == 1) {
                            visited[nh][nx][ny] = visited[h][x][y] + 1;
                            q.add(new int[]{nh, nx, ny});
                        }
                    }
                }
            }
            return;
        }

        int[][] facet = tmp_cube[index];
        go2(index+1, tmp_cube);

        rotate(facet, 1);
        go2(index+1, tmp_cube);
        rotate(facet, -1);

        rotate(facet, 2);
        go2(index+1, tmp_cube);
        rotate(facet, -2);

        rotate(facet, 3);
        go2(index+1, tmp_cube);
        rotate(facet, -3);
    }

    static void rotate(int[][] facet, int clock_count) {

        int count = Math.abs(clock_count);
        int[][] tmp_facet = new int[5][5];
        copyFacet(facet, tmp_facet);

        for (int i = 0; i < count; i++) {
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    int new_row = clock_count < 0 ? 4-row : row;
                    int new_col = clock_count > 0 ? 4-col : col;
                    facet[new_row][new_col] = tmp_facet[col][row];
                }
            }
            copyFacet(facet, tmp_facet);
        }
    }

    static void copyCube(int[][][] src, int[][][] dst) {
        for (int i = 0; i < 5; i++) {
           for (int j = 0; j < 5; j++) {
               System.arraycopy(src[order[i]][j], 0, dst[i][j], 0, 5);
           }
        }
    }

    static void copyFacet(int[][] src, int[][] dst) {
        for (int i = 0; i < 5; i++) {
            System.arraycopy(src[i], 0, dst[i], 0, 5);
        }
    }
}
