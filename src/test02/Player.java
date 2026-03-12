package test02;

import javax.swing.*;

public class Player extends JLabel implements Moveable {
    //플레이어의 현재 좌표 상태 값
    private int x;
    private int y;

    //좌우 방향 이미지(방향키에 따라서 이미지 전환)
    private ImageIcon playerR;
    private ImageIcon playerL;

    //속도 상수
    private final int SPEED = 4; //좌우 이동 속도 (픽셀)
    private final int JUMP_SPEED = 2; //점프 /낙하 속도
    private final int JUMP_HEIGHT = 130; //점프 최대 높이

    //이동 상태 플래스
    //true = 해당 방향으로 이동 중(while 루프 조건)
    //false = 멈춤( while 루프 탈출 -> thread 종료)
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    //BubbleFrame의 Key 이벤트에서 호출할 수 있도록 setter설정
    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public Player() {
        initData();
        setInitLayout();

    }

    private void initData() {
        playerR = new ImageIcon("img/playerR.png");
        playerL = new ImageIcon("img/playerL.png");
    }

    private void setInitLayout() {
        //캐릭터 초기 위치 설정
        x = 55;
        y = 555;
        setSize(50, 50);
        setIcon(playerR); // 초기 방향 설정
        setLocation(x, y);

    }

    @Override
    public void left() {
        if (left) {
            return; //이미 이동 중이면 중복 Thread 생성 방지
        }
        left = true;//빼먹지말것
        setIcon(playerL);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //left 가 true 인 동안 계속 이동
                //keyReleased에서 setLeft(false)가 호출이 되면 while 탈출
                while (left) {
                    x = x - SPEED;
                    setLocation(x, y);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    @Override
    public void right() {
        if (right) {
            return;
        }
        right = true;
        setIcon(playerR);

        // new Thread().start();//메서드 체인
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (right) {
                    x = x - SPEED;
                    setLocation(x, y);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }).start();

    }

    @Override
    public void up() {








    }

    @Override
    public void down() {


    }
}
