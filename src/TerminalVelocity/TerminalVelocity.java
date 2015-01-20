/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TerminalVelocity;

import YoungDoubleSlit.YoungDoubleSlit;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.Vector;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


/**
 *
 * @author 기진
 */
public class TerminalVelocity extends javax.swing.JApplet {

    /**
     * Initializes the applet TerminalVelocity
     */
    BufferedReader br1 = null;
    BufferedReader br1v1 = null;
    BufferedReader br1v2 = null;
    BufferedReader br2 = null;
    BufferedReader br2v1 = null;
    BufferedReader br2v2 = null;
    String input = null;
    XYSeries v1,v1v1,v1v2;
    XYSeries l1,l1v1,l1v2;
    XYSeries v2,v2v1,v2v2;
    XYSeries l2,l2v1,l2v2;
    javax.swing.Timer timer ;
    boolean isTimerOn;  

    
    @Override
    public void init() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TerminalVelocity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TerminalVelocity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TerminalVelocity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TerminalVelocity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the applet */
        try {
            InputStream path = this.getClass().getResourceAsStream("data/table10cm"); // 현재 클래스의 절대 경로를 가져온다.
            System.out.println(path);
            // BufferedReader 객체 생성

            br1 = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("data/table10cm")));
            br1v1 = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("data/table10cmv1")));
            br1v2 = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("data/table10cmv2")));
            br2 = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("data/table30cm")));
            br2v1 = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("data/table30cmv1")));
            br2v2 = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("data/table30cmv2")));
            
            int n = 0;
            v1 = new XYSeries("Histogram of velocity of sphere");
            l1 = new XYSeries("Histogram of distance of sphere");
            while((input=br1.readLine())!=null){
               String str[]=input.split("\\s"); //라인읽을 때 띄어쓰기 간격으로 하나씩 읽어 str읽어들어옴  내용다들어감
               System.out.println("\n"+str[0]+","+str[1]+","+str[2]);
               System.out.println(n);
               v1.add(Double.parseDouble(str[0]),Double.parseDouble(str[1]));
               System.out.println(n);
               l1.add(Double.parseDouble(str[0]),Double.parseDouble(str[2]));
               System.out.println(n);
               n++;
            }
            
            v1v1 = new XYSeries("Histogram of velocity of sphere");
            l1v1 = new XYSeries("Histogram of distance of sphere");
            while((input=br1v1.readLine())!=null){
               String str[]=input.split("\\s"); //라인읽을 때 띄어쓰기 간격으로 하나씩 읽어 str읽어들어옴  내용다들어감
               v1v1.add(Double.parseDouble(str[0]),Double.parseDouble(str[1]));
               l1v1.add(Double.parseDouble(str[0]),Double.parseDouble(str[2]));
            }
            
            v1v2 = new XYSeries("Histogram of velocity of sphere");
            l1v2 = new XYSeries("Histogram of distance of sphere");
            while((input=br1v2.readLine())!=null){
               String str[]=input.split("\\s"); //라인읽을 때 띄어쓰기 간격으로 하나씩 읽어 str읽어들어옴  내용다들어감
               v1v2.add(Double.parseDouble(str[0]),Double.parseDouble(str[1]));
               l1v2.add(Double.parseDouble(str[0]),Double.parseDouble(str[2]));
            }
            
            v2 = new XYSeries("Histogram of velocity of plate");
            l2 = new XYSeries("Histogram of distance of plate");
            while((input=br2.readLine())!=null){
               String str[]=input.split("\\s"); //라인읽을 때 띄어쓰기 간격으로 하나씩 읽어 str읽어들어옴  내용다들어감
               v2.add(Double.parseDouble(str[0]),Double.parseDouble(str[1]));
               l2.add(Double.parseDouble(str[0]),Double.parseDouble(str[2]));
            }
            
            v2v1 = new XYSeries("Histogram of velocity of plate");
            l2v1 = new XYSeries("Histogram of distance of plate");
            while((input=br2v1.readLine())!=null){
               String str[]=input.split("\\s"); //라인읽을 때 띄어쓰기 간격으로 하나씩 읽어 str읽어들어옴  내용다들어감
               v2v1.add(Double.parseDouble(str[0]),Double.parseDouble(str[1]));
               l2v1.add(Double.parseDouble(str[0]),Double.parseDouble(str[2]));
            }
            
            v2v2 = new XYSeries("Histogram of velocity of plate");
            l2v2 = new XYSeries("Histogram of distance of plate");
            while((input=br2v2.readLine())!=null){
               String str[]=input.split("\\s"); //라인읽을 때 띄어쓰기 간격으로 하나씩 읽어 str읽어들어옴  내용다들어감
               v2v2.add(Double.parseDouble(str[0]),Double.parseDouble(str[1]));
               l2v2.add(Double.parseDouble(str[0]),Double.parseDouble(str[2]));
            }         
            ChartPanel = new ResultViewPane(getResultChart());
            DrawPanel = new DrawViewPane();
                 
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    initComponents();
                    isTimerOn = false;   
                    timer = null;
                }
            }); 
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{			
            // BufferedReader FileReader를 닫아준다.
            if(br1 != null) try{br1.close();}catch(IOException e){}
            if(br1v1 != null) try{br1v1.close();}catch(IOException e){}
            if(br1v2 != null) try{br1v2.close();}catch(IOException e){}
            if(br2 != null) try{br2.close();}catch(IOException e){}
            if(br2v1 != null) try{br2v1.close();}catch(IOException e){}
            if(br2v2 != null) try{br2v2.close();}catch(IOException e){}
	}
    }
    
        public void timerStart()
    {   if ( timer == null ){
            System.out.println("Start Timer!!");                    
            timer = new javax.swing.Timer(100,new aListener()); 
            timer.stop();
        }
        timer.start();
        isTimerOn = true;
    }
    public void timerStop()
    {
        timer.stop();
        isTimerOn = false;
    }
    public class aListener implements ActionListener 
    {       
                public void actionPerformed(ActionEvent e) {                    
                    if ( isTimerOn ) {
                        System.out.println("On Timer!!");
                        //System.out.println( (int) theWaveLengthSlider.getValue()) ;
                       DrawPanel.repaint();
                       ChartPanel.repaint();
                    }
                    else {
                        System.out.println("Already Stop Timer!!");
                    }
                }
            //}
    };
    


    public JFreeChart getResultChart(){
        // XY시리즈를 Dataset 형태로 변경
        XYSeriesCollection data = new XYSeriesCollection(v1);
        final JFreeChart chart = ChartFactory.createXYLineChart("Amplitude of Light","Angle","Amp.",data,PlotOrientation.VERTICAL,true,true,false);
        chart.setTitle("Amplitude of light"); // 차트 타이틀
        System.out.println(v1.getItemCount());
        return chart;
    }

    public class DrawViewPane extends JPanel{        
        DrawViewPane(){                        
            super();
            System.out.println("draw here!");
        }

        public void paintComponent(Graphics g2)
        {
            super.paintComponent(g2);
            if ( isTimerOn ) {

            }
            Graphics2D g2d = (Graphics2D)g2;
            Ellipse2D.Double hole = new Ellipse2D.Double();
            hole.width = 28;
            hole.height = 28;
            hole.x = 14;
            hole.y = 14;
            System.out.println("draw there!");
        }
    }
    
    public class ResultViewPane extends ChartPanel{        
        ResultViewPane(JFreeChart chart){                        
            super(chart);
            System.out.println("I'm here!");
        }

        public void paintComponent(Graphics g2)
        {
            super.paintComponent(g2);
            if ( isTimerOn ) {
                this.setChart(getResultChart());
            }
            System.out.println("I'm there!");
        }
    }

    /**
     * This method is called from within the init() method to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainFrame = new javax.swing.JFrame();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        DrawPanel = new javax.swing.JPanel();
        ChartPanel = new javax.swing.JPanel();
        PlateButton = new javax.swing.JRadioButton();
        SphereButton = new javax.swing.JRadioButton();
        SecField = new javax.swing.JTextField();
        SpeedField = new javax.swing.JTextField();
        SecLabel = new javax.swing.JLabel();
        SpeedLabel = new javax.swing.JLabel();
        StartButton = new javax.swing.JButton();

        MainFrame.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout MainFrameLayout = new javax.swing.GroupLayout(MainFrame.getContentPane());
        MainFrame.getContentPane().setLayout(MainFrameLayout);
        MainFrameLayout.setHorizontalGroup(
            MainFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        MainFrameLayout.setVerticalGroup(
            MainFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jInternalFrame1.setVisible(true);

        DrawPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout DrawPanelLayout = new javax.swing.GroupLayout(DrawPanel);
        DrawPanel.setLayout(DrawPanelLayout);
        DrawPanelLayout.setHorizontalGroup(
            DrawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        DrawPanelLayout.setVerticalGroup(
            DrawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 203, Short.MAX_VALUE)
        );

        ChartPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout ChartPanelLayout = new javax.swing.GroupLayout(ChartPanel);
        ChartPanel.setLayout(ChartPanelLayout);
        ChartPanelLayout.setHorizontalGroup(
            ChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 281, Short.MAX_VALUE)
        );
        ChartPanelLayout.setVerticalGroup(
            ChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 122, Short.MAX_VALUE)
        );

        PlateButton.setText("1kg plate");

        SphereButton.setText("1kg sphere");
        SphereButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SphereButtonActionPerformed(evt);
            }
        });

        SecField.setText("jTextField1");

        SpeedField.setText("jTextField2");
        SpeedField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SpeedFieldActionPerformed(evt);
            }
        });

        SecLabel.setText("sec");

        SpeedLabel.setText("m/s");

        StartButton.setText("Start");
        StartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DrawPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SphereButton)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(SecField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SecLabel))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(SpeedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SpeedLabel))
                    .addComponent(StartButton)
                    .addComponent(PlateButton)))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(DrawPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jInternalFrame1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(SphereButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PlateButton)
                        .addGap(8, 8, 8)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SecField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SecLabel))
                        .addGap(14, 14, 14)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SpeedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SpeedLabel))
                        .addGap(28, 28, 28)
                        .addComponent(StartButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        StartButton.getAccessibleContext().setAccessibleName("StartButton");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SpeedFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SpeedFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SpeedFieldActionPerformed

    private void SphereButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SphereButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SphereButtonActionPerformed

    private void StartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartButtonActionPerformed
        // TODO add your handling code here:
        if ( isTimerOn ) { 
            timerStop();
            StartButton.setText("Resume");
        }
        else {
            StartButton.setText("Stop");
            timerStart();
        }
    }//GEN-LAST:event_StartButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ChartPanel;
    private javax.swing.JPanel DrawPanel;
    private javax.swing.JFrame MainFrame;
    private javax.swing.JRadioButton PlateButton;
    private javax.swing.JTextField SecField;
    private javax.swing.JLabel SecLabel;
    private javax.swing.JTextField SpeedField;
    private javax.swing.JLabel SpeedLabel;
    private javax.swing.JRadioButton SphereButton;
    private javax.swing.JButton StartButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JInternalFrame jInternalFrame1;
    // End of variables declaration//GEN-END:variables
}
