/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cycloid;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
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
 * @author Kijin
 */
public class Cycloid extends javax.swing.JApplet {

    Timer timer;
    int timersteptime = 100;
    static DrawPanel drawpanel;
    int n = 0;
    ActionListener action;
    boolean isTimerOn = false;
    XYSeriesCollection data1 = new XYSeriesCollection();
    XYSeriesCollection data1v1 = new XYSeriesCollection();
    XYSeriesCollection data1v2 = new XYSeriesCollection();
    JFreeChart chart = ChartFactory.createXYLineChart("Terminal Velocity","sec","Velocity[m/s]",data1,PlotOrientation.VERTICAL,true,true,false);
    XYPlot plot = new XYPlot();
    XYLineAndShapeRenderer render1 = new XYLineAndShapeRenderer();
    XYLineAndShapeRenderer render2 = new XYLineAndShapeRenderer();
    XYLineAndShapeRenderer render3 = new XYLineAndShapeRenderer();
    NumberAxis domainX = new NumberAxis();
    NumberAxis rangeY = new NumberAxis();
    /**
     * Initializes the applet Cycloid
     */
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
            java.util.logging.Logger.getLogger(Cycloid.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cycloid.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cycloid.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cycloid.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the applet */
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    initComponents();
                    drawpanel = (DrawPanel)drawnpanel;
                    timer = new Timer(timersteptime,action = new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if( isTimerOn && n>120){
                                timer.stop();
                                StartButton.setText("Start");
                                n = 0; 
                                isTimerOn = false;
                            }
                            else {
                                drawpanel.setTime(n);
                                drawpanel.repaint();
                                n++;
                            }
                        }
                    });
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public JFreeChart getResultChart(XYSeries x1, XYSeries x1v1, XYSeries x1v2){
        data1.removeAllSeries();
        data1v1.removeAllSeries();
        data1v2.removeAllSeries();
        // XY시리즈를 Dataset 형태로 변경
        data1.addSeries(x1);
        data1v1.addSeries(x1v1);
        data1v2.addSeries(x1v2);
        plot = chart.getXYPlot();
        plot.setDataset(1, data1v2);
        plot.setDataset(2, data1v1);
        domainX = (NumberAxis)plot.getDomainAxis();
        rangeY = (NumberAxis)plot.getRangeAxis();
        //domainX.setRange(0.,16.);
        //rangeY.setRange(0.,160.);
        render1.setSeriesShapesVisible(0, false);
        render2.setSeriesLinesVisible(0, false);
        render3.setSeriesLinesVisible(0, false);
        plot.setRenderer(2,render3);
        plot.setRenderer(0,render1);
        plot.setRenderer(1,render2);
        chart.setTitle("Terminal Velocity"); // 차트 타이틀
        chart.plotChanged(new PlotChangeEvent(plot));
//        System.out.println(v1.getItemCount());
        return chart;
    }


    /**
     * This method is called from within the init() method to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        drawnpanel = new DrawPanel();
        StartButton = new javax.swing.JButton();
        rgraph = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));

        jInternalFrame1.setVisible(true);

        drawnpanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout drawnpanelLayout = new javax.swing.GroupLayout(drawnpanel);
        drawnpanel.setLayout(drawnpanelLayout);
        drawnpanelLayout.setHorizontalGroup(
            drawnpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );
        drawnpanelLayout.setVerticalGroup(
            drawnpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 223, Short.MAX_VALUE)
        );

        StartButton.setText("Start");
        StartButton.setToolTipText("");
        StartButton.setAlignmentX(0.8F);
        StartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("r1=i*bwt + j*b");

        jLabel2.setText("r2=i*bcos(wt) + j*bsin(wt)");

        jLabel3.setText("r=r1 + r2");

        jLabel4.setText("v1=i*bw");

        jLabel5.setText("v2=-i*bwsin(wt) + j*bwcos(wt)");

        jLabel6.setText("v=v1 + v2");

        javax.swing.GroupLayout rgraphLayout = new javax.swing.GroupLayout(rgraph);
        rgraph.setLayout(rgraphLayout);
        rgraphLayout.setHorizontalGroup(
            rgraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rgraphLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rgraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rgraphLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(rgraphLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        rgraphLayout.setVerticalGroup(
            rgraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rgraphLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rgraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rgraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addContainerGap(291, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(drawnpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rgraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(StartButton))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(StartButton))
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addComponent(drawnpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rgraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

    private void StartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartButtonActionPerformed
        // TODO add your handling code here:
        if ( isTimerOn ) { 
            timer.stop();
            StartButton.setText("Resume");
            isTimerOn = false;
        }
        else {
            StartButton.setText("Stop");
            timer.start();
            isTimerOn = true;
        }
    }//GEN-LAST:event_StartButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton StartButton;
    private javax.swing.JPanel drawnpanel;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel rgraph;
    // End of variables declaration//GEN-END:variables
}
