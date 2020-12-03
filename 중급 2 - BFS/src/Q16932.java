import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q16932 {

    static int N, M;
    static int[][] map;
    static int[][] visited;
    static LinkedList<Pair> queue;
    static int[] group;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new int[N][M];
        queue = new LinkedList<>();
        group = new int[1000001];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && visited[i][j] == 0) {
                    bfs(i, j, count++);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] != 0) {
                    group[visited[i][j]]++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    int value = 1;
                    Set<Integer> set = new HashSet<>();
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                            continue;
                        }
                        if (map[nx][ny] != 0) {
                            // 그룹 번호를 저장해둠
                            set.add(visited[nx][ny]);
                        }
                    }
                    Iterator<Integer> it = set.iterator();
                    while (it.hasNext()) {
                        int groupNum = it.next();
                        value += group[groupNum];
                    }
                    max = Math.max(max, value);
                }
            }
        }

        System.out.print(max);
    }

    static void bfs(int x, int y, int count) {
        queue.add(new Pair(x, y));
        visited[x][y] = count;
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.getX() + dx[i];
                int ny = p.getY() + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                if (map[nx][ny] == 1 && visited[nx][ny] == 0) {
                    queue.add(new Pair(nx, ny));
                    visited[nx][ny] = count;
                }
            }
        }
    }

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
