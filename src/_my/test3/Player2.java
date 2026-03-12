package _my.test3;

import javax.swing.*;

public class Player2 extends JLabel implements Moveable2 {

    //플레이어 좌표
    private int x;
    private int y;

    //좌우 방향 이미지
    private ImageIcon playerL;
    private ImageIcon playerR;

    //속도 상수
    private final int SPEED = 4; //좌우 이동속도
    private final int JUMP_SPEED = 2; //점프 /낙하 속도
    private final int JUMP_HEIGHT = 130; //점프 최대 높이

    //이동 상태 플래그
    //true = 해당 방향으로 이동 중(while 루프 조건)
    //false = 멈춤( while 루프 탈출 -> thread 종료)
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    //getter
    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getX() {
        return x;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeftWallCrash() {
        return leftWallCrash;
    }

    public boolean isRightWallCrash() {
        return rightWallCrash;
    }

    //setter


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeftWallCrash(boolean leftWallCrash) {
        this.leftWallCrash = leftWallCrash;
    }

    public void setRightWallCrash(boolean rightWallCrash) {
        this.rightWallCrash = rightWallCrash;
    }

    //벽 충돌 상태 플래그
    private boolean leftWallCrash;
    private boolean rightWallCrash;

    public Player2() {
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
            return; //이미 이동 중이면 중복 쓰레드 생성 방지
        }
        left = true;
        setIcon(playerL);

        new Thread(new Runnable() {
            @Override
            public void run() {
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

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (right) {
                    x = x + SPEED;
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
        if (up) {
            return;
        }
        up = true;

        for (int i = 0; i < JUMP_HEIGHT / JUMP_SPEED; i++) {
            y = y - JUMP_SPEED;
            setLocation(x, y);

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        up =false;
        down();

    }

    @Override
    public void down() {
        down = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < JUMP_HEIGHT/JUMP_SPEED; i++) {
                    y = y + JUMP_SPEED;
                    setLocation(x, y);

                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                down = false;
            }
        }).start();

    }
}
