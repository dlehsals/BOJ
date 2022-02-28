import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_1316 {
    static int n, count;
    static boolean[] alphabet;
    static boolean check;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        count = 0;
        for(int i=0;i<n;i++){
            String str = br.readLine();
            alphabet = new boolean[26];
            check = true;
            for(int j=0;j<str.length();j++){
                int a = str.charAt(j) - 'a';
                if(alphabet[a]){            //사용한 적이 있는데
                    if(str.charAt(j) != str.charAt(j-1)){       //연속된 문자열이 아니라면
                        check = false;
                        break;
                    }
                }
                else{
                    alphabet[a] = true;
                }
            }
            if(check) count++;
        }
        System.out.println(count);
    }
}
