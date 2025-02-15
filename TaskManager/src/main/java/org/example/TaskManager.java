package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.Calendar;
import java.util.Date;
import org.jdatepicker.impl.*;
import org.jdatepicker.util.*;
import org.jdatepicker.*;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TaskManager {
    JFrame frame;
    JMenu filemenu, viewMenu;
    JMenuItem homeItem, settingsItem, helpItem;
    private JPanel northPanel, southPanel, westPanel, centerPanel, listPanel, taskPanel;
    private DefaultListModel<String> contactLisstModel;
    private JToggleButton toggleButton = new JToggleButton("DARK MODE");
    private CardLayout cardLayout = new CardLayout();


    public TaskManager() {
        this.Taskwindow();
    }


    public JFrame Taskwindow() {
        frame = new JFrame();
        frame.setTitle("TODO LIST");
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setJMenuBar(this.menuView());
        frame.setLayout(new BorderLayout());
        frame.add(BorderLayout.NORTH, this.north());
        frame.add(BorderLayout.EAST, this.nullPanel());
        frame.add(BorderLayout.WEST, this.west());
        frame.add(BorderLayout.SOUTH, this.south());
        frame.add(BorderLayout.CENTER, this.center());
        frame.setMinimumSize(new Dimension(400, 300));
        frame.pack();
        return frame;
    }

    //Menu Bar consisting of the nav components
    public JMenuBar menuView() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(this.viewOption());
        menuBar.add(this.taskMenu());
        menuBar.add(this.fileTask());
        menuBar.setBackground(new Color(176, 196, 222));
        return menuBar;
    }

    //View Option on task menuBar
    public JMenu viewOption() {

        viewMenu = new JMenu("View");
        homeItem = new JMenuItem("home");
        settingsItem = new JMenuItem("settings");
        viewMenu.add(homeItem);
        viewMenu.add(settingsItem);
        homeItem.addActionListener(e->cardLayout.show(centerPanel,"HOME"));
        settingsItem.addActionListener(e -> {
            cardLayout.show(centerPanel,"SETTINGS");
        });
        return viewMenu;

    }


    //content Panel
    public JPanel center() {
        centerPanel = new JPanel(cardLayout);
        centerPanel.setPreferredSize(new Dimension(100, 100));
        centerPanel.setForeground(Color.white);
        centerPanel.setBackground(Color.BLUE);
        centerPanel.add(this.taskAddList(),"HOME");
        centerPanel.add(this.addTaskPanel(), "ADDTASK");
        centerPanel.add(this.settingsPanel(),"SETTINGS");
        centerPanel.add(this.editPanel(),"EDIT");

        return centerPanel;
    }


    //TaskMenu Option on task menuBar
    public JMenu taskMenu() {
        JMenu taskMenu = new JMenu("Task Menu");
        JMenuItem addTask = new JMenuItem("Add Task");
        JMenuItem editTask = new JMenuItem("Edit Task");
        JMenuItem deleteTask = new JMenuItem("Delete Task");
        taskMenu.add(addTask);
        taskMenu.add(editTask);
        taskMenu.add(deleteTask);
        addTask.addActionListener(e->cardLayout.show(centerPanel,"ADDTASK"));
        editTask.addActionListener(e->cardLayout.show(centerPanel,"EDIT"));
        return taskMenu;
    }

    //file Option on the task menuBar
    public JMenu fileTask() {
        filemenu = new JMenu("file");
        JMenuItem exitItem = new JMenuItem("Exit");
        filemenu.add(exitItem);
        exitItem.addActionListener(e->System.exit(0));
        return filemenu;
    }

    //northPanel
    public JPanel north() {
        northPanel = new JPanel(new BorderLayout());
        northPanel.setPreferredSize(new Dimension(100, 250));
        return northPanel;
    }

    //southPanel
    public JPanel south() {
        southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(100, 200));
        return southPanel;
    }

    //west Panel
    public JPanel west() {
        westPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(250, 100));
        return westPanel;
    }

    //west panel
    public JPanel nullPanel() {
        JPanel right = new JPanel();
        right.setPreferredSize(new Dimension(200, 100));
        return right;
    }

    public JPanel taskAddList() {
        listPanel = new JPanel();
        listPanel.setLayout(new BorderLayout());
        listPanel.add(BorderLayout.CENTER, this.upperList());
       // listPanel.add(BorderLayout.SOUTH, this.downerList());

        return listPanel;
    }

    //The list part of the contact list
    public JPanel upperList() {
        JPanel up = new JPanel();
        contactLisstModel = new DefaultListModel<>();
        JList contactListview = new JList<>(contactLisstModel);
        contactLisstModel.addElement("Hello");
        contactListview.setPreferredSize(new Dimension(300, 600));
        up.add(contactListview);
        contactListview.setBackground(new Color(176, 196, 222));
        return up;
    }

    //the the downwer panel of the contact List
    public JPanel downerList() {
        JPanel down = new JPanel();
        JButton saveTask=new JButton("SAVE TASK");
        JButton cancel=new JButton("CANCEL");
        down.add(cancel);
        down.add(saveTask);
        return down;
    }

    //task addition method
    public JPanel addTaskPanel() {
        taskPanel = new JPanel();
        taskPanel.setPreferredSize(new Dimension(100, 100));
        GridLayout gridLayout = new GridLayout(5, 2);
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);
        taskPanel.setLayout(gridLayout);
        taskPanel.add(this.taskLabel());
        taskPanel.add(this.taskField());
        taskPanel.add(this.taskLabelDescription());
        taskPanel.add(this.taskFieldDescription());
        taskPanel.add(this.DueTaskLabel());
        taskPanel.add(this.DueTaskDate());
        taskPanel.add(taskStatusLabel());
        taskPanel.add(taskCheckBox());
        taskPanel.add(this.CancelEdit());
        taskPanel.add(this.addTaskButton());
        return taskPanel;
    }

    public JButton addTaskButton(){
        JButton saveTask=new JButton("SAVE");
        saveTask.setForeground(Color.WHITE);
        saveTask.setBackground(new Color(30,144,255));
        return saveTask;
    }


    //Task Name label
    public JLabel taskLabel() {
        JLabel taskName = new JLabel();
        taskName.setText("TASK NAME: ");
        taskName.setFont(new Font("ARIAL", Font.BOLD, 14));
        return taskName;
    }

    public JTextField taskField() {
        JTextField taskField = new JTextField();
        return taskField;
    }

    public JLabel taskLabelDescription() {
        JLabel taskName = new JLabel();
        taskName.setText("TASK DESCRIPTION: ");
        taskName.setFont(new Font("ARIAL", Font.BOLD, 14));
        return taskName;
    }

    public JTextField taskFieldDescription() {
        JTextField taskFieldDescription = new JTextField();
        return taskFieldDescription;
    }

    public JLabel taskStatusLabel() {
        JLabel statusLabel = new JLabel("TASK STATUS: ");
        statusLabel.setFont(new Font("ARIAL",Font.BOLD,14));
        return statusLabel;
    }

    public JCheckBox taskCheckBox() {
        JCheckBox taskCheckBox = new JCheckBox();
        return taskCheckBox;
    }

    public JLabel DueTaskLabel() {
        JLabel dueDate = new JLabel("DUE DATE:");
        dueDate.setFont(new Font("ARIAL",Font.BOLD,14));
        return dueDate;
    }

