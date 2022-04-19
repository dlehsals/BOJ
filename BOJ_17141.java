import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17141 {
    static int n, m;
    static String[][] map, copy;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean isOK;
    static int min;
    static int time;
    static Queue<Pair> q;
    static List<Integer> answer;
    static List<Pair> virusList;

    static public class Pair {
        int x;
        int y;
        int size;

        Pair(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        answer = new ArrayList<>();
        map = new String[n][n];
        copy = new String[n][n];
        visited = new boolean[n][n];
        virusList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = st.nextToken();
                if (map[i][j].equals("2")) {
                    virusList.add(new Pair(i, j, 0));
                    map[i][j] = "#";
                }
            }
        }

        makeVirus(0, 0);

        if (answer.size() == 0) {
            System.out.println(-1);
            System.exit(0);
        }
        System.out.println(Collections.min(answer));
    }

    public static void makeVirus(int index, int count) {
        min = Integer.MAX_VALUE;
        if (count == m) {
            visited = new boolean[n][n];
            q = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j].equals("*"))
                        q.offer(new Pair(i, j, 0));
                    else if (map[i][j].equals("1"))
                        map[i][j] = "-";
                    copy[i][j] = map[i][j];
                }
            }

            time = spreadVirus();           //0이 없는 경우의 최소시간
            //print();
            if (isExist()) return;
            answer.add(time);
            return;
        }
        for (int i = index; i < virusList.size(); i++) {
            int x = virusList.get(i).x;
            int y = virusList.get(i).y;
            map[x][y] = "*";
            makeVirus(i + 1, count + 1);
            map[x][y] = "#";
        }

    }

    public static int spreadVirus() {
        int tmp = Integer.MIN_VALUE;

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            int cx = cur.x;
            int cy = cur.y;
            int cs = cur.size;
            visited[cx][cy] = true;

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (!visited[nx][ny]) {
                    if (map[nx][ny].equals("#") || map[nx][ny].equals("0")) {         //빈칸인 경우에는 바이러스를 퍼트린다
                        copy[nx][ny] = String.valueOf(cs + 1);
                        visited[nx][ny] = true;
                        q.offer(new Pair(nx, ny, cs + 1));
                    }else if(map[nx][ny].equals("*")){
                        continue;
                    }
                }
            }
            tmp = Math.max(tmp, cs);
        }
        return tmp;
    }
    public static boolean isExist() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (copy[i][j].equals("0"))
                    return true;
            }
        }
        return false;
    }

    public static void print(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(copy[i][j]+" ");
            }
            System.out.println();
        }
    }
}
