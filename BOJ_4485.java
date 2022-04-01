import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_4485 {
    static int[][] map;
    static boolean[][] visited;
    static int[][] distance;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n;
    public static class Pair implements Comparable<Pair>{
        int x;
        int y;
        int cost;
        Pair(int x, int y, int cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pair o) {
            return cost - o.cost;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = 0;
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            cnt++;
            if(n == 0) break;

            map = new int[n][n];
            distance = new int[n][n];
            visited = new boolean[n][n];

            for(int i=0;i<n;i++){
                st = new StringTokenizer(br.readLine(), " ");
                for(int j=0;j<n;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    distance[i][j] = Integer.MAX_VALUE;
                }
            }
            System.out.println("Problem "+cnt+": "+bfs());
        }
    }
    public static int bfs(){
        PriorityQueue<Pair> q = new PriorityQueue<>();
        q.offer(new Pair(0, 0, map[0][0]));
        distance[0][0] = map[0][0];
        while(!q.isEmpty()){
            Pair cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            int cost = cur.cost;
            if(x == n-1 && y == n-1) return cost;
            for(int i=0;i<4;i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if(!visited[nx][ny]){
                    if(distance[nx][ny] > cost + map[nx][ny]){
                        distance[nx][ny] = cost + map[nx][ny];
                        q.offer(new Pair(nx, ny, cost + map[nx][ny]));
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        return -1;
    }

}
