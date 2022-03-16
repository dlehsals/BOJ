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
        dates = 0;                                          //며칠이 걸렸는지 체크할 변수
        map = new int[n][n];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true){                                    //~얼마나걸릴지 -> 계속해서 루프를 돌다가 어떤 조건에 의해 종료되고 그에 따른 값이 얼마인지 구하기 위해 while문 사용
            flag = false;                               //루프를 종료하기 위한 boolean 변수
            visited = new boolean[n][n];                //루프를 돌면서(1일, 2일, 3일..) 매일매일 인구이동을 하는 나라를 체크하기 위해선 방문체크 배열을 초기화시켜줘야 한다.

            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(!visited[i][j]){                //아직 방문하지 않은 나라들 중에서
                       if(bfs(i, j)){                  //연합인지 아닌지 체크하는 함수가 true라면
                           flag = true;                //루프를 계속해서 돌게하고 dates를 증가시켜준다. 루프가 돈다는 것은 연합할 나라가 있다는 뜻
                       }
                    }
                }
            }
            if(flag)
                dates++;
            else                                      //더이상 루프를 돌아도 연합할 나라가 없다면 dates 출력
                break;
        }
        System.out.println(dates);
    }
    private static boolean bfs(int x, int y){       
        boolean isGroup = false;                    //bfs를 시작하는 나라와 인접한 나라가 연합인지 아닌지 체크하는 변수
        int count = 1;                              //연합할 나라의 숫자(리스트크기로 해도 ㄱㅊ)
        int sum = map[x][y];                        //연합하는 나라들의 인구숫자(인구수 / 나라수 구하기 위함)
        ArrayList<Pair> group = new ArrayList<>();  //연합할 나라들을 Pair형태좌표로 담아주는 리스트
        Queue<Pair> q = new LinkedList<>();         //시작하는 나라의 좌표에서 bfs를 진행할 큐
        visited[x][y] = true;                       //시작하는 나라는 방문체크를 해주고
        group.add(new Pair(x, y));                  //연합나라 리스트와 큐에 각각 넣어준다
        q.add(new Pair(x, y));

        while(!q.isEmpty()){                        
            Pair cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;
            for(int i=0;i<4;i++){
                int nx = curX+dx[i];
                int ny = curY+dy[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if(!visited[nx][ny]){               //아직 방문을 안했고
                    int dif = Math.abs(map[nx][ny] - map[curX][curY]);      //시작하는 나라와 인접한 나라의 차이가 조건을 충족한다면
                    if(dif <= r && dif >= l) {
                        count++;                                            //연합하기때문에 변수의 값 +1
                        visited[nx][ny] = true;                             //연합나라도 방문체크
                        q.offer(new Pair(nx, ny));                          //큐와 연합나라리스트에도 넣어주고
                        group.add(new Pair(nx, ny));
                        sum += map[nx][ny];                                 //인구숫자도 더해준다
                    }
                }
            }
        }
        if(group.size() > 1){                                               //연합하는 나라가 두개이상이면
            isGroup = true;                                                 //연합하는 나라가 있다 -> 루프의 종료 조건은 fs함수의 리턴값이 false이므로 종료하지 않게 true로 바꿔준다
            int avg = sum / count;                                          //평균치를 내주고
            for(int i=0;i<group.size();i++){                                //연합하는 나라 리스트에서 좌표값을 받아와 map의 값을 바꿔준다
                Pair p = group.get(i);
                map[p.x][p.y] = avg;
            }
        }
        return isGroup;
    }
}
