/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

/**
 *
 * @author Koala
 */
public class Constants {
    //DIMENSIONES DEL FRAME
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    
    //PROPIEDAS DE JUGADOR
    public static final int FIRERATE=300;
    public static final double DELTAANGLE=0.1;
    public static final double ACC=0.08;//Representa cuanto queremos que acelere la nave
    public static final double PLAYER_MAX_VEL=7.0;
    
    //PROPIEDASDES DEL LASER
    public static final double LASER_VEL=15.0;

    //PROPIEDADES DE METEORO
    public static final double METEOR_VEL = 2.0;
    
    //PROPIEDADES UFO
    public static final int NODE_RADIUS =160;
    public static final double UFO_MASS=60;
    public static final int UFO_MAX_VEL = 3;
    public static long UFO_FIRE_RATE = 3000;//1000 equivale a 1 seg
    public static double UFO_ANGLE_RANGE = Math.PI/2;

    
    //HUD
    public static int UFO_SCORE=100;
    public static int METEOR_SCORE=30;
}
