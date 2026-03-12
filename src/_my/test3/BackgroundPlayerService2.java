package _my.test3;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackgroundPlayerService2 implements Runnable {

    private BufferedImage image;
    private Player2 player;

    public BackgroundPlayerService2(Player2 player){
        this.player= player;
        try {
            image = ImageIO.read(new File("img/backgroundMapService.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // 255, 0 , 0 -> 빨간색
    private boolean isRed(Color color) {
        return color.getRed() == 255
                && color.getGreen() == 0
                && color.getBlue() == 0;
    }

    @Override
    public void run() {
        //게임이 끝날 때 까지 실행

        Color leftColor = new Color(image.getRGB(player.getX(), player.getY()+25));
        Color rightColor = new Color(image.getRGB(player.getX() +60, player.getY()+25));

        //왼쪽 충돌 감지
        if (isRed(leftColor)){
            player.setLeftWallCrash(true);
            player.setLeft(false);
        }else {
            //벽에서 벗어나면 다시 이동 가능
            player.setLeftWallCrash(false);
        }

        //오른쪽
        if (isRed(rightColor)){
            player.setRightWallCrash(true);
            player.setRight(false);
        }else {
            //벽에서 벗어나면 다시 이동 가능
            player.setRightWallCrash(false);
        }

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
