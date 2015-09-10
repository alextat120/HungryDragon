import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements ActionListener, KeyListener
{

   public final int NORTH=0, SOUTH=180,EAST=90, WEST=270;
   public int direction;
   Timer t= new Timer(100, this);
   //public int time;
   Snake s = new Snake();
   public final int LENGTH=s.getLength();
   public final int HEIGHT=s.getHeight();
   String str = "IM HUNGRY!!!!  Help me to my food by using the ARROW KEYS";
   public int BALLSIZE = s.getBallSize();
   public int score =0;
   public int temp =0;
   public GamePanel()
   {
      this.addKeyListener(this);
   }

   
   public void paintComponent(Graphics g)
   {
      if(score<30){
         s.setInvincible(false);
      }
      else
         s.setInvincible(true);
      super.paintComponent(g);
      this.setBackground(Color.BLACK);
   
      g.setColor(Color.WHITE);
      if(isGameOver()){
         System.out.println("Coordinates of Head: " + s.getX(0) + "," +s.getY(0));
         str = str.replace(str, "GAME OVER - Score: " + score);
         g.setColor(Color.RED);
         g.setFont(new Font("Courier", Font.BOLD, 30));
         g.drawString(str, (LENGTH/2)-(str.length()*10),HEIGHT/2 );
      }
      else{
         System.out.println("Coordinates of Head: " + s.getX(0) + "," +s.getY(0));
      
         this.setBackground(Color.BLACK);
         g.setColor(Color.WHITE);
         g.drawLine(0,HEIGHT,LENGTH,HEIGHT);
         g.drawLine(LENGTH,HEIGHT,LENGTH,0);
         g.setColor(Color.GREEN);
         g.drawString(str, 400 ,500);
         paintFood(g);
         paintRocks(g);
         
         
         /*
         if(score>4){
            paintPower(g);
         }
         */
         System.out.println("Painted food");
         if (s.hasEaten()==true){
            score+=1;
            str=str.replace(str, "Yum!  CHICKEN pcs: " + score);
            s.add(s.size()-1, s.food);
         }
         /*
         if(s.poweredUp()){
            s.setInvincible(true);
         }   
         */     
         for(int z=0;z<s.size();z++)
         {
            JPanel p = new JPanel();
            ImageIcon i = new ImageIcon("firstdragon.png");   
            g.setColor(Color.BLUE);
            if(z==0){
               g.setColor(Color.CYAN);
               //g.fillOval(s.getX(z),s.getY(z),BALLSIZE,BALLSIZE);
               g.drawImage(i.getImage(), s.getX(z), s.getY(z), BALLSIZE, BALLSIZE,null);
               
               
            }
            else
               //g.drawOval(s.getX(z),s.getY(z),BALLSIZE,BALLSIZE);
            {
               ImageIcon body = new ImageIcon("body.png");   
               g.drawImage(body.getImage(),s.getX(z),s.getY(z),BALLSIZE,BALLSIZE,null);
            }
         }
      }
   
   }
   /*
   public void paintPower(Graphics g){
      ImageIcon i = new ImageIcon("human.png");
      g.drawImage(i.getImage(), s.px,s.py, BALLSIZE, BALLSIZE,null);
   }
   */
   public void paintFood(Graphics g){
      //super.paintComponent(g);
      
      //g.setColor(Color.WHITE);
      //g.drawRect(0,0,900,500);
      ImageIcon i = new ImageIcon("food.png");
      g.setColor(Color.RED);
      System.out.println("a is" + s.a);
      System.out.println("b is" + s.b);
      g.drawImage(i.getImage(), s.a, s.b, BALLSIZE, BALLSIZE,null);
   
      //g.fillOval(s.a,s.b, BALLSIZE,BALLSIZE);
   
   }
   public void paintRocks(Graphics g){
      ImageIcon i = new ImageIcon("bomb.png");
      for (int x = 0; x<s.rocks.size()-1;x++){
         g.drawImage(i.getImage(), s.rocks.get(x).getX(),s.rocks.get(x).getY(), BALLSIZE, BALLSIZE, null);
      }
   }
   public boolean isGameOver(){
      if(s.inBounds()==false || s.isAlive()==false){
         return true;
      }
      return false;
      
   }
   
   public void actionPerformed(ActionEvent e){
   
      if(s.inBounds()){
      
      
         {
         
            if (direction == NORTH)
            {
            
               System.out.println("CHANGED coordinates - NORTH");
                           
               s.add(0, new Point(s.getX(0),s.getY(0)-BALLSIZE));
               //repaint();
            
               s.remove(s.size()-1);
               repaint();
            
            
            }
            else if (direction == SOUTH)
            {
               System.out.println("CHANGED coordinates - SOUTH");
                          
               s.add(0, new Point(s.getX(0),s.getY(0)+BALLSIZE));
               //repaint();
            
               s.remove(s.size()-1);
               repaint();
            
            
            }
            else if (direction == EAST)
            {
               System.out.println("CHANGED coordinates - EAST");
               s.add(0, new Point(s.getX(0)+BALLSIZE,s.getY(0)));
               //repaint();
            
               s.remove(s.size()-1);
               repaint();
            
            
            }
            else if (direction == WEST)
            {
               System.out.println("CHANGED coordinates - WEST");
            
               s.add(0, new Point(s.getX(0)-BALLSIZE,s.getY(0)));
               //repaint();
            
               s.remove(s.size()-1);
               repaint();
            
            
            }
         }
      
         System.out.println("HAS REpainted");
      }
      else{
         str = str.replace(str, "GAME OVER");
      
         repaint();
      }
      
      
   }  
   public void keyTyped(KeyEvent e) {
   }
   public void keyReleased(KeyEvent e){
   
   }
   public void keyPressed(KeyEvent e){
      t.start();
      
      if(temp==0){
         if (e.getKeyCode() == KeyEvent.VK_LEFT) {
         //System.out.println("A KEY WAS PRESSED.");
         
            direction=WEST;
            temp=-1;
         
         }
         else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
         //System.out.println("A KEY WAS PRESSED.");
         
            direction=SOUTH;
            temp=-1;
         }
         else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
         //System.out.println("A KEY WAS PRESSED.");
         
            direction=EAST;
            temp=-1;
         }
         else /*(e.getKeyCode() == KeyEvent.VK_UP)*/ {
         //System.out.println("A KEY WAS PRESSED.");
         
            direction=NORTH;
            temp=-1;
         
         }
      
      }
      
      if(direction == WEST){
         if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            //System.out.println("A KEY WAS PRESSED.");
            direction=SOUTH;
         }
         else if (e.getKeyCode() == KeyEvent.VK_UP) {
            //System.out.println("A KEY WAS PRESSED.");
         
            direction=NORTH;
         
         }
         else //if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
         {
            System.out.println("direction is " + direction);
         
            direction=WEST;
         }
      
         
      }
      
      else if(direction ==EAST){
         if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            //System.out.println("A KEY WAS PRESSED.");
         
            direction=SOUTH;
         }
         
         else if (e.getKeyCode() == KeyEvent.VK_UP) {
            //System.out.println("A KEY WAS PRESSED.");
         
            direction=NORTH;
         
         }
         else// if (e.getKeyCode() == KeyEvent.VK_LEFT) 
         {
            //System.out.println("A KEY WAS PRESSED.");
         
            direction=EAST;
         }
         
        
      
      }
      
      else if(direction ==NORTH){
      
         if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            //System.out.println("A KEY WAS PRESSED.");
         
            direction=EAST;
         }
         else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            //System.out.println("A KEY WAS PRESSED.");
         
            direction=WEST;
         
         }
         else //(e.getKeyCode() == KeyEvent.VK_DOWN) 
         {
            //System.out.println("A KEY WAS PRESSED.");
         
            direction=NORTH;
         }
      
      
      }
      else if(direction==SOUTH){
         if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            //System.out.println("A KEY WAS PRESSED.");
         
            direction=EAST;
         }
         else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            //System.out.println("A KEY WAS PRESSED.");
         
            direction=WEST;
         }
         else// (e.getKeyCode() == KeyEvent.VK_UP)
         {
            //System.out.println("A KEY WAS PRESSED.");
         
            direction=SOUTH;
         }
      
      }
   
   
   }


}
class Snake {
   int ate=0;
   ArrayList<Point> snake = new ArrayList<Point>();
   ArrayList<Point> rocks = new ArrayList<Point>();
   //ArrayList<Point> temp = new ArrayList<Point>();
   boolean alive =true;
   final int BALLSIZE = 30;
   final int LENGTH=BALLSIZE*30;
   final int HEIGHT =BALLSIZE*16;
   int a =(int)(Math.random()*LENGTH);
   int b = (int)(Math.random()*HEIGHT);
   
