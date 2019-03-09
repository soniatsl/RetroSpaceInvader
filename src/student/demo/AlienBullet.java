/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.demo;

import java.awt.Image;

/**
 *
 * @author tamseelong
 */
public class AlienBullet {
    private Image img;
    private int x;
    private int y;

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public AlienBullet(Image img, int x, int y){
        this.img = img;
        this.x = x;
        this.y = y;
    }
}
