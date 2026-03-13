package bubble;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class Bubble extends JLabel {

    private int x;
    private int y;

    private ImageIcon bubbleIcon;
    private Player player;




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

}