public JTextField DueTaskDate(){
        JTextField dueDate=new JTextField();
        return dueDate;
}
public JTextField editDueDate(){
    JTextField editdueDate=new JTextField();
    return editdueDate;
}
    public JPanel settingsPanel() {
        JPanel settingsPanel = new JPanel(new BorderLayout());
        toggleButton.addItemListener(itemListener);
        settingsPanel.add(BorderLayout.CENTER,toggleButton);
        settingsPanel.setBackground(Color.DARK_GRAY);
        return settingsPanel;
    }

    public JPanel editPanel(){
        JPanel editPanel=new JPanel(new BorderLayout());
        editPanel.add(BorderLayout.CENTER,this.upperside());
        editPanel.add(BorderLayout.SOUTH,this.downSide());
        return editPanel;
    }
    public JPanel upperside(){
        JPanel upSide = new JPanel();
        upSide.setPreferredSize(new Dimension(100, 100));
        GridLayout gridLayout = new GridLayout(4, 2);
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);
        upSide.setLayout(gridLayout);
        upSide.add(this.taskLabel());
        upSide.add(this.editTaskNameField());
        upSide.add(this.taskLabelDescription());
        upSide.add(this.editTaskDescriptionField());
        upSide.add(this.DueTaskLabel());
        upSide.add(this.editDueDate());
        upSide.add(this.taskStatusLabel());
        upSide.add(this.editTaskCheckBox());
        return  upSide;
    }
    public JPanel downSide(){
        JPanel down=new JPanel();
        down.add(this.CancelEdit());
        down.add(this.comfirmEdit());
        return down;
    }
    public JButton comfirmEdit(){
        JButton comfirmEdit=new JButton("COMFIRM EDIT");
        comfirmEdit.setBackground(new Color(30,144,255));
        comfirmEdit.setForeground(Color.WHITE);
        return comfirmEdit;
    }
    public JButton CancelEdit(){
        JButton cancelEdit=new JButton("CANCEL");
        cancelEdit.setForeground(Color.WHITE);
        cancelEdit.setBackground(new Color(139,0,0));
        return cancelEdit;
    }


public JTextField editTaskNameField(){
        JTextField editTaskNameLabel=new JTextField();
        return editTaskNameLabel;
}
    public JTextField editTaskDescriptionField(){
        JTextField editTaskDescriptionField=new JTextField();
        return editTaskDescriptionField;
    }
    public JCheckBox editTaskCheckBox(){
        JCheckBox checkBox=new JCheckBox();
        return checkBox;

    }

    // ItemListener is notified whenever you click on the Button
    ItemListener itemListener = new ItemListener() {

        // itemStateChanged() method is invoked automatically
        // whenever you click or unclick on the Button.
        public void itemStateChanged(ItemEvent itemEvent)
        {

            // event is generated in button
            int state = itemEvent.getStateChange();

            // if selected print selected in console
            if (state == ItemEvent.SELECTED) {
                toggleButton.setText("LIGHT MODE");
                toggleButton.setBackground(Color.BLACK);
                toggleButton.setForeground(Color.white);
                System.out.println("Selected");
            }
            else {
               toggleButton.setText("DARK MODE");
                toggleButton.setBackground(Color.WHITE);
                toggleButton.setForeground(Color.BLACK);
                System.out.println("Deselected");
            }}};



}
