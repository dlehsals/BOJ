import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15665 {
    static int n, m;
    static int[] nums;
    static int[] arr;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        sb = new StringBuilder();
        nums = new int[n];
        arr = new int[m];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<nums.length;i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        dfs(0, 0);
        System.out.print(sb.toString());
    }

    public static void dfs(int index, int depth){
        if(depth == m){
            for(int i=0;i<arr.length;i++){
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        int preNum = -1;
        for(int i=index;i<n;i++){
            if(preNum == nums[i]) continue;
            arr[depth] = nums[i];
            preNum = nums[i];
            dfs(index, depth+1);
        }

    }
}
/*
N개의 자연수와 자연수 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.

N개의 자연수 중에서 M개를 고른 수열
같은 수를 여러 번 골라도 된다.
입력
첫째 줄에 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 7)

둘째 줄에 N개의 수가 주어진다. 입력으로 주어지는 수는 10,000보다 작거나 같은 자연수이다.

출력
한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.

수열은 사전 순으로 증가하는 순서로 출력해야 한다.

입력
4 2
9 7 9 1

출력
1 1
1 7
1 9
7 1
7 7
7 9
9 1
9 7
9 9
 */