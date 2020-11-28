/*
 * Created by JFormDesigner on Sat Oct 24 01:27:56 CST 2020
 */

package frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import db.Connect;
import entity.*;

/**
 * @author Brainrain
 */
public class atmospheredb extends JFrame {
    public atmospheredb() {
        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 添加关闭事件
        setResizable(false);
        addAction();
    }
    private List<Object> list=new ArrayList<>();
    private Connect dbconnect=new Connect();
    private long status=1;
    /**
     * 设置颜色
     */
    public static void setColumnColor(JTable table) {
        try {
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
                private static final long serialVersionUID = 1L;

                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                               boolean hasFocus, int row, int column) {
                    if (row % 2 == 0)
                        setBackground(Color.WHITE);// 设置奇数行底色
                    else if (row % 2 == 1)
                        setBackground(new Color(220, 230, 241));// 设置偶数行底色
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                }
            };
            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
            }
            tcr.setHorizontalAlignment(JLabel.CENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setTable(ResultSet rs , String type) {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //针对不同的查询数据生成表头
        switch (type){
            case "QdAod":
                table.setModel(new DefaultTableModel(
                        new Object[][] {
                        },
                        new String[] {
                                "date", "time", "AOD400","AOD500","AOD675","AOD870","AOD940","AOD1020","AOD1627","AOD2200","PWV","AOD550","arfa","beta"
                        }
                ));
                try {
                    long cnt=0;
                    while (rs.next()) {
                        cnt++;
                        if(status==1 || cnt % status==1){
                            Vector<Object> tableValue = new Vector<Object>();
                            tableValue.add(rs.getString("date"));
                            tableValue.add(rs.getString("time"));
                            tableValue.add(rs.getFloat("AOD400"));
                            tableValue.add(rs.getFloat("AOD500"));
                            tableValue.add(rs.getFloat("AOD675"));
                            tableValue.add(rs.getFloat("AOD870"));
                            tableValue.add(rs.getFloat("AOD940"));
                            tableValue.add(rs.getFloat("AOD1020"));
                            tableValue.add(rs.getFloat("AOD1627"));
                            tableValue.add(rs.getFloat("AOD2200"));
                            tableValue.add(rs.getFloat("PWV"));
                            tableValue.add(rs.getFloat("AOD550"));
                            tableValue.add(rs.getFloat("arfa"));
                            tableValue.add(rs.getFloat("beta"));
                            ((DefaultTableModel) table.getModel()).addRow(tableValue);
                        }
                    }
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case "SsTurb":
                table.setModel(new DefaultTableModel(
                        new Object[][] {
                        },
                        new String[] {
                                "date", "time", "Cn2"
                        }
                ));
                try {
                    long cnt=0;
                    while (rs.next()) {
                        cnt++;
                        if(status==1 || cnt % status==1){
                        Vector<Object> tableValue = new Vector<Object>();
                        tableValue.add(rs.getString("date"));
                        tableValue.add(rs.getString("time"));
                        tableValue.add(rs.getFloat("Cn2"));
                        ((DefaultTableModel) table.getModel()).addRow(tableValue);
                        }
                    }
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case "SsVis":
                table.setModel(new DefaultTableModel(
                        new Object[][] {
                        },
                        new String[] {
                                "date", "time", "vis"
                        }
                ));
                try {
                    long cnt=0;
                    while (rs.next()) {
                        cnt++;
                        if(status==1 || cnt % status==1){
                            Vector<Object> tableValue = new Vector<Object>();
                            tableValue.add(rs.getString("date"));
                            tableValue.add(rs.getString("time"));
                            tableValue.add(rs.getFloat("vis"));
                            ((DefaultTableModel) table.getModel()).addRow(tableValue);
                        }
                    }
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case "SsWs":
                table.setModel(new DefaultTableModel(
                        new Object[][] {
                        },
                        new String[] {
                                "date", "time", "T","RH","P","WS","WD"
                        }
                ));
                try {
                    long cnt=0;
                    while (rs.next()) {
                        cnt++;
                        if(status==1 || cnt % status==1){
                            Vector<Object> tableValue = new Vector<Object>();
                            tableValue.add(rs.getString("date"));
                            tableValue.add(rs.getString("time"));
                            tableValue.add(rs.getFloat("T"));
                            tableValue.add(rs.getFloat("RH"));
                            tableValue.add(rs.getFloat("P"));
                            tableValue.add(rs.getFloat("WS"));
                            tableValue.add(rs.getFloat("WD"));
                            ((DefaultTableModel) table.getModel()).addRow(tableValue);
                        }
                    }
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
        }


        // 单击表头自动排序
        table.setAutoCreateRowSorter(true);
        // 设置表头文字居中显示
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(renderer.CENTER);
        // 设置表格中的数据居中显示
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        table.setFocusable(false);
        setColumnColor(table);

    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        panel1 = new JPanel();
        btn_query = new JButton();
        textField1 = new JTextField();
        textField2 = new JTextField();
        cobFeature = new JComboBox<>();
        scrollPane1 = new JScrollPane();
        table = new JTable();
        btn_paint = new JButton();
        cobCity = new JComboBox<>();
        cobFrequency = new JComboBox<>();

        //======== this ========
        setTitle("\u5927\u6c14\u6570\u636e\u5e93\u7cfb\u7edf");
        Container contentPane = getContentPane();
        contentPane.setLayout(new CardLayout());

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("\u8bbe\u7f6e");

                //---- menuItem1 ----
                menuItem1.setText("\u9000\u51fa");
                menu1.add(menuItem1);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //======== panel1 ========
        {
            panel1.setLayout(null);

            //---- btn_query ----
            btn_query.setText("\u67e5\u8be2");
            panel1.add(btn_query);
            btn_query.setBounds(555, 80, 90, 30);
            panel1.add(textField1);
            textField1.setBounds(550, 30, 95, 35);
            panel1.add(textField2);
            textField2.setBounds(445, 30, 95, 35);

            //---- cobFeature ----
            cobFeature.setModel(new DefaultComboBoxModel<>(new String[] {
                "date",
                "time",
                "AOD400",
                "AOD500",
                "AOD550",
                "AOD675",
                "AOD870",
                "AOD940",
                "AOD1020",
                "AOD1627",
                "AOD2200",
                "PWV",
                "arfa",
                "beta"
            }));
            cobFeature.setSelectedIndex(0);
            panel1.add(cobFeature);
            cobFeature.setBounds(170, 25, 125, 40);

            //======== scrollPane1 ========
            {

                //---- table ----
                table.setModel(new DefaultTableModel());
                scrollPane1.setViewportView(table);
            }
            panel1.add(scrollPane1);
            scrollPane1.setBounds(35, 125, 625, 220);

            //---- btn_paint ----
            btn_paint.setText("\u7ed8\u56fe");
            panel1.add(btn_paint);
            btn_paint.setBounds(new Rectangle(new Point(600, 365), btn_paint.getPreferredSize()));

            //---- cobCity ----
            cobCity.setModel(new DefaultComboBoxModel<>(new String[] {
                "\u9752\u5c9bAOD",
                "\u9752\u5c9bOPC",
                "\u4e09\u6c99TURB",
                "\u4e09\u6c99VIS",
                "\u4e09\u6c99WS"
            }));
            cobCity.setSelectedIndex(0);
            panel1.add(cobCity);
            cobCity.setBounds(40, 25, 125, 40);

            //---- cobFrequency ----
            cobFrequency.setModel(new DefaultComboBoxModel<>(new String[] {
                "1 min",
                "1 hour",
                "1 day",
                "1 week",
                "1 month",
                "1 year"
            }));
            cobFrequency.setSelectedIndex(0);
            panel1.add(cobFrequency);
            cobFrequency.setBounds(305, 25, 125, 40);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel1, "card1");
        setSize(760, 535);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JPanel panel1;
    private JButton btn_query;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox<String> cobFeature;
    private JScrollPane scrollPane1;
    private JTable table;
    private JButton btn_paint;
    private JComboBox<String> cobCity;
    private JComboBox<String> cobFrequency;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public void addAction() {

        // 查询
        btn_query.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String str_begin;
                String str_end;
                switch ((String) cobCity.getSelectedItem()){
                    case "青岛AOD":
                        if(cobFeature.getSelectedItem()=="AOD400"){
                            str_begin=textField2.getText();
                            str_end=textField1.getText();
                            ResultSet rs=dbconnect.query("select * from POM_AOD_Qingdao where AOD400>"+str_begin+" and AOD400<"+str_end);
                            setTable(rs,"QdAod");
                        }
                        if(cobFeature.getSelectedItem()=="AOD500"){
                            str_begin=textField2.getText();
                            str_end=textField1.getText();
                            ResultSet rs=dbconnect.query("select * from POM_AOD_Qingdao where AOD500>"+str_begin+" and AOD500<"+str_end);
                            setTable(rs,"QdAod");
                        }
                        if(cobFeature.getSelectedItem()=="AOD675"){
                            str_begin=textField2.getText();
                            str_end=textField1.getText();
                            ResultSet rs=dbconnect.query("select * from POM_AOD_Qingdao where AOD675>"+str_begin+" and AOD675<"+str_end);
                            setTable(rs,"QdAod");
                        }
                        if(cobFeature.getSelectedItem()=="AOD870"){
                            str_begin=textField2.getText();
                            str_end=textField1.getText();
                            ResultSet rs=dbconnect.query("select * from POM_AOD_Qingdao where AOD870>"+str_begin+" and AOD870<"+str_end);
                            setTable(rs,"QdAod");
                        }
                        if(cobFeature.getSelectedItem()=="AOD940"){
                            str_begin=textField2.getText();
                            str_end=textField1.getText();
                            ResultSet rs=dbconnect.query("select * from POM_AOD_Qingdao where AOD940>"+str_begin+" and AOD940<"+str_end);
                            setTable(rs,"QdAod");
                        }
                        if(cobFeature.getSelectedItem()=="AOD1020"){
                            str_begin=textField2.getText();
                            str_end=textField1.getText();
                            ResultSet rs=dbconnect.query("select * from POM_AOD_Qingdao where AOD1020>"+str_begin+" and AOD1020<"+str_end);
                            setTable(rs,"QdAod");
                        }
                        if(cobFeature.getSelectedItem()=="AOD1627"){
                            str_begin=textField2.getText();
                            str_end=textField1.getText();
                            ResultSet rs=dbconnect.query("select * from POM_AOD_Qingdao where AOD1627>"+str_begin+" and AOD1627<"+str_end);
                            setTable(rs,"QdAod");
                        }
                        if(cobFeature.getSelectedItem()=="AOD2200"){
                            str_begin=textField2.getText();
                            str_end=textField1.getText();
                            ResultSet rs=dbconnect.query("select * from POM_AOD_Qingdao where AOD2200>"+str_begin+" and AOD2200<"+str_end);
                            setTable(rs,"QdAod");
                        }
                        if(cobFeature.getSelectedItem()=="PWV"){
                            str_begin=textField2.getText();
                            str_end=textField1.getText();
                            ResultSet rs=dbconnect.query("select * from POM_AOD_Qingdao where PWV>"+str_begin+" and PWV<"+str_end);
                            setTable(rs,"QdAod");
                        }
                        if(cobFeature.getSelectedItem()=="AOD550"){
                            str_begin=textField2.getText();
                            str_end=textField1.getText();
                            ResultSet rs=dbconnect.query("select * from POM_AOD_Qingdao where AOD550>"+str_begin+" and AOD550<"+str_end);
                            setTable(rs,"QdAod");
                        }
                        if(cobFeature.getSelectedItem()=="arfa"){
                            str_begin=textField2.getText();
                            str_end=textField1.getText();
                            ResultSet rs=dbconnect.query("select * from POM_AOD_Qingdao where arfa>"+str_begin+" and arfa<"+str_end);
                            setTable(rs,"QdAod");
                        }
                        if(cobFeature.getSelectedItem()=="beta"){
                            str_begin=textField2.getText();
                            str_end=textField1.getText();
                            ResultSet rs=dbconnect.query("select * from POM_AOD_Qingdao where beta>"+str_begin+" and beta<"+str_end);
                            setTable(rs,"QdAod");
                        }
                        if(cobFeature.getSelectedItem()=="date"){
                            str_begin=textField2.getText();
                            str_end=textField1.getText();
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            java.sql.Date date_begin=new java.sql.Date(0);
                            java.sql.Date date_end=new java.sql.Date(0);

                            date_begin=java.sql.Date.valueOf(str_begin);
                            date_end=java.sql.Date.valueOf(str_end);


                            ResultSet rs=dbconnect.query("select * from POM_AOD_Qingdao where date between '"+date_begin +"' and '"+date_end+"'");
                            setTable(rs,"QdAod");
                        }
                        if(cobFeature.getSelectedItem()=="time"){
                            str_begin=textField2.getText().trim();
                            str_end=textField1.getText().trim();
                            java.sql.Time Time_begin = new java.sql.Time(0);
                            java.sql.Time Time_end = new java.sql.Time(0);

                            Time_begin=java.sql.Time.valueOf((str_begin));
                            Time_end=java.sql.Time.valueOf((str_end));
                            ResultSet rs=dbconnect.query("select * from POM_AOD_Qingdao where time between '"+str_begin +"' and '"+str_end+"'");
                            setTable(rs,"QdAod");
                        }
                        break;
                    case "三沙TURB":
                        if(cobFeature.getSelectedItem()=="Cn2"){
                            str_begin=textField2.getText();
                            str_end=textField1.getText();
                            ResultSet rs=dbconnect.query("select * from TURB_SanSha where Cn2>"+str_begin+" and Cn2<"+str_end);
                            setTable(rs,"SsTurb");
                        }
                        if(cobFeature.getSelectedItem()=="date"){
                            str_begin=textField2.getText();
                            str_end=textField1.getText();
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            java.sql.Date date_begin=new java.sql.Date(0);
                            java.sql.Date date_end=new java.sql.Date(0);

                            date_begin=java.sql.Date.valueOf(str_begin);
                            date_end=java.sql.Date.valueOf(str_end);


                            ResultSet rs=dbconnect.query("select * from TURB_SanSha where date between '"+date_begin +"' and '"+date_end+"'");
                            setTable(rs,"SsTurb");
                        }
                        break;
                    case "三沙VIS":
                        if(cobFeature.getSelectedItem()=="vis"){
                            str_begin=textField2.getText();
                            str_end=textField1.getText();
                            ResultSet rs=dbconnect.query("select * from VIS_SanSha where vis>"+str_begin+" and vis<"+str_end);
                            setTable(rs,"SsVis");
                        }
                        if(cobFeature.getSelectedItem()=="date"){
                            str_begin=textField2.getText();
                            str_end=textField1.getText();
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            java.sql.Date date_begin=new java.sql.Date(0);
                            java.sql.Date date_end=new java.sql.Date(0);

                            date_begin=java.sql.Date.valueOf(str_begin);
                            date_end=java.sql.Date.valueOf(str_end);


                            ResultSet rs=dbconnect.query("select * from VIS_SanSha where date between '"+date_begin +"' and '"+date_end+"'");
                            setTable(rs,"SsVis");
                        }
                        break;
                    case "三沙WS":
                        if(cobFeature.getSelectedItem()=="T"){
                            str_begin=textField2.getText();
                            str_end=textField1.getText();
                            ResultSet rs=dbconnect.query("select * from WS_SanSha where T>"+str_begin+" and T<"+str_end);
                            setTable(rs,"SsWs");
                        }
                        if(cobFeature.getSelectedItem()=="RH"){
                            str_begin=textField2.getText();
                            str_end=textField1.getText();
                            ResultSet rs=dbconnect.query("select * from WS_SanSha where RH>"+str_begin+" and RH<"+str_end);
                            setTable(rs,"SsWs");
                        }
                        if(cobFeature.getSelectedItem()=="P"){
                            str_begin=textField2.getText();
                            str_end=textField1.getText();
                            ResultSet rs=dbconnect.query("select * from WS_SanSha where P>"+str_begin+" and P<"+str_end);
                            setTable(rs,"SsWs");
                        }
                        if(cobFeature.getSelectedItem()=="WS"){
                            str_begin=textField2.getText();
                            str_end=textField1.getText();
                            ResultSet rs=dbconnect.query("select * from WS_SanSha where WS>"+str_begin+" and WS<"+str_end);
                            setTable(rs,"SsWs");
                        }
                        if(cobFeature.getSelectedItem()=="WD"){
                            str_begin=textField2.getText();
                            str_end=textField1.getText();
                            ResultSet rs=dbconnect.query("select * from WS_SanSha where WD>"+str_begin+" and WD<"+str_end);
                            setTable(rs,"SsWs");
                        }
                        if(cobFeature.getSelectedItem()=="date"){
                            str_begin=textField2.getText();
                            str_end=textField1.getText();
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            java.sql.Date date_begin=new java.sql.Date(0);
                            java.sql.Date date_end=new java.sql.Date(0);

                            date_begin=java.sql.Date.valueOf(str_begin);
                            date_end=java.sql.Date.valueOf(str_end);


                            ResultSet rs=dbconnect.query("select * from WS_SanSha where date between '"+date_begin +"' and '"+date_end+"'");
                            setTable(rs,"SsWs");
                        }
                        break;
                    default:
                        break;

                }

//                ResultSet rs=dbconnect.query("select * from data");
//                setTable(rs);

//                if(comboBox1.getSelectedItem()=="仅日期"){
//                    String str_begin=textField2.getText();
//                    String str_end=textField1.getText();
//                    String[] str_begin3=str_begin.split("\\.");
//                    String[] str_end3=str_end.split("\\.");
//                    System.out.println(str_begin3[0]+str_begin3[1]+str_begin3[2]);
//                    ResultSet id1=dbconnect.query("select id from data where observe_year="+str_begin3[0]+" and observe_month="+str_begin3[1]+" and observe_day="+str_begin3[2]);
//                    ResultSet id2=dbconnect.query("select id from data where observe_year="+str_end3[0]+" and observe_month="+str_end3[1]+" and observe_day="+str_end3[2]);
//                    int id_begin=0,id_end=0;
//                    try {
//                        id1.next();
//                        id2.next();
//                        id_begin=id1.getInt("id");
//                        id_end=id2.getInt("id");
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    }
//                    System.out.println(id_begin+","+id_end);
//                    ResultSet rs=dbconnect.query("select * from data where id>="+id_begin+" and id<="+id_end);
//                    setTable(rs);
//                }
//                if(comboBox1.getSelectedItem()=="仅时间"){
//                    String str_begin=textField2.getText();
//                    String str_end=textField1.getText();
//                    ResultSet rs=dbconnect.query("select * from data where observe_time>="+str_begin+" and observe_time<="+str_end);
//                    setTable(rs);
//                }
//                if(comboBox1.getSelectedItem()=="空气温度") {
//                    String str_begin = textField2.getText();
//                    String str_end = textField1.getText();
//                    ResultSet rs = dbconnect.query("select * from data where air_temperature>=" + str_begin + " and air_temperature<=" + str_end);
//                    setTable(rs);
//                }
//                if(comboBox1.getSelectedItem()=="露点温度") {
//                    String str_begin = textField2.getText();
//                    String str_end = textField1.getText();
//                    ResultSet rs = dbconnect.query("select * from data where dew_point_temperature>=" + str_begin + " and dew_point_temperature<=" + str_end);
//                    setTable(rs);
//                }
            }

        });
        cobFrequency.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == e.SELECTED){
                    switch (cobFrequency.getSelectedIndex()){
                        case 0:status=1;break;// 5 sec
                        case 1:status=12;break;// 1 min=12 * 5 sec
                        case 2:status=720;break;//  1 hour= 720 * 5 sec
                        case 3:status=17280;break;// 1 day = 17280 * 5 sec
                        case 4:status=120960;break;// 1 week = 120960 * 5 sec
                        case 5:status=3628800;break;// 1 month = 3628800 * 5 sec
                        case 6:status=43545600;break;// 1 year = 43545600 * 5 sec
                    }
                    System.out.println(status);
                }
            }
        });
        cobFeature.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == e.SELECTED){
                    if(cobFeature.getSelectedItem()=="time") {
//                        cobFrequency.setModel(new DefaultComboBoxModel<>(new String[] {
//                                "-"
//                        }));
                        cobFrequency.setEnabled(false);
                    }
                    if(cobFeature.getSelectedItem()!="time") {

                        cobFrequency.setEnabled(true);

                    }
                    cobFrequency.setSelectedIndex(0);
                }
            }

        });
        cobCity.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                // TODO Auto-generated method stub
                if(e.getStateChange() == e.SELECTED){
                    if(cobCity.getSelectedItem()=="青岛OPC") {
                        cobFeature.setModel(new DefaultComboBoxModel<>(new String[] {
                                "time",
                                "Radius",
                                "dN/d(logR)"
                        }));
                        cobFrequency.setModel(new DefaultComboBoxModel<>(new String[]{
                                "5 sec",
                                "1 min",
                                "1 hour",
                                "1 day",
                                "1 week",
                                "1 month",
                                "1 year",
                        }));
                    }
                    if(cobCity.getSelectedItem()=="青岛AOD") {
                        cobFeature.setModel(new DefaultComboBoxModel<>(new String[]{
                                "date",
                                "time",
                                "AOD400",
                                "AOD500",
                                "AOD550",
                                "AOD675",
                                "AOD870",
                                "AOD940",
                                "AOD1020",
                                "AOD1627",
                                "AOD2200",
                                "PWV",
                                "arfa",
                                "beta"
                        }));
                        cobFrequency.setModel(new DefaultComboBoxModel<>(new String[]{
                                "1 min",
                                "1 hour",
                                "1 day",
                                "1 week",
                                "1 month",
                                "1 year",
                        }));
                    }
                    if(cobCity.getSelectedItem()=="三沙TURB") {
                        cobFeature.setModel(new DefaultComboBoxModel<>(new String[] {
                                "date",
                                "time",
                                "Cn2"
                        }));
                        cobFrequency.setModel(new DefaultComboBoxModel<>(new String[]{
                                "5 sec",
                                "1 min",
                                "1 hour",
                                "1 day",
                                "1 week",
                                "1 month",
                                "1 year",
                        }));

                    }
                    if(cobCity.getSelectedItem()=="三沙VIS") {
                        cobFeature.setModel(new DefaultComboBoxModel<>(new String[] {
                                "date",
                                "time",
                                "VIS"
                        }));
                        cobFrequency.setModel(new DefaultComboBoxModel<>(new String[]{
                                "5 sec",
                                "1 min",
                                "1 hour",
                                "1 day",
                                "1 week",
                                "1 month",
                                "1 year",
                        }));

                    }
                    if(cobCity.getSelectedItem()=="三沙WS") {
                        cobFeature.setModel(new DefaultComboBoxModel<>(new String[] {
                                "date",
                                "time",
                                "T",
                                "RH",
                                "P",
                                "WS",
                                "WD"
                        }));
                        cobFrequency.setModel(new DefaultComboBoxModel<>(new String[]{
                                "5 sec",
                                "1 min",
                                "1 hour",
                                "1 day",
                                "1 week",
                                "1 month",
                                "1 year",
                        }));
                    }
                }

            }

        });
        //绘图
        btn_paint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                int rows = table.getRowCount();//hang
