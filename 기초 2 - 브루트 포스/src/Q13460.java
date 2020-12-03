import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 최소 몇 번만에 빨간구슬을 구멍으로 빼낼 수 있을지는 구하는 문제였음
// 빨간구슬을 빼낼 수 있는 모든 경우 체크하여 최소값을 갱신해야함
// 재귀함수를 작성한 뒤 여러가지 종료조건들을 설정해야함

// Main 함수에서 재귀함수를 호출할 때도 방문여부를 체크해야함
// 문제의 종료조건을 재귀함수에 적용하지 않아 시간초과 발생함
// 빨간구슬과 파란구슬은 동시에 움직이므로 방문배열은 4차원 배열을 사용해야함

public class Q13460 {

    static int N;
    static int M;
    static char[][] board;
    static boolean[][][][] check;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        check = new boolean[N][M][N][M];

        int rx=-1, ry=-1, bx=-1, by=-1;
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
               board[i][j] = s.charAt(j);
               if (board[i][j] == 'R') {
                   rx = i;
                   ry = j;
               }
               if (board[i][j] == 'B') {
                   bx = i;
                   by = j;
               }
            }
        }

        check[rx][ry][bx][by] = true;
        go(rx, ry, bx, by, 0, -1);
        check[rx][ry][bx][by] = false;
        System.out.print(min == Integer.MAX_VALUE ? -1 : min);
    }

    // blockDir : 좌우 또는 상하로 반복해서 기울이는 경우를 방지하기 위한 변수
    static void go(int rx, int ry, int bx, int by, int depth, int blockDir) {

        // 기울이는 회수가 10회가 넘어가는 경우 종료
        if (depth > 10) {
            return;
        }

        // 빨간구슬, 파란구슬이 모두 구멍에 들어가면 종료
        if (board[rx][ry] == 'O' && board[bx][by] == 'O') {
            return;
        }

        // 파란구슬만 구멍에 들어가면 종료
        if (board[bx][by] == 'O') {
            return;
        }

        // 빨간구슬만 구멍에 들어가면 최소값 갱신 후 종료
        if (board[rx][ry] == 'O') {
            min = Math.min(min, depth);
            return;
        }

        // 현재 좌표에서 상,하,좌,우로 기울임
        for (int i = 0; i <= 3; i++) {

            // 왔던 방향으로는 다시 기울이지 않음
            if (blockDir == i) {
                continue;
            }

            int rnx = rx + dx[i];
            int rny = ry + dy[i];
            int bnx = bx + dx[i];
            int bny = by + dy[i];

            // 빨간구슬을 굴림
            while (true) {
                // 빨간구슬이 벽을 만나면
                // 구슬을 벽 바로 이전 위치로 옮긴 뒤 break
                if (board[rnx][rny] == '#') {
                    rnx += (-dx[i]);
                    rny += (-dy[i]);
                    break;
                }
                // 빨간구슬이 구멍을 만나면
                // 구슬 위치 이동없이 break
                if (board[rnx][rny] == 'O') {
                    break;
                }
                // 벽도 구멍도 아니면 다음칸으로 이동
                rnx += dx[i];
                rny += dy[i];
            }

            // 파란구슬을 굴림
            while (true) {
                if (board[bnx][bny] == '#') {
                    bnx += (-dx[i]);
                    bny += (-dy[i]);
                    break;
                }
                if (board[bnx][bny] == 'O') {
                    break;
                }
                bnx += dx[i];
                bny += dy[i];
            }

            // 빨간구슬, 파란구슬이 구멍이 아닌 위치에서 같은 좌표에 있는 경우
            // 이동하기 전의 두 구슬의 선후 관계에 따라 구슬위치를 재조정
            if (board[rnx][rny] != 'O' && (rnx == bnx && rny == bny)) {
                // 아래쪽으로 기울인 경우
                if (dx[i] == 1) {
                    // 파란구슬이 빨간구슬보다 위에 있었다면
                    // 파란구슬을 한칸 위로 이동시킴
                    if (rx > bx) { bnx += (-dx[i]); }
                    else {rnx += (-dx[i]); }
                }
                // 위쪽으로 기울인 경우
                if (dx[i] == -1) {
                    // 파란구슬이 빨간구슬보다 아래에 있었다면
                    // 파란구슬을 한칸 아래로 이동시킴
                    if (rx < bx) { bnx += (-dx[i]); }
                    else {rnx += (-dx[i]); }
                }
                // 오른쪽으로 기울인 경우
                if (dy[i] == 1) {
                    if (ry > by) { bny += (-dy[i]); }
                    else {rny += (-dy[i]); }
                }
                // 왼쪽으로 기울인 경우
                if (dy[i] == -1) {
                    if (ry < by) { bny += (-dy[i]); }
                    else {rny += (-dy[i]); }
                }
            }

            // 최종적으로 멈춰있는 두개의 구슬이 있는 좌표가
            // 이미 방문한 곳이면 continue
            if (check[rnx][rny][bnx][bny] == true) {
                continue;
            }

            check[rnx][rny][bnx][bny] = true;
            go(rnx, rny, bnx, bny, depth+1, i%2==0?i+1:i-1);
            check[rnx][rny][bnx][bny] = false;
        }
    }
}
