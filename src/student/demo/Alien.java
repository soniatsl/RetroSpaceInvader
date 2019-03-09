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
public class Alien {
    
    private Image img1;
    private Image img2;
    private Image[] images={img1,img2};
    private int x;
    private int y;
    private int i;
    boolean isToLeft = false;
    boolean isVisible = true;
    boolean Die = false;

    public boolean isDie() {
        return Die;
    }

    public void setDie(boolean Die) {
        this.Die = Die;
    }
    
    public boolean isIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
    
//    public boolean isIsToRight(){
//        return isToRight;
//    }
//    
//    public void setToRight(boolean toRight){
//        this.isToRight = toRight;
//    }

    public boolean isIsToLeft() {
        return isToLeft;
    }

    public void setToLeft(boolean toLeft) {
        this.isToLeft = toLeft;
    }
    
    public Image[] getImages(){
        return images;
    }
    
    public void setImages(Image[] images){
        this.images=images;
    }
    
    
    public int geti(){
        return i;
    }
    
    public void seti(int i){
        this.i=i;
    }
    
    public Alien(Image[] images, int x, int y, int i){
        this.images=images;
        this.x=x;
        this.y=y;
        this.i=i;
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

}
