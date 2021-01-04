import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q16988 {
    static int n, m;
    static int[][] arr = new int[20][20];
    static int[][] group = new int[20][20];
    static int[] groupCount;
    static Set<Pair> candidate1 = new HashSet<>();
    static ArrayList<Pair> candidate2 = new ArrayList<>();
    static boolean[] visited = new boolean[20*20];
    static int[] order = new int[2];
    static int max = Integer.MIN_VALUE;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    static ArrayList<HashSet<Pair>> deadList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 2번돌의 그룹을 나눈다.
        int groupNum = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 2 && group[i][j] == 0) {
                    setGroup(i, j, groupNum++);
                }
            }
        }

        // 각 그룹의 돌의 개수를 저장한다.
        groupCount = new int[groupNum];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (group[i][j] != 0) {
                    groupCount[group[i][j]]++;
                }
            }
        }

        if (groupNum == 1) {
            System.out.print(0);
            return;
        }

        // 각 그룹을 죽이기위해 필요한 빈칸들을 저장한 HashSet.
        for (int i = 0; i < groupNum; i++) {
            deadList.add(new HashSet<>());
        }

        // 2번돌과 인접해있는 모든 빈칸을 후보리스트에 넣는다.
        // 이 때, 각 그룹을 죽이기위해 필요한 빈칸들을 구분해서 저장해둔다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 2) {
                    addCandidate(i, j);
                }
            }
        }

        // 2개의 돌을 뽑기위해 ArrayList 에 옮겨담는다.
        candidate2.addAll(candidate1);
        go(0, 0);
        System.out.print(max);
    }

    static void go(int index, int start) {

        if (index == 2) {
            int cnt = 0;
            Pair p1 = candidate2.get(order[0]);
            Pair p2 = candidate2.get(order[1]);
            for (int i = 1; i < deadList.size(); i++) {
                HashSet<Pair> set = deadList.get(i);
                // i 그룹을 죽일 수 있음
                if (set.size() == 1) {
                    if (set.contains(p1) || set.contains(p2)) {
                        cnt += groupCount[i];
                    }
                } else if (set.size() == 2) {
                    if (set.contains(p1) && set.contains(p2)) {
                        cnt += groupCount[i];
                    }
                }
            }

            max = Math.max(max, cnt);
            return;
        }

        for (int i = start; i < candidate2.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                order[index] = i;
                go(index+1, i+1);
                visited[i] = false;
            }
        }
    }

    static void setGroup(int x, int y, int groupNum) {
        LinkedList<Pair> q = new LinkedList<>();
        group[x][y] = groupNum;
        q.add(new Pair(x, y));
        while (!q.isEmpty()) {
           Pair p = q.poll();
           x = p.getX();
           y = p.getY();
           for (int i = 0; i < 4; i++) {
               int nx = x + dx[i];
               int ny = y + dy[i];
               if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                   if (arr[nx][ny] == 2 && group[nx][ny] == 0) {
                       group[nx][ny] = groupNum;
                       q.add(new Pair(nx, ny));
                   }
               }
           }
        }
    }

    static void addCandidate(int x, int y) {
        int groupNum = group[x][y];
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                if (arr[nx][ny] == 0) {
                    candidate1.add(new Pair(nx, ny));
                    deadList.get(groupNum).add(new Pair(nx, ny));
                }
            }
        }
    }

    static class Pair {
        private final int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() { return x; }
        public int getY() { return y; }

        @Override
        public String toString() { return "("+x+","+y+")"; }

        @Override
        public int hashCode() {
            return Integer.parseInt(x+""+y);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj.getClass() != this.getClass()) {
                return false;
            }
            final Pair other = (Pair)obj;
            if (this.x != other.x) {
                return false;
            } else {
                return (this.y == other.y);
            }
        }
    }
}
