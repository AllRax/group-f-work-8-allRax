package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.util.ArrayList;

public class TaskManager {
    JFrame frame;
    JMenu filemenu, viewMenu;
    JMenuItem homeItem, settingsItem;
    private JPanel northPanel, southPanel, westPanel, centerPanel, listPanel, taskPanel;
    private DefaultListModel<String> taskListview;
    private JList taskListView;
    private JTextField taskField,taskFieldDescription,editdueDate,dueDate,editTaskNameLabel,editTaskDescriptionField;
    private JToggleButton toggleButton = new JToggleButton("DARK MODE");
    private CardLayout cardLayout = new CardLayout();
    private ArrayList<Tasks> taskList=new ArrayList<>();
    private JCheckBox taskCheckBox,edit_checkBox;
    private static final String DB_URL = "jdbc:mysql://localhost:3307/tasks";
    private static final String DB_USER = "root";
    private static final String DB_PASS =  "";
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
        editTask.addActionListener(e-> {
            int selectedIndex=taskListView.getSelectedIndex();
            if(selectedIndex>=0&&selectedIndex<taskListview.size()){
                Tasks selectedTask=taskList.get(selectedIndex);
                editdueDate.setText(selectedTask.getDueDate());
                editTaskDescriptionField.setText(selectedTask.getTaskDescription());
                editTaskNameLabel.setText(selectedTask.getTaskName());
                edit_checkBox.setSelected(false);
            }
            cardLayout.show(centerPanel, "EDIT");
        });
        deleteTask.addActionListener(e->{

                        int selected= taskListView.getSelectedIndex();
                        if(selected>-1&&selected<taskListview.size()){
                            String selectedTask = taskListview.getElementAt(selected);


                                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                                     PreparedStatement stmt = conn.prepareStatement("DELETE FROM tasks WHERE task_name = ?")) {

                                    stmt.setString(1, selectedTask);
                                    int rowsDeleted = stmt.executeUpdate();
                                    taskList.remove(selected);
                                    taskListview.remove(selected);


                                    if (rowsDeleted > 0) {
                                        JOptionPane.showMessageDialog(deleteTask, "Task deleted successfully!");
                                        cardLayout.show(centerPanel,"ADDTASK");
                                    } else {
                                        JOptionPane.showMessageDialog(deleteTask, "Task not found in database.");
                                    }

                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                    JOptionPane.showMessageDialog(deleteTask, "Error deleting task: " + ex.getMessage());
                                }


                        }


                    });
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
        taskListview = new DefaultListModel<>();
        taskListView = new JList<>(taskListview);
        taskListView.setPreferredSize(new Dimension(300, 600));
        up.add(taskListView);
        taskListView.setBackground(new Color(176, 196, 222));
        return up;
    }

    //the the downwer panel of the contact List
