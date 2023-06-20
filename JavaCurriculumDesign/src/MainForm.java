


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MainForm extends JFrame {

    //主窗口中的所有基本属性组件
    JTabbedPane jTabPanel;
    JPanel timeDataPanel,timeZonePanel,intnetTimePanel,dataPanel,timePanel,buttonZonePanel,showDateAndTimePanel;
    JLabel showTimeZoneLabel;
    JButton defineButton,cancelButton,applyButton;

    //主窗口的三个鼠标点击事件,分别用于设置月份,年份,和时间
    DataPanel.MouseClickDefineMonth mouseClickDefineMonth = new DataPanel.MouseClickDefineMonth();
    DataPanel.MouseClickDefineYear mouseClickDefineYear = new DataPanel.MouseClickDefineYear();
    TimePanel.MouseClickDefineTime mouseClickDefineTime = new TimePanel.MouseClickDefineTime();

    public MainForm()  {
        super("日期和时间 属性");
        //设置主窗口的布局管理器
        setLayout(new BorderLayout());

        //初始化主窗口里的选项卡窗格并为其添加边框
        jTabPanel = new JTabbedPane();
        jTabPanel.setBorder(BorderFactory.createEmptyBorder(5,10,0,10));

        //初始化选项卡窗格里的时间日期面板并设置其布局管理器
        timeDataPanel = new JPanel();
        timeDataPanel.setLayout(new BorderLayout(0,10));

        //初始化时间日期面板里的 显示日期和时间面板 并为其设置边框和布局管理器
        showDateAndTimePanel = new JPanel();
        showDateAndTimePanel.setBorder(BorderFactory.createEmptyBorder(7,15,0,15));
        showDateAndTimePanel.setLayout(new GridLayout(1,2,10,0));

        //初始化 显示日期和时间面板里的 日期面板 和 时间面板并将其添加进去
        dataPanel = new DataPanel();
        timePanel = new TimePanel();
        showDateAndTimePanel.add(dataPanel);
        showDateAndTimePanel.add(timePanel);

        //初始化时间日期面板里的 显示时区标签 并为其添加边框
        showTimeZoneLabel = new JLabel("当前时区: 中国标准时间");
        showTimeZoneLabel.setBorder(BorderFactory.createEmptyBorder(0,15,15,0));
        timeDataPanel.add(showDateAndTimePanel, BorderLayout.CENTER);
        timeDataPanel.add(showTimeZoneLabel,BorderLayout.SOUTH);

        //初始化选项卡窗格里的的 时区面板 和 Internet时间 面板并将其添加进去
        timeZonePanel = new JPanel();
        intnetTimePanel = new JPanel();
        jTabPanel.add("时间和日期",timeDataPanel);
        jTabPanel.add("时区",timeZonePanel);
        jTabPanel.add("Internet 时间",intnetTimePanel);
        add(jTabPanel,BorderLayout.CENTER);

        //初始化主窗口里的 按钮区面板 及其里面的3个按钮并将其添加进去
        buttonZonePanel = new JPanel();
        defineButton = new JButton("确定");
        cancelButton = new JButton("取消");
        applyButton = new JButton("应用(A)");
        buttonZonePanel.setLayout(new FlowLayout(4));
        buttonZonePanel.add(defineButton);
        buttonZonePanel.add(cancelButton);
        buttonZonePanel.add(applyButton);

        //在确认按钮上添加设置月份 年份 时间的鼠标点击事件
        defineButton.addMouseListener(mouseClickDefineMonth);
        defineButton.addMouseListener(mouseClickDefineYear);
        defineButton.addMouseListener(mouseClickDefineTime);

        //在取消和应用按钮上添加 退出窗口 的鼠标点击事件
        MouseClickClosed mouseClickClosed=new MouseClickClosed();
        cancelButton.addMouseListener(mouseClickClosed);
        applyButton.addMouseListener(mouseClickClosed);
        add(buttonZonePanel,BorderLayout.SOUTH);

        //设置主窗口的一些属性
        setBounds(400,150,770,440);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    //鼠标点击退出窗口的事件类
    class MouseClickClosed extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.exit(0);
        }
    }


    //初始化一个主窗口
    public static void main(String[] args)  { new MainForm(); }

}

