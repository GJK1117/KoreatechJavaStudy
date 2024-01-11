import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1197_최소신장트리 {
    static List<ArrayList<Integer>> trees = new ArrayList<>();  //각 노드1, 노드2, 가중치를 저장할 리스트
    static int[] node;  //유니온 파인드 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        node = new int[V+1];
        for(int i=1; i<V+1; i++) node[i] = i;   //유니온 파인드 배열 초기화
        int res = 0;    //결과값 변수

        for(int i=1; i<E+1; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            //노드1, 노드2, 가중치 저장
            ArrayList<Integer> tree = new ArrayList<>();
            tree.add(x);
            tree.add(y);
            tree.add(value);
            trees.add(tree);
        }
        //가중치 기준 오름차순 정렬
        trees.sort((o1, o2) -> Integer.compare(o1.get(2), o2.get(2)));

        //find 연산 수행 해 대표노드가 같지 않으면 union 연산 수행 -> 대표노드 같도록 해줌
        //해당하는 가중치 결과값에 저장
        //대표노드가 같으면 사이클이 생기므로 연산 수행 x
        for (ArrayList<Integer> list : trees) {
            if(find(list.get(0)) != find(list.get(1))){
                union(list.get(0), list.get(1));
                res += list.get(2);
            };
        }
        System.out.println(res);    //결과값 출력
    }

    public static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a != b){
            node[b] = a;
        }
    }
    //find 연산
    public static int find(int a){
        if (a==node[a]) return a;
        else return node[a] = find(node[a]);
    }
}
