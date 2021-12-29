import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FirstActivity extends JFrame {
    private JButton jButton01;
    public FirstActivity(){
        //ȡ�����ֹ�����
        this.setLayout(null);
        this.setVisible(true);
        this.setLocation(500,500);
        this.setSize(700,500);

        jButton01=new JButton("�����ս");
        jButton01.setBounds(200,200,200,50);
        jButton01.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SecondActivity();
                dispose();

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.add(jButton01);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);


        String path = "image/img01.PNG";
        // ����ͼƬ
        ImageIcon background = new ImageIcon(path);
        // �ѱ���ͼƬ��ʾ��һ����ǩ����
        JLabel label = new JLabel(background);
        // �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������
        label.setBounds(0, 0, this.getWidth(), this.getHeight());
        // �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
        JPanel imagePanel = (JPanel) this.getContentPane();
        imagePanel.setOpaque(false);
        // �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        g.setFont(new Font("����", Font.BOLD, 20));
        g.drawString("����¬ս��", 200, 120);


    }

}
