package states;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import gameObjects.Constants;
import gameObjects.Message;
import gameObjects.Meteor;
import gameObjects.MovingObject;
import gameObjects.Player;
import gameObjects.Size;
import gameObjects.Ufo;
import graphics.Animation;
import graphics.Assets;
import graphics.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import math.Vector2D;



/**
 *
 * @author Koala
 */
public class GameState {
    
    private Player player;
    //arrego de objeto movible que se va a encargar de actualizar y dibujar todos los objetos que se muevan en el juego
    private ArrayList<MovingObject> movingObjects = new ArrayList<MovingObject>();
    //Animation
    private ArrayList<Animation> explotions = new ArrayList<Animation>();

    //Mensaje- para eliminar
    private ArrayList<Message> messages = new ArrayList<Message>();
    private Message mensaje;
    
    //Score
    private int score=0;
    
    //Lives
    private int lives =3;
    
    //Meteoros
    private int meteors;
    
    //Cantidad de oleadas
    private int waves = 1;
    
    
    public GameState(){
        player = new Player(new Vector2D(390,500),new Vector2D(), Constants.PLAYER_MAX_VEL,Assets.player, this);
        movingObjects.add(player);
    
        meteors=1;
        startWave();
    
    }
    
    //Suma los puntajes
    public void addScore(int value, Vector2D position){
        score +=value;
        messages.add(new Message(position, true, "+"+value+" score", Color.WHITE, false, Assets.fontMed, this));
    }
    //Resta de vidas
    public void restLive(int value){
        lives-=value;
        
    }
    
    
    //Dividir los Meteoros
    public void divideMeteor(Meteor meteor){
        
        Size size = meteor.getSize();
        
        BufferedImage[] textures = size.textures;
        
        Size newSize = null;
        
        switch(size){
            case BIG_BROWN:
                newSize = Size.MED_BROWN;
                break;
                
            case MED_BROWN:
                newSize = Size.SMALL_BROWN;
                break;
            
            /*case SMALL_BROWN:
                newSize = Size.TINY_BROWN;
                break;*/
            
            case BIG_GRAY:
                newSize = Size.MED_GRAY;
                break;
                
            case MED_GRAY:
                newSize = Size.SMALL_GRAY;
                break;
            
            /*case SMALL_GRAY:
                newSize = Size.TINY_GRAY;
                break;*/
                
            default:
                return;
        }
        
        //Agregar los meteoros
        for(int i=0; i<size.quantity;i++){
        
            movingObjects.add(new Meteor(
                    meteor.getPosition(),
                    new Vector2D(0,1).setDirection(Math.random()*Math.PI*2),
                    Constants.METEOR_VEL*Math.random()+1,
                    textures[(int)(Math.random()*textures.length)],
                    this,
                    newSize));
        
        }
        
    }
    

    
    //Para iniciar oleadas de meteoros
    private void startWave(){
        
        
        messages.add(new Message(new Vector2D(Constants.WIDTH/2, Constants.HEIGHT/2), false, "A pelear puto "+ waves, Color.CYAN, true, Assets.fontBig, this));
        
        double x,y;
        for(int i = 0; i<meteors; i++){
            //posiciones aleatorios
            x= i%2==0 ? Math.random()*Constants.WIDTH : 0;
            y= i%2==0 ? 0: Math.random()*Constants.HEIGHT;            
                
            /*BufferedImage texture = Assets.bigsBrown[(int)(Math.random()*Assets.bigsBrown.length)];
                    movingObjects.add(new Meteor(
                    new Vector2D(x,y),
                    new Vector2D(0,1).setDirection(Math.random()*Math.PI*2),
                    Constants.METEOR_VEL*Math.random()+1,
                    texture,
                    this,
                    Size.BIG_BROWN));*/
                    
            BufferedImage texture2 = Assets.bigsGrey[(int)(Math.random()*Assets.bigsBrown.length)];
                    movingObjects.add(new Meteor(
                    new Vector2D(x,y),
                    new Vector2D(0,1).setDirection(Math.random()*Math.PI*2),
                    Constants.METEOR_VEL*Math.random()+1,
                    texture2,
                    this,
                    Size.BIG_GRAY));
                    
            }

        meteors++;   
        spawnUfo();

        
    }
    
    
    public void update(){
        //player.update();
        for(int i=0; i<movingObjects.size();i++){
            movingObjects.get(i).update();
        }
        
        //animations
        for(int i=0; i<explotions.size();i++){
            Animation anim= explotions.get(i);
            anim.update();
            if(!anim.isRunning()){
                explotions.remove(i);
            }
        }
        
        //MAS OLEADAS
        for(int i=0; i<movingObjects.size();i++){
            if(movingObjects.get(i) instanceof Meteor){
                return ;
            }
        }
        startWave();
        
        
    }
    
