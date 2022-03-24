import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2638 {
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n, m, answer, cheeseCnt;
    static ArrayList<Pair> cheese;
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
        cheese = new ArrayList<>();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        answer = 0;
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<m;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<n;i++){               //치즈인 애들의 좌표를 리스트에 저장해준다
            for(int j=0;j<m;j++){
                if(map[i][j] == 1){
                    cheese.add(new Pair(i, j));
                    cheeseCnt++;
                }

            }
        }
        while(cheeseCnt != 0){
            visited = new boolean[n][m];
            answer++;
            bfs();
            cheeseMelting();
        }
        //printMap();
        System.out.println(answer);
    }
    public static void printMap(){
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void bfs(){                   //외부 공기 판별
        visited[0][0] = true;
        map[0][0] = 7;
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(0, 0));

        while(!q.isEmpty()){
            Pair cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            for(int i=0;i<4;i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if(map[nx][ny] == 1 || visited[nx][ny]) continue;
                map[nx][ny] = 7;
                visited[nx][ny] = true;
                q.offer(new Pair(nx, ny));
            }
        }
    }

    public static void cheeseMelting(){
        for(int i=0;i<cheese.size();i++){
            int cnt = 0;
            int cx = cheese.get(i).x;
            int cy = cheese.get(i).y;
            for(int j=0;j<4;j++){
                int nx = cx+dx[j];
                int ny = cy+dy[j];
                if(map[nx][ny] == 7)
                    cnt++;
            }

            if(cnt >= 2){
                map[cx][cy] = 0;
                cheese.remove(i);
                cheeseCnt--;
                i--;        //리스트에서 삭제하면 배열의 길이가 줄어들면서 인덱스오류나므로 i--
            }
        }
    }
}
