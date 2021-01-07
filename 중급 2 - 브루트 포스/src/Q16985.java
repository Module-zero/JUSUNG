import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q16985 {
    static int[][][] cube = new int[5][5][5];
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
        System.out.print(min);
    }

    static boolean[] check = new boolean[5];
    static int[] order = new int[5];
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

    static int[][] delta = {{0, -1},{-1, 0},{0, 1},{1, 0}};
    static void go2(int index, int[][][] tmp_cube) {

        if (index == 5) {
            // 만들어진 큐브로 이동회수만 세어보면됨
            int cnt = getCount(tmp_cube);
            /*
            if (tmp_cube[0][0][0] == 0) {
                int[][] visited = new int[5][5];
                LinkedList<int[]> q = new LinkedList<>();
                visited[0][0] = 1;
                q.add(new int[]{0, 0});
                while (!q.isEmpty()) {
                    int[] p = q.poll();
                    int x = p[0], y = p[1];

                    // 아래칸이 비어있으면 이동
                    if (tmp_cube[1][x][y] == 0) {
                        // go3(1, x, y, visited[x][y]-1, tmp_cube);
                    }

                    for (int i = 0; i < 4; i++) {
                        int nx = x + delta[i][0];
                        int ny = y + delta[i][1];
                        if (nx >= 0 && ny >= 0 && nx < 5 && ny < 5) {
                            if (visited[nx][ny] == 0 && tmp_cube[0][nx][ny] == 0) {
                                visited[nx][ny] = visited[x][y]+1;
                                q.add(new int[]{nx, ny});
                            }
                        }
                    }
                }
            }
             */
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

    static int getCount(int[][][] tmp_cube) {

        if (tmp_cube[0][0][0] == 0) {
            return -1;
        }

        go3(0, 0, 0, 0, tmp_cube);
    }

    static void go3(int index, int startX, int startY, int cnt, int[][][] tmp_cube) {

        int[][] facet = tmp_cube[index];

        int[][] visited = new int[5][5];
        LinkedList<int[]> q = new LinkedList<>();
        visited[startX][startY] = cnt+1;
        q.add(new int[]{startX, startY});
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int x = p[0], y = p[1];

            if (index == 4 && x == 4 && y == 4) {
                min = Math.min(min, visited[x][y]-1);
                return;
            }

            // 아래칸이 비어있으면 이동
            if (index < 4) {
                if (tmp_cube[index+1][x][y] == 0) {
                    go3(index+1, x, y, visited[x][y]-1, tmp_cube);
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + delta[i][0];
                int ny = y + delta[i][1];
                if (nx >= 0 && ny >= 0 && nx < 5 && ny < 5) {
                    if (visited[nx][ny] == 0 && facet[nx][ny] == 0) {
                        visited[nx][ny] = visited[x][y]+1;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
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

    static void printCube(int[][][] arr) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    System.out.print(arr[i][j][k]+" ");
                } System.out.println();
            }
        } System.out.println();
    }
}
