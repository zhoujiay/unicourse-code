
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.Date;


public class Clock extends Canvas implements ActionListener {

    static javax.swing.Timer secondTime;

    Date date;
    Line2D secondLine,minuteLine,hourLine;
    int hour,minute,second;
    int a,b,c;
    double pointSX[] = new double[60],  //表示秒针端点坐标的数组
            pointSY[] = new double[60],
            pointMX[] = new double[60],  //表示分针端点坐标的数组
            pointMY[] = new double[60],
            pointHX[] = new double[60],  //表示时针端点的数组
            pointHY[] = new double[60];


    Clock() {
        //初始化一个计时器线程并将其为钟表面板计时
        secondTime = new javax.swing.Timer(1000,this);

        //得到 钟表中不同时刻 时针,分针,秒针的坐标
        pointSX[0] = 0;
        pointSY[0] = -100;
        pointMX[0] = 0;
        pointMY[0] = -90;
        pointHX[0] = 0;
        pointHY[0] = -70;
        double angle = 6 * Math.PI / 180;
        for (int i = 0; i < 59; i++){
            pointSX[i+1] = pointSX[i]*Math.cos(angle)-Math.sin(angle)*pointSY[i];
            pointSY[i+1] = pointSY[i]*Math.cos(angle)+pointSX[i]*Math.sin(angle);
            pointMX[i+1] = pointMX[i]*Math.cos(angle)-Math.sin(angle)*pointMY[i];
            pointMY[i+1] = pointMY[i]*Math.cos(angle)+pointMX[i]*Math.sin(angle);
            pointHX[i+1] = pointHX[i]*Math.cos(angle)-Math.sin(angle)*pointHY[i];
            pointHY[i+1] = pointHY[i]*Math.cos(angle)+pointHX[i]*Math.sin(angle);
        }
        for (int i = 0; i < 60; i++){
            pointSX[i] = pointSX[i]+120;
            pointSY[i] = pointSY[i]+120;
            pointMX[i] = pointMX[i]+120;
            pointMY[i] = pointMY[i]+120;
            pointHX[i] = pointHX[i]+120;
            pointHY[i] = pointHY[i]+120;
        }

        //初始化分别表示 秒针 时针和分针的3根二维线条
        secondLine = new Line2D.Double(0,0,0,0);
        minuteLine = new Line2D.Double(0,0,0,0);
        hourLine = new Line2D.Double(0,0,0,0);
        secondTime.start();

    }


    //绘制实时钟表
    public void paint(Graphics g){
        for(int i = 0; i < 60; i++){
            int m = (int)pointSX[i];
            int n = (int)pointSY[i];
            if (i%5 == 0){
                g.setColor(new Color(100, 149, 237));
                g.fillOval(m-4,n-4,8,8);
            }
            else{
                g.setColor(Color.gray);
                g.fillOval(m-2,n-2,4,4);
            }
        }

        g.fillOval(115,115,10,10);
        Graphics2D g_2d = (Graphics2D)g;
        g_2d.setColor(new Color(165,42,42));
        g_2d.draw(secondLine);
        BasicStroke bs = new BasicStroke(3f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER);
        g_2d.setStroke(bs);
        g_2d.setColor(new Color(33,136,104));
        g_2d.draw(minuteLine);
        bs = new BasicStroke(6f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER);
        g_2d.setStroke(bs);
        g_2d.setColor(new Color(33,136,104));
        g_2d.draw(hourLine);
    }


    //检测 设置时间的鼠标点击事件 未触发时添加在该面板上的计时器线程并实时绘制钟表显示时间
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == secondTime){
            date = new Date();
            String s = date.toString();
            hour = Integer.parseInt(s.substring(11,13));
            minute = Integer.parseInt(s.substring(14,16));
            second = Integer.parseInt(s.substring(17,19));
            int h = hour % 12;
            a = second;
            b = minute;
            c = h*5 + minute/12;
            secondLine.setLine(120,120,(int)pointSX[a],(int)pointSY[a]);
            minuteLine.setLine(120,120,(int)pointMX[b],(int)pointMY[b]);
            hourLine.setLine(120,120,(int)pointHX[c],(int)pointHY[c]);
            repaint();
        }

    }

}
