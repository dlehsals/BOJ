import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1926 {
    static int n, m, max;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static List<Integer> answer = new ArrayList<>();
    static public class Pair{
        int x;
        int y;
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        //max = Integer.MIN_VALUE;

        map = new int[n][m];
        visited = new boolean[n][m];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<m;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(map[i][j] == 1 && !visited[i][j]){
                    int area = bfs(i, j);
                    answer.add(area);
                }
            }
        }
        if(answer.size() == 0){
            System.out.println("0");
            System.out.println("0");
            System.exit(0);
        }
        System.out.println(answer.size());
        System.out.println(Collections.max(answer));

    }

    public static int bfs(int x, int y){
        max = Integer.MIN_VALUE;
        int size = 1;
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(x, y));
        visited[x][y] = true;

        while(!q.isEmpty()){
            Pair cur = q.poll();
            int cx = cur.x;
            int cy = cur.y;
            for(int i=0;i<4;i++){
                int nx = cx+dx[i];
                int ny = cy+dy[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if(map[nx][ny] == 1 && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    size++;
                    q.offer(new Pair(nx, ny));
                }
            }
            max = Math.max(max, size);
        }
        return max;
    }
}
