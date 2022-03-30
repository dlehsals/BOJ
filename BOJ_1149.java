import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1149 {
    static int n;
    static int[][] arr;
    static int[][] dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        arr = new int[n+1][3];
        dp = new int[n+1][3];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            arr[i][0] = Integer.parseInt(st.nextToken());       //빨강
            arr[i][1] = Integer.parseInt(st.nextToken());       //초록
            arr[i][2] = Integer.parseInt(st.nextToken());       //파랑
        }

        dp[1][0] = arr[1][0];       //각 색깔별로 시작할때 최솟값을 구하기 위해서 첫 값을 저장시키고 누적 ㄱㄱ
        dp[1][1] = arr[1][1];
        dp[1][2] = arr[1][2];

        System.out.println(Math.min(answer(n, 0), Math.min(answer(n, 1), answer(n, 2))));
    }

    public static int answer(int n, int rgb){
        if(dp[n][rgb] == 0){

            if(rgb == 0){
                dp[n][0] = Math.min(answer(n-1, 1), answer(n-1, 2)) + arr[n][0];
            }
            else if(rgb == 1){
                dp[n][1] = Math.min(answer(n-1, 2), answer(n-1, 0)) + arr[n][1];
            }
            else if(rgb == 2){
                dp[n][2] = Math.min(answer(n-1, 1), answer(n-1, 0)) + arr[n][2];
            }
        }

        return dp[n][rgb];
    }
}
