import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_16953 {
    static int a, b, answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        answer = 0;

        while (a != b) {
            if (b < a) {
                System.out.println("-1");
                System.exit(0);
            }
            if(b % 2 == 0) b /= 2;    //b가 2로 나누어떨어지면 그대로 2로 나눠주고
            else if(b % 10 == 1) b /= 10;       //끝자리가 1이라면 10으로 나눠 자릿수를 낮춰줌
            else{
                System.out.println("-1");       //
                System.exit(0);
            }
            answer++;
        }
        System.out.println(answer+1);
    }
}
