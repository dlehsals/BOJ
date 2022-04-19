import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11399 {
    static int n;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine(), " ");

        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());      //인덱스는 사람의 순서, 배열의 값은 돈을 인출하는데에 걸리는 시간이다
        }                                                   // 최소가 되기 위해선 돈을 뽑는 대기시간이 적은 순서로 정렬되어야 한다 -> 오름차순

        Arrays.sort(arr);

        int wait = 0;
        int sum = 0;

        for(int i=0;i<arr.length;i++){
            wait += arr[i];         //만약 2번째 사람이 돈을 뽑을려면 대기시간은 1번째 사람의 값 + 2번째 사람의 대기시간이다
            sum += wait;
        }

        System.out.println(sum);
    }
}
