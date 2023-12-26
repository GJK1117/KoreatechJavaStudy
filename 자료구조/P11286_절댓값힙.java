package 자료구조;

import java.io.*;
import java.util.PriorityQueue;

public class P11286_절댓값힙 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        //우선순위 큐에 조건 설정하는 방법
        PriorityQueue<Integer> Myqueue = new PriorityQueue<>((o1, o2) -> {
            //파라미터로 받은 o1, o2는 object형으로 들어감
            //절댓값 구하는 함수 Math.abs
            int first = Math.abs(o1);
            int second = Math.abs(o2);
            /*
            -------중요!-------
            o1, o2 중 o1에게 우선순위를 주고 싶다면 음수값 return하면 됨
            */
            if(first == second){    //절댓값이 같으면
                //o1이 작으면(음수이면) o1에 우선순위 부여, 아닐 경우 o2에 우선순위됨
                return o1 > o2 ? 1 : -1;
            }
            else                    //절댓값이 다르면
                //first가 작을 경우 음수가 return되므로 o1에 우선순위 부여됨
                //first가 클 경우 양수가 return되므로 o2에 우선순위 부여됨
                return first - second;
        });
        for(int i=0; i<N; i++){
            int input = (Integer.parseInt(br.readLine()));
            if(input == 0){
                if(!Myqueue.isEmpty()) {
                    /*
                    bw.write를 하면 출력되는 줄 알았지만
                    bw.write는 버퍼에 쓸 뿐이고 bw.flush를 함께 써줘야
                    println과 같이 출력해줄 수 있음
                    */
                    bw.write(String.valueOf(Myqueue.poll()));
                    bw.newLine();
                    bw.flush();
                }
                else{
                    bw.write('0');
                    bw.newLine();
                    bw.flush();
                }
            }
            else
                Myqueue.add(input);
        }
        bw.close();
    }
}