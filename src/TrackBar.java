import javax.swing.JFrame;
import javax.swing.JSlider;

public class TrackBar extends JFrame{
	private JSlider js;
	public void addJSlider(){
		js = new JSlider();
		js.setBounds(0,30,280,50);
		js.setPaintTicks(true);//���û�����ƿ̶ȱ��  
        js.setPaintLabels(true); //�������̶ȱ�ǵ�״̬  
        js.setMajorTickSpacing(10);//�������̶ȱ�ǵļ��  
        js.setMinorTickSpacing(2);//���ø��̶ȱ�ǵļ�� 
		js.setValue ((int)js.getValue());
		this.getContentPane().add(js);
	}

	public long getValue(){
		return js.getValue();
	}

	public TrackBar(){
		setLayout(null);
		addJSlider();
		setTitle("�Ѷ�");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(230,340,300,130);
		//setVisible(true);
	}

	public static void main(String[] args){
		TrackBar tb = new TrackBar();
	}
}