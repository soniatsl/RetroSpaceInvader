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
public class Bullet {
    
//    private Console console;
    private int x;
    private int y;
    private Image img;
    boolean Visible=false;

    public boolean isVisible() {
        return Visible;
    }

    public void setVisible(boolean Visible) {
        this.Visible = Visible;
    }

    public Image getImg() {
        return img;
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

    public void setImg(Image img) {
        this.img = img;
    }
    
    public Bullet(Image img, int x, int y){
//        this.console=console;
        this.x=x;
        this.y=y;
        this.img = img;
    }
    
    
    
}
