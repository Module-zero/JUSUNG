import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q16236 {
    static int n;
    static int[][] map;
    static int sharkSize = 2;
    static int[] nFish = new int[7];
    static int answer = 0;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        Node start = null;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    if (map[i][j] != 9) {
                        nFish[map[i][j]]++;
                    } else {
                        start = new Node(i, j, 0);
                    }
                }
            }
        }

        int count = 0;
        while (true) {
            PriorityQueue<Node> q = new PriorityQueue<>();
            boolean[][] visited = new boolean[n][n];
            int[] copyFish = nFish.clone();
            map[start.getX()][start.getY()] = 0;
            visited[start.getX()][start.getY()] = true;
            q.add(start);

            while (!q.isEmpty()) {
                Node p = q.poll();
                int x = p.getX();
                int y = p.getY();
                int cost = p.getCost();

                // 아기 상어가 물고기를 먹는 경우
                if (map[x][y] != 0 && map[x][y] < sharkSize) {
                    nFish[map[x][y]]--;
                    map[x][y] = 0;
                    start = new Node(x, y, 0);
                    if (sharkSize == (++count)) {
                        sharkSize++;
                        count = 0;
                    }
                    answer += cost;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                        if (map[nx][ny] <= sharkSize && !visited[nx][ny]) {
                            visited[nx][ny] = true;
                            q.add(new Node(nx, ny, cost+1));
                        }
                    }
                }
            }

            // 변화가 없으면 엄마 상어를 불러야함
            if (!isChange(copyFish)) {
                break;
            }
        }

        System.out.print(answer);
    }

    static boolean isChange(int[] copy) {
        int limit = sharkSize <= 7 ? sharkSize : 7;
        for (int i = 1; i < limit; i++) {
            if (nFish[i] != copy[i]) {
                return true;
            }
        }
        return false;
    }

    static class Node implements Comparable<Node> {
        private int x, y, cost;
        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        public int getX() { return x; }
        public int getY() { return y; }
        public int getCost() { return cost; }

        @Override
        public int compareTo(Node o) {
            if (this.cost != o.cost) {
                return this.cost - o.cost;
            } else {
                if (this.x != o.x) {
                    return this.x - o.x;
                } else {
                    return this.y - o.y;
                }
            }
        }
    }
}