    //Agregando animaciones al arreglo explotions 
    public void playExplosion(Vector2D position){
        explotions.add(new Animation(
                Assets.exp, 
                50, 
                position.subtracc(new Vector2D(Assets.exp[0].getWidth()/2, Assets.exp[0].getWidth()/2))));
    
    }
    
    
    //nos permite crear el UFO
    private void spawnUfo(){
    
        int rand = (int)(Math.random()*2);
        
        double x = rand == 0 ? (Math.random()*Constants.WIDTH): 0;
        double y = rand == 0 ? 0 : (Math.random()*Constants.WIDTH);
        //Camino para los UFO
        ArrayList<Vector2D> path = new ArrayList<Vector2D>();
        
        double posX, posY;
        
        posX = Math.random()*Constants.WIDTH/2;
        posY = Math.random()*Constants.HEIGHT/2;
        path.add(new Vector2D(posX, posY));
        
        posX = Math.random()*(Constants.WIDTH/2) + Constants.WIDTH/2;
        posY = Math.random()*(Constants.WIDTH/2);
        path.add(new Vector2D(posX, posY));
        
        posX = Math.random()*Constants.WIDTH/2;
        posY = Math.random()*(Constants.HEIGHT/2) + Constants.HEIGHT/2;
        path.add(new Vector2D(posX, posY));
        
        posX = Math.random()*(Constants.WIDTH/2) + Constants.WIDTH/2;
        posY = Math.random()*(Constants.HEIGHT/2) + Constants.HEIGHT/2;
        path.add(new Vector2D(posX, posY));
        
        posX = Math.random()*Constants.WIDTH/2;
        posY = Math.random()*Constants.HEIGHT/2;
        path.add(new Vector2D(posX, posY));
        
        movingObjects.add(new Ufo(
                new Vector2D(x,y),
                new Vector2D(),
                Constants.UFO_MAX_VEL,
                Assets.ufo[(int)(Math.random()*Assets.ufo.length)],
                path,
                this));
        
    }
    
    
    //-------------DIBUJO------------
    
    public void draw(Graphics g){
        
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        
        //Messages
        for(int i=0; i<messages.size();i++){
            System.out.println("tamañano de mensaje: "+messages.size());
            messages.get(i).draw(g2d);
        }
        
        for(int i=0; i<movingObjects.size();i++){
            movingObjects.get(i).draw(g);
        }
        
        for(int i=0; i<explotions.size();i++){
            Animation anim= explotions.get(i);
            g2d.drawImage(anim.getCurrentFrame(), (int)anim.getPosition().getX(), (int)anim.getPosition().getY() , null);
        }
        
        drawScore(g);
        drawLives(g);
        //Text.drawText(g, "WAVES "+waves, new Vector2D(Constants.WIDTH/2, Constants.HEIGHT/2), true, Color.CYAN, Assets.fontBig);
        
        
        
    }
    
    //Dibujando Score
    private void drawScore(Graphics g){
        //Posicion donde estara el puntaje
        Vector2D pos= new Vector2D(700,25);

        //Sabemos cuantos digitos hay
        String scoreToString = Integer.toString(score);
        
        //
        for(int i = 0; i < scoreToString.length(); i++){
            
            g.drawImage(Assets.numbers[Integer.parseInt(scoreToString.substring(i,i+1))],
                    (int)pos.getX(), (int)pos.getY(), null);
            pos.setX(pos.getX()+ 20);
        }
    }
    
    //Dibujando la vida
    private void drawLives(Graphics g){
        //Posicion donde estaran las vidas.
        Vector2D livePosition = new Vector2D(25, 25);
        //Dibujamos la Seta
        g.drawImage(Assets.life2, (int)livePosition.getX(), (int)livePosition.getY(), null);
        //Diujamos el X
        g.drawImage(Assets.X, (int)livePosition.getX()+35, (int)livePosition.getY()+8, null);
        
        String livesToString = Integer.toString(lives);
        
        Vector2D pos = new  Vector2D(livePosition.getX(),livePosition.getY());
        
        for(int i = 0; i< livesToString.length(); i++){
        
            int number = Integer.parseInt(livesToString.substring(i,i+1));
            
            if(number<0)
                break;
            
            g.drawImage(Assets.numbers[number],
                    (int)pos.getX()+60,
                    (int)pos.getY()+8, null);
            
        }
        
        
    }
    
 
    public ArrayList<MovingObject> getMovingObjects() {
        return movingObjects;
    }
    
    public ArrayList<Message> getMessages(){
        return messages;
    }
    
    public void setMovingObjects(ArrayList<MovingObject> movingObjects) {
        this.movingObjects = movingObjects;
    }
    
    public Player getPlayer(){
        return player;
    }
    
    public Message getMessage(){
        return mensaje;
    }
    
    
}
