import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

public class GameStart extends JFrame implements Runnable, ActionListener {

    private boolean isMusic = false;
    private boolean isPause = true;
    private JLabel jlBackground;    //背景标签
    private Cursor myCursor;        //鼠标标签
    private Cursor myCursor2;       //鼠标标签2
    // private ImageIcon imgMouse;     //地鼠图片
    private ImageIcon imgMouse1;    //击中后地鼠图片

    //TODO
    private ImageIcon aMouse;     //a地鼠图片
    private ImageIcon bMouse;     //b地鼠图片
    private ImageIcon cMouse;     //c地鼠图片
    private ImageIcon dMouse;     //d地鼠图片

    private String rightItem;      //正确选项
    private ImageIcon rightMouse;   //正确地鼠图片

    // private ImageIcon imgBomb;      //炸弹图片
    private ImageIcon imgBoom;      //点击炸弹后图片

    private ImageIcon imgPause;     //暂停图片
    private ImageIcon imgContinue;  //继续图片
    private ImageIcon imgOnMusic;   //打开音乐
    private ImageIcon imgOffMusic;  //关闭音乐
    private JLabel[] mouses;        //存放地鼠标签数组
    private JButton[] menus;        //存放菜单按钮数组
    private JLabel[] shows;         //存放显示信息数组

    private Font font = new Font("黑体", Font.BOLD, 22);
    private int time = 10;          //时间
    private int score = 0;          //分数
    private int life = 1;          //生命

    private String level = "..";    //难度

    private long x = 1500;

    private Timer timerCount = new Timer();
    private Timer timerString = new Timer();

    private Music bgm = new Music("music/LoveYourself.wav");
    private Music qiao = new Music("music/jida.WMA");
    private Music boom = new Music("music/boom.wav");
    private Music over = new Music("music/over.wav");
    private TrackBar tb = new TrackBar();
    private Rank rank = new Rank();

