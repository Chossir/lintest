
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * To change b license header, choose License Headers in Project Properties.
 * To change b template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class a extends JFrame{
    private  JButton[]buttons=new JButton[9];
     private int[]boom=new int[9];
    private ImageIcon a;
    private ImageIcon b;
    private static javax.swing.JTextArea Area;
     private javax.swing.JScrollPane jScrollPane1;
     private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Area = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Area.setColumns(20);
        Area.setRows(5);
        jScrollPane1.setViewportView(Area);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 773, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(500, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>            
     public void setButtons(){
         initComponents() ;
        for(int i=0;i<9;i++){
            buttons[i]=new JButton();
        }
        buttons[0].setBounds(200,110,100,100);
        buttons[1].setBounds(300,110,100,100);
        buttons[2].setBounds(400,110,100,100);
        buttons[3].setBounds(200,210,100,100);
        buttons[4].setBounds(300,210,100,100);
        buttons[5].setBounds(400,210,100,100);
        buttons[6].setBounds(200,310,100,100);
        buttons[7].setBounds(300,310,100,100);
        buttons[8].setBounds(400,310,100,100);
        for(int i=0;i<9;i++)
        {
            add(buttons[i]);
        }
     }
    public void change() throws SocketException, IOException
    {
     a=new ImageIcon("image/img2.PNG");
     b=new ImageIcon("image/img3.PNG");
     DatagramSocket socket=new DatagramSocket(55555);
     while(true)
     {
         
        byte[] by=new byte[1024];
        DatagramPacket packet=new DatagramPacket(by,by.length);
        socket.receive(packet);//get real time infomation.
                byte[] data=packet.getData();
                int len=packet.getLength();
                String str=new String(data,0,len);
                System.out.println(str+"+++233");
                System.out.print(str.contains("ip"));
                if(str.contains("ip")){
                Area.append(str+"\n");
                }
                else{
                System.out.println(str+"сно╥");
                Integer integer=new Integer(str.split(",")[0]);
                Integer integer1=new Integer(str.split(",")[1]);
               
                buttons[integer].setIcon(a);
                 socket.receive(packet);//get real time infomation.
                data=packet.getData();
                len=packet.getLength();
                str=new String(data,0,len);
                 if(str.contains("ip")){
                 Area.append(str+"\n");
                }
                 socket.receive(packet);//get real time infomation.
                data=packet.getData();
                len=packet.getLength();
                str=new String(data,0,len);
                 if(str.contains("ip")){
                 Area.append(str+"\n");
                }
                System.out.println(str+"23333");
                integer=new Integer(str.split(",")[0]);
                integer1=new Integer(str.split(",")[1]);
                buttons[integer].setIcon(b);
                }
     }
    }
     public static void main(String[] args) throws SocketException, IOException {
        
                
                //buttons[integer].setIcon(b);
               
        a b=new a();
        b.repaint();
        b.setVisible(true);
        b.setLayout(null);
        b.setSize(800,800);
        b.setButtons();
       b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       b.change();
        
}
     }
     
   