   int rx =(int)(Math.random()*LENGTH);
   int ry = (int)(Math.random()*HEIGHT);
   //int px;
   //int py;
   
   Point food; 
   
   public Snake()
   {
   
   
      //snake.add(new Point(0,10));
      snake.add(new Point(0,BALLSIZE));
      //snake.add(new Point(0,30));
      snake.add(new Point(0,BALLSIZE*2));
      //snake.add(new Point(0,50));
      snake.add(new Point(0,BALLSIZE*3));
      //snake.add(new Point(0,70));
      snake.add(new Point(0,BALLSIZE*4));
   
   
      while(a%BALLSIZE != 0){
         a =(int)(Math.random()*LENGTH);
         
      }
      while(b%BALLSIZE !=0){
         b =(int)(Math.random()*HEIGHT);
      }
      
      while((rx%BALLSIZE != 0) || (Math.abs(rx-a)<(BALLSIZE*3))) {
         rx =(int)(Math.random()*LENGTH);
         
      }
      while((ry%BALLSIZE !=0) ||(Math.abs(ry-b)<(BALLSIZE*3))){
         ry =(int)(Math.random()*HEIGHT);
      }
      
      food = new Point(a,b);
      rocks.add(new Point(rx,ry));
   
   
   }
   public int getHeight(){
      return HEIGHT;
   }
   public int getLength(){
      return LENGTH;
   }
   public int getBallSize(){
      return BALLSIZE;
   }
   public int size(){
      return snake.size();
   }
   public int getX(int x){
      return snake.get(x).getX();
   }
   public int getY(int y){
      return snake.get(y).getY();
   }
   public void add(int z, Point p){
      snake.add(z,p);
   }
   public void remove(int r){
      snake.remove(r);
   }
   public boolean inBounds(){
      if (this.getX(0)<LENGTH && this.getX(0)>=0 && this.getY(0)>=0 && this.getY(0)<HEIGHT){
         return true;
      }
      else 
         return false;
   }
   public boolean hasEaten(){
   /*
      ate++;
      if(ate==5){
         px = (int)(Math.random()*LENGTH);
         py = (int)(Math.random()*LENGTH);
      
      }
   
   */
      if(this.getX(0) == a && this.getY(0)== b)
      {
         a =(int)(Math.random()*LENGTH);
         b = (int)(Math.random()*HEIGHT);
         rx=(int)(Math.random()*LENGTH);
         ry =(int)(Math.random()*LENGTH);
      //a == rx and ry only checks for the one being created, need to check previous ones with ArrayList rocks
         // make a temporary arraylist to store the points
         // go through array looking to see if there exists the same rx or ry that equals a or b.
         /*for(int x =0;x<rocks.size();x++){
            temp.add(rocks.get(x));
         }
         */ 
      
         
         
         while(a%BALLSIZE != 0  || a==rx)  {
            a =(int)(Math.random()*LENGTH);
         }
         while(b%BALLSIZE !=0 || b==ry){
            b =(int)(Math.random()*HEIGHT);
         }
         while((rx%BALLSIZE != 0) || ( Math.abs(rx-a)<(BALLSIZE*3))){
            rx =(int)(Math.random()*LENGTH);
         
         }
         while((ry%BALLSIZE != 0) || (Math.abs(rx-a)<(BALLSIZE*3))){
            ry =(int)(Math.random()*HEIGHT);
         }
      
         
         rocks.add(new Point(rx,ry));
         if (rocks.size()>7){
            rocks.remove(0);
         }
              
         food = new Point (a,b);
         return true;
      }
      else
         return false;
   }
   public void setInvincible(boolean b){
      if(b == true){/*
         for(int x = 1; x <snake.size();x++){
            if (this.getX(0) == this.getX(x) && this.getY(0) == this.getY(x)){
               alive=true;
            }
         }
         for(int x=0;x<rocks.size();x++){
            if(this.getX(0)==rocks.get(x).getX() && this.getY(0) == rocks.get(x).getY()){
               alive = true;
            }
         }
      */
      }
      if(b==false){
         for(int x = 1; x <snake.size();x++){
            if (this.getX(0) == this.getX(x) && this.getY(0) == this.getY(x)){
               alive=false;
            }
         }
         
         for(int x=0;x<rocks.size();x++){
            if(this.getX(0)==rocks.get(x).getX() && this.getY(0) == rocks.get(x).getY()){
               alive = false;
            }
         }
         
      }
   }
   public boolean isAlive(){
      return alive;
   }
   /*
   public boolean poweredUp(){
      if(px == this.getX(0) && py == this.getY(0)){
         return true;
      }
      else
         return false;
   }
   */
   public static void main(String[] args)
   {
      JFrame window = new JFrame("DRAGON TRAIL");
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      //int x=100;
      
      GamePanel panel = new GamePanel();
      panel.setFocusable(true); 
      window.add(panel);
      window.setSize(900+20,500+50);
      window.setLocationRelativeTo(null);
      window.setResizable(true);
      
      if (panel.isGameOver()){
         JFrame over = new JFrame("YOU DIED...");
         over.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         JPanel p = new JPanel();
         JButton b = new JButton("RETRY");
         p.add(b);
         over.add(p);
         over.setVisible(true);
      }
      window.setVisible(true);
   
      /*
      while(true){
         if(panel.score<10){
            x = 100;
         }
         else if(panel.score<20){
            x=80;
         }
         else if(panel.score<30){
            x=60;
         }
         else{
            x=50;
         }
      }
      */
   }
}
