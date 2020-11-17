import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 1.
// 서로 다른 섬 사이의 최소거리 구하기

// 2.
// 우선 섬들을 구분할 수 있어야함.
// 각 섬의 모든 지점에서 다른 섬에 도달할때까지의 거리를 구할 수 있어야함
// 이를 위해 map 에는 서로 다른 숫자로 섬들을 구분하였고, visited 에는 거리를 기록함
// 내가 푼 것보다 땅따먹기 방식이 월등히 빠름

// 3.
// bfs 활용

// 4.
// 각 섬마다 bfs 를 번갈아가며 한번씩 진행하기위해
// bfs 전에 큐에 모든 섬들의 좌표를 미리 집어넣는 방식을 사용함

public class Q2146 {

    static int N;
    static int[][] map;
    static int[][] usedVisited;
    // static int[][] sourceVisited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static LinkedList<Pair> queue = new LinkedList<>();
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        usedVisited = new int[N][N];
        // sourceVisited = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 우선 visited 는 모두 -1로 채움
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                usedVisited[i][j] = -1;
            }
        }

        int count = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0 && usedVisited[i][j] == -1) {
                    // map 에는 숫자를 다르게 표시함으로서 섬을 구분하고
                    // visited 에 바다는 0으로 표시
                    divideIsland(i, j, count++);
                }
            }
        }

        // 섬들의 좌표를 미리 큐에 모두 집어넣음
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0) {
                    queue.add(new Pair(i, j));
                }
            }
        }

        // 섬들의 좌표를 모두 미리 큐에 넣었기 때문에
        // 각 섬을 차례대로 번갈아가면서 확장시킬 수 있음
        expandLand();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                updateMin(i, j);
            }
        }

        System.out.print(min);
    }

    static void divideIsland(int x, int y, int count) {
        queue.add(new Pair(x, y));
        usedVisited[x][y] = 0;
        map[x][y] = count;
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.getX() + dx[i];
                int ny = p.getY() + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }
                if (map[nx][ny] != 0 && usedVisited[nx][ny] == -1) {
                    queue.add(new Pair(nx, ny));
                    usedVisited[nx][ny] = 0;
                    map[nx][ny] = count;
                }
            }
        }
    }

    // 맵을 가득 채울 때까지 각 섬의 땅을 확장시킴
    // 동시에 방문배열에는 확장되는 거리를 표시
    static void expandLand() {
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.getX() + dx[i];
                int ny = p.getY() + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }
                if (map[nx][ny] == 0) {
                    queue.add(new Pair(nx, ny));
                    map[nx][ny] = map[p.getX()][p.getY()];
                    usedVisited[nx][ny] = usedVisited[p.getX()][p.getY()] + 1;
                }
            }
        }
    }

    static void updateMin(int x, int y) {
       for (int i = 0; i < 4; i++) {
           int nx = x + dx[i];
           int ny = y + dy[i];
           if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
               return;
           }
           if (map[x][y] != map[nx][ny]) {
               int distance = usedVisited[x][y] + usedVisited[nx][ny];
               min = Math.min(min, distance);
           }
       }
    }

    /* 느린 방법
    static void getBridge(int x, int y) {
        queue.add(new Pair(x, y));
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.getX() + dx[i];
                int ny = p.getY() + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }
                if (usedVisited[nx][ny] == 0) {
                    queue.add(new Pair(nx, ny));
                    usedVisited[nx][ny] = usedVisited[p.getX()][p.getY()] + 1;
                }
                else {
                    // 다른 섬에 도달한 경우
                    if ((map[nx][ny] != 0) && (usedVisited[nx][ny] != usedVisited[x][y])) {
                        min = Math.min(min, usedVisited[p.getX()][p.getY()] - usedVisited[x][y]);
                    }
                }
            }
        }
    }
     */

    static class Pair {
        private int x;
        private int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() { return x; }
        public int getY() { return y; }
    }
}
