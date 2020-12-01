import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class Q16954 {
    static LinkedList<Pair> q = new LinkedList<>();
    static int[][] map;
    static boolean[][] visited = new boolean[8][8];
    static int[] dx = {0, -1, 0, 1, -1, -1, 1, 1};
    static int[] dy = {-1, 0, 1, 0, -1, 1, 1, -1};
    static ArrayList<Pair> walls = new ArrayList<>();
    static boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 8; i++) {
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                if (s.charAt(j) == '#') {
                    walls.add(new Pair(i, j, -1));
                }
            }
        }

        // 맵 초기화
        updateMap();

        visited[7][0] = true;
        q.add(new Pair(7, 0, 0));
        while (!q.isEmpty()) {
            Pair p = q.poll();
            // System.out.println(p);
            if (p.getX() == 0 && p.getY() == 7) {
                flag = true;
                break;
            }

            // 벽과 겹치면 인접좌표삽입 X
            if (map[p.getX()][p.getY()] != 1) {
                for (int i = 0; i < 8; i++) {
                    int nx = p.getX() + dx[i];
                    int ny = p.getY() + dy[i];
                    if (nx >= 0 && ny >= 0 && nx < 8 && ny < 8) {
                        if (map[nx][ny] != 1 && !visited[nx][ny]) {
                            visited[nx][ny] = true;
                            q.add(new Pair(nx, ny, p.getDepth() + 1));
                        }
                    }
                }
            }

            // depth 가 바뀔때만 벽을 한칸씩 아래로 이동시킴
            if (q.peek() != null) {
                if (p.getDepth() != q.peek().getDepth()) {
                    down();
                    updateMap();
                    /*
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            System.out.print(map[i][j]);
                        }
                        System.out.println();
                    }
                     */
                }
            }

        }

        if (flag) {
            System.out.print(1);
        } else {
            System.out.print(0);
        }
    }

    static void updateMap() {
        map = new int[8][8];
        for (Pair p : walls) {
            map[p.getX()][p.getY()] = 1;
        }
    }

    static void down() {
        ArrayList<Pair> tmp = new ArrayList<>();
        for (Pair p : walls) {
            int nx = p.getX() + dx[3];
            int ny = p.getY() + dy[3];
            if (nx < 8) {
                tmp.add(new Pair(nx, ny, -1));
            }
        }
        walls = tmp;
    }

    static class Pair {
       private int x, y, depth;
       public Pair(int x, int y, int depth) {
           this.x = x;
           this.y = y;
           this.depth = depth;
       }
       public int getX() { return x; }
       public int getY() { return y; }
       public int getDepth() { return depth; }
       public String toString() {
           return x+","+y;
       }
    }
}
