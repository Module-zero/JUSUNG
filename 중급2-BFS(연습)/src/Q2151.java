import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

// 최소로 거울을 사용하여 반대편 문을 바라볼 수 있도록 하는 문제
// 이 문제의 핵심은 '방향' 임. 그리고 우선순위 큐를 사용하는 것임
// 좌표상에서 어떤 '최소값' 을 찾아내는 문제는 우선순위 큐로 해결가능함
// 대부분은 cost 가 작은것이 먼저 나오게한 뒤, 해당 좌표를 방문처리해버리면
// 다음에 같은 좌표지만 cost 가 더 큰것이 와도 값을 갱신할 수 없음
public class Q2151 {

    static int N;
    static char[][] map;
    static int[][][] visited;
    static ArrayList<Pair> twoDoors;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new int[N][N][4];
        twoDoors = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == '#') {
                    // 양쪽 문의 좌표를 저장
                    twoDoors.add(new Pair(i, j, -1, 0));
                }
            }
        }

        bfs();
    }

    // 0 : 서
    // 1 : 북
    // 2 : 동
    // 3 : 남
    static void bfs() {

        PriorityQueue<Pair> q = new PriorityQueue<>();
        Pair end = twoDoors.get(1);

        // 시작위치를 모든 방향으로 세팅해서 모두 큐에 넣음
        for (int i = 0; i < 4; i++) {
            Pair start = new Pair(twoDoors.get(0).getX(), twoDoors.get(0).getY(), i, 0);
            q.add(start);
        }

        while (!q.isEmpty()) {

            Pair p = q.poll();

            // 우선 순위 큐를 사용하기 때문에 항상 최소의 cost 를 가지고 있는 좌표가 먼저 나옴
            // 즉 가장 먼저 end 좌표를 찾는 경우가 가장 최소의 cost 를 가지고 있는 경우임
            if ((p.getX() == end.getX()) && (p.getY() == end.getY())) {
               System.out.print(p.getCost());
               break;
            }

            // 좌표를 우선순위 큐에 넣었기 때문에
            // cost(사용한 거울의 개수)가 작은 좌표가 먼저 나옴
            // 최소값을 구하는 문제이므로 먼저 나온 좌표를 먼저 방문으로 체크해야
            // 나중에 cost 가 더 큰 좌표가 방문하지 않게 할 수 있음
            if (visited[p.getX()][p.getY()][p.getDir()] == 1) {
                continue;
            }
            visited[p.getX()][p.getY()][p.getDir()] = 1;

            // for 문으로 서북동남 전 방향으로 이동하는 것이 아님
            // 기본적으로는 부모의 방향을 따라서 이동해야함
            int nx = p.getX() + dx[p.getDir()];
            int ny = p.getY() + dy[p.getDir()];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                continue;
            }
            if (map[nx][ny] == '*') {
                continue;
            }

            // 거울을 사용한 경우 진행방향의 양쪽방향도 q 에 넣어야함
            // 거울에 반사된 경우이므로 부모 cost+1 을 함
            if (map[nx][ny] == '!') {
                q.add(new Pair(nx, ny, (p.getDir()+1)%4, p.getCost()+1));
                q.add(new Pair(nx, ny, (p.getDir()+3)%4, p.getCost()+1));
            }

            // 거울을 사용하지 않을 경우 부모의 cost 를 그대로 가져감
            q.add(new Pair(nx, ny, p.getDir(), p.getCost()));
        }
    }

    static class Pair implements Comparable<Pair> {
        private int x;
        private int y;
        private int dir;
        private int cost;

        public Pair(int x, int y, int dir, int cost) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cost = cost;
        }

        public int getX() { return x; }
        public int getY() { return y; }
        public int getDir() { return dir; }
        public int getCost() { return cost; }

        @Override
        public int compareTo(Pair o) {
            // q 에서 cost 가 작은 객체 먼저 나옴
            return this.cost - o.cost;
        }
    }
}
