import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class bj_9012 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        List<String> answer = new ArrayList<String>();
        int count;
        boolean check;
        int n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            check = false;
            count = 0;
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '(') {
                    count++;
                } else if (count == 0 && str.charAt(j) == ')') {
                    check = true;
                    break;
                } else {
                    count--;
                }
            }
            if(!check && count == 0)
                answer.add("YES");
            else
                answer.add("NO");
            }
            for (String s : answer)
                System.out.println(s);
    }
}