//    public JPanel downerList() {
//        JPanel down = new JPanel();
//        JButton saveTask=new JButton("SAVE TASK");
//        JButton cancel=new JButton("CANCEL");
//        down.add(cancel);
//        down.add(saveTask);
//        return down;
//    }

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
        taskPanel.add(this.taskStatusLabel());
        taskPanel.add(this.taskCheckBox());
        taskPanel.add(this.CancelEdit());
        taskPanel.add(this.addTaskButton());
        return taskPanel;
    }

    public ArrayList<Tasks> getTaskList() {
        return taskList;
    }

    public JList getTaskListView() {
        return taskListView;
    }

    public JButton addTaskButton(){
        JButton saveTask=new JButton("SAVE");
        saveTask.setForeground(Color.WHITE);
        saveTask.setBackground(new Color(30,144,255));


        saveTask.addActionListener(e->{
                    String taskName=taskField.getText();
                    String taskDescription=taskFieldDescription.getText();
                    String dueTaskDate=dueDate.getText();
                    String status= taskCheckBox.isSelected()?"Completed":"Not Completed";
                    if(!taskName.isBlank()&&!taskDescription.isBlank()&&!dueTaskDate.isBlank()){
                        Tasks newTask=new Tasks(taskName,taskDescription,dueTaskDate,status);
                        taskField.setText("");
                        taskFieldDescription.setText("");
                        dueDate.setText("");
                        taskCheckBox.setSelected(false);
                        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                             PreparedStatement stmt = conn.prepareStatement(
                                     "INSERT INTO tasks (task_name, task_description, due_date, task_status) VALUES (?, ?, ?, ?)")) {

                            stmt.setString(1, taskName);
                            stmt.setString(2, taskDescription);
                            stmt.setString(3, dueTaskDate);
                            stmt.setString(4, status);

                            int rowsInserted = stmt.executeUpdate();
                            if (rowsInserted > 0) {
                                JOptionPane.showMessageDialog(addTaskButton(), "Task saved successfully!");
                                taskList.add(newTask);
                                taskListview.addElement(taskName);
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(addTaskPanel(), "Error saving task: " + ex.getMessage());
                        }

                    }

                    cardLayout.show(centerPanel,"HOME");

                }


                );
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
        taskField = new JTextField();
        return taskField;
    }

    public JLabel taskLabelDescription() {
        JLabel taskName = new JLabel();
        taskName.setText("TASK DESCRIPTION: ");
        taskName.setFont(new Font("ARIAL", Font.BOLD, 14));
        return taskName;
    }

    public JTextField taskFieldDescription() {
         taskFieldDescription = new JTextField();
        return taskFieldDescription;
    }

    public JLabel taskStatusLabel() {
        JLabel statusLabel = new JLabel("TASK STATUS: ");
        statusLabel.setFont(new Font("ARIAL",Font.BOLD,14));
        return statusLabel;
    }

    public JCheckBox taskCheckBox() {
         taskCheckBox = new JCheckBox();
        return taskCheckBox;
    }

    public JLabel DueTaskLabel() {
        JLabel dueDate = new JLabel("DUE DATE:");
        dueDate.setFont(new Font("ARIAL",Font.BOLD,14));
        return dueDate;
    }

public JTextField DueTaskDate(){
         dueDate=new JTextField();
        return dueDate;
}
public JTextField editDueDate(){
     editdueDate=new JTextField();
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
        comfirmEdit.addActionListener(e->{
            int selectedIndex=taskListView.getSelectedIndex();
            if(selectedIndex>=0&&selectedIndex<taskListview.size()){
                Tasks selectedTask=taskList.get(selectedIndex);
                selectedTask.setTaskName(editTaskNameLabel.getText());
                selectedTask.setTaskDescription(editTaskDescriptionField.getText());
                selectedTask.setDueDate(editdueDate.getText());

                taskCheckBox.setSelected(false);
                taskList.set(selectedIndex,new Tasks(editTaskNameLabel.getText(),editTaskDescriptionField.getText(),editdueDate.getText(),edit_checkBox.isSelected()?"COMPLETED":"NOT COMPLETED"));
                taskListview.set(selectedIndex,selectedTask.getTaskName());
                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                     PreparedStatement stmt = conn.prepareStatement(
                             "UPDATE tasks SET task_name = ?, task_description = ?, due_date = ? WHERE task_name = ?")) {

                    stmt.setString(1, editTaskNameLabel.getText());
                    stmt.setString(2, editTaskDescriptionField.getText());
                    stmt.setString(3, editdueDate.getText());
                    stmt.setString(4, selectedTask.getTaskName());

                    int rowsUpdated = stmt.executeUpdate();

                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(comfirmEdit, "Task updated successfully!");
                    } else {
                        JOptionPane.showMessageDialog(comfirmEdit, "Task not found in database.");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(comfirmEdit, "Error updating task: " + ex.getMessage());
                }

                cardLayout.show(centerPanel, "HOME");

                editTaskDescriptionField.setText("");
                editTaskNameLabel.setText("");
                editdueDate.setText("");
            }
        });
        return comfirmEdit;
    }
    public JButton CancelEdit(){
        JButton cancelEdit=new JButton("CANCEL");
        cancelEdit.setForeground(Color.WHITE);
        cancelEdit.setBackground(new Color(139,0,0));
        cancelEdit.addActionListener(e->{
            editTaskDescriptionField.setText("");
            editTaskNameLabel.setText("");
            editdueDate.setText("");
            cardLayout.show(centerPanel,"HOME");
        });
        return cancelEdit;
    }


public JTextField editTaskNameField(){
         editTaskNameLabel=new JTextField();
        return editTaskNameLabel;
}
    public JTextField editTaskDescriptionField(){
         editTaskDescriptionField=new JTextField();
        return editTaskDescriptionField;
    }
    public JCheckBox editTaskCheckBox(){
        edit_checkBox=new JCheckBox();
        return edit_checkBox;

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
