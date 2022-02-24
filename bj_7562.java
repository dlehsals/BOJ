import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class bj_7562 {
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-2, -1, 2, 1, 2, 1, -2, -1};
    static int[] dy = {1, 2, 1, 2, -1, -2, -1, -2};
    static int n, l, count;
    static List<Integer> list;
    static Queue<Pair> q;
    static public class Pair{
        int x;
        int y;
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n  = Integer.parseInt(st.nextToken());   //테스트케이스의 갯수
        list = new ArrayList<Integer>();
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());   //체스판의 길이 l * l
            map = new int[l][l];
            visited = new boolean[l][l];
            st = new StringTokenizer(br.readLine(), " ");
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine(), " ");
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

            q = new LinkedList<Pair>();
            q.offer(new Pair(startX, startY));
            visited[startX][startY] = true;
            while(!q.isEmpty()){
                Pair p = q.poll();
                int x = p.x;
                int y = p.y;
                if(x == endX && y == endY) break;
                for(int j=0;j<8;j++){
                    int nx = p.x + dx[j];
                    int ny = p.y + dy[j];
                    if(nx < 0 || ny < 0 || nx >= l || ny >= l) continue;
                    if(!visited[nx][ny]){
                        map[nx][ny] = map[x][y]+1;
                        visited[nx][ny] = true;
                        q.offer(new Pair(nx, ny));
                    }
                }
            }
            System.out.println(map[endX][endY]);
        }
    }
}

