import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1389 {
    static int[][] relations;
    static int[] distance;
    static int n, m;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        relations = new int[n+1][n+1];
        distance = new int[n+1];

        for(int i=1;i<n+1;i++){             //자기 자신이면 0 아니면 무한대로 초기화
            for(int j=1;j<n+1;j++){
                if(i == j) relations[i][j] = 0;
                relations[i][j] = 987654321;
            }
        }


        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            relations[a][b] = relations[b][a] = 1;
        }

        for(int k=1;k<n+1;k++){         //경유
            for(int i=1;i<n+1;i++){     //출발
                for(int j=1;j<n+1;j++){ //도착
                    if(relations[i][j] > relations[i][k] + relations[k][j]){
                        relations[i][j] = relations[i][k] + relations[k][j];
                    }
                }
            }
        }

        for(int i=1;i<n+1;i++){
            int dist = 0;
            for(int j=1;j<n+1;j++){
                dist += relations[i][j];
            }
            distance[i] = dist;
            if(min > dist) min = dist;          //min = 최소비용
        }

        for(int i=1;i<n+1;i++){
            if(min == distance[i]){
                System.out.println(i);
                return;
            }
        }
    }
}
