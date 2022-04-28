import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_3190 {
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};    //우 상 좌 하
    static int[] dy = {1, 0, -1, 0};
    static int n, k, l, second;
    static public class Snake{
        int x;
        int y;
        Snake(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static HashMap<Integer, String> commands;
    static List<Snake> snakeInfo = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        commands = new HashMap<>();
        second = 0;

        for(int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            map[r][c] = 2;
        }

        //print();

        st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());


        for(int i=0;i<l;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int t = Integer.parseInt(st.nextToken());
            String s = st.nextToken();
            commands.put(t, s);
        }
        search(0, 0);
    }

    public static void print(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void search(int x, int y){
        snakeInfo.add(new Snake(x, y));
        int turn = 0;
        int cx = 0;
        int cy = 0;
        while(true){
            second++;
            int nx = cx+dx[turn];       //뱀이 다음 이동할 방향(맨 처음은 오른쪽)
            int ny = cy+dy[turn];
            if(finish(nx, ny)) break;
            if(map[nx][ny] == 2){
                map[nx][ny] = 0;  //사과 먹고
                snakeInfo.add(new Snake(nx, ny));
            }else{
                snakeInfo.add(new Snake(nx, ny));
                snakeInfo.remove(0);
            }
            cx = nx;            //사과를 먹고(뱀의 머리가 사과 위치로 이동했으니) 그 위치에 머리위치를 세팅
            cy = ny;

            if(commands.containsKey(second)){           //만약 second값이 해쉬맵 안에 들어있으면
                if(commands.get(second).equals("D")){       //D는 오른쪽, L은 왼쪽
                    turn += 1;
                    if(turn == 4) turn = 0;
                }
                else if(commands.get(second).equals("L")){
                    turn -= 1;
                    if(turn == -1) turn = 3;
                }
            }
        }
        System.out.println(second);
    }

    public static boolean finish(int nx, int ny){
        if(nx < 0 || ny < 0 || nx >= n || ny >= n) return true;
        for(int i=0;i<snakeInfo.size();i++){
            int x = snakeInfo.get(i).x;
            int y = snakeInfo.get(i).y;
            if(nx == x && ny == y) return true;
        }
        return false;
    }
}
