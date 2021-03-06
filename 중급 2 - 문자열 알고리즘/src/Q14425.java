import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14425 {
    static int n, m;
    static Node trie;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 루트 노드를 저장
        trie = new Node('\0');

        for (int i = 0; i < n; i++) {
            trie.add(br.readLine(), 0);
        }

        int cnt = 0;
        for (int i = 0; i < m; i++) {
            if (trie.find(br.readLine(), 0)) {
                cnt++;
            }
        }

        System.out.print(cnt);
    }

    // Node : 문자열의 접두사(prefix)
    static class Node {
        Node[] children;
        boolean valid;
        char ch;

        public Node(char ch) {
            children = new Node[26];
            valid = false;
            this.ch = ch;
        }

        public void add(String s, int index) {

            if (index == s.length()) {
                this.valid = true;
                return;
            }

            int c = s.charAt(index) - 'a';
            if (children[c] == null) {
                children[c] = new Node(s.charAt(index));
            }

            children[c].add(s,index+1);
        }

        public boolean find(String s, int index) {

            if (index == s.length()) {
                return valid;
            }

            int c = s.charAt(index) - 'a';
            if (children[c] == null) {
                return false;
            }

            return children[c].find(s, index+1);
        }
    }
}
