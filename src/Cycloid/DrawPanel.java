/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cycloid;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JPanel;
//import org.jfree.data.xy.XYSeries;

/**
 *
 * @author Kijin
 */
public class DrawPanel extends javax.swing.JPanel {

    Vector prepointvector = new Vector();
    int time = 0;
    Ellipse2D.Double hole = new Ellipse2D.Double();
    Point rcenter;
    Point rcircle;
    Point rtotal;
    Point vcenter;
    Point vcircle;
    Point vtotal;
    Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
    /**
     * Creates new form DrawPanel
     */
    public DrawPanel() {
        initComponents();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //Point prepoint = new Point();
        Point tempprepoint = new Point();
        Point temppostpoint = new Point();
        double h = 0;
         double d = 0;
         double b = 0;
         int bw = 0;
         rcenter = new Point();
         rcircle = new Point();
         rtotal = new Point();
         vcenter = new Point();
         vcircle = new Point();
         vtotal = new Point();

        h = this.getHeight()/10;
        d = this.getHeight()/4;
        b = d/2;
       hole.height = d;
       hole.width =  d;
       bw = this.getWidth()/100;
       hole.y = this.getHeight() - d - h;
       hole.x = bw*time;
       rcenter.x = (int)(hole.x + b);
       rcenter.y = (int)(hole.y + b);
       vcenter.x = (int)bw;
       vcenter.y = 0;
       rcircle.x = (int)(b*sin(hole.x/b));
       rcircle.y = (int)- (b*cos(hole.x/b));
       vcircle.x = (int)(bw*cos(hole.x/b));
       vcircle.y = (int)(b*sin(hole.x/b));
       rtotal.x = rcenter.x + rcircle.x;
       rtotal.y = rcenter.y + rcircle.y;
       vtotal.x = vcenter.x + vcircle.x;
       vtotal.y = vcenter.y + vcircle.y;
       Graphics2D g2d = (Graphics2D)g;
       g2d.draw(hole);
       drawArrow(g2d,"v",rtotal.x,rtotal.y,
               vtotal.x + rtotal.x,
               vtotal.y + rtotal.y);
       drawArrow(g2d,"vcenter",rcenter.x,rcenter.y,
               vcenter.x + rcenter.x,
               vcenter.y + rcenter.y);
       drawArrow(g2d,"v1",rtotal.x,rtotal.y,
               vcenter.x + rtotal.x,
               vcenter.y + rtotal.y);
       drawArrow(g2d,"v2",rtotal.x,rtotal.y,
               vcircle.x + rtotal.x,
               vcircle.y + rtotal.y);
        
       //prepoint.x = rtotal.x;
       //prepoint.y = rtotal.y;
        //if(time!=0)prepointvector.add(prepoint);

        int freeset = 1;
        for(int i = freeset;i<=time;i=i+freeset){
            tempprepoint.x = (int)(bw*(i-freeset) + b) + (int)(b*sin(bw*(i-freeset)/b));
            tempprepoint.y = (int)(hole.y + b) - (int)(b*cos(bw*(i-freeset)/b));
            temppostpoint.x = (int)(bw*i + b) + (int)(b*sin(bw*i/b));
            temppostpoint.y = (int)(hole.y + b) - (int)(b*cos(bw*i/b));
            //g2d.setStroke(dashed);
           g2d.drawLine(tempprepoint.x, tempprepoint.y, temppostpoint.x, temppostpoint.y);
       }
    }
  /*  
    public XYSeries getRCenter(){
        double h = 0;
         double d = 0;
         double b = 0;
         int bw = 0;

        h = this.getHeight()/10;
        d = this.getHeight()/10;
        b = d/2;
       bw = this.getWidth()/100;
       double y = this.getHeight() - d - h;
       
       XYSeries rcenter = new XYSeries("r to center");
        for(int i = 0;i<=2*PI*b/bw;i++){
            rcenter.add((bw*i + b),(y + b));
        }
        
        return rcenter;
    }
    
    public XYSeries getRCircle(){
        double h = 0;
         double d = 0;
         double b = 0;
         int bw = 0;
        h = this.getHeight()/10;
        d = this.getHeight()/10;
        b = d/2;
       bw = this.getWidth()/100;
       XYSeries rcircle = new XYSeries("r from center to circle");
        for(int i = 0;i<=2*PI*b/bw;i++){
            rcircle.add((bw*i + b), - (int)(b*cos(bw*i/b)));
        }
        
        return rcircle;
    }
    
    public XYSeries getRTotal(){
        double h = 0;
         double d = 0;
         double b = 0;
         int bw = 0;

        h = this.getHeight()/10;
        d = this.getHeight()/10;
        b = d/2;
       bw = this.getWidth()/100;
       double y = this.getHeight() - d - h;
       XYSeries rtotal = new XYSeries("r to circle");
        for(int i = 0;i<=2*PI*b/bw;i++){
            rtotal.add((bw*i + b) + (int)(b*sin(bw*i/b)),(y + b) - (int)(b*cos(bw*i/b)));
        }
        return rtotal;
    }
    
    public XYSeries getVCenter(){
        double h = 0;
         double d = 0;
         double b = 0;
         int bw = 0;

        h = this.getHeight()/10;
        d = this.getHeight()/10;
        b = d/2;
       bw = this.getWidth()/100;
       double y = this.getHeight() - d - h;
       vcenter.x = (int)bw;
       vcenter.y = 0;
       rcircle.x = (int)(b*sin(hole.x/b));
       rcircle.y = (int)- (b*cos(hole.x/b));
       vcircle.x = (int)(bw*cos(hole.x/b));
       vcircle.y = (int)(b*sin(hole.x/b));

       XYSeries rtotal = new XYSeries("r to circle");
        for(int i = 0;i<=2*PI*b/bw;i++){
            rtotal.add((bw*i + b) + (int)(b*sin(bw*i/b)),(y + b) - (int)(b*cos(bw*i/b)));
        }
        return rtotal;
    }
    
    public XYSeries getVCircle(){
        double h = 0;
         double d = 0;
         double b = 0;
         int bw = 0;

        h = this.getHeight()/10;
        d = this.getHeight()/10;
        b = d/2;
       bw = this.getWidth()/100;
       double y = this.getHeight() - d - h;
       XYSeries rtotal = new XYSeries("r to circle");
        for(int i = 0;i<=2*PI*b/bw;i++){
            rtotal.add((bw*i + b) + (int)(b*sin(bw*i/b)),(y + b) - (int)(b*cos(bw*i/b)));
        }
        return rtotal;
    }
    
    public XYSeries getVTotal(){
        double h = 0;
         double d = 0;
         double b = 0;
         int bw = 0;

        h = this.getHeight()/10;
        d = this.getHeight()/10;
        b = d/2;
       bw = this.getWidth()/100;
       double y = this.getHeight() - d - h;
       XYSeries rtotal = new XYSeries("r to circle");
        for(int i = 0;i<=2*PI*b/bw;i++){
            rtotal.add((bw*i + b) + (int)(b*sin(bw*i/b)),(y + b) - (int)(b*cos(bw*i/b)));
        }
        return rtotal;
    }
    */
    public void setTime(int _time){
        time = _time;
    }

    void drawArrow(Graphics g, String text, int x1, int y1, int x2, int y2) {
	drawArrow(g, text, x1, y1, x2, y2, 5);
    }

    void drawArrow(Graphics g, String text,
		   int x1, int y1, int x2, int y2, int as) {
	g.drawLine(x1, y1, x2, y2);
	double l = java.lang.Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
	if (l > as/2) {  // was as
	    double hatx = (x2-x1)/l;
	    double haty = (y2-y1)/l;
	    g.drawLine(x2, y2,
		       (int) (haty*as-hatx*as+x2),
		       (int) (-hatx*as-haty*as+y2));
	    g.drawLine(x2, y2,
		       (int) (-haty*as-hatx*as+x2),
		       (int) (hatx*as-haty*as+y2));
	    if (text != null)
		g.drawString(text, (int) (x2+hatx*10), (int) (y2+haty*10));
	}
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
