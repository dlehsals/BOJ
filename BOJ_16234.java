import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16234 {
    static int[][] map;
    static boolean[][] visited;
    static int n, l, r, dates;
    static boolean flag;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
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
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        dates = 0;
        map = new int[n][n];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true){
            flag = false;
            visited = new boolean[n][n];

            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(!visited[i][j]){
                       if(bfs(i, j)){
                           flag = true;
                       }
                    }
                }
            }
            if(flag)
                dates++;
            else
                break;
        }
        System.out.println(dates);
    }
    private static boolean bfs(int x, int y){
        boolean isGroup = false;
        int count = 1;
        int sum = map[x][y];
        ArrayList<Pair> group = new ArrayList<>();
        Queue<Pair> q = new LinkedList<>();
        visited[x][y] = true;
        group.add(new Pair(x, y));
        q.add(new Pair(x, y));

        while(!q.isEmpty()){
            Pair cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;
            for(int i=0;i<4;i++){
                int nx = curX+dx[i];
                int ny = curY+dy[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if(!visited[nx][ny]){
                    int dif = Math.abs(map[nx][ny] - map[curX][curY]);
                    if(dif <= r && dif >= l) {
                        count++;
                        visited[nx][ny] = true;
                        q.offer(new Pair(nx, ny));
                        group.add(new Pair(nx, ny));
                        sum += map[nx][ny];
                    }
                }
            }
        }
        if(group.size() > 1){
            isGroup = true;
            int avg = sum / count;
            for(int i=0;i<group.size();i++){
                Pair p = group.get(i);
                map[p.x][p.y] = avg;
            }
        }
        return isGroup;
    }
}