import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_15650 {
    static int N, M;
    static boolean[] check;
    static int[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());           // N : 1~N까지     M : M만큼의 자릿수를 가진 수열
        M = Integer.parseInt(st.nextToken());

        arr = new int[M];
        check = new boolean[N];
        dd(0, 0);
    }

    private static void dd(int num, int depth){
        if(depth == M){
            for(int i=0;i<M;i++){
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }
        for(int i=num;i<N;i++){     // i = 시작하는 숫자
            if(!check[i]){          // i를 사용한 적이 없는데
                if(depth > 0 && arr[depth-1] > i) continue;     //depth가 0일때는 첫번째 숫자니까 무조건 뽑기때문에 제외하고, i는 현재 숫자/arr[depth-1]는 이전숫자인데 이전숫자가 현재숫자보다 크다면 제낀다
                check[i] = true;       //체크해주고
                arr[depth] = i+1;       //0부터니까 +1을 해서 배열에 넣어준다
                dd(num, depth+1);   //재귀 ㄱㄱ
                check[i] = false;
            }
        }

    }
}
