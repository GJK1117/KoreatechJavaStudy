package 트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14425_문자열찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        tNode root = new tNode();
        //트라이 자료구조 구축
        while (N>0){
            String temp = br.readLine();
            tNode cur = root;
            for(int i=0; i<temp.length(); i++){
                char c = temp.charAt(i);
                int index = c - 'a';
                //공백 상태이면 신규 노드 생성
                if(cur.next[index] == null){
                    cur.next[index] = new tNode();
                }
                //공백 상태가 아니면 다음 노드로 넘어감
                cur = cur.next[index];
                //리프 노드 표시
                if(i == temp.length() - 1)
                    cur.isEnd = true;
            }
            N--;
        }
        int result = 0;
        while(M>0){
            String temp = br.readLine();
            tNode cur = root;
            //문자열 검사
            for(int i=0; i<temp.length(); i++){
                char c = temp.charAt(i);
                int index = c - 'a';
                //해당 인덱스가 공백이면 틀린 것
                if(cur.next[index] == null)
                    break;
                cur = cur.next[index];
                //temp의 마지막이 리프 노드일 때 정답
                if(i == temp.length() - 1 && cur.isEnd)
                    result++;
            }
            M--;
        }
        System.out.println(result);
    }
    static class tNode{
        tNode[] next = new tNode[26];
        boolean isEnd;
    }
}