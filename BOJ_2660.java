import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2660 {
    static int[][] distance;
    static List<Integer> list;
    static int n;
    static int[] scores;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        distance = new int[n][n];
        scores = new int[n];
        list = new ArrayList<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i == j){
                    distance[i][j] = 0;
                    continue;
                }
                distance[i][j] = 987654321;
            }
        }
        while(true){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            if( a == -2 && b == -2) break;
            distance[a][b] = 1;
            distance[b][a] = 1;
        }
        for(int k=0;k<n;k++){       //경유
            for(int i=0;i<n;i++){   //시작
                for(int j=0;j<n;j++){   //종료
                    if(distance[i][j] > distance[i][k] + distance[k][j]){
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }
        int reader = 987654321;             //최종으로 나와야할 값(회장점수)
        for(int i=0;i<n;i++){
            int score = 0;                  //회원들의 점수
            for(int j=0;j<n;j++){
                score = Math.max(distance[i][j], score);
                scores[i] = score;
            }
            reader = Math.min(reader, score);
        }
        for(int i=0;i<n;i++){
            if(reader == scores[i])
                list.add(i);
        }

        System.out.print(reader + " " + list.size());
        System.out.println();
        Collections.sort(list);
        for(int i=0;i<scores.length;i++){
            if(reader == scores[i])
                System.out.print(i+1 + " ");
        }
    }
}
