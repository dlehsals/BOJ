import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj_1753 {
    static public class Node implements Comparable<Node>{
        int index;
        int weight;
        Node(int index, int weight){
            this.index = index;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
    static int[] distance;
    static ArrayList<ArrayList<Node>> nodeList;
    static int V, E, start;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());

        distance = new int[V+1];
        Arrays.fill(distance,Integer.MAX_VALUE);
        nodeList = new ArrayList<ArrayList<Node>>();
        for(int i=0;i<V+1;i++){
            nodeList.add(new ArrayList<Node>());    //노드리스트 초기화
        }

        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            nodeList.get(u).add(new Node(v, w));
        }

        distance[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node currentNode = pq.poll();
            if(distance[currentNode.index] < currentNode.weight) continue;   //만약 선택한 노드의 최소비용이 지금 비용보다 크다면 넘어간다.
            for(int i=0;i<nodeList.get(currentNode.index).size();i++){
                Node nextNode = nodeList.get(currentNode.index).get(i);
                if(distance[nextNode.index] > currentNode.weight + nextNode.weight){
                    distance[nextNode.index] = currentNode.weight + nextNode.weight;
                    pq.offer(new Node(nextNode.index, distance[nextNode.index]));
                }
            }
        }
        for(int i=1;i<=V;i++){
            System.out.println(distance[i] == Integer.MAX_VALUE ? "INF" : distance[i]);
        }
    }
}