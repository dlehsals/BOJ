import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class BOJ_15686{
    static public class Pair{
        int x;
        int y;
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int[][] map;
    static boolean[] visited;
    static List<Integer> dist;
    static int n, m, answer, sum;
    static List<Pair> chicken;
    static List<Pair> house;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        dist = new ArrayList<>();
        chicken = new ArrayList<>();
        house = new ArrayList<>();
        answer = Integer.MAX_VALUE;
        sum = 0;

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(map[i][j] == 2) chicken.add(new Pair(i, j));    //치킨집 좌표 큐에 넣어주기
                if(map[i][j] == 1) house.add(new Pair(i, j));
            }
        }
        visited = new boolean[chicken.size()];
        dfs(0, 0);

        System.out.println(answer);
    }

    private static int distCal(int x1, int y1, int x2, int y2){
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private static void dfs(int start, int depth){
        if(depth == m){
            int sum = 0;
            for(int i=0;i<house.size();i++){
                int tmp = Integer.MAX_VALUE;
                int x = house.get(i).x;
                int y = house.get(i).y;
                for(int j=0;j< chicken.size();j++){
                    int cx = chicken.get(j).x;
                    int cy = chicken.get(j).y;
                    if(!visited[j]) continue;
                    int imsi = distCal(x, y, cx, cy);

                    tmp = Math.min(tmp, imsi);
                }
                sum += tmp;
            }
            answer = Math.min(answer, sum);
            return ;
        }

        for(int i=start;i<chicken.size();i++){
            visited[i] = true;
            dfs(i+1, depth+1);
            visited[i] = false;
        }

    }
}