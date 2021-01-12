import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14426 {
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        Node trie = new Node('\0');

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

    static class Node {
        Node[] children;
        char ch;

        public Node(char ch) {
            children = new Node[26];
            this.ch = ch;
        }

        public void add(String s, int index) {

            if (index == s.length()) {
                return;
            }

            int num = s.charAt(index) - 'a';
            if (children[num] == null) {
                children[num] = new Node(s.charAt(index));
            }

            children[num].add(s, index+1);
        }

        public boolean find(String s, int index) {

            if (index == s.length()) {
                return true;
            }

            int num = s.charAt(index) - 'a';
            if (children[num] == null) {
                return false;
            }

            return children[num].find(s, index+1);
        }
    }
}
