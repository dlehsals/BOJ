import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class bj_2583 {
    static int n, m, k, cnt;
    static int[][] map;
    static boolean[][] visited;
    static List<Integer> list;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());  //너비
        m = Integer.parseInt(st.nextToken());  //높이
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        for(int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int y1 = Integer.parseInt(st.nextToken());   // 왼쪽 아래 꼭지점 (0, 2) -> x좌표가 y축, y좌표가 x축
            int x1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            for(int x=x1;x<x2;x++){                 //y축을 기준으로 for문을 돌려야하니까
                for(int y=y1;y<y2;y++){
                    map[x][y] = 1;                  //1인 부분은 직사각형에 포함되는 부분이다.
                }
            }
        }
        list = new ArrayList<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(map[i][j] == 0 && !visited[i][j])  // 직사각형에 포함되지 않고 아직 탐색을 안한부분이 있으면 dfs실행
                {
                    cnt = 0;                          //이 조건문에 걸려 들어오는 경우는 for문 조건이 첫탐색이거나 다음좌표로 넘어가는거기때문에 cnt를 초기화시켜주고
                    dfs(i, j);                        //dfs실행
                    list.add(cnt);                    //실행하고 난뒤 증가된 cnt를 list에 넣어준다. 각 dfs마다 구한 cnt들은 나머지 영역들의 넓이가 된다
                }

            }
        }
        System.out.println(list.size());            //총 영역들의 갯수는 dfs를 통해 얻은 cnt가 list에 들어가고, list에 있는 인덱스의 갯수랑 같다
        Collections.sort(list);                     //오름차순이니까 sort한번해주고
        for(int i : list)                           //대충 출력하면끝
            System.out.print(i + " ");
    }
    public static void dfs(int x, int y){
        visited[x][y] = true;               //탐색을 시작하는부분은 true로 바꿔주고
        cnt++;                              //찾을때마다 cnt++
        for(int i=0;i<4;i++){
            int nx = x + dx[i];             //4방향 탐색을하면서
            int ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;        //범위밖으로 나가는거만 제외해주고
            if(!visited[nx][ny] && map[nx][ny] == 0){                   //만약 값이 0이고(찾아야하는 영역) 아직 탐색하지 않은 부분이라면
                dfs(nx, ny);                                            //dfs 재귀 ㄱㄱ
            }
        }
    }
}
