

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

public class TimePanel extends JPanel implements ActionListener {

    //时间面板的所有属性
    static javax.swing.Timer secondTime2;
    static Clock clock;
    static JSpinner nowTimeSpin;
    JPanel nowTimePanel;


    public TimePanel(){
        //初始化一个计时器线程并将其为时间面板计时
        secondTime2 = new javax.swing.Timer(1000,this);

        //设置时间面板的边框和布局管理器
        setBorder(BorderFactory.createTitledBorder("时间(T)"));
        setLayout(new BorderLayout());

        //初始化 显示时间微调器 并设置其显示的时间格式和数值
        SpinnerModel time = new SpinnerDateModel();
        nowTimeSpin = new JSpinner(time);
        nowTimeSpin.setValue(new Date());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(nowTimeSpin,"HH:mm:ss");
        nowTimeSpin.setEditor(editor);

        //初始化 显示时间面板 设置其边框并将 显示时间微调器 添加进去
        nowTimePanel = new JPanel();
        nowTimePanel.add(nowTimeSpin);
        nowTimePanel.setLayout(new GridLayout(1,1));
        nowTimePanel.setBorder(BorderFactory.createEmptyBorder(10,30,10,30));

        //初始化一个 钟表 并将其和 显示时间面板 添加到时间面板
        clock = new Clock();
        add(clock,BorderLayout.CENTER);
        add(nowTimePanel,BorderLayout.SOUTH);
    }


    //检测 设置时间的鼠标点击事件 触发后添加在该面板上的计时器线程 并按设置后的时间实时显示时间
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == secondTime2) {
            clock.second = (clock.second+1)%60;
            int h = clock.hour%12;
            clock.a = clock.second;
            clock.b = clock.minute;
            clock.c = h*5+clock.minute/12;
            clock.secondLine.setLine(120,120,(int)clock.pointSX[clock.a],(int)clock.pointSY[clock.a]);
            clock.minuteLine.setLine(120,120,(int)clock.pointMX[clock.b],(int)clock.pointMY[clock.b]);
            clock.hourLine.setLine(120,120,(int)clock.pointHX[clock.c],(int)clock.pointHY[clock.c]);
            clock.repaint();
        }

    }


    //鼠标点击设置时间的事件类
    static class  MouseClickDefineTime extends MouseAdapter {
        public void mouseClicked(MouseEvent e){
            String timeStr = nowTimeSpin.getValue().toString();
            clock.hour = Integer.parseInt(timeStr.substring(11,13));
            clock.minute = Integer.parseInt(timeStr.substring(14,16));
            clock.second = Integer.parseInt(timeStr.substring(17,19));
            int h = clock.hour%12;
            clock.a = clock.second;
            clock.b = clock.minute;
            clock.c = h*5+clock.minute/12;
            clock.secondLine.setLine(120,120,(int)clock.pointSX[clock.a],(int)clock.pointSY[clock.a]);
            clock.minuteLine.setLine(120,120,(int)clock.pointMX[clock.b],(int)clock.pointMY[clock.b]);
            clock.hourLine.setLine(120,120,(int)clock.pointHX[clock.c],(int)clock.pointHY[clock.c]);
            clock.repaint();
            Clock.secondTime.stop();//鼠标点击设置时间事件触发前的计时器线程停止
            secondTime2.start();//鼠标点击设置时间事件出发后的计时器线程开始

        }

    }

}

