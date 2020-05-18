import java.awt.*;
import java.awt.event.*;
public class Snake extends Frame{
	class Close extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			dispose();
		}
	}
	class Mover extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			
			switch(e.getKeyCode()){
				case KeyEvent.VK_S:
									last=e;
									break;
					
				case KeyEvent.VK_A:
									last=e;
									break;
					
				case KeyEvent.VK_D:
									last=e;
									break;
					
				case KeyEvent.VK_W:
									last=e;
									break;
			}			
		}
	}
	KeyEvent last=null;
	int x,mm,score=0,random=649;
	Button bt[];
	int []L,R,D,U,MM;
	Dialog d;
	Mover m;
	Snake(){
		super("Snake Game");		
		x=30;
		mm=(x*x)/2 - (x/2 +1);
		L=new int[x];
		R=new int[x];
		D=new int[x];
		U=new int[x];
		MM=new int[]{mm};
		L[0]=0;
		R[0]=x-1;
		D[0]=x*(x-1);
		U[0]=0;
		for(int i=1;i<x;++i){
			L[i]=L[i-1]+x;
			R[i]=R[i-1]+x;
			D[i]=D[i-1]+1;
			U[i]=U[i-1]+1;			
		}
		m=new Mover();
		bt=new Button[x*x];
		for(int i=0;i<x*x;++i){
			bt[i]=new Button();
			add(bt[i]);
			bt[i].setBackground(Color.CYAN);
			bt[i].addKeyListener(m);
		}
		setLayout(new GridLayout(x,x));
		bt[mm].setBackground(Color.RED);
		for(int o : MM)
			bt[o].setBackground(Color.RED);
		bt[random].setBackground(Color.ORANGE);
		addKeyListener(m);
		setBounds(430,170,500,500);	
		setVisible(true);
		d=new Dialog(this,"Game Over");
		d.setLayout(new FlowLayout());
		d.addWindowListener(new Close());
		d.setBounds(450,300,400,100);
		Thread z=new Thread(){
			KeyEvent a=null;
			public int function(){
				try{
					Thread.sleep(50);
				}catch(Exception e){}
				for(int o : MM)
					bt[o].setBackground(Color.RED);
				bt[MM[MM.length-1]].setBackground(Color.CYAN);	
				for(int i : MM){
					if(mm==i){
						bt[mm].setBackground(Color.BLACK);
						d.add(new Label("Score = "+String.valueOf(score)));
						d.setVisible(true);
						return 0;
					}
				}
				if(mm==random){
					score+=5;
					int[] tmp=new int[MM.length+1];
					tmp[0]=mm;
					for(int i=0;i<MM.length;++i){
						tmp[i+1]=MM[i];
					}
					MM=tmp;
					tmp=null;
					boolean T=true;
					int ctr=0;
					while(T){
						random=(int)(Math.random()*899);
						for(int i: MM){
							if(random==i){
								ctr++;break;
							}
						}
						if(ctr==0)
							break;
						}
						bt[random].setBackground(Color.ORANGE);
					}
					bt[mm].setBackground(Color.RED);
				return 1;
			}
			public void run(){
				if(last==null){}
				else if(last!=null&a==null){
					a=last;
					
				}
				else if(last!=a){
					a=last;
				}
				else if(last==a){
					switch(a.getKeyCode()){
						case KeyEvent.VK_S:
							
							for(int i : D){
								if(mm==i){
									bt[mm].setBackground(Color.BLACK);
									d.add(new Label("Score = "+String.valueOf(score)));
									d.setVisible(true);
									return;
								}
							}
							
							
							mm+=30;
							for(int i=MM.length-1;i>=0;--i){
								if(i==0){
									MM[i]=mm-30;
								}
								else{
									MM[i]=MM[i-1];
								}
							}
							if(function()==0)
								return;
							break;
							
						case KeyEvent.VK_A:
							
							for(int i : L){
								if(mm==i){
									bt[mm].setBackground(Color.BLACK);
									d.add(new Label("Score = "+String.valueOf(score)));
									d.setVisible(true);
									return;
								}
							}
							
							
							mm-=1;
							for(int i=MM.length-1;i>=0;--i){
								if(i==0){
									MM[i]=mm+1;
								}
								else{
									MM[i]=MM[i-1];
								}
							}
							if(function()==0)
								return;
							break;
							
						case KeyEvent.VK_D:
							for(int i : R){
								if(mm==i){
									bt[mm].setBackground(Color.BLACK);
									d.add(new Label("Score = "+String.valueOf(score)));
									d.setVisible(true);
									return;
								}
							}
							
							
							mm+=1;
							for(int i=MM.length-1;i>=0;--i){
								if(i==0){
									MM[i]=mm-1;
								}
								else{
									MM[i]=MM[i-1];
								}
							}
							if(function()==0)
								return;
							break;
							
						case KeyEvent.VK_W:
							for(int i : U){
								if(mm==i){
									bt[mm].setBackground(Color.BLACK);
									d.add(new Label("Score = "+String.valueOf(score)));
									d.setVisible(true);
									return;
								}
							}
							
							
							mm-=30;
							for(int i=MM.length-1;i>=0;--i){
								if(i==0){
									MM[i]=mm+30;
								}
								else{
									MM[i]=MM[i-1];
								}
							}
							if(function()==0)
								return;
							break;
					}
				}
				try{
					Thread.sleep(50);
				}catch(Exception e){}
				run();
			}
		};
		z.start();
	}
	public static void main(String ... arhs){
		new Snake();
	}
}