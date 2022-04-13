import java.util.Scanner;

public class BOJ_9663 {
    static int n;
    static int[] arr;
    static int answer;
    static boolean flag;
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        arr = new int[n];

        answer = 0;

        dfs(0);

        System.out.println(answer);
    }

    public static void dfs(int depth){
        if(depth == n){
            answer++;
            return;
        }

        for(int i=0;i<n;i++){
            arr[depth] = i;
            if(check(depth)){
                dfs(depth + 1);
            }
        }
    }

    public static boolean check(int row){
        flag = true;
        for(int i=0;i<row;i++){
            if(arr[i] == arr[row] || Math.abs(row-i) == Math.abs(arr[row] - arr[i]))
                flag = false;
        }
        return flag;

    }
}