import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 일반큐 vs 우선순위 큐, 덱
// 가중치가 매번 동일하게 증가하는 경우에는 일반큐
// 가중치가 조건에 따라 다르게 증가할 수 있는 경우에는 우선순위 큐, 덱
public class Q9019 {
    static int t, a, b;
    static LinkedList<Node> q;
    static boolean[] visited;
    static int[] parent;
    static char[] usedCal;
    static char[] answer;
    static int depth;
    static int[] pow = {1, 10, 100, 1000};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            q = new LinkedList<>();
            visited = new boolean[10000];
            parent = new int[10000];
            usedCal = new char[10000];

            q.add(new Node(a, 0));
            visited[a] = true;
            parent[a] = -1;

            while (!q.isEmpty()) {
                Node node = q.poll();
                int n = node.getX();
                int cost = node.getCost();

                if (n == b) {
                    depth = cost-1;
                    answer = new char[cost];
                    sort(b);
                    StringBuilder sb = new StringBuilder();
                    for (char i : answer) {
                        sb.append(i);
                    }
                    System.out.println(sb);
                    break;
                }

                foo((n*2) % 10000, n, cost+1, 'D');
                foo(n==0 ? 9999 : n-1, n, cost+1, 'S');
                foo(shift(n, 'L'), n, cost+1, 'L');
                foo(shift(n, 'R'), n, cost+1, 'R');
            }
        }
    }

    static void foo(int val, int parentVal, int cost, char cal) {
        if (!visited[val]) {
            q.add(new Node(val, cost));
            visited[val] = true;
            parent[val] = parentVal;
            usedCal[val] = cal;
        }
    }

    static int shift(int n, char dir) {
        int idx = 3;
        int res = 0;
        while (n != 0) {
            int x = n % 10;
            int newIdx = ((idx--) + (dir=='L' ? 3 : 1)) % 4;
            res += pow[3-newIdx]*x;
            n /= 10;
        }
        return res;
    }

    static void sort(int n) {
        if (parent[n] == -1) {
            return;
        }
        answer[depth--] = usedCal[n];
        sort(parent[n]);
    }

    static class Node {
        private int x, cost;
        public Node(int x, int cost) {
            this.x = x;
            this.cost = cost;
        }
        public int getX() { return x; }
        public int getCost() { return cost; }
    }
}
