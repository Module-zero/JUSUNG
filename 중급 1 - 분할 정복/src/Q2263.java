import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2263 {
    static int n;
    static int[] inOrder, postOrder, order;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        inOrder = new int[n];
        postOrder = new int[n];
        order = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
            order[inOrder[i]] = i;
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            postOrder[i] = Integer.parseInt(st.nextToken());
        }

        int root = order[postOrder[postOrder.length-1]];
        printPreOrder(0, postOrder.length-1, root);
        System.out.print(sb);
    }

    static void printPreOrder(int start, int end, int root) {
        sb.append(inOrder[root]).append(" ");

        if (start == end) {
            return;
        }

        if (root > 0) {
            printPreOrder(start, root - 1, order[start + root - 1]);
        }
        if (root < n-1) {
            printPreOrder(root + 1, end, order[end]);
        }
    }

    // inOrder 배열에서 start 부터 end 까지 탐색하여
    // root 의 인덱스를 리턴
    /*
    static int findRoot(int start, int end) {

        int max = -1, root = -1;
        for (int i = start; i <= end; i++) {
            if (max < order[inOrder[i]]) {
                max = order[inOrder[i]];
                root = i;
            }
        }
        return root;
    }
         */

}
