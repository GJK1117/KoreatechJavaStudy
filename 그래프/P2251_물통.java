import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P2251_물통 {
    static boolean[][][] visited = new boolean[201][201][201];  //방문 체크 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Queue<ArrayList<Integer>> queue = new LinkedList<>();   //BFS를 위한 큐
        ArrayList<Integer> list = new ArrayList<>();    //물통의 용량 배열
        List<Integer> res = new ArrayList<>();  //결과값 배열
        ArrayList<Integer> temp;    //임시 배열
        for(int i=0; i<3; i++){
            int n = Integer.parseInt(st.nextToken());
            list.add(n);
        }

        queue.offer(new ArrayList<>(Arrays.asList(0, 0, list.get(2)))); //처음 물통의 상태를 큐에 넣어줌

        //BFS 사용
        while(!queue.isEmpty()){
            ArrayList<Integer> poll = queue.poll();
            //물통의 A 물통이 비어있는 경우 C 물통 값을 중복되지 않게 결과값 배열에 넣어준다.
            if(poll.get(0)==0) {
                if(!res.contains(poll.get(2))) res.add(poll.get(2));
            }
            for(int i=0; i<3; i++){
                if(poll.get(i)==0) continue;    //현재 검사하는 물통의 값이 0인 경우는 물을 옮길 수 없으므로 continue

                //물을 옮길 수 있는 경우 A->(B,C), B->(A,C), C->(A,B)
                //각각 인덱스로 접근해줌 -> 0->(1,2), 1->(0,2), 2->(0,1)
                for(int j=1; j<=2; j++) {
                    temp = new ArrayList<>(poll);   //임시 배열에 현재 물통의 상태를 넣어줌

                    //현재 물통의 값이 다른 물통의 용량보다 작거나 같은 경우
                    //현재 물통의 값만큼 다른 물통에 모두 옮기고 현재 물통은 비어있게 됨
                    if (poll.get(i) <= list.get((i + j) % 3) - poll.get((i + j) % 3)) {
                        int k = temp.get((i + j) % 3);
                        temp.set((i + j) % 3, k+temp.get(i));
                        temp.set(i, 0);

                        //방문하지 않았으면 큐에 넣어주고 방문 체크
                        if (!checkVisited(temp)) {
                            queue.offer(temp);
                            visited[temp.get(0)][temp.get(1)][temp.get(2)] = true;
                        }
                    }
                    //현재 물통의 값이 다른 물통의 용량보다 큰 경우
                    //다른 물통의 용량만큼 현재 물통에서 빼주고 다른 물통은 가득차게 됨
                    else {
                        int k = temp.get(i);
                        k -= list.get((i + j) % 3) - poll.get((i + j) % 3);
                        temp.set(i, k);
                        temp.set((i + j) % 3, list.get((i + j) % 3));

                        //방문하지 않았으면 큐에 넣어주고 방문 체크
                        if (!checkVisited(temp)) {
                            queue.offer(temp);
                            visited[temp.get(0)][temp.get(1)][temp.get(2)] = true;
                        }
                    }
                }
            }
        }
        //출력 오름차순 이므로 정렬해줌
        Collections.sort(res);
        for (Integer re : res) {
            System.out.print(re + " ");
        }
    }

    //방문 체크 메서드
    public static boolean checkVisited(ArrayList<Integer> temp){
        //이미 방문 했으면 true
        if(visited[temp.get(0)][temp.get(1)][temp.get(2)]) return true;
        else return false;
    }
}
