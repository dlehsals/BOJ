import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17141 {
    static int n, m;
    static String[][] map, copy;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean isOK;
    static int min;
    static int time;
    static Queue<Pair> q;
    static List<Integer> answer;
    static List<Pair> virusList;
    
    //바이러스를 퍼트리는데 걸리는 시간을 표시하려고 size 추가
    static public class Pair {              
        int x;
        int y;
        int size;

        Pair(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        answer = new ArrayList<>();
        map = new String[n][n];
        copy = new String[n][n];
        visited = new boolean[n][n];
        virusList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = st.nextToken();
                //바이러스는 2로 입력받지만 퍼트리는 과정에서 헷갈리니까 #으로 바꿔줬다
                //입력함과 동시에 미리 바이러스의 좌표를 리스트에 넣어줌 -> 시간복잡도 줄이기
                if (map[i][j].equals("2")) {
                    virusList.add(new Pair(i, j, 0));
                    map[i][j] = "#";
                }
            }
        }
        
        //바이러스 생성(완전탐색)
        makeVirus(0, 0);
        
        //0이 있는 경우는 퍼트리는데 걸린 시간을 answer 리스트에 넣어주지 않고 넘어가므로
        //answer.size() == 0 이란 뜻은 바이러스를 어떻게 배치를 하든 모든 구역에 퍼트릴 수 없다는 의미이다.
        if (answer.size() == 0) {
            System.out.println(-1);
            System.exit(0);
        }
        
        //그게 아니라면 걸린 시간들 중에서 제일 최소값을 찾기
        System.out.println(Collections.min(answer));
    }

    public static void makeVirus(int index, int count) {
        
        //최소값을 구하기 위해 정수중에 제일 큰 값으로 설정(바이러스 위치를 바꿀때마다 비교해야되므로 제일 상단에서 계속 초기화시킴)
        min = Integer.MAX_VALUE;
        if (count == m) {
            visited = new boolean[n][n];
            q = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    //#으로 표시된 부분은 바이러스이고 *로 표시된 부분은 바이러스 배치를 했을 때 퍼트릴 수 있는 부분이다
                    if (map[i][j].equals("*"))
                        //퍼트릴 수 있는 부분 중에서 실제로 퍼트려 볼 애들을 큐에 넣어준다.
                        q.offer(new Pair(i, j, 0));
                    //벽은 1로 표시되었지만 마찬가지로 바이러스를 퍼트리면서 헷갈리기때문에 -로 바꿔줬음
                    else if (map[i][j].equals("1"))
                        map[i][j] = "-";
                    
                    //배열을 복사한 이유는
                    //바이러스를 퍼트리면서 기존의 값을 덮어씌우게 되면 최소시간을 구할 수 없기 때문에
                    //BFS를 통해 비교해나갈때는 map 배열값을 이용하고, 실제 값 반영은 copy배열에 해줬음
                    
                    copy[i][j] = map[i][j];
                }
            }

            time = spreadVirus();           //0이 없는 경우의 최소시간
            //print();
            
            //0이 존재하게 되면 바이러스를 퍼트린 시간자체가 필요없으므로 리턴
            if (isExist()) return;
            
            //0이 없다면 answer 리스트에 걸린 시간을 담아준다.
            answer.add(time);
            return;
        }
        
        //입력값에 따라 2중for문을 매번 돌리게되면 메모리터지고 시간초과나서 미리 바이러스의 좌표들 리스트를 만들어준다.
        for (int i = index; i < virusList.size(); i++) {    
            int x = virusList.get(i).x;
            int y = virusList.get(i).y;
            //바이러스를 퍼트릴부분은 *로 바꿔주고
            map[x][y] = "*";
            //m개까지 퍼트리므로 count를 1, 다음 좌표를 찾기위해 i도 1 증가시킨다.
            makeVirus(i + 1, count + 1);
            //완전 탐색을 끝내고 return되면 체크했던 *를 #으로 다시 바꿔준다.
            //값 자체를 바꾸기 때문에 굳이 visited배열이 필요없음
            map[x][y] = "#";
        }

    }
    
    //큐에 넣어줬던(*로 바꿔준) 애들을 BFS실행
    public static int spreadVirus() {
        int tmp = Integer.MIN_VALUE;
        
        //방문체크는 함수 파라미터로 받아온게 없으니까 while문 안에서 해줬고
        while (!q.isEmpty()) {      
            Pair cur = q.poll();
            int cx = cur.x;
            int cy = cur.y;
            int cs = cur.size;
            visited[cx][cy] = true;
            
            //상하좌우확인하면서
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                //맵밖으로 나가면 제끼고
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                //방문하지 않은 애들중에서
                if (!visited[nx][ny]) {
                    //빈칸이거나 바이러스를 퍼트릴 수 있었는데 퍼트리지 않은 부분은 빈칸과 같기 때문에
                    if (map[nx][ny].equals("#") || map[nx][ny].equals("0")) {
                        //바이러스를 퍼트려준다. cs는 바이러스를 퍼트리는데에 걸린 시간이므로 +1씩 계속 증가시켜줌
                        copy[nx][ny] = String.valueOf(cs + 1);
                        visited[nx][ny] = true;
                        //다음 퍼트릴 곳으로 넣어줌
                        q.offer(new Pair(nx, ny, cs + 1));
                        //바이러스를 실제로 퍼트릴 곳이 붙어있는 경우에 예외처리를 위해서 제껴준다.
                        //이부분 처리안하면 붙어있는 경우 시간이 0이 나와야 하는데 1이 나와서 틀림
                    }else if(map[nx][ny].equals("*")){
                        continue;
                    }
                }
            }
            // 상하좌우 다 체크하고 현재 좌표에서 걸린 시간과 최소값 비교해서 tmp로 최대값 리턴 
            tmp = Math.max(tmp, cs);
        }
        return tmp;
    }
    
    //0이 하나라도 존재하면 true
    public static boolean isExist() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (copy[i][j].equals("0"))
                    return true;
            }
        }
        return false;
    }

    public static void print(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(copy[i][j]+" ");
            }
            System.out.println();
        }
    }
}
