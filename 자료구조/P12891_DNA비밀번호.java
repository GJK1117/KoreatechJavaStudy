package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P12891_DNA비밀번호 {
    static int checkArr[];
    static int myArr[];
    static int checkSecret;

    public static void main(String[] args) throws IOException {
        //BufferedReader를 사용할 경우 버퍼를 이용하므로 Scanner를 사용한 것에 비해 전달속도가 빠름
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader는 엔터키만 경계로 인식하고, String으로만 입력받을 수 있음(형변환 필수)
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int S = Integer.parseInt(st.nextToken());   //전체 문자열의 길이
        int P = Integer.parseInt(st.nextToken());   //슬라이딩윈도우의 크기(암호의 길이)
        int Result = 0;                             //경우의 수를 세는 변수
        char A[] = new char[S];                     //전체 문자열

        //각각의 문자(A, C, G, T)의 요구 개수 설정하는 배열
        checkArr = new int[4];
        //각각의 문자(A, C, G, T)의 실제 개수를 카운트하는 배열
        myArr = new int[4];
        //각각의 문자(A, C, G, T)가 요구한 개수만큼 들어갔는지 세는 변수(4가 되어야 기준에 부합하는것)
        checkSecret = 0;
        //전체 문자열 입력받아 배열에 저장
        A = bf.readLine().toCharArray();
        //문자열에 포함되어야할 A, C, G, T의 개수 입력받아 StringTokenizer에 저장
        st = new StringTokenizer(bf.readLine());

        //요구 개수 설정(요구하는 개수가 0일 경우에는 기준에 이미 부합한 것으로 판단 후 checkSecret++)
        for(int i=0; i<4; i++){
            checkArr[i] = Integer.parseInt(st.nextToken());
            if(checkArr[i] == 0){
                checkSecret++;
            }
        }

        //처음 P부분 Add해주는 부분
        for(int i=0; i<P; i++){
            Add(A[i]);
        }
        //기준에 부합하면 Result++
        if(checkSecret == 4)
            Result++;
        /*
        핵심
        슬라이딩윈도우 처리부분
        슬라이딩윈도우가 한칸 이동하면 새로운 문자는 Add하고 맨 첫번째 문자는 Remove함
         */
        for(int i=P; i<S; i++){
            int j = i - P;
            Add(A[i]);
            Remove(A[j]);
            if(checkSecret == 4)
                Result++;
        }
        System.out.println(Result);
        bf.close();
    }

    private static void Add(char c){
        //알파벳을 하나 받아와서 case에 따라 myArr++, myArr이 checkArr과 같아지면 기준에 부합한 것이므로 checkSecret++
        switch (c){
            case 'A':
                myArr[0]++;
                if(myArr[0] == checkArr[0])
                    checkSecret++;
                break;
            case 'C':
                myArr[1]++;
                if(myArr[1] == checkArr[1])
                    checkSecret++;
                break;
            case 'G':
                myArr[2]++;
                if(myArr[2] == checkArr[2])
                    checkSecret++;
                break;
            case 'T':
                myArr[3]++;
                if(myArr[3] == checkArr[3])
                    checkSecret++;
                break;
        }
    }

    private static void Remove(char c){
        //슬라이딩윈도우가 이동할 때 기준 문자(맨 앞쪽을 지워야함)를 지우는 함수
        switch (c){
            case 'A':
                if(myArr[0] == checkArr[0])
                    checkSecret--;
                myArr[0]--;
                break;
            case 'C':
                if(myArr[1] == checkArr[1])
                    checkSecret--;
                myArr[1]--;
                break;
            case 'G':
                if(myArr[2] == checkArr[2])
                    checkSecret--;
                myArr[2]--;
                break;
            case 'T':
                if(myArr[3] == checkArr[3])
                    checkSecret--;
                myArr[3]--;
                break;
        }
    }
}
