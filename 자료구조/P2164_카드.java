import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2164_카드 {
    public static void main(String[] args) throws IOException{
        // 버퍼 입력 사용
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 스페이스 단위로 입력하기 위해 토큰 선언 및 초기화, 엔터 입력 후 다음 입력 시에 항상 이 구문을 사용하여 토큰 초기화
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // 입력할 숫자 개수 변수
        int N = Integer.parseInt(stringTokenizer.nextToken());
        // 큐 선언
        Queue<Integer> q = new LinkedList<>();

        // 큐에 1부터 N까지 순차적으로 추가
        for(int i = 1; i <= N; i+=0){
            q.add(i);
        }

        // 큐의 크기가 1이 될 때까지 반복
        while(q.size()!=1){
            // 카드 버리기와 맨 뒤로 카드 보내기 반복
            q.poll();
            q.add(q.poll());
        }
        // 남은 마지막 한 장 출력
        System.out.println(q.poll());
    }
}
