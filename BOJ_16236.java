import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16236 {
    static int n, startX, startY;
    static int[][] map;
    static int[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static List<Pair> fishes;
    static Queue<Pair> shark = new LinkedList<>();

    static public class Pair {
        int x;
        int y;
        int dist;

        Pair(int x, int y, int dist) {
            super();
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {                                 //상어의 위치를 startX, startY에 담아준다
                    startX = i;
                    startY = j;
                    shark.offer(new Pair(i, j, 0));
                    map[i][j] = 0;
                }
            }
        }
        int time = 0;
        int size = 2;
        int eat = 0;

        while (true) {
            fishes = new ArrayList<>();
            visited = new int[n][n];
            while (!shark.isEmpty()) {

                Pair cur = shark.poll();
                int cx = cur.x;
                int cy = cur.y;
                for (int i = 0; i < 4; i++) {
                    int nx = cx + dx[i];
                    int ny = cy + dy[i];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                    if (map[nx][ny] <= size && visited[nx][ny] == 0) {
                        visited[nx][ny] = visited[cx][cy] + 1;
                        shark.offer(new Pair(nx, ny, visited[nx][ny]));
                        if (1 <= map[nx][ny] && map[nx][ny] <= 6 && map[nx][ny] < size) {
                            fishes.add(new Pair(nx, ny, visited[nx][ny]));
                        }
                    }
                }
            }

            if (fishes.size() == 0) {
                System.out.println(time);
                return;
            }

            Pair curFish = fishes.get(0);
            for (int i = 1; i < fishes.size(); i++) {
                if (curFish.dist > fishes.get(i).dist) {
                    curFish = fishes.get(i);
                } else if (curFish.dist == fishes.get(i).dist) {
                    if (curFish.x > fishes.get(i).x) {
                        curFish = fishes.get(i);
                    } else if (curFish.x == fishes.get(i).x) {
                        if (curFish.y > fishes.get(i).y)
                            curFish = fishes.get(i);
                    }
                }
            }

            time += curFish.dist;
            eat++;
            map[curFish.x][curFish.y] = 0;
            if (eat == size) {
                eat = 0;
                size++;
            }
            shark.offer(new Pair(curFish.x, curFish.y, 0));

        }


    }
}
    /*public static void bfs(int x, int y){
        Queue<Pair> shark = new LinkedList<>();
        shark.offer(new Pair(x, y, 0));             //아기상어의 크기와 좌표를 넣어준다

        while(!shark.isEmpty()){
            fishes = new ArrayList<>();
            visited = new int[n][n];                //거리를 기록하는 배열은 상어가 이동하고 난뒤 초기화해줘야한다
            Pair cur = shark.poll();
            int cx = cur.x;
            int cy = cur.y;
            for(int i=0;i<4;i++){
                int nx = cx+dx[i];
                int ny = cy+dy[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if(visited[nx][ny] == 0){
                    if(map[nx][ny] == 0){                   //빈칸이라면 그대로 이동
                        visited[nx][ny] = visited[cx][cy] + 1;
                        shark.offer(new Pair(nx, ny, visited[nx][ny]));
                    }else{                                  //물고기가 있는 칸에서
                        if(map[nx][ny] > size) continue;    //만약 아기상어의 크기보다 크다면 넘어가고
                        else{                               //아기 상어가 먹을 수 있다면?
                            visited[nx][ny] = visited[cx][cy] + 1;
                            fishes.add(new Pair(nx, ny, visited[nx][ny]));       //먹을 수 있는 물고기들을 리스트에 담아준다.
                        }
                    }
                }
            }
        }
        if(fishes.size() == 0){
            System.out.println(time);
            return;
        }

        Pair curFish = fishes.get(0);       //물고기 리스트를 정렬하는 과정
        for(int i=1;i< fishes.size();i++){
            if(curFish.dist > fishes.get(i).dist){
                curFish = fishes.get(i);                //제일 거리가 가까운 물고기
            }else if(curFish.dist == fishes.get(i).dist){
                if(curFish.x > fishes.get(i).x) curFish = fishes.get(i);
                else if(curFish.x == fishes.get(i).x){
                    if(curFish.y > fishes.get(i).y)
                        curFish = fishes.get(i);
                }
            }
        }

        time += curFish.dist;                   //물고기와의 거리만큼 시간이 걸렸으니 더해주면서
        stack++;                                //한입먹을때마다 스택증가
        map[curFish.x][curFish.y] = 0;          //물고기를 먹었으니까 0으로 바꿔주고
        if(stack == size){
            size++;
            stack = 0;
        }
        shark.offer(new Pair(curFish.x, curFish.y, 0));
    }*/




/*
1. 자신의 크기와 같은 수의 물고기를 먹으면 크기가 1 증가한다.
1.5 거리가 가까운 애들 순서대로 먹는다 -> dist로 판단
2. 거리가 가까운 물고기가 많다면 가장 위의 물고기 -> x값이 낮은 애 먼저, 그런 애들이 또 여러마리면 가장 왼쪽 -> y의 값이 작은 애들
 */