import javax.swing.*;
import java.awt.*;

public class FrameCommon{
	static JFrame frame ;
	static JWindow window;
	public static void initFrame(Container con, String str, int width, int height){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension d = toolkit.getScreenSize();
		int x = (int) d.getWidth();
		int y = (int) d.getHeight();
		con.setBounds((x - width)/2, (y - height)/2, width, height);
		if (con instanceof JFrame) {
			frame = (JFrame)con;
			frame.setTitle(str);
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}else if (con instanceof JWindow) {
			window = (JWindow)con;
		}
	}
}
