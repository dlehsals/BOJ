import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2212 {
    static int n, k;
    static int[] arr;
    static int[] diff;
    static int result;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n];  //5
        diff = new int[n-1]; //4
        result = 0;
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        for(int i=0;i<n-1;i++){
            diff[i] = arr[i+1] - arr[i];
        }
        Arrays.sort(diff);
        for(int i=0;i<n-k;i++){
            result += diff[i];
        }
        System.out.println(result);
    }
}
