

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

/*
一个月历面板类
 */
public class CalendarBean extends JPanel {

    //月历类的所有属性
    //用于显示天份的面板
    static JLabel[] jLabelsShowDate = new JLabel[42];
    //用于显示星期的面板
    JLabel[] jLabelsShowDay = new JLabel[7];
    int year = Calendar.getInstance().get(Calendar.YEAR),
        month = Calendar.getInstance().get(Calendar.MONTH)+1,
        currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

    //初始化一个 鼠标点击设置天份 的时间
    DataPanel.MouseClickDefineDay mouseClickDefineDay = new DataPanel.MouseClickDefineDay();


    public CalendarBean(){
        //设置月历面板的边框 背景颜色以及布局管理器
        setBorder(BorderFactory.createLoweredSoftBevelBorder());
        setBackground(Color.WHITE);
        setLayout(new GridLayout(7,7,30,13));
    }


    //设置月历的年份
    public void setYear(int year){ this.year = year; }


    //设置月历的月份
    public void setMonth(int month){ this.month = month; }


    //设置月历的日份
    public void setDay(int currentDay){this.currentDay = currentDay;}


    //用于获得一个月历
    public void  getCalendar(){
        //初始化 显示星期标签 并将其添加到月历面板
        char [] str = "日一二三四五六".toCharArray();
        for (int i = 0; i < 7; i++){
            jLabelsShowDay[i]=new JLabel(String.valueOf(str[i]));
            add(jLabelsShowDay[i]);
        }
        Calendar rili = Calendar.getInstance();
        rili.set(year,month-1,1);
        int weekDay = rili.get(Calendar.DAY_OF_WEEK)-1; //计算出1号的星期
        int day = 0;

        //得到一年中不同月份的天数
        if (month==1||month==3||month==5||month==7||month==8||month==10||month==12)
            day = 31;
        if (month==4||month==6||month==9|month==11)
            day = 30;
        if (month == 2){
            if ( ((year%4==0)&&(year%100!=0)) || year%400==0 )
                day = 29;
            else
                day = 28;
        }

        //初始化月历前面的空的 显示天份标签 并将其添加到 月历面板
        for(int i = 0; i < weekDay; i++){
            jLabelsShowDate[i] = new JLabel();
            add(jLabelsShowDate[i]);
        }

        //初始化月历里有实际天份的 显示天份标签 并将其添加到 月历面板
        for (int i = weekDay, n=1; i < weekDay + day; i++) {
            jLabelsShowDate[i] = new JLabel(String.valueOf(n));
            //设置显示当前天份标签的背景颜色
            if (n == currentDay) {
                jLabelsShowDate[i].setOpaque(true);
                jLabelsShowDate[i].setBackground(new Color(100, 149, 237));
            }
            n++;
            add(jLabelsShowDate[i]);
            jLabelsShowDate[i].addMouseListener(mouseClickDefineDay);
        }

        //初始化月历后面的空的 显示天份标签 并将其添加到 月历面板
        for (int i = weekDay + day; i < 42; i++){
            jLabelsShowDate[i] = new JLabel();
            add(jLabelsShowDate[i]);
        }

    }

}
