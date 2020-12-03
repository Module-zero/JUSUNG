import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1991 {
    static int n;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        Node root = new Node('A');

        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            char parentData = st.nextToken().charAt(0);
            char leftData = st.nextToken().charAt(0);
            char rightData = st.nextToken().charAt(0);

            // 부모 노드를 트리에서 탐색
            Node x = findNode(root, parentData);
            if (leftData != '.') {
                x.setLeft(new Node(leftData));
            }
            if (rightData != '.') {
                x.setRight(new Node(rightData));
            }
        }

        preOrder(root);
        answer.append("\n");
        inOrder(root);
        answer.append("\n");
        postOrder(root);
        System.out.print(answer);
    }

    static void preOrder(Node root) {
        answer.append(root.getData());
        if (root.getLeft() != null) {
            preOrder(root.getLeft());
        }
        if (root.getRight() != null) {
            preOrder((root.getRight()));
        }
    }

    static void inOrder(Node root) {
        if (root.getLeft() != null) {
            inOrder(root.getLeft());
        }
        answer.append(root.getData());
        if (root.getRight() != null) {
            inOrder(root.getRight());
        }
    }

    static void postOrder(Node root) {
        if (root.getLeft() != null) {
            postOrder(root.getLeft());
        }
        if (root.getRight() != null) {
            postOrder(root.getRight());
        }
        answer.append(root.getData());
    }

    static Node findNode(Node root, char data) {
        if (root.getData() == data) {
            return root;
        }
        Node res = null;
        if (root.getLeft() != null) {
            res = findNode(root.getLeft(), data);
            if (res != null) { return res; }
        }
        if (root.getRight() != null) {
            res = findNode(root.getRight(), data);
            if (res != null) { return res; }
        }
        return null;
    }

    static class Node {
        private char data;
        private Node left, right;
        public Node(char data) {
            this.left = null;
            this.right = null;
            this.data = data;
        }
        public void setData(char data) { this.data = data; }
        public void setLeft(Node left) { this.left = left; }
        public void setRight(Node right) { this.right = right;}
        public char getData() { return data; }
        public Node getLeft() { return left; }
        public Node getRight() { return right; }
    }
}
