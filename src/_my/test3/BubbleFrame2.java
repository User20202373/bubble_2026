package _my.test3;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BubbleFrame2 extends JFrame {
    private JLabel backgroundMap;
    private Player2 player;


    public BubbleFrame2() {
        initData();
        setInitLayout();
        addEventListener();

        new Thread(new BackgroundPlayerService2(player)).start();

    }

    private void initData() {
        setTitle("버블 버블 게임");
        setSize(1000, 660);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        backgroundMap = new JLabel(new ImageIcon("img/backgroundMap.png"));
        setContentPane(backgroundMap);//JFrame 안에서 컴포넌트들을 올려놓는 메인 영역을 새로운 컨테이너(보통 JPanel)로 바꾸는 메서드

        player = new Player2();
    }

    private void setInitLayout() {
        setLayout(null);//좌표기반
        setResizable(false);//창 크기 고정
        setLocationRelativeTo(null);//화면 정 중앙 배치(프레임)

        backgroundMap.add(player);
        setVisible(true);
    }

    private void addEventListener() {
        //키 입력 했을 때 반응
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        player.setLeft(false);
                        break;
                    case KeyEvent.VK_RIGHT:
                        player.setRight(false);
                        break;
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (player.isLeft() == false && player.isLeftWallCrash() == false) {
                            player.left();
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (player.isRight() == false && player.isRightWallCrash() == false) {
                            player.right();
                        }
                        break;
                    case KeyEvent.VK_UP:
                        player.up();
                        break;
                }
            }
        });
    }

    public static void main(String[] args) {
        new BubbleFrame2();
    }
}



