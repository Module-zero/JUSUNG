import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q4574 {

    static int[][] sudoku;
    static int M;   // 주어지는 도미노의 개수
    static boolean[][] c1;
    static boolean[][] c2;
    static boolean[][] c3;
    static boolean[][] domino;
    static int[] dx = {0, 1};
    static int[] dy = {1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = 1;
        while (true) {
            sudoku = new int[9][9];
            c1 = new boolean[9][10];
            c2 = new boolean[9][10];
            c3 = new boolean[9][10];
            domino = new boolean[10][10];
            M = Integer.parseInt(br.readLine());
            if (M == 0) { break; }

            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int U = Integer.parseInt(st.nextToken());
                String LU = st.nextToken();
                int V = Integer.parseInt(st.nextToken());
                String LV = st.nextToken();
                int ux = LU.charAt(0)-'A';
                int uy = LU.charAt(1)-'0'-1;
                int vx = LV.charAt(0)-'A';
                int vy = LV.charAt(1)-'0'-1;
                sudoku[ux][uy] = U;
                sudoku[vx][vy] = V;
                c1[ux][U] = c2[uy][U] = true;
                c1[vx][V] = c2[vy][V] = true;
                int uBoxNum = (ux/3)*3 + uy/3;
                int vBoxNum = (vx/3)*3 + vy/3;
                c3[uBoxNum][U] = c3[vBoxNum][V] = true;
                domino[U][V] = domino[V][U] = true;
            }

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 1; i <= 9; i++) {
                String point = st.nextToken();
                int px = point.charAt(0)-'A';
                int py = point.charAt(1)-'0'-1;
                sudoku[px][py] = i;
                c1[px][i] = c2[py][i] = true;
                c3[(px/3)*3 + py/3][i] = true;
            }

            System.out.println("Puzzle " + count++);
            go(0);
        }
    }

    static boolean go(int index) {

        if (index == 81) {
            printSudoku();
            return true;
        }

        // 숫자 하나를 두개의 좌표로 표현
        int x = index / 9;
        int y = index % 9;

        if (sudoku[x][y] != 0) {
            if (go(index + 1)) {
                return true;
            }
        }
        else {
            for (int i = 0; i < 2; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위 체크
                if (nx >= 9 || ny >= 9) {
                    continue;
                }

                // 비어있는 칸인지 확인
                if (sudoku[nx][ny] != 0) {
                    continue;
                }

                for (int j = 1; j <= 9; j++) {
                    for (int k = 1; k <= 9; k++) {

                        // 도미노는 서로 다른 숫자로 구성되어있음
                        if (j == k) {
                            continue;
                        }

                        if (domino[j][k] && domino[k][j]) {
                            continue;
                        }

                        // (x,y) 에 j 가 들어갈 수 있는지 체크
                        if (c1[x][j] || c2[y][j]) { continue; }
                        if (c3[(x/3)*3 + y/3][j]) { continue; }

                        // (nx, ny) 에 k 가 들어갈 수 있는지 체크
                        if (c1[nx][k] || c2[ny][k]) { continue; }
                        if (c3[(nx/3)*3 + ny/3][k]) { continue; }

                        c1[x][j] = c2[y][j] = c3[(x/3)*3 + y/3][j] = true;
                        c1[nx][k] = c2[ny][k] = c3[(nx/3)*3 + ny/3][k] = true;
                        sudoku[x][y] = j;
                        sudoku[nx][ny] = k;
                        domino[j][k] = domino[k][j] = true;
                        if (go(index+1)) {
                            return true;
                        }
                        c1[x][j] = c2[y][j] = c3[(x/3)*3 + y/3][j] = false;
                        c1[nx][k] = c2[ny][k] = c3[(nx/3)*3 + ny/3][k] = false;
                        sudoku[x][y] = 0;
                        sudoku[nx][ny] = 0;
                        domino[j][k] = domino[k][j] = false;
                    }
                }
            }
        }
        return false;
    }

    static void printSudoku() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j]);
            } System.out.println();
        }
    }
}
