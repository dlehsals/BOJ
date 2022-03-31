import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2156 {
    static int n;
    static int[] arr;
    static int[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        arr = new int[n+1];
        dp =  new int[n+1];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = 0;
        if(n >= 1)
            dp[1] = arr[1];
        if(n >= 2)
            dp[2] = dp[1] + arr[2];
        System.out.println(solve(n));
    }
    public static int solve(int n){
        if(dp[n] == 0){
            for(int i=3;i<=n;i++){          // 0 + 6  :  10 + 0
                dp[i] = Math.max( Math.max(dp[i-1], dp[i-3]+arr[i-1]+arr[i]), dp[i-2]+arr[i]);
                // xoo : dp[i-3] + arr[i-1] + arr[i] (dp[i-3]은 x 이전의 누적값이고 현재의 값과 이전의 값 더하면 됨)
                // oxo : dp[i-2] + arr[i]
                // oox : dp[i-1]
            }
        }

        return dp[n];
    }
}

// 0 6 10 13 9 8 1
// 0 1  2  3 4 5 6