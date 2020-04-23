import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameMain extends JFrame{
	
	private Rank rank = new Rank();

	public GameMain(){
		this.getContentPane().setLayout(null);
		addBackground();
		addMenu();
		FrameCommon.initFrame(this, "打地鼠", 800, 510);
		this.setVisible(true);
	}

	//添加欢迎界面背景
	public void addBackground(){
		ImageIcon bgImage = new ImageIcon(this.getClass().getResource("image/GameInterface.png"));
		JLabel jlBackground = new JLabel(bgImage);
		jlBackground.setBounds(0, 0, bgImage.getIconWidth(), bgImage.getIconHeight());
		this.getLayeredPane().add(jlBackground, new Integer(Integer.MIN_VALUE));
		((JPanel) this.getContentPane()).setOpaque(false);
	}

	//添加欢迎界面按钮
	public void addMenu(){
		ImageIcon imgGameStart = new ImageIcon(this.getClass().getResource("image/GameStart.png"));
		ImageIcon imgGameRank = new ImageIcon(this.getClass().getResource("image/GameRank.png"));
		ImageIcon imgGameHelp = new ImageIcon(this.getClass().getResource("image/GameHelp.png"));
		ImageIcon imgGameExit = new ImageIcon(this.getClass().getResource("image/GameExit.png"));
		JLabel[] menus = new JLabel[4];
		for(int i = 0; i < menus.length; i++){
			menus[i] = new JLabel();
			menus[i].setSize(250, 85);
			this.getContentPane().add(menus[i]);
		}
		
		menus[0].setIcon(imgGameStart);
		menus[1].setIcon(imgGameRank);
		menus[2].setIcon(imgGameHelp);
		menus[3].setIcon(imgGameExit);
		
		menus[0].setLocation(250, 55);
		menus[1].setLocation(250, 150);
		menus[2].setLocation(250, 245);
		menus[3].setLocation(250, 340);

		//响应开始游戏
		menus[0].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				dispose();//关闭当前窗口释放资源
				new ProgressBar();//创建游戏加载窗口
			}

			public void mousePressed(MouseEvent e){
				menus[0].setLocation(250, 57);
			}

			public void mouseReleased(MouseEvent e){
				menus[0].setLocation(250, 55);
			}
		});
		//响应排名
		menus[1].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				rank.jf.setVisible(true);
				rank.read();
			}
			public void mousePressed(MouseEvent e){
				menus[1].setLocation(250, 152);
			}

			public void mouseReleased(MouseEvent e){
				menus[1].setLocation(250, 150);
			}
		});
		//响应帮助
		menus[2].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				JOptionPane.showMessageDialog(null, "这种游戏还需要帮助？", "哈哈",JOptionPane.INFORMATION_MESSAGE);
			}
			public void mousePressed(MouseEvent e){
				menus[2].setLocation(250, 247);
			}

			public void mouseReleased(MouseEvent e){
				menus[2].setLocation(250, 245);
			}
		});
		//响应退出
		menus[3].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				System.exit(0);
			}
			public void mousePressed(MouseEvent e){
				menus[3].setLocation(250, 342);
			}

			public void mouseReleased(MouseEvent e){
				menus[3].setLocation(250, 340);
			}
		});
	}

	// public static void main(String[] args) {
	// 	new GameMain();
	// }

}