//			    System.out.println(rows);
//			    System.out.println(cols);
                list.clear();
                switch (cobCity.getSelectedItem().toString()){
                    case "三沙TURB":
                        for(int i=0;i<rows;i++) {
                            SsTurb stb=new SsTurb();
                            stb.setDate(table.getValueAt(i, 0).toString());
                            stb.setTime(table.getValueAt(i, 1).toString());
                            stb.setCn2(table.getValueAt(i, 2).toString());
                            list.add(stb);
                        }
                        break;
                    case "青岛AOD":
                        for(int i=0;i<rows;i++){
                            QdAod qad=new QdAod();
                            qad.setDate(table.getValueAt(i, 0).toString());
                            qad.setTime(table.getValueAt(i, 1).toString());
                            qad.setAOD400(table.getValueAt(i, 2).toString());
                            qad.setAOD500(table.getValueAt(i, 3).toString());
                            qad.setAOD675(table.getValueAt(i, 4).toString());
                            qad.setAOD870(table.getValueAt(i, 5).toString());
                            qad.setAOD940(table.getValueAt(i, 6).toString());
                            qad.setAOD1020(table.getValueAt(i, 7).toString());
                            qad.setAOD1627(table.getValueAt(i, 8).toString());
                            qad.setAOD2200(table.getValueAt(i, 9).toString());
                            qad.setPWV(table.getValueAt(i, 10).toString());
                            qad.setAOD550(table.getValueAt(i, 11).toString());
                            qad.setArfa(table.getValueAt(i, 12).toString());
                            qad.setBeta(table.getValueAt(i, 13).toString());
                            list.add(qad);
                        }
                        break;
                    case "三沙VIS":
                        for(int i=0;i<rows;i++) {
                            SsVis svs=new SsVis();
                            svs.setDate(table.getValueAt(i, 0).toString());
                            svs.setTime(table.getValueAt(i, 1).toString());
                            svs.setVis(table.getValueAt(i, 2).toString());
                            list.add(svs);
                        }
                        break;
                    case "三沙WS":
                        for(int i=0;i<rows;i++) {
                            SsWs sws=new SsWs();
                            sws.setDate(table.getValueAt(i, 0).toString());
                            sws.setTime(table.getValueAt(i, 1).toString());
                            sws.setT(table.getValueAt(i, 2).toString());
                            sws.setRH(table.getValueAt(i, 3).toString());
                            sws.setP(table.getValueAt(i, 4).toString());
                            sws.setWS(table.getValueAt(i, 5).toString());
                            sws.setWD(table.getValueAt(i, 6).toString());
                            list.add(sws);
                        }
                        break;
                }


//				for(AtmosphereDB a:list) {
//					System.out.println(a.getAir_temperature());
//				}
                new PaintFrame(list);

            }
        });
    }
    public static void main(String[] args) {
        try {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();// 启用BeautyEye主题
        } catch (Exception e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    atmospheredb atdb = new atmospheredb();
                    atdb.setVisible(true);
//                    atdb.setResizable(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