    public GameStart() {
        setLayout(null);
        addShow();
        addBackground();
        addMouse();
        addMouses();
        addMenu();

        new Thread(this).start();

        FrameCommon.initFrame(this, "打地鼠", 849, 670);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    //添加背景方法
    public void addBackground() {
        ImageIcon bgImage = new ImageIcon(this.getClass().getResource("image/back.png"));
        JLabel jlBackground = new JLabel(bgImage);
        jlBackground.setBounds(0, 0, bgImage.getIconWidth(), bgImage.getIconHeight());
        this.getLayeredPane().add(jlBackground, new Integer(Integer.MIN_VALUE));
        ((JPanel) this.getContentPane()).setOpaque(false);

        ImageIcon show = new ImageIcon(this.getClass().getResource("image/show.png"));
        JLabel jlShow = new JLabel(show);
        jlShow.setBounds(630, 10, show.getIconWidth(), show.getIconHeight());
        this.getContentPane().add(jlShow);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                setCursor(myCursor2);
            }

            public void mouseReleased(MouseEvent e) {
                setCursor(myCursor);
            }
        });
    }

    //添加鼠标图标方法
    public void addMouse() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = new ImageIcon(this.getClass().getResource("image/cz.png")).getImage();
        Image img2 = new ImageIcon(this.getClass().getResource("image/czz.png")).getImage();
        myCursor = kit.createCustomCursor(img, new Point(3, 3), "光标名字");
        myCursor2 = kit.createCustomCursor(img2, new Point(3, 3), "光标名字");
        setCursor(myCursor);
    }

    //添加菜单按钮方法
    public void addMenu() {
        ImageIcon imgInterface = new ImageIcon(this.getClass().getResource("image/interface.png"));
        ImageIcon imgInterface2 = new ImageIcon(this.getClass().getResource("image/interface.gif"));
        ImageIcon imgNewGame = new ImageIcon(this.getClass().getResource("image/newGame.png"));
        ImageIcon imgNewGame2 = new ImageIcon(this.getClass().getResource("image/newGame.gif"));
        ImageIcon imgLevel = new ImageIcon(this.getClass().getResource("image/level.png"));
        ImageIcon imgLevel2 = new ImageIcon(this.getClass().getResource("image/level.gif"));
        imgPause = new ImageIcon(this.getClass().getResource("image/pause.png"));
        ImageIcon imgPause2 = new ImageIcon(this.getClass().getResource("image/pause.gif"));
        imgContinue = new ImageIcon(this.getClass().getResource("image/continue.png"));
        ImageIcon imgContinue2 = new ImageIcon(this.getClass().getResource("image/continue.gif"));
        imgOnMusic = new ImageIcon(this.getClass().getResource("image/onmusic.png"));
        ImageIcon imgOnMusic2 = new ImageIcon(this.getClass().getResource("image/onmusic.gif"));
        imgOffMusic = new ImageIcon(this.getClass().getResource("image/offmusic.png"));
        ImageIcon imgOffMusic2 = new ImageIcon(this.getClass().getResource("image/offmusic.gif"));
        ImageIcon imgExit = new ImageIcon(this.getClass().getResource("image/exit.png"));
        ImageIcon imgExit2 = new ImageIcon(this.getClass().getResource("image/exit.gif"));
        menus = new JButton[6];
        for (int i = 0; i < menus.length; i++) {
            menus[i] = new JButton();
            menus[i].setSize(100, 30);
            menus[i].addActionListener(this);
            this.getContentPane().add(menus[i]);
        }

        menus[3].setEnabled(false);

        menus[0].setIcon(imgInterface);
        menus[1].setIcon(imgNewGame);
        menus[2].setIcon(imgLevel);
        menus[3].setIcon(imgPause);
        menus[4].setIcon(imgOffMusic);
        menus[5].setIcon(imgExit);

        menus[0].setLocation(0, 20);
        menus[1].setLocation(0, 60);
        menus[2].setLocation(0, 100);
        menus[3].setLocation(0, 140);
        menus[4].setLocation(0, 180);
        menus[5].setLocation(0, 220);

        menus[0].addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                menus[0].setIcon(imgInterface2);
            }

            public void mouseExited(MouseEvent e) {
                menus[0].setIcon(imgInterface);
            }
        });
        menus[1].addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                menus[1].setIcon(imgNewGame2);
            }

            public void mouseExited(MouseEvent e) {
                menus[1].setIcon(imgNewGame);
            }
        });
        menus[2].addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                menus[2].setIcon(imgLevel2);
            }

            public void mouseExited(MouseEvent e) {
                menus[2].setIcon(imgLevel);
            }
        });
        menus[3].addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (isPause) {
                    menus[3].setIcon(imgContinue2);
                } else {
                    menus[3].setIcon(imgPause2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (isPause) {
                    menus[3].setIcon(imgContinue);
                } else {
                    menus[3].setIcon(imgPause);
                }
            }
        });
        menus[4].addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (isMusic) {
                    menus[4].setIcon(imgOnMusic2);
                } else {
                    menus[4].setIcon(imgOffMusic2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (isMusic) {
                    menus[4].setIcon(imgOnMusic);
                } else {
                    menus[4].setIcon(imgOffMusic);
                }
            }
        });
        menus[5].addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                menus[5].setIcon(imgExit2);
            }

            public void mouseExited(MouseEvent e) {
                menus[5].setIcon(imgExit);
            }
        });
    }

    //添加显示信息方法
    public void addShow() {
        String[] name = {"剩余生命：" + life, "当前分数：" + score, "当前难度：" + level, "剩余时间：" + time};
        shows = new JLabel[4];
        for (int i = 0; i < shows.length; i++) {
            shows[i] = new JLabel(name[i]);
            shows[i].setSize(200, 50);
            shows[i].setFont(font);
            shows[i].setForeground(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
            this.getContentPane().add(shows[i]);
        }

        shows[0].setLocation(160, 20);
        shows[1].setLocation(315, 20);
        shows[2].setLocation(480, 20);
        shows[3].setLocation(660, 20);

    }

    //添加地鼠方法
    public void addMouses() {

        //abcd
        aMouse = new ImageIcon(this.getClass().getResource("image/msa.png"));
        bMouse = new ImageIcon(this.getClass().getResource("image/msb.png"));
        cMouse = new ImageIcon(this.getClass().getResource("image/msc.png"));
        dMouse = new ImageIcon(this.getClass().getResource("image/msd.png"));

        // imgMouse = new ImageIcon(this.getClass().getResource("image/ms.png"));
        imgMouse1 = new ImageIcon(this.getClass().getResource("image/mss.png"));
        // imgBomb = new ImageIcon(this.getClass().getResource("image/bomb.png"));
        imgBoom = new ImageIcon(this.getClass().getResource("image/boom.png"));
        mouses = new JLabel[9];
        for (int i = 0; i < mouses.length; i++) {
            mouses[i] = new JLabel();
            // mouses[i].setSize(imgMouse.getIconWidth(), imgMouse.getIconHeight());
            mouses[i].setSize(aMouse.getIconWidth(), aMouse.getIconHeight());

            mouses[i].addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    Object obj = e.getSource();
                    if (obj instanceof JLabel) {
                        JLabel label = (JLabel) obj;
                        //TODO
                        //正确选项
                        if (label.getIcon() != null && label.getIcon() == rightMouse) {
                            qiao.play();
                            label.setIcon(imgMouse1);
                            label.setSize(imgMouse1.getIconWidth(), imgMouse1.getIconHeight());
                            score += 10;
                            shows[1].setText("当前分数：" + score);

                        } else if (label.getIcon() != null && label.getIcon() != rightMouse) {//错误选项
                            boom.play();
                            label.setIcon(imgBoom);
                            label.setSize(imgBoom.getIconWidth(), imgBoom.getIconHeight());
                            life--;
                            shows[0].setText("剩余生命：" + life);
                        }
                    }
                }

                public void mousePressed(MouseEvent e) {
                    setCursor(myCursor2);
                }

                public void mouseReleased(MouseEvent e) {
                    setCursor(myCursor);
                }
            });
            this.getContentPane().add(mouses[i]);
        }
        mouses[0].setLocation(213, 140);
        mouses[1].setLocation(382, 140);
        mouses[2].setLocation(552, 140);
        mouses[3].setLocation(185, 267);
        mouses[4].setLocation(382, 267);
        mouses[5].setLocation(578, 267);
        mouses[6].setLocation(160, 405);
        mouses[7].setLocation(382, 405);
        mouses[8].setLocation(600, 405);
    }

    // 提示窗口，可以多个地方调用
    public void winMessage(String str) {
        //timeCountStop();
        bgm.stop();
        over.play();
        isPause = true;
        JOptionPane.showMessageDialog(this, str, "温馨提示", 1);
        Object[] options = {"退出", "保存记录并回主界面", "继续"};
        int response = JOptionPane.showOptionDialog(this, "你的得分是：" + score + "!", "you death",
                JOptionPane.YES_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (response == 0) {
            System.exit(0);
        } else if (response == 1) {
            rank.write(this);
            dispose();
            new GameMain();
        } else {
            try {
                Thread.sleep(100);
                life = 1;
                //score = 0;
                time = 10;
                shows[0].setText("剩余生命：" + life);
                shows[1].setText("当前分数：" + score);
                shows[3].setText("剩余时间：" + time);

                //显示题目
                //TODO
                Problem problem = null;
                if ((problem = Problems.get()) != null) {

                    rightItem = problem.getRightItem();

                    JOptionPane.showMessageDialog(this,
                            problem.getProblem() + "\n\nA." + problem.getA() + "\nB." + problem.getB() + "\nC." + problem.getC() + "\nD." + problem.getD(),
                            "问题", 3);
                }

                if (!isMusic) {
                    bgm.loopPlay();
                }
                isPause = false;
                //timeCount();

            } catch (Exception e) {
                e.printStackTrace();

                System.out.println("no problem ...");
                isPause = true;
                bgm.stop();
                //timeCountStop();
                Object[] options1 = {"退出", "保存记录并回主界面"};
                int response1 = JOptionPane.showOptionDialog(this, "题目做完了！\n总分：" + score + "!", "结束",
                        JOptionPane.YES_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, options1, options1[0]);
                if (response1 == 0) {
                    System.exit(0);
                } else {
                    rank.write(this);
                    dispose();
                    new GameMain();
                }

            }
        }
    }

    //时间倒计时
    public void timeCount() {
        timerCount.schedule(new TimerTask() {
            public void run() {
                while (true) {
                    while (isPause) {
                        try {
                            Thread.sleep(100);
                        } catch (Exception e) {
                        }
                    }
                    time--;
                    shows[3].setText("剩余时间：" + time);
                    if (time <= 0) {
                        shows[3].setText("剩余时间：" + 0);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                }
            }
        }, 1000);
    }

    public void timeCountStop(){
        timerCount.cancel();
    }

    //显示信息的颜色变化
    public void timeString() {
        timerString.schedule(new TimerTask() {
            public void run() {
                while (true) {
                    for (int i = 0; i < shows.length; i++) {
                        shows[i].setForeground(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
                    }
                    try {
                        Thread.sleep(200);
                    } catch (Exception e) {
                    }
                }
            }
        }, 1000);
    }

    //按钮的监听
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.menus[0]) {//回主界面
            bgm.stop();
            dispose();
            new GameMain();
        } else if (e.getSource() == this.menus[1]) {//开始游戏
            isPause = true;
            bgm.stop();
            //提示新游戏开始
            JOptionPane.showMessageDialog(this, "新游戏已开始", "温馨提示", 1);

            Problems.init();
            //显示题目
            //TODO
            Problem problem = null;
            if ((problem = Problems.get()) != null) {
                rightItem = problem.getRightItem();

                JOptionPane.showMessageDialog(this,
                        problem.getProblem() + "\n\nA." + problem.getA() + "\nB." + problem.getB() + "\nC." + problem.getC() + "\nD." + problem.getD(),
                        "问题", 3);
            } else {
                JOptionPane.showMessageDialog(this, "恭喜通关！", "温馨提示", 1);
                return;
            }

            menus[3].setEnabled(true);
            if (!isMusic) {
                bgm.loopPlay();
            }
            isPause = false;
            timeCount();
            timeString();
            life = 1;
            score = 0;
            time = 10;
            shows[0].setText("剩余生命：" + life);
            shows[1].setText("当前分数：" + score);
            shows[3].setText("剩余时间：" + time);
        } else if (e.getSource() == this.menus[2]) {
            tb.setVisible(true);
        } else if (e.getSource() == this.menus[3]) {
            if (isPause) {
                isPause = !isPause;
                menus[3].setIcon(imgPause);
            } else {
                isPause = !isPause;
                menus[3].setIcon(imgContinue);
            }
        } else if (e.getSource() == this.menus[4]) {
            if (isMusic) {
                isMusic = !isMusic;
                bgm.loopPlay();
                menus[4].setIcon(imgOffMusic);
            } else {
                isMusic = !isMusic;
                bgm.stop();
                menus[4].setIcon(imgOnMusic);
            }
        } else {
            System.exit(0);
        }
    }

    //线程
    public void run() {
        while (true) {
            while (isPause) {
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                }
            }

            //清除上次分布
            for (int i=0;i<mouses.length;i++){
                if (mouses[i].isShowing()){
                    mouses[i].setIcon(null);
                }
            }

            try {

                switch (rightItem) {
                    case "a":
                        rightMouse = aMouse;
                        break;
                    case "b":
                        rightMouse = bMouse;
                        break;
                    case "c":
                        rightMouse = cMouse;
                        break;
                    case "d":
                        rightMouse = dMouse;
                        break;
                    default:
                        System.out.println("wrong rightItem!");
                }

                //随机位置
                int i = (int) (Math.random() * 9);
                //Thread.sleep(500);
                //重新分布
                if (mouses[i].getIcon() == null) {
                    mouses[i].setIcon(aMouse);
                }
                while (mouses[i].getIcon() != null) {
                    i = (int) (Math.random() * 9);
                }
                mouses[i].setIcon(bMouse);
                while (mouses[i].getIcon() != null) {
                    i = (int) (Math.random() * 9);
                }
                mouses[i].setIcon(cMouse);
                while (mouses[i].getIcon() != null) {
                    i = (int) (Math.random() * 9);
                }
                mouses[i].setIcon(dMouse);

                //难度由速度实现
                x = 2500 - 20 * tb.getValue();
                if (x >= 2100) {
                    level = "菜鸟";
                    shows[2].setText("当前难度：" + level);
                } else if (x >= 1300) {
                    level = "入门";
                    shows[2].setText("当前难度：" + level);
                } else if (x >= 700) {
                    level = "中级";
                    shows[2].setText("当前难度：" + level);
                } else {
                    level = "大师";
                    shows[2].setText("当前难度：" + level);
                }
                Thread.sleep(x);


            } catch (Exception e) {
                e.printStackTrace();
            }
            if (life == 0) {
                winMessage("you are die!");
            }

            if (time <= 0) {
                winMessage("时间到");
            }
        }
    }

    public int getScore() {
        return score;
    }
}
