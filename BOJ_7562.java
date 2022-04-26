import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7562 {
    static int t, n, min;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-2, -1, 1, 2, 2, 1, -2, -1};
    static int[] dy = {-1, -2, -2, -1, 1, 2, 1, 2};
    public static class Pair{
        int x;
        int y;
        int dist;
        Pair(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        t = Integer.parseInt(st.nextToken());
        while(t-->0){
            min = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            map = new int[n][n];
            visited = new boolean[n][n];

            st = new StringTokenizer(br.readLine(), " ");

            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine(), " ");

            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());
            bfs(startX, startY, endX, endY);
            System.out.println(min);

        }
    }

    public static void bfs(int startX, int startY, int endX, int endY){
        Queue<Pair> q = new LinkedList<>();
        visited[startX][startY] = true;
        q.offer(new Pair(startX, startY, 0));

        while(!q.isEmpty()){
            Pair cur = q.poll();
            int cx = cur.x;
            int cy = cur.y;
            int cd = cur.dist;

            if(cx == endX && cy == endY) {
                min = Math.min(min, cd);
                return;
            }
            for(int i=0;i<8;i++){
                int nx = cx+dx[i];
                int ny = cy+dy[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    q.offer(new Pair(nx, ny, cd+1));
                }
            }

        }
    }
}
