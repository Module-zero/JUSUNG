import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q12906 {

    static Stack<Character>[] start = new Stack[3];
    static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            start[i] = new Stack<>();
            if (n != 0) {
                String s = st.nextToken();
                for (int j = 0; j < n; j++) {
                    start[i].push(s.charAt(j));
                }
            }
        }

        bfs();
    }

    static boolean check(Stack<Character>[] s) {
        boolean res = true;
        if (s[0].contains('B') || s[0].contains('C')) {
           res = false;
        }
        if (s[1].contains('A') || s[1].contains('C')) {
            res = false;
        }
        if (s[2].contains('A') || s[2].contains('B')) {
            res = false;
        }
        return res;
    }

    static String setStatus(Stack<Character>[] stacks) {
        String s = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < stacks[i].size(); j++) {
                s += stacks[i].get(j);
            }
            s += "/";
        }
        return s;
    }

    static void bfs() {
        LinkedList<Stack<Character>[]> q = new LinkedList<>();
        map.put(setStatus(start), 0);
        q.add(start);
        while (!q.isEmpty()) {
            Stack<Character>[] s = q.poll();
            int count = map.get(setStatus(s));
            if (check(s)) {
                System.out.print(count);
                break;
            }

            for (int i = 0; i < 3; i++) {
                if (s[i].size() != 0) {
                    int idx1 = (i + 1) % 3;
                    int idx2 = (i + 2) % 3;

                    Stack[] s1 = new Stack[3];
                    Stack[] s2 = new Stack[3];
                    for (int j = 0; j < 3; j++) {
                        s1[j] = new Stack<>();
                        s2[j] = new Stack<>();
                        s1[j].addAll(s[j]);
                        s2[j].addAll(s[j]);
                    }

                    if (s1[i] != null) {
                        s1[idx1].push(s1[i].pop());
                        if (map.get(setStatus(s1)) == null) {
                            map.put(setStatus(s1), count + 1);
                            q.add(s1);
                        }
                    }

                    if (s2[i] != null) {
                        s2[idx2].push(s2[i].pop());
                        if (map.get(setStatus(s2)) == null) {
                            map.put(setStatus(s2), count + 1);
                            q.add(s2);
                        }
                    }
                }
            }
        }
    }
}
