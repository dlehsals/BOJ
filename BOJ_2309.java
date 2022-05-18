import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2309 {
    static int[] arr;
    static int[] nums;
    static boolean[] visited;
    static int sum = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        arr = new int[9];
        nums = new int[7];
        visited = new boolean[9];

        for(int i=0;i<arr.length;i++){
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        dfs(0, 0);
    }
    public static void dfs(int index, int depth){
        if(depth == 7){
            for(int i=0;i< nums.length;i++){
                sum += nums[i];
            }
            Arrays.sort(nums);
            if(sum == 100){
                for(int i : nums)
                    System.out.println(i);
                System.exit(0);
            }
            sum = 0;
            return;
        }

        for(int i=index;i<arr.length;i++){
            if(!visited[i]){
                visited[i] = true;
                nums[depth] = arr[i];
                dfs(i+1, depth+1);
                visited[i] = false;
            }
        }
    }
}
