import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q16933 {
    static int n, m, k;
    static int[][] map;
    static LinkedList<Node> q = new LinkedList<>();
    static boolean[][][] visited;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static int answer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m][k+1];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        visited[0][0][0] = true;
        q.add(new Node(0, 0, 1, 0, false));
        while (!q.isEmpty()) {
            Node p = q.poll();
            int cost = p.getCost();
            int count = p.getCount();
            boolean flag = p.getFlag();
            if (p.getX() == n-1 && p.getY() == m-1) {
                answer = cost;
                break;
            }

            // 벽이고 밤일 경우 cost 를 하나 증가시킨 뒤 낮으로 바꾸고 큐에 다시 넣음
            if (map[p.getX()][p.getY()] == 1 && flag == false) {
                q.add(new Node(p.getX(), p.getY(), cost+1, count, true));
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = p.getX() + dx[i];
                int ny = p.getY() + dy[i];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if (map[nx][ny] == 1) {
                        if (count+1 <= k) {
                            if (!visited[nx][ny][count + 1]) {
                                // 벽이 밤이든 낮이든 관계없이 일단 큐에 넣음
                                visited[nx][ny][count + 1] = true;
                                q.add(new Node(nx, ny, cost + 1, count + 1, !flag));
                            }
                        }
                    } else {
                        if (!visited[nx][ny][count]) {
                            visited[nx][ny][count] = true;
                            q.add(new Node(nx, ny, cost+1, count, !flag));
                        }
                    }
                }
            }
        }
        System.out.print(answer);
    }

    static class Node {
        private int x, y, cost, count;
        // true : 낮, false : 밤
        private boolean flag;
        public Node(int x, int y, int cost, int count, boolean flag) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.count = count;
            this.flag = flag;
        }
        public int getX() { return x; }
        public int getY() { return y; }
        public int getCost() { return cost; }
        public int getCount() { return count; }
        public boolean getFlag() { return flag; }
    }
}
