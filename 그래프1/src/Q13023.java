import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q13023 {

    static int n;
    static int m;
    static int startVertex;
    static Stack<Integer> stack;
    static int[][] A;
    static boolean[] check;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());   // 사람의 수
        m = Integer.parseInt(st.nextToken());   // 친구 관계의 수
        stack = new Stack<>();
        check = new boolean[n];

        // 인접행렬구성
        A = new int[n][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int rowIndex = Integer.parseInt(st.nextToken());
            int columnIndex = Integer.parseInt(st.nextToken());
            A[rowIndex][columnIndex] = A[columnIndex][rowIndex] = 1;

            // 처음 입력받는 노드를 시작노드로 지정
            if (i == 0) {
                startVertex = rowIndex;
            }
        }

        // dfs 로 탐색
        System.out.print(dfs());
    }

    public static int dfs() {
        stack.push(startVertex);
        check[startVertex] = true;

        while (!stack.isEmpty()) {
            int top = stack.peek();
            boolean isConnected = false;    // top 노드에서 탐색가능한 노드가 있는지
            for (int i = 0; i < n; i++) {
                if (A[top][i] == 1 && check[i] == false) {
                    isConnected = true;
                    check[i] = true;
                    stack.push(i);
                    if (stack.size() == 4) {
                        for (int j = 0; j < n; j++) {
                            if (A[stack.peek()][j] == 1) {
                                return 1;
                            }
                        }
                    }
                    break;
                }
            }
            if (isConnected == false) {
                stack.pop();
            }
        }
        return 0;
    }
}
