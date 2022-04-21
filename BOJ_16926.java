import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16926 {
    static int n, m, r;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        int group = Math.min(n, m) / 2; //돌려야하는 그룹의 개수

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<m;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<r;i++){
            for(int j=0;j<group;j++){
                int x = j;
                int y = j;
                int temp = map[x][y];       //각 라인의 첫번째 값
                int dir = 0;

                while(dir < 4){
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];
                    if(nx >= j && ny >= j && nx < n-j && ny < m-j){
                        map[x][y] = map[nx][ny];
                        x = nx;
                        y = ny;
                    }else
                        dir++;
                }
                map[j+1][j] = temp;
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }


    }


}
