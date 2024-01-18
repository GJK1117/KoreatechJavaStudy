import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P11725_트리의부모찾기 {
    static ArrayList<ArrayList<Integer>> nodes = new ArrayList<>(); //인접리스트
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   //노드 개수 1~N
        Queue<Integer> queue = new LinkedList<>();  //탐색 시 사용할 큐
        int[] tree = new int[N+1];      //부모노드 저장할 배열 -> 부모노드쪽으로 올라가는 것 방지
        boolean[] visited = new boolean[N+1];   //방문 표시 배열
        for(int i=0; i<N+1; i++) nodes.add(new ArrayList<>());  //인접리스트 초기화
        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            nodes.get(x).add(y);    //서로 연결
            nodes.get(y).add(x);
        }
        queue.offer(1);     //노드 1번부터 시작
        visited[1] = true;     //1번 노드 방문 표시
        while(!queue.isEmpty()){
            Integer node = queue.poll();
            for (Integer n : nodes.get(node)) {
                if(visited[n]) continue;    //방문하였으면 continue

                queue.offer(n);
                tree[n] = node;     //연결되어있으므로 부모노드 저장
                visited[n] = true;  //방문표시
            }
        }
        for(int i=2; i<N+1; i++){
            System.out.println(tree[i]);    //부모노드 출력
        }
    }
}
