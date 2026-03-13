package test04;

import javax.swing.*;

public class Bubble extends JLabel {

    private int x;
    private int y;

    private ImageIcon bubbleIcon;
    private Player player;
    private boolean Space = false;

    private final int SPEED = 4; //좌우 이동 속도 (픽셀)


    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    //DI <- 용어 기억하기
    public Bubble(Player player) {
        this.player = player;
        initData();
        setInitLayout();

    }

    private void initData() {

        bubbleIcon = new ImageIcon("img/bubble.png");


    }

    private void setInitLayout() {
        x = player.getX();
        y = player.getY();
        setIcon(bubbleIcon);
        setSize(50, 50);
        setLocation(x, y);
        setVisible(true);
    }

    private void Space() {
        if (Space) {
            return;
        }
        Space = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < SPEED + 50; i++) {
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
}


