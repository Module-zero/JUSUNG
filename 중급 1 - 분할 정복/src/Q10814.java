import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q10814 {
    static int n;
    static ArrayList<Member> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            list.add(new Member(age, name, i));
        }

        Collections.sort(list);
        for (Member m : list) {
            System.out.println(m);
        }
    }

    static class Member implements Comparable<Member> {
        private final int age;
        private final String name;
        private final int order;
        public Member(int age, String name, int order) {
            this.age = age;
            this.name = name;
            this.order = order;
        }

        @Override
        public int compareTo(Member o) {
            if (this.age != o.age) {
                return this.age - o.age;
            } else {
                return this.order - o.order;
            }
        }

        @Override
        public String toString() {
            return age+" "+name;
        }
    }
}
