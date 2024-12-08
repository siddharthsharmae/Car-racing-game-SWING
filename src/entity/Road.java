package entity;

import java.awt.Graphics;
import java.io.IOException;
import javax.imageio.ImageIO;
import carGame.AppPanel;
import carGame.KeyControls;

public class Road extends Entity {

    AppPanel appPanel;
    KeyControls keyControl;
    public static int roadSpeed;
    public float friction;

    public Road(AppPanel appPanel, KeyControls keyControl) {
        this.appPanel = appPanel;
        this.keyControl = keyControl;
        x = 0;
        y = 0;
        sizex = appPanel.SCREEN_WIDTH;
        sizey = appPanel.SCREEN_HEIGHT;
        speed = 0;
        friction = 0;
        loadRoad();
    }

    public void loadRoad() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/background/road4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyControl.up) {
            speed += acceleration;
            friction = 0;
        } else {
            friction = -0.05f;
        }
        
        if(keyControl.down) {
        	speed-=acceleration;
        }

        if (speed > 0) speed += friction;
            if (speed < 0) speed = speed -= friction;
        if(speed<0) {
        	y = (int)(y%500);
        }
        y = (int) ((y + speed) % 500);//for road to again appear
        roadSpeed = (int) speed;
    }

    public void reset() {
        speed = 0;
        friction = 0;
        y = 0;
    }

    public void paintRoad(Graphics g) {
        g.drawImage(image, x, y - 500, sizex, sizey, null);
        g.drawImage(image, x, y, sizex, sizey, null);
        g.drawImage(image, x, y+500, sizex, sizey, null);
    }
}