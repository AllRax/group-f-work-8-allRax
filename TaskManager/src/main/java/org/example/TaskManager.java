package org.example;

import javax.swing.*;
import java.awt.*;

public class TaskManager {
    JFrame frame;
    JMenu filemenu,viewMenu;
    JMenuItem  homeItem,settingsItem,helpItem;
    JPanel contentPanel,north,east,west,homeView;

    public TaskManager() {
        this.Taskwindow();
    }


    public JFrame Taskwindow(){
        frame=new JFrame();
        frame.setTitle("Swing Application");
        frame.setSize(800,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setJMenuBar(this.menuView());
        frame.setLayout(new BorderLayout());
        frame.add(BorderLayout.CENTER,this.contentPanel());
        frame.add(BorderLayout.NORTH,this.northpanel());
        frame.add(BorderLayout.EAST,this.eastpanel());
        frame.add(BorderLayout.WEST,this.westpanel());
        frame.setMinimumSize(new Dimension(400,300));
        frame.pack();
        return frame;
    }
//Menu Bar consisting of the nav components
    public JMenuBar menuView(){
        JMenuBar menuBar=new JMenuBar();
        menuBar.add(this.viewOption());
        menuBar.add(this.taskMenu());
        menuBar.add(this.fileTask());
        menuBar.setBackground(new Color(70,130,180));
        return menuBar;
    }

    //View Option on task menuBar
    public JMenu viewOption(){

        viewMenu=new JMenu("View");
         homeItem=new JMenuItem("home");
         settingsItem=new JMenuItem("settings");
         helpItem=new JMenuItem("Help");
        viewMenu.add(homeItem);
        viewMenu.add(settingsItem);
        viewMenu.add(helpItem);
        return viewMenu;

    }
//TaskMenu Option on task menuBar
    public JMenu taskMenu(){
        JMenu taskMenu=new JMenu("Task Menu");
        JMenuItem addTask=new JMenuItem("Add Task");
        JMenuItem editTask=new JMenuItem("Edit Task");
        JMenuItem deleteTask=new JMenuItem("Delete Task");
        taskMenu.add(addTask);
        taskMenu.add(editTask);
        taskMenu.add(deleteTask);
        return taskMenu;
    }

//file Option on the task menuBar
    public JMenu fileTask(){
        filemenu=new JMenu("file");
        JMenuItem exitItem=new JMenuItem("Exit");
        filemenu.add(exitItem);
        return filemenu;
    }

//Cards parent component
    public JPanel contentPanel(){
        contentPanel=new JPanel(new CardLayout());
        contentPanel.setBackground(Color.lightGray);
        contentPanel.add(this.HomeView());
        return contentPanel;
    }
//north panel component
    public JPanel northpanel(){
        north=new JPanel();
        north.setBackground(Color.cyan);
       // north.setPreferredSize(new Dimension(100,100));
        return north;
    }
    //east panel component
    public JPanel eastpanel(){
        east=new JPanel();
        east.setBackground(Color.cyan);
        east.setPreferredSize(new Dimension(50,100));
        return east;
    }
//west panel component
public JPanel westpanel(){
    west=new JPanel();
    west.setBackground(Color.cyan);
    west.setPreferredSize(new Dimension(50,100));
    return west;
}

public JPanel HomeView(){
        homeView=new JPanel(new BorderLayout());
    DefaultListModel defaultListModel=new DefaultListModel<>();
    defaultListModel.addElement("Hello");
    defaultListModel.addElement("Hello2");
    JList taskList=new JList<>(defaultListModel);
//    taskList.setPreferredSize(new Dimension(100,100));
    JLabel tasksTitle=new JLabel("TASKS");
    tasksTitle.setFont(new Font("ARIAL",Font.TRUETYPE_FONT,19));

    homeView.add(BorderLayout.NORTH,tasksTitle);
    homeView.add(BorderLayout.CENTER,taskList);
    taskList.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 5));
    taskList.setFont(new Font("ARIAL",Font.ROMAN_BASELINE,23));

        //homeView.add(taskList);
        return homeView;
}



}
