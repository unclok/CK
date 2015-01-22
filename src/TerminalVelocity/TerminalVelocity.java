/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TerminalVelocity;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
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
    Vector vv1 = new Vector(); 
    Vector vl1 = new Vector(); 
    Vector vv1v1 = new Vector(); 
    Vector vv1v2 = new Vector(); 
    Vector vv2 = new Vector(); 
    Vector vl2 = new Vector(); 
    Vector vv2v1 = new Vector(); 
    Vector vv2v2 = new Vector(); 
    int n;
    javax.swing.Timer timer ;
    boolean isTimerOn;  
    static ResultViewPane tempChartPanel;
    static DrawViewPane tempDrawPanel;
    Ellipse2D.Double hole = new Ellipse2D.Double();
       
    //static ArrayList<Thread> threads = new ArrayList<Thread>();
    Image cloud1,cloud2,mountain,background;
    
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
        n=0;
        SecField = new JTextField();
        DistanceField = new JTextField();
        SpeedField = new JTextField();
        SecField.setText("0.00");
        DistanceField.setText("0.00");
        SpeedField.setText("0.00"); 
        tempDrawPanel = (DrawViewPane)DrawPanel;
        tempChartPanel = (ResultViewPane)ChartPanel;     
        hole.height = 10;
        hole.width = 10;            

        /* Create and display the applet */
        try {
            InputStream backgroundpath = this.getClass().getResourceAsStream("data/background.png");
            InputStream cloud1path = this.getClass().getResourceAsStream("data/cloud1.png");
            InputStream cloud2path = this.getClass().getResourceAsStream("data/cloud2.png");
            InputStream mountainpath = this.getClass().getResourceAsStream("data/southmountain.png");
            InputStream path = this.getClass().getResourceAsStream("data/table10cm"); // 현재 클래스의 절대 경로를 가져온다.
            System.out.println(path);
            cloud1 = ImageIO.read(cloud1path);
            cloud2 = ImageIO.read(cloud2path);
            mountain = ImageIO.read(mountainpath);
            background = ImageIO.read(backgroundpath);
            
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
                if(Double.parseDouble(str[2])<1000){
                //v1.add(Double.parseDouble(str[0]),Double.parseDouble(str[1]));
                vv1.add(Double.parseDouble(str[1]));
                vl1.add(Double.parseDouble(str[2]));
                }
            }  
            
            v1v1 = new XYSeries("Histogram of velocity of sphere");
            l1v1 = new XYSeries("Histogram of distance of sphere");
            while((input=br1v1.readLine())!=null){
               String str[]=input.split("\\s"); //라인읽을 때 띄어쓰기 간격으로 하나씩 읽어 str읽어들어옴  내용다들어감
               if(Double.parseDouble(str[2])<1000){
               vv1v1.add(Double.parseDouble(str[1]));
               l1v1.add(Double.parseDouble(str[0]),Double.parseDouble(str[2]));
              }
             }
            
            v1v2 = new XYSeries("Histogram of velocity of sphere");
            l1v2 = new XYSeries("Histogram of distance of sphere");
            while((input=br1v2.readLine())!=null){
               String str[]=input.split("\\s"); //라인읽을 때 띄어쓰기 간격으로 하나씩 읽어 str읽어들어옴  내용다들어감
               if(Double.parseDouble(str[2])<1000){
               vv1v2.add(Double.parseDouble(str[1]));
               l1v2.add(Double.parseDouble(str[0]),Double.parseDouble(str[2]));
               }
            }
            
            v2 = new XYSeries("Histogram of velocity of plate");
            l2 = new XYSeries("Histogram of distance of plate");
            while((input=br2.readLine())!=null){
               String str[]=input.split("\\s"); //라인읽을 때 띄어쓰기 간격으로 하나씩 읽어 str읽어들어옴  내용다들어감
               if(Double.parseDouble(str[2])<1000){
               vv2.add(Double.parseDouble(str[1]));
               l2.add(Double.parseDouble(str[0]),Double.parseDouble(str[2]));
              }
             }
            
            v2v1 = new XYSeries("Histogram of velocity of plate");
            l2v1 = new XYSeries("Histogram of distance of plate");
            while((input=br2v1.readLine())!=null){
               String str[]=input.split("\\s"); //라인읽을 때 띄어쓰기 간격으로 하나씩 읽어 str읽어들어옴  내용다들어감
               if(Double.parseDouble(str[2])<1000){
               vv2v1.add(Double.parseDouble(str[1]));
               l2v1.add(Double.parseDouble(str[0]),Double.parseDouble(str[2]));
              }
             }
            
            v2v2 = new XYSeries("Histogram of velocity of plate");
            l2v2 = new XYSeries("Histogram of distance of plate");
            while((input=br2v2.readLine())!=null){
               String str[]=input.split("\\s"); //라인읽을 때 띄어쓰기 간격으로 하나씩 읽어 str읽어들어옴  내용다들어감
               if(Double.parseDouble(str[2])<1000){
               vv2v2.add(Double.parseDouble(str[1]));
               l2v2.add(Double.parseDouble(str[0]),Double.parseDouble(str[2]));
              }
             }         
            
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    initComponents();
                    isTimerOn = false;   
                    timer = null;
                    DrawPanel.repaint();
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
        hole.x = DrawPanel.getWidth()/2;
        hole.y = DrawPanel.getHeight()/10;
    }
    
        public void timerStart()
    {   if ( timer == null ){
            System.out.println("Start Timer!!");                    
            timer = new javax.swing.Timer(10,new aListener()); 
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
                    tempDrawPanel = (DrawViewPane)DrawPanel;
                    tempChartPanel = (ResultViewPane)ChartPanel;     
                    if ( isTimerOn ) {
                        if(n==0){
                            v1.clear();
                            l1.clear();
                            v1v1.clear();
                            v1v2.clear();
                            v2.clear();
                            l2.clear();
                            v2v1.clear();
                            v2v2.clear();
                            SecField.setText("0.00");
                            DistanceField.setText("0.00");
                            SpeedField.setText("0.00");
                        }
                        int onestepcount = 40;//
                        for(int i=0;i<40;i++){
                        if(vv1.size()>=n+1){
                                
                            if(i==39)SecField.setText(String.format("%.2f",n*0.04));
                            if(vv1.size()>=n+1){
                                v1.add(n*0.04, (double) vv1.get(n));
                                if(i==39)SpeedField.setText(String.format("%.2f",(double)vv1.get(n)));
                            }
                            if(vl1.size()>=n+1){
                                l1.add(n*0.04, (double) vl1.get(n));
                                if(i==3)DistanceField.setText(String.format("%.2f",(double)vl1.get(n)));
                            }
                            if(vv1v1.size()>=n+1)v1v1.add(n*0.04, (double) vv1v1.get(n));
                            if(vv1v2.size()>=n+1){v1v2.add(n*0.04, (double) vv1v2.get(n));
                            n++;}
                            if(i==39)tempDrawPanel.repaint((double) vl1.get(n-1));
                            if(i==39)tempChartPanel.repaint(getResultChart(v1,v1v1,v1v2));
                        }
                        else
                        {
                            isTimerOn = false;
                            StartButton.setText("Start");
                            n = 0;
                            tempDrawPanel.repaint();
                        }
                        
                        }
                    }
                    else {
                        System.out.println("Already Stop Timer!!");
                    }
                }
            //}
    };
