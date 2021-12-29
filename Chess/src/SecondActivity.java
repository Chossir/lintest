import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SecondActivity extends JFrame{
    public TextField textField01;
    public TextField textField02;
    private JButton jButton;
    public String iP;
    public String port;
    private JComboBox<String>faceCombo;

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

    }

    public SecondActivity(){
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(700,500);
        textField01=new TextField("������Է��ľ�����IP��ַ",20);
        faceCombo=new JComboBox<>();
        faceCombo.addItem("�ҷ�����");
        faceCombo.addItem("�з�����");
        jButton=new JButton("��ʼ��Ϸ");
        JButton jButton1=new JButton("�鿴����IP");
        textField01.setBounds(200,200,200,25);
        faceCombo.setBounds(200,250,150,25);
        faceCombo.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
              GameActivity.signal=faceCombo.getSelectedIndex();
            }
        });
        jButton1.setBounds(200,300,200,25);
        jButton.setBounds(220,400,150,25);
        this.add(faceCombo);
        this.add(jButton);
        this.add(textField01);
        this.add(jButton1);
         jButton1.addMouseListener(new MouseListener() {
             @Override
             public void mouseClicked(MouseEvent e) {
                 showIp();
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
        jButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                iP=textField01.getText();
                GameActivity.ip=iP;

                try {
                    //System.out.println("����ipΪ"+iP+"���û���������Ϸ..........");//Client wipe off this element,This is player ip
                    new Thread(new GameActivity()).start(); //������Ϸ

                    dispose();
                } catch (SocketException e1) {
                    e1.printStackTrace();
                }


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


    }
    public void showIp(){
        InetAddress addr= null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        JOptionPane.showConfirmDialog(null, addr.getHostAddress(), null, JOptionPane.CLOSED_OPTION);


    }

}
