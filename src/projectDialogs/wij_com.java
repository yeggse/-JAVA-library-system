package projectDialogs;

import javax.swing.*;
import java.awt.*;

public class wij_com {
    public static void main(String args[]){

        Dimension dim = new Dimension(430, 400);  //단순 2차원값 입력을 위한 클래스

        JFrame frame = new JFrame("wij complex");
        frame.setLocation(0, 0);				  //출력 위치를 화면 좌상단에 위치
        frame.setPreferredSize(dim);              //위 코드에서 지정한 좌표를 프레임 사이즈로 사용
        frame.setLayout(null);                    //레이아웃을 사용하지 않고 절대좌표계 사용

        String header[]={"도 서 명", "출 판 사", "작 가", "위 치","대여여부"};
        String contents[][]={
                {"이정현", "50", "60", "70", "70"},
                {"김영호", "70", "80", "75", "70"},
                {"전수용", "80", "65", "95", "70"},
                {"김진희", "80", "65", "95", "70"},
                {"신정섭", "85", "60", "85", "70"},
                {"김승현", "80", "65", "95", "70"},
                {"김영석", "80", "65", "95", "70"},
                {"이정석", "80", "65", "95", "70"},
                {"이승근", "80", "65", "95", "70"},
        };

        JTable table = new JTable(contents, header);
        //table.setLocation(0,0);

        JScrollPane jscp1 = new JScrollPane(table); //이런식으로 생성시에 테이블을 넘겨주어야 정상적으로 볼 수 있다.
                                                    //jscp1.add(table); 과 같이 실행하면, 정상적으로 출력되지 않음.
        jscp1.setLocation(0,0);
        jscp1.setSize(300,160);

        frame.add(jscp1);


        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //닫기버튼 클릭시 프로그램 종료

    }
}