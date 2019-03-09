/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.demo;

import game.v2.Console;
import game.v2.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author tamseelong
 */
public class AlienDemo extends Game {

    Image imgA = Console.loadImage("/student/demo/img/invader64_1.png");
    Image imgB = Console.loadImage("/student/demo/img/invader64_2.png");
    Image imgC = Console.loadImage("/student/demo/img/spaceship_1.png");
    Image imgD = Console.loadImage("/student/demo/img/ufo.png");
    Image imgE = Console.loadImage("/student/demo/img/bullet.png");
    Image imgF = Console.loadImage("/student/demo/img/invader_1.png");
    Image imgG = Console.loadImage("/student/demo/img/invader_2.png");
    Image imgPause = Console.loadImage("/student/demo/img/pause.png");
    Image imgReplay = Console.loadImage("/student/demo/img/replay.png");
    Image text1 = Console.loadImage("/student/demo/img/text1.png");
    Image text2 = Console.loadImage("/student/demo/img/text2.png");
    Image Yes = Console.loadImage("/student/demo/img/yes.png");
    Image No = Console.loadImage("/student/demo/img/no.png");
    Image Score = Console.loadImage("/student/demo/img/score.png");
    Image Transparent = Console.loadImage("/student/demo/img/transparent.png");
    Image bulletInvisible = Console.loadImage("/student/demo/img/bulletTransparent.png");
    Image abulletInvisible = Console.loadImage("/student/demo/img/abulletTransparent.png");
    Image Start = Console.loadImage("/student/demo/img/start.png");
    Image gameOver = Console.loadImage("/student/demo/img/gameover.png");
    Image alienBullet = Console.loadImage("/student/demo/img/alienBullet.png");
    Image scrList = Console.loadImage("/student/demo/img/scorelist.png");
    Image shelter = Console.loadImage("/student/demo/img/shelter.png");
    Image shelterTpr = Console.loadImage("/student/demo/img/shelterTpr.png");
    Image shelterRed = Console.loadImage("/student/demo/img/shelter_red.png");
    Image shelterYellow = Console.loadImage("/student/demo/img/shelter_yellow.png");
    Image ins1 = Console.loadImage("/student/demo/img/ins1.png");
    Image ins2 = Console.loadImage("/student/demo/img/ins2.png");
    Image back = Console.loadImage("/student/demo/img/back.png");
    
    Image[] bigImages = {imgF, imgG};
    Image[] Images = {imgA, imgB};
    Image[] tpr = {Transparent, Transparent};
    int i = 0;
    boolean isStart = true;
    boolean isPlaying = false;
    boolean isPause = false;
    boolean isBullet = false;
    boolean isReplay = false;
    boolean ScrList = false;
    boolean Menu = true;
    boolean GameOver = false;
    boolean abullet = false;
    boolean draw = true;
    boolean ins = false;
    
    int o;
    int p;
    int countDie = 55;
    int count = 0;
    int adie = 0;

    Alien[][] aliens;
    int rx, lx, ly = 0;
    int ry = 10;

    String score = "0";
    int mark = Integer.parseInt(score);

    String lives = "3";
    int live = Integer.parseInt(lives);

    static int highestMark = 0;
    String stringMark;

    SpaceShip spaceship = new SpaceShip(imgC, 500, 540);

    List<Bullet> bullets = new ArrayList<>();
    int capacity = 1;
    UFO ufo = new UFO(imgD, 0, 50);

    Sound background = new Sound("/student/demo/audio/Frantic-Gameplay.wav");
    SoundEffect shoot = new SoundEffect("/student/demo/audio/shoot.wav");

    String scrItem;
    int sx = 250;
    int sy = 150;

    Shelter shelter1 = new Shelter(150, 425, shelter);
    Shelter shelter2 = new Shelter(350, 425, shelter);
    Shelter shelter3 = new Shelter(550, 425, shelter);
    Shelter shelter4 = new Shelter(750, 425, shelter);

    List<AlienBullet> abullets = new ArrayList<>();
    int acapacity = 1;

