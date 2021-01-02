import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q17085 {
    static int n, m;
    static char[][] arr = new char[15][15];
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    // depth 가 1 일 때 십자가의 넓이
    static int area = 0;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == '#') {
                    findCross(i, j, 1);
                }
            }
        }

        System.out.print(max);
    }

    static void findCross(int x, int y, int depth) {
        int[][] next = {{x, y}, {x, y}, {x, y}, {x, y}};
        int crossSize = 0;
        do {
            // depth 가 1 일때는 x, y 에서 만들 수 있는 모든 십자가를 찾아야함
            if (depth == 1) {
                // 십자가를 맵에서 삭제
                disposeCross(x, y, crossSize, '.');
                area = 1+crossSize*4;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (arr[i][j] == '#') {
                            findCross(i, j, 2);
                        }
                    }
                }
                // 십자가를 맵에서 복구
                disposeCross(x, y, crossSize, '#');
            }

            int cnt = 0;
            for (int i = 0; i < 4; i++) {
                int nx = next[i][0] + dx[i];
                int ny = next[i][1] + dy[i];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && arr[nx][ny] == '#') {
                    next[i][0] = nx;
                    next[i][1] = ny;
                    cnt++;
                }
            }

            if (cnt == 4) {
                crossSize++;
            } else {
                break;
            }
        } while (true);

        //  depth 가 2 일때는 x, y 에서 가장 큰 십자가만 찾으면 됨
        if (depth == 2) {
            // depth 가 2 일 때 십자가의 넓이
            int area2 = 1+crossSize*4;
            max = Math.max(max, area*area2);
        }
    }

    static void disposeCross(int x, int y, int size, char ch) {
        arr[x][y] = ch;
        for (int i = 0; i < 4; i++) {
            int nx = x;
            int ny = y;
            for (int j = 0; j < size; j++) {
                nx += dx[i];
                ny += dy[i];
                arr[nx][ny] = ch;
            }
        }
    }
}
