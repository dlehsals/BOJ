import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_5014 {
    //f : 건물의 층수, g : 목적지의 층수, s : 현재 위치, u : 위로 u만큼, d : 아래로 d만큼
    static int f, g, s, u, d, min;
    static int[] building;
    static boolean[] visited;
    static public class Gangho{
        int floor;
        int dist;
        Gangho(int floor, int dist){
            this.floor = floor;
            this.dist = dist;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        f = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        min = Integer.MAX_VALUE;
        building = new int[f+1];
        visited = new boolean[f+1];

        System.out.println(bfs(f, s, g, u, d)); //현재 위치에서 bfs 시작
    }

    public static String bfs(int totalFloor, int start, int targetFloor, int up, int down){
        Queue<Gangho> q = new LinkedList<>();
        q.offer(new Gangho(start, 0));
        visited[start] = true;

        while(!q.isEmpty()){
            Gangho cur = q.poll();
            int cf = cur.floor;
            int cd = cur.dist;
            if(cf == targetFloor){
                min = Math.min(min, cd);
                return String.valueOf(min);
            }
            if((cf + up) <= totalFloor && !visited[cf+up]){     //cf+up = 현재 위치에서 위로 u만큼 올라갈 때
                visited[cf+up] = true;
                q.offer(new Gangho(cf+up, cd+1));
            }
            if((cf - down) > 0 && !visited[cf - down]){
                visited[cf-down] = true;
                q.offer(new Gangho(cf - down, cd+1));
            }
        }
        return "use the stairs";
    }
}
