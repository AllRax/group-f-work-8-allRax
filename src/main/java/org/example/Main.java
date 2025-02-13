package org.example;

import javax.swing.*;
import java.awt.*;

public class Main {
    public Main(){
        initialeUI();
    }
    private void initialeUI(){
        JFrame frame=new JFrame();
        frame.setTitle("Swing Application");
        frame.setSize(800,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //menuBar
        JMenuBar menuBar=new JMenuBar();
        JMenu viewMenu=new JMenu("View");
        JMenuItem homeItem=new JMenuItem("home");
        JMenuItem settingsItem=new JMenuItem("settings");
        JMenuItem helpItem=new JMenuItem("Help");
        viewMenu.add(homeItem);
        viewMenu.add(settingsItem);
        viewMenu.add(helpItem);
        menuBar.add(viewMenu);

        //Task menu
        JMenu taskMenu=new JMenu("Task");
        JMenuItem addTask=new JMenuItem("Add Task");
        JMenuItem editTask=new JMenuItem("Edit Task");
        JMenuItem deleteTask=new JMenuItem("Delete Task");
        taskMenu.add(addTask);
        taskMenu.add(editTask);
        taskMenu.add(deleteTask);
        menuBar.add(taskMenu);





        //File menu with Exit
        JMenu filemenu=new JMenu("file");
        JMenuItem exitItem=new JMenuItem("Exit");
        filemenu.add(exitItem);
        menuBar.add(filemenu);

        frame.setJMenuBar(menuBar);

        //content panel with cardlayouts
        CardLayout cardLayout=new CardLayout();
        JPanel contentPanel=new JPanel(cardLayout);
        contentPanel.add(createHomePanel(),"HOME");
        contentPanel.add(createSettingsPanel(),"SETTINGS");
        contentPanel.add(createHelpPanel(),"HELP");
        frame.add(contentPanel,BorderLayout.CENTER);

        //actions
        homeItem.addActionListener(e->cardLayout.show(contentPanel,"HOME"));
        helpItem.addActionListener(e->cardLayout.show(contentPanel,"HELP"));
        settingsItem.addActionListener(e->cardLayout.show(contentPanel,"SETTINGS"));
        exitItem.addActionListener(e->System.exit(0));




    }
    private JPanel createHomePanel(){
        JPanel panel=new JPanel(new BorderLayout());
        JLabel label=new JLabel("welcome to home page");
        panel.add(label,BorderLayout.CENTER);

        //add components
        JPanel gridpanel=new JPanel(new GridLayout(2,2));
        gridpanel.add(new JButton("Button 1"));
        gridpanel.add(new JButton("Button 2"));
        gridpanel.add(new JButton("Button 3"));
        gridpanel.add(new JButton("Button 4"));
        panel.add(gridpanel,BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createSettingsPanel(){
        JPanel panel=new JPanel();
        panel.add(new Checkbox("Add feature 1"));
        panel.add(new Checkbox("Add feature 2"));
        panel.add(new TextField("Enter settings here"));

        return panel;
    }
    private JPanel createHelpPanel(){
        JPanel panel=new JPanel();
        panel.add(new JLabel("contact@gmail.com"));

        return panel;
    }
    public static void main(String[] args) {

       new Main();
    }
}