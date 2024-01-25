import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class P2775_부녀회장이될테야 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Node> list = new ArrayList<>();
        int T = Integer.parseInt(br.readLine());    //테스트케이스 수
        int max = 0;            //문제에서 주어진 층수 중 가장 큰 값
        for(int i=0; i<T; i++){
            int x = Integer.parseInt(br.readLine());
            int y = Integer.parseInt(br.readLine());
            if(x >= max) max = x;
            list.add(new Node(x, y));       //층수, 호수 각각 넣어준다.
        }
        int[][] home = new int[max+1][14];  //아파트[층][호] -> 층은 아까 문제에서 주어진 층수 중 가장 큰 값으로 정해줌
        for(int i=0; i<max+1; i++) home[i][0] = 1;  //각 층의 1호를 모두 1로 초기화
        for(int i=0; i<14; i++) home[0][i] = i+1;   //0층의 각 호수를 각각의 1~14로 초기화

        for(int i=1; i<max+1; i++){
            for(int j=1; j<14; j++){
                home[i][j] = home[i-1][j] + home[i][j-1];;  //점화식 사용해 각각의 집마다 들어가는 인원 초기화
            }
        }
        for (Node node : list) {
            System.out.println(home[node.x][node.y-1]); //결과 값 출력
        }
    }
    static class Node{
        int x;  //층
        int y;  //호
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
