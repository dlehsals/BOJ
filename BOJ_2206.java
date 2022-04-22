import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206 {
    static int n, m;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static public class Pair{
        int x;
        int y;
        int dist;
        boolean isCrushed;
        Pair(int x, int y, int dist, boolean isCrushed){
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.isCrushed = isCrushed;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m][2];             //0은 부수지 않음, 1은 부숨

        for(int i=0;i<n;i++){
            String s = br.readLine();
            for(int j=0;j<m;j++){
                map[i][j] = Character.getNumericValue(s.charAt(j));
            }
        }

        bfs(0, 0);

    }

    public static void bfs(int x, int y){
        Queue<Pair> q = new LinkedList<>();
        visited[x][y][0] = true;
        q.offer(new Pair(x, y, 1, false));

        while(!q.isEmpty()){
            Pair cur = q.poll();
            int cx = cur.x;
            int cy = cur.y;
            int cd = cur.dist;
            boolean cc = cur.isCrushed;
            if(cx == n-1 && cy == m-1) {
                System.out.println(cd);
                return;
            }
            for(int i=0;i<4;i++){
                int nx = cx+dx[i];
                int ny = cy+dy[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if(map[nx][ny] == 0){
                    if(!cc && !visited[nx][ny][0]){
                        visited[nx][ny][0] = true;
                        q.offer(new Pair(nx, ny, cd+1, false));
                    }else if(cc && !visited[nx][ny][1]){
                        q.offer(new Pair(nx, ny, cd+1, true));
                        visited[nx][ny][1] = true;
                    }
                }
                else if(map[nx][ny] == 1){
                    if(!cc){
                        q.offer(new Pair(nx, ny, cd+1, true));
                        visited[nx][ny][1] = true;
                    }
                }
            }
        }
        System.out.println(-1);
    }

}