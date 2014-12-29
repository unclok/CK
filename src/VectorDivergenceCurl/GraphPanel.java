/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VectorDivergenceCurl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Horang
 */
public class GraphPanel extends JPanel {    
    
// Parameters for plotting a graph    
//    private int width = 800;
//    private int heigth = 400;
    private int padding = 25;  // margin on each side... 
    private int labelPadding = 25;   // margin on axis side...
    private Color lineColor = new Color(44, 102, 230, 180);
    private Color pointColor = new Color(100, 100, 100, 180);
    private Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f,BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
    private int pointWidth = 0; 
    private int numberYDivisions = 10;
    private List<Double> plotdata;
    private double x_offset = 0;
    private double x_multiplier = 1.0;
 
    private String x_format = "%.0f";
    
    
    
    public GraphPanel() {
        
    }
    
    public GraphPanel(List<Double> plotdata) {
        this.plotdata = plotdata;
      
    }
    
    
  
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
 
        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (plotdata.size() - 1);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxScore() - getMinScore());
         
 
        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < plotdata.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxScore() - plotdata.get(i)) * yScale + padding);
            graphPoints.add(new Point(x1, y1));
        }
 
        // draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);
 
        // create hatch marks and grid lines for y axis.
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            if (plotdata.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                String yLabel = ((double) ((getMinScore() + (getMaxScore() - getMinScore()) * ((i * 1.0) / numberYDivisions)))) + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }
 
        // and for x axis
        for (int i = 0; i < plotdata.size()+1; i++) {
            if (plotdata.size() > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (plotdata.size() - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                //if ((i % ((int) ((plotdata.size() / 20.0)) + 1)) == 0) {
                if ((i % 100 ) == 0) {    
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.BLACK);
                    String xLabel = ((double)i*x_multiplier + x_offset) + "";   ///  change x_offset if wavelength region have to be changed....
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }
        }
        
        
 
        // create x and y axes 
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);
 
        Stroke oldStroke = g2.getStroke();
        g2.setColor(lineColor);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }
 
        g2.setStroke(oldStroke);
        g2.setColor(pointColor);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - pointWidth / 2;
            int y = graphPoints.get(i).y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }
 
//    @Override
//    public Dimension getPreferredSize() {
//        return new Dimension(width, heigth);
//    }
 
    private double getMinScore() {
        double minScore = Double.MAX_VALUE;
        for (Double score : plotdata) {
            //minScore = Math.min(minScore, score);
            if(score<minScore)minScore=score;
        }
        // return minScore;
        //return Math.floor(minScore/10)*10; // For making min value increase by 10
        return 0;
    }
 
    private double getMaxScore() {
        double maxScore = Double.MIN_VALUE;
        for (Double score : plotdata) {
            //maxScore = Math.max(maxScore, score);
            if(score>maxScore)maxScore=score;
        }
        return maxScore;
        // return Math.ceil(maxScore/10)*10;  // For making max value increase by 10
        //return 100.0;
    }
 
    public void setScores(List<Double> plotdata) {
        this.plotdata = plotdata;
        invalidate();
        this.repaint();
    }
 
    public List<Double> getScores() {
        return plotdata;
    }
    
    public void setXmultiplier(double xm) {
        x_multiplier = xm;
    }
    
    public void setXoffset(double offset) {
        x_offset = offset;
    }
    
    public void setXformat(String formatstring) {
        x_format = formatstring;
    }
    
    
//    static void createAndShowGui(javax.swing.JPanel panel) {   // Garbage...
////    static void createAndShowGui() {        
////    default static void createAndShowGui() {
//        List<Double> plotdata = new ArrayList<>();
//        Random random = new Random();   
//        int maxDataPoints = 10;
//        int maxScore = 10;
//           
//        
//        for (int i = 0; i < maxDataPoints; i++) {
//            plotdata.add((double) random.nextDouble() * maxScore);
////            plotdata.add((double) i);
//        }  // Make garbage data in here
//
//        JFrame frame = new JFrame("DrawGraph");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add(mainPanel);
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);     
//    }
}