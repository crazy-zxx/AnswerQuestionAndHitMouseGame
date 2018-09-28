import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

public class ProgressBar extends JWindow implements Runnable{
	private JProgressBar jpb;
	public ProgressBar(){
		ImageIcon image = new ImageIcon(this.getClass().getResource("image/background.png"));
		JLabel j = new JLabel(image);
		int width = image.getIconWidth();
		int height = image.getIconHeight();
		j.setBounds(0, 0, width, height);

		jpb = new JProgressBar();                       // ����������           
		jpb.setStringPainted(true);                     // ��ʾ��ǰ����ֵ��Ϣ        
		jpb.setBorderPainted(false);                    // ���ý������߿���ʾ           
		jpb.setForeground(Color.BLUE);                  // ���ý�������ǰ��ɫ           
		jpb.setBackground(Color.WHITE);                 // ���ý������ı���ɫ 
		jpb.setBounds(0, height, width, 20);
		
		this.setLayout(null);
		this.add(j);
		this.add(jpb);
		new Thread(this).start();
		FrameCommon.initFrame(this, null, width, height+20);
		this.setVisible(true);
	}
	
	public void run(){
		for(int i = 0; i < 100; i=i+3){
			try{
				Thread.sleep(100);
			}catch(InterruptedException e){}
			jpb.setValue(i);
		}
		new GameStart();
		dispose();
	}
	
	public static void main(String[] args){
		new ProgressBar();
	}
}
