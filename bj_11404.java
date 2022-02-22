import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_11404 {
    static int n, m;
    static int[][] map;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringTokenizer st2;

        n = Integer.parseInt(br.readLine());      //도시의 갯수
        m = Integer.parseInt(br.readLine());      //버스의 갯수

        map = new int[n+1][n+1];

        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(i == j) map[i][j] = 0;
                map[i][j] = 9999999;
            }
        }
        for(int i=0;i<m;i++){
            st2 = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st2.nextToken());
            int end = Integer.parseInt(st2.nextToken());
            int cost = Integer.parseInt(st2.nextToken());
            map[start][end] = Math.min(map[start][end], cost);   //가는길이 한개가 아닐 수 있다 -> 더 짧은 비용이 들 수 있다.
        }

        for(int k=1;k<=n;k++){  //경유지
            for(int i=1;i<=n;i++){  //출발지            출발+도착 > (출발+경유) + (경유+도착) 인 경우 갱신해준다.
                if(i == k) continue;
                for(int j=1;j<=n;j++){  //도착지
                    if(i == j || j == k) continue;
                    if(map[i][j] > map[i][k] + map[k][j]){
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(map[i][j] == 9999999) map[i][j] = 0;
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }




    }
}
