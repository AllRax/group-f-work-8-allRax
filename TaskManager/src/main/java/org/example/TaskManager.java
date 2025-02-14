package org.example;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TaskManager {
    JFrame frame;
    JMenu filemenu,viewMenu;
    JMenuItem  homeItem,settingsItem,helpItem;
    private JPanel northPanel, southPanel, westPanel, centerPanel,listPanel,taskPanel;
    private DefaultListModel<String> contactLisstModel;


    public TaskManager() {
        this.Taskwindow();
    }


    public JFrame Taskwindow(){
        frame=new JFrame();
        frame.setTitle("TODO LIST");
        frame.setMinimumSize(new Dimension(800,600));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setJMenuBar(this.menuView());
        frame.setLayout(new BorderLayout());
        frame.add(BorderLayout.NORTH,this.north());
        frame.add(BorderLayout.EAST,this.nullPanel());
        frame.add(BorderLayout.WEST,this.west());
        frame.add(BorderLayout.SOUTH,this.south());
        frame.add(BorderLayout.CENTER,this.center());
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
        menuBar.setBackground(new Color(176,196,222));
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


//content Panel
    public JPanel center(){
        centerPanel= new JPanel(new BorderLayout());
        centerPanel.setPreferredSize(new Dimension(100,100));
        centerPanel.setForeground(Color.white);
        centerPanel.setBackground(Color.BLUE);
        centerPanel.add(BorderLayout.CENTER,this.contactList());
        return centerPanel;
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

    //northPanel
    public JPanel north(){
        northPanel= new JPanel(new BorderLayout());
        northPanel.setPreferredSize(new Dimension(100,150));
        return northPanel;
    }

    //southPanel
    public JPanel south(){
        southPanel= new JPanel();
        southPanel.setPreferredSize(new Dimension(100,100));
        return southPanel;
    }

//west Panel
    public JPanel west(){
        westPanel= new JPanel();
        westPanel.setPreferredSize(new Dimension(50,100));
        return westPanel;
    }

    //west panel
    public JPanel nullPanel(){
        JPanel right=new JPanel();
        right.setPreferredSize(new Dimension(100,100));
        return right;
    }

    public JPanel contactList(){
        listPanel = new JPanel();
        listPanel.setLayout(new BorderLayout());
        listPanel.add(BorderLayout.CENTER,this.upperList());
        listPanel.add(BorderLayout.SOUTH,this.downerList());

        return listPanel;
    }

    //The list part of the contact list
    public JPanel upperList(){
        JPanel up=new JPanel();
        contactLisstModel=new DefaultListModel<>();
        JList contactListview=new JList<>(contactLisstModel);

        contactListview.setPreferredSize(new Dimension(300,600));
        up.add(contactListview);
        contactListview.setBackground(new Color(176,196,222));
        return up;
    }

    //the the downwer panel of the contact List
    public JPanel downerList(){
        JPanel down=new JPanel();
        return down;
    }

    //task addition method
    public JPanel taskManager(){
        taskPanel=new JPanel();
        taskPanel = new JPanel();
        taskPanel.setPreferredSize(new Dimension(100,100));
        GridLayout gridLayout = new GridLayout(4,2);
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);
        taskPanel.setLayout(gridLayout);

        return taskPanel;
    }


    //Task Name label
    public JLabel taskLabel(){
        JLabel taskName=new JLabel();
        taskName.setText("TASK NAME: ");
        taskName.
        return taskName;
    }








}
