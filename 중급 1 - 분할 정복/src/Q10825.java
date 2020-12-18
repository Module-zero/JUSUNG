import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q10825 {
    static int n;
    static ArrayList<Student> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String name = st.nextToken();
            int kor = Integer.parseInt(st.nextToken());
            int eng = Integer.parseInt(st.nextToken());
            int math = Integer.parseInt(st.nextToken());
            list.add(new Student(name, kor, eng, math));
        }

        Collections.sort(list);
        for (Student s : list) {
            System.out.println(s);
        }
    }

    static class Student implements Comparable<Student> {
        private final String name;
        private final int kor, eng, math;
        public Student(String name, int kor, int eng, int math) {
            this.name = name;
            this.kor = kor;
            this.eng = eng;
            this.math = math;
        }

        @Override
        public int compareTo(Student o) {
            if (this.kor != o.kor) {
                return o.kor - this.kor;
            } else if (this.eng != o.eng) {
                return this.eng - o.eng;
            } else if (this.math != o.math) {
                return o.math - this.math;
            } else {
                return this.name.compareTo(o.name);
            }
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
