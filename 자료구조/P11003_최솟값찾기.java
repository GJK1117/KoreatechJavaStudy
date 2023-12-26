package 자료구조;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class P11003_최솟값찾기 {
    public static void main(String[] args) throws IOException {
        //입출력을 위한 br bw st 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   //전체 숫자의 수
        int L = Integer.parseInt(st.nextToken());   //최솟값을 구할 영역의 크기
        st = new StringTokenizer(br.readLine());
        /*
        정렬 알고리즘을 사용하면 시간복잡도가 nlogn으로 최대 범위가 5000000인 이 문제에선 사용 불가
        그러므로 슬라이딩 윈도우를 deque로 구현해 정렬 효과를 볼 수 있음
        Node의 구조는 value와 index로 구성
         */
        Deque<Node> mydeque = new LinkedList<>();

        for(int i=0; i<N; i++){
            int cur = Integer.parseInt(st.nextToken()); //현재 탐색중인 숫자
            //덱이 비어있지 않고, 덱의 마지막 값이 cur보다 크면 덱의 마지막 값을 삭제
            //이 과정을 통해 덱에 숫자가 정렬되어 들어감
            while (!mydeque.isEmpty() && mydeque.getLast().value > cur){
                mydeque.removeLast();
            }
            mydeque.addLast(new Node(cur, i));  //덱의 마지막에 cur과 i를 value와 index로 추가
            //index의 범위가 슬라이딩윈도우의 크기(L)을 벗어나면 첫 번째 노드 제거
            if(mydeque.getFirst().index <= i-L){
                mydeque.removeFirst();
            }
            bw.write(mydeque.getFirst().value + " ");   //최솟값을 출력하기 위해 deque의 첫 번째 value를 출력
        }
        //BufferedWriter는 버퍼를 비워주기 위해 flush로 남은 문자를 출력 후 close를 해줘야함
        bw.flush();
        bw.close();
    }
    static class Node{
        public int value;
        public int index;
        Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}