    public static void main(String[] args) throws IOException {

        /*
        Customize the console window per your need but do not show it yet.
         */
        Console.getInstance()
                .setTitle("Retro Space Invader")
                .setWidth(1000)
                .setHeight(600)
                .setTheme(Console.Theme.DARK);

        new AlienDemo()
                .setFps(25) // set frame rate
                .setShowFps(true) // set to display fps on screen
                .setBackground(Console.loadImage("/student/demo/img/bg.png")) // set background image
                .start();// start game loop

    }

    @Override
    protected void cycle() {

        //To create an Alien array, which contains 55 Alien objects
        if (isStart) {

            console.drawRectangle(0, 0, 1000, 600, Color.white);
            console.drawImage(250, 300, Start);

            aliens = new Alien[5][11];

            int y1 = 110;

            for (int j = 0; j < 5; j++) {

                int x1 = 0;

                for (int k = 0; k < 11; k++) {
                    aliens[j][k] = new Alien(Images, x1, y1, 0);
                    x1 += 64;
                }

                y1 += 40;
            }

            isStart = false;

            if (isReplay) {
                Menu = true;
                isReplay = false;
            }
        }

        //state: is playing
        if (isPlaying) {
            //When the group of aliens is moving to the RHS

            if (aliens[rx][ry].isToLeft == false) {

                while (!aliens[rx][ry].isVisible) {
                    if ((rx != lx || ry != ly) && ry > 0) {
                        rx++;
                        if (rx == 4) {
                            ry--;
                            rx = 0;
                        }
                    }
                }

                if (aliens[rx][ry].getX() + 1 >= 936) {
                    for (int m = 0; m < 5; m++) {
                        for (int n = 0; n < 11; n++) {
                            aliens[m][n].setX(aliens[m][n].getX() + 1);
                            aliens[m][n].setY(aliens[m][n].getY() + 10);

                            if (aliens[m][n].getY() + 50 >= 600) {
                                spaceship.setDie(true);
                            }

                            aliens[m][n].isToLeft = true;
                        }
                    }
                } else {
                    for (int m = 0; m < 5; m++) {
                        for (int n = 0; n < 11; n++) {

                            if (countDie <= 55 && countDie > 40) {
                                aliens[m][n].setX(aliens[m][n].getX() + 1);
                            } else if (countDie <= 40 && countDie > 25) {
                                aliens[m][n].setX(aliens[m][n].getX() + 4);
                            } else if (countDie <= 25 && countDie > 10) {
                                aliens[m][n].setX(aliens[m][n].getX() + 6);
                            } else if (countDie <= 10 && countDie > 1) {
                                aliens[m][n].setX(aliens[m][n].getX() + 10);
                            } else {
                                aliens[m][n].setX(aliens[m][n].getX() + 14);
                            }

                            if (alienShelter(aliens[m][n], shelter1) && !aliens[m][n].Die) {
                                shelter1.lives--;
                                if (shelter1.lives == 3) {
                                    shelter1.setImg(shelterYellow);
                                } else if (shelter1.lives == 1) {
                                    shelter1.setImg(shelterRed);
                                } else if (shelter1.lives == 0) {
                                    shelter1.setImg(shelterTpr);
                                }
                            } else if (alienShelter(aliens[m][n], shelter2) && !aliens[m][n].Die) {
                                shelter2.lives--;
                                if (shelter2.lives == 3) {
                                    shelter2.setImg(shelterYellow);
                                } else if (shelter2.lives == 1) {
                                    shelter2.setImg(shelterRed);
                                } else if (shelter2.lives == 0) {
                                    shelter2.setImg(shelterTpr);
                                }
                            } else if (alienShelter(aliens[m][n], shelter3) && !aliens[m][n].Die) {
                                shelter3.lives--;
                                if (shelter3.lives == 3) {
                                    shelter3.setImg(shelterYellow);
                                } else if (shelter3.lives == 1) {
                                    shelter3.setImg(shelterRed);
                                } else if (shelter3.lives == 0) {
                                    shelter3.setImg(shelterTpr);
                                }
                            } else if (alienShelter(aliens[m][n], shelter4) && !aliens[m][n].Die) {
                                shelter4.lives--;
                                if (shelter4.lives == 3) {
                                    shelter4.setImg(shelterYellow);
                                } else if (shelter4.lives == 1) {
                                    shelter4.setImg(shelterRed);
                                } else if (shelter4.lives == 0) {
                                    shelter4.setImg(shelterTpr);
                                }
                            }
                        }
                    }

                }
            }

            //When the group of aliens is moving to the LHS
            if (aliens[lx][ly].isToLeft == true) {

                while (!aliens[lx][ly].isVisible) {
                    if ((lx != rx || ly != ry) && ly <= 10) {
                        lx++;
                        if (lx == 4) {
                            ly++;
                            lx = 0;
                        }
                    }
                }

                if (aliens[lx][ly].getX() - 1 <= 0) {

                    for (int m = 0; m < 5; m++) {
                        for (int n = 0; n < 11; n++) {
                            aliens[m][n].setX(aliens[m][n].getX() - 1);
                            aliens[m][n].setY(aliens[m][n].getY() + 10);

                            if (aliens[m][n].getY() + 50 >= 600) {
                                spaceship.setDie(true);
                            }

                            aliens[m][n].isToLeft = false;
                        }
                    }

                } else {
                    for (int m = 0; m < 5; m++) {
                        for (int n = 0; n < 11; n++) {

                            if (countDie <= 55 && countDie > 40) {
                                aliens[m][n].setX(aliens[m][n].getX() - 1);
                            } else if (countDie <= 40 && countDie > 25) {
                                aliens[m][n].setX(aliens[m][n].getX() - 4);
                            } else if (countDie <= 25 && countDie > 10) {
                                aliens[m][n].setX(aliens[m][n].getX() - 6);
                            } else if (countDie <= 10 && countDie > 1) {
                                aliens[m][n].setX(aliens[m][n].getX() - 10);
                            } else {
                                aliens[m][n].setX(aliens[m][n].getX() - 14);
                            }

                            if (alienShelter(aliens[m][n], shelter1) && !aliens[m][n].Die) {
                                shelter1.lives--;
                                if (shelter1.lives == 3) {
                                    shelter1.setImg(shelterYellow);
                                } else if (shelter1.lives == 1) {
                                    shelter1.setImg(shelterRed);
                                } else if (shelter1.lives == 0) {
                                    shelter1.setImg(shelterTpr);
                                }
                            } else if (alienShelter(aliens[m][n], shelter2) && !aliens[m][n].Die) {
                                shelter2.lives--;
                                if (shelter2.lives == 3) {
                                    shelter2.setImg(shelterYellow);
                                } else if (shelter2.lives == 1) {
                                    shelter2.setImg(shelterRed);
                                } else if (shelter2.lives == 0) {
                                    shelter2.setImg(shelterTpr);
                                }
                            } else if (alienShelter(aliens[m][n], shelter3) && !aliens[m][n].Die) {
                                shelter3.lives--;
                                if (shelter3.lives == 3) {
                                    shelter3.setImg(shelterYellow);
                                } else if (shelter3.lives == 1) {
                                    shelter3.setImg(shelterRed);
                                } else if (shelter3.lives == 0) {
                                    shelter3.setImg(shelterTpr);
                                }
                            } else if (alienShelter(aliens[m][n], shelter4) && !aliens[m][n].Die) {
                                shelter4.lives--;
                                if (shelter4.lives == 3) {
                                    shelter4.setImg(shelterYellow);
                                } else if (shelter4.lives == 1) {
                                    shelter4.setImg(shelterRed);
                                } else if (shelter4.lives == 0) {
                                    shelter4.setImg(shelterTpr);
                                }
                            }
                        }
                    }
                }
            }

            //set the bullets for the aliens
            if (acapacity > 0) {

                if (draw) {
                    int[] aindex = aIndex();
                    if (!aliens[aindex[0]][aindex[1]].Die) {
                        AlienBullet alienBullets = new AlienBullet(alienBullet, aliens[aindex[0]][aindex[1]].getX() + 30, aliens[aindex[0]][aindex[1]].getY());
                        abullets.add(alienBullets);
                        draw = false;
                    } else {
                        draw = true;
                    }
                }
                abullet = true;
            }

            if (!abullets.isEmpty()) {
                for (int j = 0; j < abullets.size(); j++) {
                    if (abullets.get(j).getY() + 20 > 600) {
                        acapacity++;
                        abullets.remove(abullets.get(j));
                        abullet = false;
                        draw = true;
                    } else if (alienShooted(abullets.get(j), spaceship)) {
                        acapacity++;
                        abullets.remove(abullets.get(j));
                        spaceship.setDie(true);
                        abullet = false;
                        draw = true;
                    } else if (aShooteds(abullets.get(j), shelter1)&& shelter1.lives > 0) {
                        acapacity++;
                        abullets.remove(abullets.get(j));
                        abullet = false;
                        draw = true;
                        shelter1.lives--;
                        if (shelter1.lives == 2) {
                            shelter1.setImg(shelterYellow);
                        } else if (shelter1.lives == 1) {
                            shelter1.setImg(shelterRed);
                        } else if (shelter1.lives == 0) {
                            shelter1.setImg(shelterTpr);
                        }
                    } else if (aShooteds(abullets.get(j), shelter2)&& shelter2.lives > 0) {
                        acapacity++;
                        abullets.remove(abullets.get(j));
                        abullet = false;
                        draw = true;
                        shelter2.lives--;
                        if (shelter2.lives == 2) {
                            shelter2.setImg(shelterYellow);
                        } else if (shelter2.lives == 1) {
                            shelter2.setImg(shelterRed);
                        } else if (shelter2.lives == 0) {
                            shelter2.setImg(shelterTpr);
                        }
                    } else if (aShooteds(abullets.get(j), shelter3)&& shelter3.lives > 0) {
                        acapacity++;
                        abullets.remove(abullets.get(j));
                        abullet = false;
                        draw = true;
                        shelter3.lives--;
                        if (shelter3.lives == 2) {
                            shelter3.setImg(shelterYellow);
                        } else if (shelter3.lives == 1) {
                            shelter3.setImg(shelterRed);
                        } else if (shelter3.lives == 0) {
                            shelter3.setImg(shelterTpr);
                        }
                    } else if (aShooteds(abullets.get(j), shelter4)&& shelter4.lives > 0) {
                        acapacity++;
                        abullets.remove(abullets.get(j));
                        abullet = false;
                        draw = true;
                        shelter4.lives--;
                        if (shelter4.lives == 2) {
                            shelter4.setImg(shelterYellow);
                        } else if (shelter4.lives == 1) {
                            shelter4.setImg(shelterRed);
                        } else if (shelter4.lives == 0) {
                            shelter4.setImg(shelterTpr);
                        }
                    } else {
                        abullets.get(j).setY(abullets.get(j).getY() + 20);
                    }
                }
            }

            //set the conditions of the ufo
            if (!ufo.isFly()) {
                ufo.setX(ufo.getX() + 5);
                if (!ufo.isDie() && !bullets.isEmpty()) {

                    for (int j = bullets.size() - 1; j >= 0; j--) {
                        if (shootedUFO(bullets.get(j), ufo)) {
                            ufo.setImg(Transparent);
                            ufo.setDie(true);
                            mark += 50;
                            capacity += 2;
                            bullets.remove(bullets.get(j));
                            isBullet = false;
                            break;
                        }

                    }

                }
                if (ufo.getX() > 1000) {
                    ufo.setFly(true);
                    ufo.setDie(false);
                    ufo.setImg(imgD);
                }
            } else {
                ufo.setX(ufo.getX() - 1700);
                ufo.setFly(false);
            }

            //bullet fly
            if (!bullets.isEmpty()) {

                for (int j = 0; j < bullets.size(); j++) {
                    
                    if (shelterDecay(bullets.get(j), shelter1) ) {
                        capacity++;
                        bullets.remove(bullets.get(j));
                        isBullet = false;
                        shelter1.lives--;
                        if (shelter1.lives == 3) {
                            shelter1.setImg(shelterYellow);
                        } else if (shelter1.lives == 1) {
                            shelter1.setImg(shelterRed);
                        } else if (shelter1.lives == 0) {
                            shelter1.setImg(shelterTpr);
                        }
                    } else if (shelterDecay(bullets.get(j), shelter2 )) {
                        capacity++;
                        bullets.remove(bullets.get(j));
                        isBullet = false;
                        shelter2.lives--;
                        if (shelter2.lives == 3) {
                            shelter2.setImg(shelterYellow);
                        } else if (shelter2.lives == 1) {
                            shelter2.setImg(shelterRed);
                        } else if (shelter2.lives == 0) {
                            shelter2.setImg(shelterTpr);
                        }
                    } else if (shelterDecay(bullets.get(j), shelter3) ) {
                        capacity++;
                        bullets.remove(bullets.get(j));
                        isBullet = false;
                        shelter3.lives--;
                        if (shelter3.lives == 3) {
                            shelter3.setImg(shelterYellow);
                        } else if (shelter3.lives == 1) {
                            shelter3.setImg(shelterRed);
                        } else if (shelter3.lives == 0) {
                            shelter3.setImg(shelterTpr);
                        }
                    } else if (shelterDecay(bullets.get(j), shelter4)) {
                        capacity++;
                        bullets.remove(bullets.get(j));
                        isBullet = false;
                        shelter4.lives--;
                        if (shelter4.lives == 3) {
                            shelter4.setImg(shelterYellow);
                        } else if (shelter4.lives == 1) {
                            shelter4.setImg(shelterRed);
                        } else if (shelter4.lives == 0) {
                            shelter4.setImg(shelterTpr);
                        }
                    } else if (bullets.get(j).getY() - 30 < 0) {
                        capacity++;
                        bullets.remove(bullets.get(j));
                        isBullet = false;
                    } else if (shooted(bullets.get(j), aliens)) {
                        capacity++;
                        bullets.remove(bullets.get(j));
                        aliens[o][p].setDie(true);
                        countDie--;
                        aliens[o][p].setImages(tpr);
                        aliens[o][p].setIsVisible(false);
                        mark += 10;
                        isBullet = false;
                    } else {
                        bullets.get(j).setY(bullets.get(j).getY() - 30);
                    }

                }

            }

            if (!spaceship.isDie()) {
                for (int x = 0; x < 5; x++) {
                    for (int s = 0; s < 11; s++) {
                        if (aliens[x][s].isDie() == false
                                && (spaceship.getx() >= aliens[x][s].getX() && spaceship.getx() <= aliens[x][s].getX() + 64)
                                && (spaceship.gety() >= aliens[x][s].getY() && spaceship.gety() <= aliens[x][s].getY() + 64)) {

                            spaceship.setDie(true);
                        }
                    }

                }
            }

            if (ly == ry && countDie == 0) {
                countDie = 55;
                shelter1.lives = 5;
                shelter2.lives = 5;
                shelter3.lives = 5;
                shelter4.lives = 5;
                shelter1.setImg(shelter);
                shelter2.setImg(shelter);
                shelter3.setImg(shelter);
                shelter4.setImg(shelter);
                acapacity += 2;
                lx = 0;
                ly = 0;
                rx = 0;
                ry = 10;
                isStart = true;
            }

            if (spaceship.isDie()) {
                live -= 1;
                spaceship.setDie(false);
            }

            if (live == 0) {

                if (mark > highestMark) {
                    highestMark = mark;
                    try {
                        FileWriter fr = new FileWriter("scoreList.txt");
                        fr.write(String.valueOf(highestMark));
                        fr.close();
                    } catch (IOException e) {
                    }
                }
                System.out.println(highestMark);
                isPlaying = false;
                GameOver = true;
            }

        }  //end of is Playing

        //draw images
        i = ++i % 20;// 0 to 19

        if (isPlaying && !isReplay && !Menu && !isPause) {

            for (int d = 0; d < 11; d++) {

                for (int c = 0; c < 5; c++) {

                    console.drawImage(aliens[c][d].getX(), aliens[c][d].getY(), aliens[c][d].getImages()[i / 10]);

                }
            }

            console.drawImage(ufo.getX(), ufo.getY(), ufo.getImg());

            if (isBullet) {
                for (int j = 0; j < bullets.size(); j++) {
                    console.drawImage(bullets.get(j).getX(), bullets.get(j).getY(), imgE);
                }
            }

            console.drawRectangle(880, 15, 42, 42, Color.yellow);
            console.drawImage(885, 20, imgPause);

            console.drawRectangle(942, 15, 42, 42, Color.yellow);
            console.drawImage(947, 20, imgReplay);

            console.drawImage(spaceship.getx(), spaceship.gety(), spaceship.getImage());

            console.drawImage(0, 10, Score);
            if (shelter1.lives > 0) {
                console.drawImage(shelter1.getX(), shelter1.getY(), shelter1.getImg());
            }
            if (shelter2.lives > 0) {
                console.drawImage(shelter2.getX(), shelter2.getY(), shelter2.getImg());
            }
            if (shelter3.lives > 0) {
                console.drawImage(shelter3.getX(), shelter3.getY(), shelter3.getImg());
            }
            if (shelter4.lives > 0) {
                console.drawImage(shelter4.getX(), shelter4.getY(), shelter4.getImg());
            }

            console.drawText(135, 45, score.valueOf(mark), new Font("Arial", Font.BOLD, 32), Color.white);
            console.drawText(240, 45, "lives x " + lives.valueOf(live), new Font("Arial", Font.BOLD, 32), Color.white);

            for (int j = 0; j < abullets.size(); j++) {
                console.drawImage(abullets.get(j).getX(), abullets.get(j).getY(), alienBullet);
            }

        }//end of drawings

        //another states
        if (Menu) {
            console.drawRectangle(0, 0, 1000, 600, Color.black);
            console.drawImage(210, 150, bigImages[i / 10]);
            console.drawImage(310, 150, bigImages[i / 10]);
            console.drawImage(410, 150, bigImages[i / 10]);
            console.drawImage(510, 150, bigImages[i / 10]);
            console.drawImage(610, 150, bigImages[i / 10]);
            console.drawImage(235, 300, Start);
            console.drawImage(300, 400, ins1);
            //console.drawImage(330, 400, scrList);
            File f = new File("scoreList.txt");
            try {
                Scanner sc = new Scanner(f);
                stringMark = sc.next();
                //System.out.println("highest score : " + stringMark);
                highestMark = Integer.parseInt(stringMark);
            } catch (FileNotFoundException e) {
            }
        }
        
        if(ins){
            console.drawRectangle(0, 0, 1000, 600, Color.black);
            console.drawImage(80, 200, ins2);
            console.drawImage(400, 100, back);
        }
        
        if (GameOver) {
            console.drawRectangle(0, 0, 1000, 600, Color.black);
            console.drawImage(300, 200, gameOver);
            File f = new File("scoreList.txt");
            try {
                Scanner sc = new Scanner(f);
                stringMark = sc.next();
                //System.out.println("highest score : " + stringMark);
            } catch (FileNotFoundException e) {
            }
            console.drawText(300, 350, "Your Mark : " + score.valueOf(mark), new Font("Arial", Font.BOLD, 32), Color.white);
            console.drawText(300, 400, "Highest Mark : " + stringMark, new Font("Arial", Font.BOLD, 32), Color.white);
        }

        if (isPause) {
            console.drawImage(400, 200, text2);
        }

        if (isReplay) {
            console.drawImage(200, 150, text1);
            console.drawImage(335, 320, Yes);
            console.drawImage(550, 320, No);
        }

    }
    //a function to check whether an alien is shooted by the spacehsip

