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
public class SpaceShip {
    
    private Image img;
    private int x;
    private int y;
    private boolean die=false;

    public boolean isDie() {
        return die;
    }

    public void setDie(boolean die) {
        this.die = die;
    }
    
    public SpaceShip(Image img, int x, int y){
        this.img=img;
        this.x=x;
        this.y=y;
    }
    
    public Image getImage(){
        return img;
    }
    
    public void setImage(Image img){
        this.img=img;
    }
    
    public int getx(){
        return x;
    }
    
    public void setx(int x){
        this.x=x;
    }
    
    public int gety(){
        return y;
    }
    
    public void sety(){
        this.y=y;
    }
    
}
