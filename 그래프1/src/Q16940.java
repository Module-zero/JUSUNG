import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q16940 {

    static int N;
    static GraphList graph;
    static int[] visited;
    static LinkedList<Integer> queue = new LinkedList<>();
    static int[] a;
    static int[] b;
    static int[] order;
    static int index = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new GraphList(N);
        visited = new int[N+1];
        for (int i = 1; i <= N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            graph.put(n1, n2);
        }

        a = new int[N];
        b = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        order = new int[N+1];
        for (int i = 0; i < N; i++) {
            order[b[i]] = i;
        }

        // 주어진 방문순서에 맞게 인접리스트의 각 리스트의 순서를 정렬
        sort();
        // 정렬된 인접리스트로 bfs 탐색
        bfs(1);
        if (Arrays.equals(a, b)) {
            System.out.print(1);
        }
        else {
            System.out.print(0);
        }
    }

    static void bfs(int n) {
        queue.add(n);
        visited[n] = 1;
        while (!queue.isEmpty()) {
            int p = queue.poll();
            a[index++] = p;
            ArrayList<Integer> list = graph.getList().get(p);
            for (int i = 0; i < list.size(); i++) {
                int np = list.get(i);
                if (visited[np] == 0) {
                    queue.add(np);
                    visited[np] = 1;
                }
            }
        }
    }

    static void sort() {
        for (int i = 1; i <= N; i++) {
            ArrayList<Integer> list = graph.getList().get(i);
            Collections.sort(list, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if (order[o1] > order[o2]) { return 1; }
                    else { return -1; }
                }
            });
        }
    }

    // 인접리스트 클래스
    static class GraphList {

        private ArrayList<ArrayList<Integer>> list;

        public GraphList(int listSize) {
            list = new ArrayList<>();
            // List 는 제로베이스이므로 입력된 사이즈보다 하나 더 크게 잡아줌
            for (int i = 0; i < listSize + 1; i++) {
                list.add(new ArrayList<Integer>());
            }
        }

        public ArrayList<ArrayList<Integer>> getList() {
            return this.list;
        }

        // 양방향 그래프 추가
        public void put(int x, int y) {
            list.get(x).add(y);
            list.get(y).add(x);
        }

        // 그래프 출력
        public void printGraph() {
            for(int i = 1; i < list.size(); i++) {
                System.out.print("정점 " + i + "의 인접리스트 : ");
                Iterator<Integer> it = list.get(i).iterator();
                while (it.hasNext()) {
                    System.out.print(it.next() + " ");
                }
                System.out.println();
            }
        }
    }
}