    public boolean shooted(Bullet bullet, Alien[][] aliens) {

        for (int t = 0; t < 5; t++) {
            for (int j = 0; j < 11; j++) {
                for (int k = 0; k < bullets.size(); k++) {

                    if (aliens[t][j].isDie() == false
                            && (bullets.get(k).getX() >= aliens[t][j].getX() && bullets.get(k).getX() <= aliens[t][j].getX() + 64)
                            && (bullets.get(k).getY() >= aliens[t][j].getY() && bullets.get(k).getY() <= aliens[t][j].getY() + 64)) {

                        o = t;
                        p = j;

                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean shootedUFO(Bullet bullet, UFO ufo) {
        for (int j = 0; j < bullets.size(); j++) {

            if (ufo.isDie() == false
                    && (bullets.get(j).getX() >= ufo.getX() && bullets.get(j).getX() <= ufo.getX() + 64)
                    && (bullets.get(j).getY() >= ufo.getY() && bullets.get(j).getY() <= ufo.getY() + 64)) {

                return true;
            }

        }
        return false;
    }

    public boolean alienShooted(AlienBullet a, SpaceShip b) {

        if ((a.getX() >= b.getx() && a.getX() <= b.getx() + 48)
                && (a.getY() >= b.gety() && a.getY() <= b.gety() + 48)) {

            return true;
        } else {
            return false;
        }
    }

    public boolean shelterDecay(Bullet a, Shelter b) {
        if ((a.getX() >= b.getX() && a.getX() <= b.getX() + 100)
                && (a.getY() >= b.getY() && a.getY() <= b.getY() + 66) && b.lives > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean alienShelter(Alien a, Shelter b) {

        if ((a.getX() >= b.getX() && a.getX() <= b.getX() + 100)
                && a.getY() >= b.getY() && a.getY() <= b.getY() + 66) {
            return true;
        } else {
            return false;
        }
    }

    public boolean aShooteds(AlienBullet a, Shelter b) {

        if ((a.getX() >= b.getX() && a.getX() <= b.getX() + 100)
                && (a.getY() >= b.getY() && a.getY() <= b.getY() + 66)) {
            return true;
        } else {
            return false;
        }
    }

    public int[] aIndex() {

        int[] result = new int[2];
        Double a = Math.random() * 5;
        Double b = Math.random() * 11;
        result[0] = a.intValue();
        result[1] = b.intValue();

        return result;
    }

    @Override
    protected void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                spaceship.setx(spaceship.getx() - 10);
                break;
            case KeyEvent.VK_RIGHT:
                spaceship.setx(spaceship.getx() + 10);
                break;
            case KeyEvent.VK_SPACE:
                if (capacity > 0) {
                    Bullet bullet = new Bullet(imgE, spaceship.getx() + 20, spaceship.gety());
                    bullets.add(bullet);
                    capacity--;
                }
                shoot.play();
                isBullet = true;
                break;

        }
    }

    @Override
    protected void mouseClicked(MouseEvent e) {
        //when clicking the pause button
        if (isPlaying) {
            if ((e.getX() >= 880 && e.getX() <= 922) && (e.getY() >= 15 && e.getY() <= 57)) {
                isPlaying = false;
                isPause = true;
            }
        }

        //when clicking the resume button after clicking pause button
        if (!isPlaying && !isReplay) {
            if ((e.getX() >= 400 && e.getX() <= 620) && (e.getY() >= 200 && e.getY() <= 260)) {
                isPlaying = true;
                isPause = false;
            }
        }

        //when clicking the replay button
        if (!isReplay) {
            if ((e.getX() >= 942 && e.getX() <= 984) && (e.getY() >= 15 && e.getY() <= 57)) {
                isReplay = true;
                isPlaying = false;
            }
        }

        //when clicking the no button after clicking replay button
        if (isReplay) {
            if ((e.getX() >= 550 && e.getY() <= 660) && (e.getY() >= 320 && e.getY() <= 380)) {
                isReplay = false;
                isPlaying = true;
            } //when clicking the yes button after clicking replay button
            else if ((e.getX() >= 335 && e.getX() <= 445) && (e.getY() >= 320 && e.getY() <= 380)) {
                live = 3;
                mark = 0;
                countDie = 55;
                shelter1.lives = 5;
                shelter2.lives = 5;
                shelter3.lives = 5;
                shelter4.lives = 5;
                shelter1.setImg(shelter);
                shelter2.setImg(shelter);
                shelter3.setImg(shelter);
                shelter4.setImg(shelter);
                lx = 0;
                ly = 0;
                rx = 0;
                ry = 10;
                acapacity = 1;
                capacity = 1;
                isStart = true;
                isPlaying = true;

            }
        }

        //when clicking the start button in the menu
        if (Menu && !isReplay) {
            if ((e.getX() >= 225 && e.getX() <= 690) && (e.getY() >= 300 && e.getY() <= 375)) {
                Menu = false;
                isStart = true;
                isPlaying = true;
                ins = false;
                background.play();
            }
            
            if((e.getX() >= 300 && e.getX() <= 590) && (e.getY() >= 400 && e.getY() <= 450)){
                ins = true;
                Menu = false;               
            }
        }
        
        if(ins){
            if((e.getX() >= 400 && e.getX() <= 540) && (e.getY() >= 100 && e.getY() <= 150)){
                ins = false;
                Menu = true;
            }
        }

        System.out.println("Click on (" + e.getX() + "," + e.getY() + ")");
    }

}
