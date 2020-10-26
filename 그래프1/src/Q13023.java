import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 접근법 :
// DFS 를 사용함. 그래프에 단순경로가 4인 경로(깊이가 4인 경로)가 있는지 찾는 문제였음
// DFS 는 깊이우선탐색이므로 그래프에서 깊이를 찾을 때 유용함
public class Q13023 {

    static int n;
    static int m;
    static GraphList graph = null;
    static boolean[] check = null;
    static int answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());   // 사람의 수
        m = Integer.parseInt(st.nextToken());   // 친구 관계의 수
        graph = new GraphList(n);
        check = new boolean[n];
        answer = 0;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            graph.put(v1, v2);
        }

        // graph.printGraph();

        // 단순경로가 4인 경로를 찾는다
        // 이 때, 모든 정점에서 경로를 찾아보아야함
        for (int i = 0; i < n; i++) {

            dfs(i, 0);

            if (answer == 1) {
                break;
            }
        }

        System.out.print(answer);
    }

    public static void dfs(int x, int depth) {

        if (depth == 4) {
            answer = 1;
            return;
        }

        check[x] = true;

        for (int i = 0; i < graph.getList().get(x).size(); i++) {
            int v = graph.getList().get(x).get(i);
            if (check[v] == false) {
                dfs(v, depth + 1);
            }
        }

        // 한번 탐색하고 끝이 아니라
        // 계속 새로운 경로를 탐색해야하므로
        // 한번 방문한 정점도 다시 방문하지 않은것으로 표시해야함
        check[x] = false;
    }

    // 인접리스트 클래스
   static class GraphList {

        private ArrayList<ArrayList<Integer>> list;

        public GraphList(int listSize) {
            list = new ArrayList<>();
            for (int i = 0; i < listSize; i++) {
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
            for(int i = 0; i < list.size(); i++) {
                System.out.print("정점 " + i + "의 인접리스트");
                for(int j = 0; j < list.get(i).size(); j++) {
                    System.out.print(" -> " + list.get(i).get(j));
                } System.out.println();
            }
        }
    }
}


