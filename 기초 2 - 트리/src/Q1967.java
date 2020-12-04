import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q1967 {
    static int n;
    static ArrayList<Node>[] list;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        list = new ArrayList[n+1];
        visited = new boolean[n+1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            list[parent].add(new Node(child, dist));
        }

        Pair answer = dfs(1);
        System.out.print(answer.getDiameter());
    }

    static Pair dfs(int root) {

        visited[root] = true;

        // root 부터 leaf 까지의 거리들을 저장
        ArrayList<Integer> heights = new ArrayList<>();

        // 트리의 지름
        int diameter = 0;

        // root 에서 leaf 까지 중 가장 긴 거리
        int height = 0;

        ArrayList<Node> l = list[root];
        for (int i = 0; i < l.size(); i++) {
            Node node = l.get(i);
            if (!visited[node.getVal()]) {
                Pair p = dfs(node.getVal());
                heights.add(p.getHeight() + node.getDist());
                if (p.getDiameter() > diameter) {
                    diameter = p.getDiameter();
                }
            }
        }

        Collections.sort(heights);
        Collections.reverse(heights);

        // root 의 서브트리가 1개 이상
        if (heights.size() >= 1) {
            height = heights.get(0);
            if (diameter < heights.get(0)) {
                diameter = heights.get(0);
            }
        }

        // root 의 서브트리가 2개 이상
        if (heights.size() >= 2) {
            if (diameter < heights.get(0) + heights.get(1)) {
                diameter = heights.get(0) + heights.get(1);
            }
        }

        return new Pair(diameter, height);
    }

    static class Pair {
        private int diameter, height;
        public Pair(int diameter, int height) {
            this.diameter = diameter;
            this.height = height;
        }
        public int getDiameter() { return diameter; }
        public int getHeight() { return height; }
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
