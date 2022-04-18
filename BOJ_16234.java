import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16234 {
    static int n, l, r, days, totalPopulTmp, totalAssoTmp;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    static boolean isOpen;
    static public class Nation{
        int x;
        int y;
        int popul;
        Nation(int x, int y, int popul){
            this.x = x;
            this.y = y;
            this.popul = popul;
        }
    }
    static List<Nation> Asso;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        isOpen = false;
        map = new int[n][n];
        days = 0;
        Asso = new ArrayList<>();

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true){
            isOpen = false;
            visited = new boolean[n][n];
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(!visited[i][j]) {      //아무 나라나 고르고
                        bfs(i, j);
                    }
                }
            }
            if(!isOpen) break;
            days++;
        }
        System.out.println(days);
    }

    public static boolean open(int Nation1, int Nation2){           //두 나라의 인구수 차이가 l이상 r이하일 때 true
        if(Math.abs(Nation1 - Nation2) <= r && l <= Math.abs(Nation1 - Nation2)) return true;
        return false;
    }

    public static void bfs(int x, int y){
        totalAssoTmp = 0;
        totalPopulTmp = 0;
        Queue<Nation> q = new LinkedList<>();
        q.offer(new Nation(x, y, map[x][y]));
        visited[x][y] = true;
        Asso.add(new Nation(x, y, map[x][y]));             //현재 나라를 연합 리스트에 추가하고
        while(!q.isEmpty()){
            Nation cur = q.poll();
            int cx = cur.x;
            int cy = cur.y;
            for(int i=0;i<4;i++){
                int nx = cx+dx[i];
                int ny = cy+dy[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if(open(map[cx][cy], map[nx][ny]) && !visited[nx][ny]) {             //인구수가 l 이상 r 이하인 나라를 찾게되면
                    isOpen = true;
                    visited[nx][ny] = true;
                    Asso.add(new Nation(nx, ny, map[nx][ny]));                   //추가 나라도 추가해준다
                    q.offer(new Nation(nx, ny, map[nx][ny]));
                }
            }
        }
        for(Nation n : Asso){
            totalPopulTmp += n.popul;           //연합 나라들의 인구수 총합
        }

        int changePopul = calc(totalPopulTmp, Asso.size());

        for(int i=0;i<Asso.size();i++){
            int changeX = Asso.get(i).x;
            int changeY = Asso.get(i).y;
            map[changeX][changeY] = changePopul;
        }

        Asso.clear();

    }

    public static int calc(int totalPopul, int totalAsso){
        return totalPopul / totalAsso;
    }
}
