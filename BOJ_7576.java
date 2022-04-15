import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576 {
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n, m, days;
    static Queue<Pair> q = new LinkedList<>();
    static public class Pair{
        int x;
        int y;
        int dist;
        Pair(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        days = 0;
        map = new int[m][n];
        visited = new boolean[m][n];

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(map[i][j] == 1 && !visited[i][j])
                    q.offer(new Pair(i, j, 0));
            }
        }

        int days = bfs();

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(map[i][j] == 0 && !visited[i][j]){
                    System.out.println(-1);
                    System.exit(0);
                }
            }
        }
        System.out.println(days);
    }

    public static int bfs(){

        int tmp = -1;

        while(!q.isEmpty()){
            Pair cur = q.poll();
            int cx = cur.x;
            int cy = cur.y;
            int cd = cur.dist;
            visited[cx][cy] = true;
            tmp = cd;
            for(int i=0;i<4;i++){
                int nx = cx+dx[i];
                int ny = cy+dy[i];
                if(nx < 0 || ny < 0 || nx >= m || ny >= n || map[nx][ny] == -1) continue;
                if(map[nx][ny] == 0 && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    q.offer(new Pair(nx, ny, cd+1));
                }
            }
        }
        return tmp;
    }
}
