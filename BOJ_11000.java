import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_11000 {
    static int n;
    static public class Pair implements Comparable<Pair>{
        int start;
        int end;
        Pair(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Pair o) {          //강의실의 시작시간으로 오름차순 정렬을 해준다.
            //끝나는 시간으로 
            if(this.start == o.start) return Integer.compare(this.end,o.end);
            return Integer.compare(this.start, o.start);
        }
    }
    static List<Pair> lectures = new ArrayList<>();
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lectures.add(new Pair(a, b));
        }

        Collections.sort(lectures);
        pq.offer(lectures.get(0).end);      //pq에 있다는 건 강의실이 배정되었다는 이야기

        for(int i=1;i<lectures.size();i++){     //배정되지 않은 나머지 강의들 중에서
            if(pq.peek() <= lectures.get(i).start){     //만약 배정된 종료시간보다 다음 강의가 시작하는 시간이 같거나 크다면?
                pq.poll();                              //이어서 강의를 배정할 수 있기 때문에 강의를 종료(peek)시키고
            }
            pq.offer(lectures.get(i).end);              //다음 강의를 넣어준다
        }
        System.out.println(pq.size());                  //강의실의 갯수는 pq의 사이즈와 같다.
    }
}
/*
수강신청의 마스터 김종혜 선생님에게 새로운 과제가 주어졌다.

김종혜 선생님한테는 Si에 시작해서 Ti에 끝나는 N개의 수업이 주어지는데, 최소의 강의실을 사용해서 모든 수업을 가능하게 해야 한다.

참고로, 수업이 끝난 직후에 다음 수업을 시작할 수 있다. (즉, Ti ≤ Sj 일 경우 i 수업과 j 수업은 같이 들을 수 있다.)

수강신청 대충한 게 찔리면, 선생님을 도와드리자!

첫 번째 줄에 N이 주어진다. (1 ≤ N ≤ 200,000)

이후 N개의 줄에 Si, Ti가 주어진다. (0 ≤ Si < Ti ≤ 109)

강의실의 개수를 출력하라.

입력
3
1 3
2 4
3 5

출력
2
 */