/*    
    public class Threadrun implements Runnable{
        int seq;
        public Threadrun(int seq) {
            this.seq = seq;
        }
        public void run() {
            System.out.println(this.seq+" thread start.");
            try {
                Thread.sleep(1000);
            }catch(Exception e) {
            }
            System.out.println(this.seq+" thread end.");
        }
    }
*/

    public JFreeChart getResultChart(XYSeries v1, XYSeries v1v1, XYSeries v1v2){
        // XY시리즈를 Dataset 형태로 변경
        XYSeriesCollection data1 = new XYSeriesCollection(v1);
        XYSeriesCollection data1v1 = new XYSeriesCollection(v1v1);
        XYSeriesCollection data1v2 = new XYSeriesCollection(v1v2);
        JFreeChart chart = ChartFactory.createXYLineChart("Amplitude of Light","Angle","Amp.",data1,PlotOrientation.VERTICAL,true,true,false);
        XYPlot plot = chart.getXYPlot();
        plot.setDataset(1, data1v1);
        plot.setDataset(2, data1v2);
        NumberAxis domainX = (NumberAxis)plot.getDomainAxis();
        domainX.setRange(0.,16.);
        NumberAxis rangeY = (NumberAxis)plot.getRangeAxis();
        rangeY.setRange(0.,160.);
        XYLineAndShapeRenderer render0 = new XYLineAndShapeRenderer();
        XYLineAndShapeRenderer render1 = new XYLineAndShapeRenderer();
        XYLineAndShapeRenderer render2 = new XYLineAndShapeRenderer();
        plot.setRenderer(0,render0);
        plot.setRenderer(1,render1);
        plot.setRenderer(2,render2);
        chart.setTitle("Amplitude of light"); // 차트 타이틀
        chart.plotChanged(new PlotChangeEvent(plot));
//        System.out.println(v1.getItemCount());
        return chart;
    }

    public class DrawViewPane extends JPanel{
        DrawViewPane(){                        
            super();
        }

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.drawImage(background, 0 ,0, DrawPanel.getWidth(),DrawPanel.getHeight(), this);
            g2d.setColor(Color.gray);
            g2d.fill(hole);
            g2d.draw(hole);            
        }

        
        public void paintComponent(Double height){
            super.paintComponent(this.getGraphics());
            Graphics2D g2d = (Graphics2D)this.getGraphics();
            g2d.drawImage(background, 0 ,0, DrawPanel.getWidth(),DrawPanel.getHeight(), this);        
            hole.x = DrawPanel.getWidth()/2;
            hole.y = DrawPanel.getHeight()/10 + DrawPanel.getHeight()*9*height/1000/10;
            g2d.setColor(Color.gray);
            g2d.fill(hole);
            g2d.draw(hole);
        }
        
        public void repaint(Double height){
            paintComponent(height);
            repaint();
        }
    }
    
    public class ResultViewPane extends ChartPanel{        
        ResultViewPane(JFreeChart chart){                        
            super(chart);
        }

        public void paintComponent(Graphics g2)
        {
            super.paintComponent(g2);
            if ( isTimerOn ) {
                this.setChart(this.getChart());
            }
        }
        
        public void paintComponent(JFreeChart chart)
        {
            super.paintComponent(this.getGraphics());
            if ( isTimerOn ) {
                this.setChart(chart);
            }
        }
        
        public void repaint(JFreeChart chart)
        {
            this.paintComponent(chart);
            repaint();
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        DrawPanel = new DrawViewPane();
        ChartPanel = ChartPanel = new ResultViewPane(getResultChart(v1,v1v1,v1v2));
        PlateButton = new javax.swing.JRadioButton();
        SphereButton = new javax.swing.JRadioButton();
        SecField = new javax.swing.JTextField();
        SpeedField = new javax.swing.JTextField();
        SecLabel = new javax.swing.JLabel();
        SpeedLabel = new javax.swing.JLabel();
        StartButton = new javax.swing.JButton();
        DistanceField = new javax.swing.JTextField();
        DistanceLabel = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(400, 419));

        jInternalFrame1.setResizable(true);
        jInternalFrame1.setMinimumSize(new java.awt.Dimension(400, 415));
        jInternalFrame1.setVisible(true);

        DrawPanel.setBackground(new java.awt.Color(255, 255, 255));
        DrawPanel.setMinimumSize(new java.awt.Dimension(200, 150));
        DrawPanel.setPreferredSize(new java.awt.Dimension(281, 150));

        javax.swing.GroupLayout DrawPanelLayout = new javax.swing.GroupLayout(DrawPanel);
        DrawPanel.setLayout(DrawPanelLayout);
        DrawPanelLayout.setHorizontalGroup(
            DrawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        DrawPanelLayout.setVerticalGroup(
            DrawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 227, Short.MAX_VALUE)
        );

        ChartPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout ChartPanelLayout = new javax.swing.GroupLayout(ChartPanel);
        ChartPanel.setLayout(ChartPanelLayout);
        ChartPanelLayout.setHorizontalGroup(
            ChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        ChartPanelLayout.setVerticalGroup(
            ChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 153, Short.MAX_VALUE)
        );

        PlateButton.setText("1kg plate");

        SphereButton.setText("1kg sphere");
        SphereButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SphereButtonActionPerformed(evt);
            }
        });

        SecField.setText("0.00");

        SpeedField.setText("0.00");
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

        DistanceField.setText("0.00");

        DistanceLabel.setText("m");

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DrawPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SphereButton)
                    .addComponent(StartButton)
                    .addComponent(PlateButton)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(DistanceField, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                            .addComponent(SecField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SpeedField, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SpeedLabel)
                            .addComponent(DistanceLabel)
                            .addComponent(SecLabel)))))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(DrawPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DistanceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DistanceLabel))
                        .addGap(27, 27, 27)))
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(StartButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(ChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        StartButton.getAccessibleContext().setAccessibleName("StartButton");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    private javax.swing.JTextField DistanceField;
    private javax.swing.JLabel DistanceLabel;
    private javax.swing.JPanel DrawPanel;
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
