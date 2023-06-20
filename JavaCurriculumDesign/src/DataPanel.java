

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

public class DataPanel extends JPanel {

    //日期面板里的所有属性
    static final String monthStr[] = {"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月", "十二月"};
    static JComboBox monthListCombB = new JComboBox(monthStr);
    static JPanel showYearAndMonthPanel,showCalendarPanel;
    static JSpinner yearListSpinner;
    static CalendarBean currentCalendar;


    public DataPanel(){
        //设置日期面板的边框和布局管理器
        setBorder(BorderFactory.createTitledBorder("日期(D)"));
        setLayout(new BorderLayout());

        //初始化日期面板里的 显示年份和月份面板 并为其设置布局管理器和边框
        showYearAndMonthPanel = new JPanel();
        showYearAndMonthPanel.setLayout(new GridLayout(1,2,20,10));
        showYearAndMonthPanel.setBorder(BorderFactory.createEmptyBorder(10,20,5,20));

        //设置 月份列表框 显示的月份
        monthListCombB.setSelectedIndex(Calendar.getInstance().get(Calendar.MONTH));

        //初始化 显示年份和月份面板 里的 年份微调器 并设置所显示的年份以及调节范围 将其添加到面板里
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        SpinnerModel year = new SpinnerNumberModel(currentYear,currentYear-100,currentYear+100,1);
        yearListSpinner = new JSpinner(year);
        showYearAndMonthPanel.add(monthListCombB);
        showYearAndMonthPanel.add(yearListSpinner);

        //初始化 显示月历面板 并为其设置布局管理器和边框 和一个 月历面板 并将 月历面板 添加到 显示月历面板
        currentCalendar = new CalendarBean();
        currentCalendar.getCalendar();
        showCalendarPanel = new JPanel();
        showCalendarPanel.setLayout(new BorderLayout());
        showCalendarPanel.setBorder(BorderFactory.createEmptyBorder(10,20,20,20));
        showCalendarPanel.add(currentCalendar,BorderLayout.CENTER);

        //将 显示年份和月份面板 和 显示月历面板 添加到日期面板里
        add(showYearAndMonthPanel,BorderLayout.NORTH);
        add(showCalendarPanel,BorderLayout.CENTER);
    }


    //鼠标点击设置月份的事件类
    public static class MouseClickDefineMonth extends  MouseAdapter{

        @Override
        public void mouseClicked(MouseEvent e) {
            showCalendarPanel.removeAll();
            currentCalendar.removeAll();
            //将下拉列表里的月份数组值一一与所设置并显示的月份比较,如果相同,则将月份值置为所设置的月份值
            if(monthStr[0].equals(monthListCombB.getSelectedItem()))
                currentCalendar.setMonth(1);
            if(monthStr[1].equals(monthListCombB.getSelectedItem()))
                currentCalendar.setMonth(2);
            if(monthStr[2].equals(monthListCombB.getSelectedItem()))
                currentCalendar.setMonth(3);
            if(monthStr[3].equals(monthListCombB.getSelectedItem()))
                currentCalendar.setMonth(4);
            if(monthStr[4].equals(monthListCombB.getSelectedItem()))
                currentCalendar.setMonth(5);
            if(monthStr[5].equals(monthListCombB.getSelectedItem()))
                currentCalendar.setMonth(6);
            if(monthStr[6].equals(monthListCombB.getSelectedItem()))
                currentCalendar.setMonth(7);
            if(monthStr[7].equals(monthListCombB.getSelectedItem()))
                currentCalendar.setMonth(8);
            if(monthStr[8].equals(monthListCombB.getSelectedItem()))
                currentCalendar.setMonth(9);
            if(monthStr[9].equals(monthListCombB.getSelectedItem()))
                currentCalendar.setMonth(10);
            if(monthStr[10].equals(monthListCombB.getSelectedItem()))
                currentCalendar.setMonth(11);
            if(monthStr[11].equals(monthListCombB.getSelectedItem()))
                currentCalendar.setMonth(12);
            //重新获得设置月份后的月历并将 显示月历面板刷新 以显示新月历
            currentCalendar.getCalendar();
            showCalendarPanel.add(currentCalendar);
            showCalendarPanel.validate();
        }

    }


    //鼠标点击设置年份的事件类
    static class MouseClickDefineYear extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            showCalendarPanel.removeAll();
            currentCalendar.removeAll();
            currentCalendar.setYear((int)yearListSpinner.getValue());

            //重新获得设置年份份后的月历并将 显示月历面板刷新 以显示新月历
            currentCalendar.getCalendar();
            showCalendarPanel.add(currentCalendar);
            showCalendarPanel.validate();
        }

    }

    //鼠标点击设置天份的事件类
    static class MouseClickDefineDay extends MouseAdapter{

        @Override
        public void mouseClicked(MouseEvent e){
            JLabel jLabel = (JLabel)e.getComponent();//获得显示所设置的天份的标签

            //去掉设置前天份的背景色
            for (int i = 0;i < 42;i++){
                if (currentCalendar.jLabelsShowDate[i].getText() != ""){
                    int dayOfSelect = Integer.parseInt(currentCalendar.jLabelsShowDate[i].getText());
                    if (dayOfSelect == currentCalendar.currentDay){
                        currentCalendar.jLabelsShowDate[i].setBackground(Color.white);
                    }
                }
            }
            currentCalendar.setDay(Integer.parseInt(jLabel.getText()));

            //为显示所设置的天份的标签添加背景颜色
            jLabel.setOpaque(true);
            jLabel.setBackground(new Color(100, 149, 237));
        }
    }

}

