import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17142 {
    static int n, m, zeroCnt, zeroTmp;
    static String[][] map;
    static String[][] copy;
    static boolean[][] visited;
    static List<Pair> virusList;
    static int max;
    static Queue<Pair> q;
    static List<Integer> timeList = new ArrayList<>();
    static public class Pair{
        int x;
        int y;
        int dist;
        Pair(int x, int y, int dist){
            super();
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());               //바이러스의 개수

        map = new String[n][n];
        copy = new String[n][n];
        virusList = new ArrayList<>();
        zeroCnt = 0;
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<n;j++){
                map[i][j] = st.nextToken();
                if(map[i][j].equals("2")){
                    virusList.add(new Pair(i, j, 0));           //2로 표시된 부분 : 바이러스 이므로 따로 리스트에 담아준다
                    map[i][j] = "#";
                }
                if(map[i][j].equals("0")) zeroCnt++;
            }
        }

        makeVirus(0, 0);
        if(timeList.size() == 0){
            System.out.println("-1");
            System.exit(0);
        }

        System.out.println(Collections.min(timeList));
    }

    public static void makeVirus(int index, int depth){
        if(depth == m){
            visited = new boolean[n][n];
            q = new LinkedList<>();
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(map[i][j].equals("*")){             //바이러스 활성화
                        q.offer(new Pair(i, j, 0));
                    }
                    else if(map[i][j].equals("1")){        //벽은 -로
                        map[i][j] = "-";
                    }
                    copy[i][j] = map[i][j];
                }
            }
            if(zeroCnt == 0){
                System.out.println("0");
                System.exit(0);
            }
            spreadVirus();

            if(zeroTmp > 0) return;           // 0이 존재하면 벗어나기
            else{
                timeList.add(max);
            }
            return;
        }
        for(int i=index;i<virusList.size();i++){
            int x = virusList.get(i).x;
            int y = virusList.get(i).y;
            map[x][y] = "*";                   //바이러스 활성화
            makeVirus(i+1, depth + 1);
            map[x][y] = "#";
        }
    }

    public static void spreadVirus(){
        max = Integer.MIN_VALUE;
        zeroTmp = zeroCnt;
        while(!q.isEmpty() && zeroTmp > 0){
            Pair cur = q.poll();
            int cx = cur.x;
            int cy = cur.y;
            int cd = cur.dist;
            visited[cx][cy] = true;
            for(int i=0;i<4;i++){
                int nx = cx+dx[i];
                int ny = cy+dy[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if(!visited[nx][ny]) {
                    if (map[nx][ny].equals("0")) {
                        visited[nx][ny] = true;
                        copy[nx][ny] = String.valueOf(cd + 1);
                        q.offer(new Pair(nx, ny, cd + 1));
                        zeroTmp--;
                    } else if (map[nx][ny].equals("*")) continue;
                    else if (map[nx][ny].equals("#")) {
                        visited[nx][ny] = true;
                        q.offer(new Pair(nx, ny, cd+1));
                    }
                }
            }
            max = Math.max(max, cd+1);
        }
    }
}
