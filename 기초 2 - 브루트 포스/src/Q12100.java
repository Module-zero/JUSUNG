import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q12100 {

    static int N;
    static int[][] board;
    static boolean[][] check;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        go(1);
        System.out.print(max);
    }

    static void go(int nLean) {

        // 최대 5번까지만 기울이도록함
        if (nLean > 5) {
            return;
        }

        // 원본 board 배열을 저장
        int[][] sourceBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sourceBoard[i][j] = board[i][j];
            }
        }

        // i 는 이동하는 방향(상,하,좌,우)를 의미
        for (int i = 0; i <= 3; i++) {
            // "한번 합쳐진 블록은 또 합쳐질 수 없다" 는 규칙은 한번의 기울임에만 해당되므로
            // 매번 기울일 때마다 check 배열 새로 생성해야함
            check = new boolean[N][N];

            // j는 한줄(열 or 행)을 의미
            // 반복문 종료 시 전체 board 의 한번의 이동 완료
            for (int j = 0; j < N; j++) {
                while (true) {
                    // i 방향으로 이동할 때 j 줄을 갱신
                    // 갱신 후에도 j 줄에 변화가 없으면 false 를 리턴
                    boolean isChange = update(i, j);

                    // 갱신된 j 줄에서 최대값을 max 와 비교한뒤 저장
                    max = Math.max(max, getMax(i, j));

                    // j줄을 갱신해도 변화가 없을 경우 break
                    if (isChange == false) {
                        break;
                    }
                }
            }

            // 계속해서 기울이기
            go(nLean+1);

            // 원본 board 배열을 복구
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    board[j][k] = sourceBoard[j][k];
                }
            }
        }
    }

    static boolean update(int dir, int line) {
        boolean isChange = false;
        // 갱신 방향이 위쪽인 경우
        if (dir == 0) {
            for (int k = 0; k <= N-2; k++) {
                if (board[k][line] == 0 && board[k+1][line] == 0) {
                    continue;
                }
                if (board[k][line] == 0 && board[k+1][line] != 0) {
                    board[k][line] = board[k+1][line];
                    board[k+1][line] = 0;
                    isChange = true;
                    continue;
                }
                if (check[k][line] || check[k+1][line]) {
                    continue;
                }
                if (board[k][line] == board[k+1][line]) {
                    board[k][line] += board[k+1][line];
                    board[k+1][line] = 0;
                    check[k][line] = true;
                    isChange = true;
                }
            }
        }
        // 갱신 방향이 아래쪽인 경우
        else if (dir == 1) {
            for (int k = N-1; k >= 1; k--) {
                if (board[k][line] == 0 && board[k-1][line] == 0) {
                    continue;
                }
                if (board[k][line] == 0 && board[k-1][line] != 0) {
                    board[k][line] = board[k-1][line];
                    board[k-1][line] = 0;
                    isChange = true;
                    continue;
                }
                if (check[k][line] || check[k-1][line]) {
                    continue;
                }
                if (board[k][line] == board[k-1][line]) {
                    board[k][line] += board[k-1][line];
                    board[k-1][line] = 0;
                    check[k][line] = true;
                    isChange = true;
                }
            }
        }
        // 갱신 방향이 왼쪽인 경우
        else if (dir == 2) {
            for (int k = 0; k <= N-2; k++) {
                if (board[line][k] == 0 && board[line][k+1] == 0) {
                   continue;
                }
                if (board[line][k] == 0 && board[line][k+1] != 0) {
                    board[line][k] = board[line][k+1];
                    board[line][k+1] = 0;
                    isChange = true;
                    continue;
                }
                if (check[line][k] || check[line][k+1]) {
                    continue;
                }
                if (board[line][k] == board[line][k+1]) {
                    board[line][k] += board[line][k+1];
                    board[line][k+1] = 0;
                    check[line][k] = true;
                    isChange = true;
                }
            }
        }
        // 갱신 방향이 오른쪽인 경우
        else {
            for (int k = N-1; k >= 1; k--) {
                if (board[line][k] == 0 && board[line][k-1] == 0) {
                    continue;
                }
                if (board[line][k] == 0 && board[line][k-1] != 0 ) {
                    board[line][k] = board[line][k-1];
                    board[line][k-1] = 0;
                    isChange = true;
                    continue;
                }
                if (check[line][k] || check[line][k-1]) {
                    continue;
                }
                if (board[line][k] == board[line][k-1]) {
                    board[line][k] += board[line][k-1];
                    board[line][k-1] = 0;
                    check[line][k] = true;
                    isChange = true;
                }
            }
        }

        return isChange;
    }

    static int getMax(int dir, int line) {

        int max = Integer.MIN_VALUE;
        for (int k = 0; k < N; k++) {
            if (dir == 0 || dir == 1) {
                max = Math.max(max, board[k][line]);
            } else {
                max = Math.max(max, board[line][k]);
            }
        }
        return max;
    }
}
