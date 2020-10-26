import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q13023 {

    static int n;
    static int m;
    static GraphList graph = null;
    static boolean[] check = null;
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());   // 사람의 수
        m = Integer.parseInt(st.nextToken());   // 친구 관계의 수
        graph = new GraphList(n);
        check = new boolean[n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            graph.put(v1, v2);
        }

        // graph.printGraph();

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
        check[x] = false;
    }
}

// 인접리스트 클래스
class GraphList {

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
