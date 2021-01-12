import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q14425 {
    static int n, m;
    static ArrayList<Node> trie = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 루트 노드를 저장
        trie.add(new Node());

        for (int i = 0; i < n; i++) {
            add(0, br.readLine(), 0);
        }

        int cnt = 0;
        for (int i = 0; i < m; i++) {
            if (find(0, br.readLine(), 0)) {
                cnt++;
            }
        }

        System.out.print(cnt);
    }

    static int init() {
        trie.add(new Node());
        return trie.size() - 1;
    }

    static void add(int node, String s, int index) {

        if (index == s.length()) {
            trie.get(node).valid = true;
            return;
        }

        char[] str = s.toCharArray();
        int c = str[index] - 'a';
        if (trie.get(node).children[c] == -1) {
            // 다음 노드를 생성한 뒤, 다음 노드의 번호를 리턴한다.
            trie.get(node).children[c] = init();
        }

        int child = trie.get(node).children[c];
        add(child, s, index+1);
    }

    static boolean find(int node, String s, int index) {

        // 해당 문자열이 없는 경우
        if (node == -1) {
            return false;
        }

        if (index == s.length()) {
            return trie.get(node).valid;
        }

        char[] str = s.toCharArray();
        int c = str[index] - 'a';
        int child = trie.get(node).children[c];
        return find(child, s, index+1);
    }

    // Node : 문자열의 접두사(prefix)
    static class Node {
        // 다음 알파벳이 몇 번 노드에 저장되어있는지를 저장한다.
        int[] children;
        // 특정 문자열을 찾는 과정에 있는 노드면 false
        // 특정 문자열을 찾은 노드면 true
        boolean valid;
        public Node() {
            children = new int[26];
            for (int i = 0; i < 26; i++) {
                children[i] = -1;
            }
        }
    }
}
