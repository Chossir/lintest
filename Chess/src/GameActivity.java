import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class GameActivity extends JFrame implements Runnable{

    String ip_s="192.168.43.158";
    public static int signal=0;
    public static  String ip;
    private  JButton[]buttons=new JButton[9];
        /**
         * 0 未触发
         * 1 我方触发
         * 2 敌方触发
         */
        private int[]boom=new int[9];
        private int judge=2;
        private ImageIcon a;
        private ImageIcon b;
         //8888
    private DatagramSocket client;
    private int serverPort=6666;
    private int clientPort=8888;
    private int outPort=9999;
    private int service=55555;
    //6666
    private DatagramSocket server;

    public GameActivity() throws SocketException {
        a=new ImageIcon("image/img2.PNG");
        b=new ImageIcon("image/img3.PNG");
        if(signal==1){
            serverPort=7777;
            clientPort=9999;
            outPort=8888;
            //change
            ImageIcon c=a;
            a=b;
            b=c;
        }
        this.setVisible(true);
        this.setLayout(null);
        this.setSize(800,800);
        this.setButtons();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//page design
    }
    public void setButtons(){
        for(int i=0;i<9;i++){
            buttons[i]=new JButton();
        }
        buttons[0].setBounds(100,150,150,150);
        buttons[1].setBounds(260,150,150,150);
        buttons[2].setBounds(420,150,150,150);
        buttons[3].setBounds(100,310,150,150);
        buttons[4].setBounds(260,310,150,150);
        buttons[5].setBounds(420,310,150,150);
        buttons[6].setBounds(100,470,150,150);
        buttons[7].setBounds(260,470,150,150);
        buttons[8].setBounds(420,470,150,150);
        for(int i=0;i<9;i++){
            int j=i;
            buttons[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if(myJudge()&&boom[j]==0){
                        buttons[j].setIcon(a);
                        boom[j]=1;

                        try {
                            server=new DatagramSocket(serverPort);//

                            //databases
                            String msg;
                            if(win()){
                              msg=j+","+1;//why?
                            }else if(noWin()){
                                msg=j+","+2;
                            }else{
                                msg=j+","+0;
                            }

                            byte[] data =msg.getBytes();
                            //3pack
                            
                            DatagramPacket packet=new DatagramPacket(data,data.length,new InetSocketAddress(ip,outPort));//send data.j is the position
                            DatagramPacket packet_s=new DatagramPacket(data,data.length,new InetSocketAddress(ip_s,service));
                          
                             String message="ip涓�"+ip+"鐨勭敤鎴峰凡杩炴帴......";
                            byte[] data1 =message.getBytes(); 
                            DatagramPacket packet_s1=new DatagramPacket(data1,data1.length,new InetSocketAddress(ip_s,service));
                            //4send
                            server.send(packet);
                            server.send(packet_s);
                            server.send(packet_s1);
                            //System.out.println("ip为"+ip+"的用户已下一颗棋子.....");
                            if(win()){
                                JOptionPane.showConfirmDialog(null, "you win", null, JOptionPane.CLOSED_OPTION);
                                System.out.println("ip为"+ip+"的用户胜利了.....");
                                String message1="ip为"+ip+"的用户失败了......";
                            byte[] data2 =message1.getBytes(); 
                            DatagramPacket packet_s2=new DatagramPacket(data2,data2.length,new InetSocketAddress(ip_s,service));
                             server.send(packet_s2);
                                boom=new int[9];
                                for(JButton jButton:buttons){
                                    jButton.setIcon(null);
                                }
                                repaint();

                            }
                            server.close();
                        }catch(Exception ec) {
                            System.out.println("白方异常");
                        }
                        judge++;
                        if(noWin()){//show all position and judge that
                            JOptionPane.showConfirmDialog(null, "和棋", null, JOptionPane.CLOSED_OPTION);
                            judge=2;
                            boom=new int[9];
                            for(JButton jButton:buttons){
                                jButton.setIcon(null);
                            }
                        }

                    }



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
            this.add(buttons[i]);

        }


    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(myJudge()) {//What is muJudge function
                continue;
            }
            try {//
                System.out.println("来自ip为\"+ip+\"的用户已连接游戏..........");
                client=new DatagramSocket(clientPort);
                //2、准备接收容器
                byte[] container=new byte[1024];
                //3、封装成包DatagramPacket(byte[] buf,int length)
                DatagramPacket packet=new DatagramPacket(container,container.length);//a udp connection packet 
                //4、接收
                client.receive(packet);//get real time infomation.
                byte[] data=packet.getData();
                int len=packet.getLength();
                String str=new String(data,0,len);
                Integer integer=new Integer(str.split(",")[0]);
                Integer integer1=new Integer(str.split(",")[1]);
                boom[integer]=5;
                buttons[integer].setIcon(b);
                System.out.println(integer1);
                judge++;
                repaint();
                client.close();
                if(integer1==1){
                    JOptionPane.showConfirmDialog(null, "you default", null, JOptionPane.CLOSED_OPTION);
                    boom=new int[9];
                    for(JButton jButton:buttons){
                        jButton.setIcon(null);
                        judge=2;
                    }
                    repaint();
                }else if(integer1==2){
                    JOptionPane.showConfirmDialog(null, "鍜屾", null, JOptionPane.CLOSED_OPTION);
                    boom=new int[9];
                    for(JButton jButton:buttons){
                        jButton.setIcon(null);
                        judge=2;
                    }
                    repaint();
                }
            }catch(Exception ec) {
                System.out.println("异常");
            }
            //setButtons();
        }

    }
    public boolean win() {
        if(boom[0]+boom[1]+boom[2]==3||
           boom[3]+boom[4]+boom[5]==3||
           boom[6]+boom[7]+boom[8]==3)
            return true;
        if(boom[0]+boom[3]+boom[6]==3||
           boom[1]+boom[4]+boom[7]==3||
           boom[2]+boom[5]+boom[8]==3)
            return true;
        if(boom[0]+boom[4]+boom[8]==3||
           boom[2]+boom[4]+boom[6]==3)
            return true;

       return false;
    }
    public  boolean noWin() {
        for (int i : boom) {
            if (i == 0) {
                return false;
            }
        }
        return true;
    }
    public boolean myJudge(){
        if(signal==0){
            if( judge%2==0){
                return true;
            }else{
                return false;
            }
        }else{
            if(judge%2!=0){
                return true;
            }else{
                return false;
            }
        }
    }

}
