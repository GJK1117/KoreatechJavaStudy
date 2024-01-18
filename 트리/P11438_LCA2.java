import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P11438_LCA2 {
    static int N, M, maxDep;
    static ArrayList<Integer>[] tree;
    static boolean[] visited;
    static int[][] parent;
    static int[] nodeDep;  //노드 깊이
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Queue<Integer> queue = new LinkedList<>();
        N = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N+1];
        visited = new boolean[N+1];
        nodeDep = new int[N+1];
        for(int i=1; i<N+1; i++) tree[i] = new ArrayList<>();
        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            tree[x].add(y);
            tree[y].add(x);
        }
        int temp = 1;
        maxDep = 0;
        while (temp <= N) {
            temp *= 2;      //현재 트리의 최대 깊이 구하기
            maxDep++;
        }
        parent = new int[maxDep+1][N+1];   //각 노드의 부모 노드 저장 배열 [깊이][노드]

        //노드 깊이 구함
        queue.offer(1);
        int cnt=0, q_size=1, level=1;
        visited[1] = true;
        while(!queue.isEmpty()){
            Integer v = queue.poll();
            for (Integer next : tree[v]) {
                if(visited[next]) continue;
                queue.offer(next);
                nodeDep[next] = level;
                parent[0][next] = v;   //현재 노드의 바로 위의 부모노드 저장
                visited[next] = true;
            }
            cnt++;
            if(cnt==q_size){
                cnt = 0;
                q_size = queue.size();
                level++;
            }
        }
        setParent();    //각 노드의 2^k번째에 위치한 부모노드 저장

        M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++){
            //공통 조상 찾을 두 노드 입력
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            System.out.println(LCA(x, y));;
        }
    }

    static int LCA(int x, int y){
        //더 깊은 노드를 y로 변경
        if(nodeDep[x] > nodeDep[y]){    //x 가 더 깊다면 y 가 더 깊은 노드가 되도록 변경
            int tmp = x;
            x = y;
            y = tmp;
        }
        //x와 y의 깊이를 맞춤
        for(int k = maxDep; k>=0; k--){
            if(nodeDep[y]-nodeDep[x] >= Math.pow(2, k)){
                //y 노드 깊이가 x 노드와 비슷해질 때 해당 부모노드를 y에 저장
                if(nodeDep[x] <= nodeDep[parent[k][y]]){
                    y = parent[k][y];
                }
            }
        }
        //서로의 조상 노드가 같아질때까지 노드를 위로 올려준다.
        for(int k = maxDep; k>=0; k--){
            //2^k 번째의 부모노드가 달라지는 지점을 찾는다.
            //공통 부모노드 중 깊이가 제일 깊은 노드를 찾는 것이고, 깊이가 가장 적은 노드부터 검사(maxDep 부터 시작)하기 때문에
            //최소 공통 조상이 아닌 더 깊이가 얕은 공통조상을 제외시켜주는 과정
            if(parent[k][x] != parent[k][y]){
                x = parent[k][x];
                y = parent[k][y];
            }
        }
        int lca = x;
        if(x != y) lca = parent[0][lca];    //두개의 노드가 다르다면 바로 위의 노드가 최소 공통조상이 됨
        return lca;
    }

    static void setParent(){
        for(int k=1; k<maxDep+1; k++){
            for(int j=1; j<N+1; j++){
                //점화식 사용해 각 노드의 2^k 번째의 부모노드 구하기
                parent[k][j] = parent[k-1][parent[k-1][j]];
            }
        }
    }
}
