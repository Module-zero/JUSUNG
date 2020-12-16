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

        printPreOrder(0, postOrder.length-1, 0);
        System.out.print(sb);
    }

    static void printPreOrder(int start, int end, int prog) {
        int size = end-start+1;
        if (size == 0) {
            return;
        }

        int rootIndex = order[postOrder[prog+size-1]];
        sb.append(inOrder[rootIndex]).append(" ");

        printPreOrder(start, rootIndex-1, prog);
        printPreOrder(rootIndex+1, end, prog+rootIndex-start);
    }
}
