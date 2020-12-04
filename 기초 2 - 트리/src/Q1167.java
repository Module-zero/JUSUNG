import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q1167 {
    static int v;
    static ArrayList<Node>[] list;
    static int[] target1;
    static int[] target2;
    static LinkedList<Integer> q;
    static boolean[] visited;

    static void dfs(int start, int[] target) {
        ArrayList<Node> l = list[start];
        for (int i = 0; i < l.size(); i++) {
            Node n = l.get(i);
            if (!visited[n.getVal()]) {
                visited[n.getVal()] = true;
                target[n.getVal()] = target[start] + n.getDist();
                dfs(n.getVal(), target);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        v = Integer.parseInt(br.readLine());
        list = new ArrayList[v+1];
        target1 = new int[v+1];
        target2 = new int[v+1];

        // 초기화
        for (int i = 0; i <= v; i++) {
            list[i] = new ArrayList<>();
        }

        // 트리 구성
        for (int i = 0; i < v; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n1 = Integer.parseInt(st.nextToken());
            while (true) {
                int n2 = Integer.parseInt(st.nextToken());
                if (n2 != -1) {
                    int dist = Integer.parseInt(st.nextToken());
                    list[n1].add(new Node(n2, dist));
                } else {
                    break;
                }
            }
        }

        // 1번에서 가장 거리가 먼 노드 찾기
        visited = new boolean[v+1];
        visited[1] = true;
        dfs(1, target1);

        // farthest : 1번과 가장 거리가 먼 노드
        int max = Integer.MIN_VALUE;
        int farthest = -1;
        for (int i = 1; i <= v; i++) {
            if (max < target1[i]) {
                farthest = i;
                max = target1[i];
            }
        }

        // farthest 에서 가장 거리가 먼 노드까지의 거리가 정답
        visited = new boolean[v+1];
        visited[farthest] = true;
        dfs(farthest, target2);

        max = Integer.MIN_VALUE;
        for (int i = 1; i <= v; i++) {
            if (max < target2[i]) {
                max = target2[i];
            }
        }

        System.out.print(max);

        /*
        // bfs 풀이
        // 1번에서 가장 거리가 먼 노드 찾기
        q = new LinkedList<>();
        visited = new boolean[v+1];
        visited[1] = true;
        q.add(1);
        while (!q.isEmpty()) {
            int p = q.poll();
            ArrayList<Node> adjList = list[p];
            for (int i = 0; i < adjList.size(); i++) {
                Node n = adjList.get(i);
                if (!visited[n.getVal()]) {
                    visited[n.getVal()] = true;
                    q.add(n.getVal());
                    target1[n.getVal()] = target1[p] + n.getDist();
                }
            }
        }

        // farthest : 1번과 가장 거리가 먼 노드
        int max = Integer.MIN_VALUE;
        int farthest = -1;
        for (int i = 1; i <= v; i++) {
            if (max < target1[i]) {
                farthest = i;
                max = target1[i];
            }
        }

        // farthest 에서 가장 거리가 먼 노드까지의 거리가 정답
        q = new LinkedList<>();
        visited = new boolean[v+1];
        visited[farthest] = true;
        q.add(farthest);
        while (!q.isEmpty()) {
            int p = q.poll();
            ArrayList<Node> adjList = list[p];
            for (int i = 0; i < adjList.size(); i++) {
                Node n = adjList.get(i);
                if (!visited[n.getVal()]) {
                    visited[n.getVal()] = true;
                    q.add(n.getVal());
                    target2[n.getVal()] = target2[p] + n.getDist();
                }
            }
        }

        max = Integer.MIN_VALUE;
        for (int i = 1; i <= v; i++) {
            if (max < target2[i]) {
                max = target2[i];
            }
        }

        System.out.print(max);
         */
    }

    static class Node {
        private int val, dist;
        public Node(int val, int dist) {
            this.val = val;
            this.dist = dist;
        }
        public int getVal() { return val; }
        public int getDist() { return dist; }
    }
}
