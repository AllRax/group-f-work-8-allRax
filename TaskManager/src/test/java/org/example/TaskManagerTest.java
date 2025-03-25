package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {
    private TaskManager taskManager;

    @BeforeEach
    void setup() {
        taskManager = new TaskManager();
    }

    @Test
    void taskWindowTest() {
        assertNotNull(taskManager);
        assertNotNull(taskManager.Taskwindow());
        assertTrue(taskManager.Taskwindow().isVisible());
    }

    @Test
    void menuViewTest() {
        assertTrue(taskManager.menuView().isVisible());
    }

    @Test
    void viewOptionTest() {
        taskManager.viewMenu.doClick();
        assertTrue(taskManager.homeItem.isVisible());
        assertTrue(taskManager.settingsItem.isVisible());
    }

    @Test
    void taskMenuTest() {
        JMenu taskMenu = taskManager.taskMenu();
        assertNotNull(taskMenu);
        assertEquals(3, taskMenu.getItemCount());
        assertEquals("Add Task", taskMenu.getItem(0).getText());
        assertEquals("Edit Task", taskMenu.getItem(1).getText());
        assertEquals("Delete Task", taskMenu.getItem(2).getText());
    }

    @Test
    void fileTaskTest() {
        JMenu fileMenu = taskManager.fileTask();
        assertNotNull(fileMenu);
        assertEquals(1, fileMenu.getItemCount());
        assertEquals("Exit", fileMenu.getItem(0).getText());
    }

    @Test
    void addTaskPanel() {
        JPanel addTaskPanel = taskManager.addTaskPanel();
        assertNotNull(addTaskPanel);
        assertTrue(addTaskPanel.isVisible());
        assertEquals(10, addTaskPanel.getComponentCount());


        JButton addTaskButton = (JButton) addTaskPanel.getComponent(9);
        assertNotNull(addTaskButton);
        assertEquals("SAVE", addTaskButton.getText());


        taskManager.taskField().setText("Test Task");
        taskManager.taskFieldDescription().setText("Test Description");
        taskManager.DueTaskDate().setText("2025-02-15");
        taskManager.taskCheckBox().setSelected(true);
        addTaskButton.doClick();


        assertEquals("Test Task", taskManager.getTaskList().getFirst().getTaskName());
        assertEquals("Test Description", taskManager.getTaskList().getFirst().getTaskName());
        assertEquals("", taskManager.getTaskList().getFirst().getTaskName());
        assertEquals("Test Task", taskManager.getTaskList().getFirst().getTaskName());
    }

    @Test
    void settingsPanel() {
        JPanel settingsPanel = taskManager.settingsPanel();
        assertNotNull(settingsPanel);
        assertTrue(settingsPanel.isVisible());
        assertEquals(1, settingsPanel.getComponentCount());

        //// toggle button functionality
        JToggleButton toggleButton = (JToggleButton) settingsPanel.getComponent(0);
        assertNotNull(toggleButton);
        assertEquals("DARK MODE", toggleButton.getText());

        toggleButton.doClick();
        assertEquals("LIGHT MODE", toggleButton.getText());
    }

    @Test
    void savingAndEditingTaskTest() {
        JPanel addTaskPanel = taskManager.addTaskPanel();
        assertNotNull(addTaskPanel);
        JPanel editPanel = taskManager.editPanel();
        assertNotNull(editPanel);
        assertTrue(editPanel.isVisible());
        assertEquals(2, editPanel.getComponentCount());

        JButton addTaskButton = (JButton) addTaskPanel.getComponent(9);
        assertNotNull(addTaskButton);
        assertEquals("SAVE", addTaskButton.getText());

        // Add a task to edit
        taskManager.taskField().setText("Test Task");
        taskManager.taskFieldDescription().setText("Test Description");
        taskManager.DueTaskDate().setText("2023-12-31");
        taskManager.taskCheckBox().setSelected(true);
        addTaskButton.doClick();


        // Select the task to edit
        taskManager.getTaskListView().setSelectedIndex(0);
        taskManager.taskMenu().getItem(1).doClick(); // Click Edit Task

        // Test edit functionality
        JButton confirmEditButton = (JButton) ((JPanel) editPanel.getComponent(1)).getComponent(1);
        assertNotNull(confirmEditButton);
        assertEquals("COMFIRM EDIT", confirmEditButton.getText());

        taskManager.editTaskNameField().setText("Updated Task");
        taskManager.editTaskDescriptionField().setText("Updated Description");
        taskManager.editDueDate().setText("2024-01-01");
        taskManager.editTaskCheckBox().setSelected(true);
        confirmEditButton.doClick();

        assertEquals("Updated Task", taskManager.getTaskList().get(0).getTaskName());
        assertEquals("Updated Description", taskManager.getTaskList().get(0).getTaskDescription());
        assertEquals("2024-01-01", taskManager.getTaskList().get(0).getDueDate());
        assertEquals("COMPLETED", taskManager.getTaskList().get(0).getTaskstatus());
    }
    @Test
    void addingAndDeletingTask(){
        JPanel addTaskPanel = taskManager.addTaskPanel();
        assertNotNull(addTaskPanel);
        assertTrue(addTaskPanel.isVisible());
        assertEquals(10, addTaskPanel.getComponentCount());

        // add task button functionality
        JButton addTaskButton = (JButton) addTaskPanel.getComponent(9);
        assertNotNull(addTaskButton);
        assertEquals("SAVE", addTaskButton.getText());

        // adding a task
        taskManager.taskField().setText("Test Task");
        taskManager.taskFieldDescription().setText("Test Description");
        taskManager.DueTaskDate().setText("2023-12-31");
        taskManager.taskCheckBox().setSelected(true);
        addTaskButton.doClick();

        //deleting task
        taskManager.getTaskListView().setSelectedIndex(0);
        taskManager.taskMenu().getItem(2).doClick();//click Delete Task

        assertTrue(taskManager.getTaskList().isEmpty());



    }
}
