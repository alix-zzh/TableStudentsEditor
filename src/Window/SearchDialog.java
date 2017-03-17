package Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by alex on 16.3.17.
 */
public class SearchDialog {

    private TableModel tableModel;
    private JTextField lastName;
    private static final String LAST_NAME = "Фамилия:";
    private static final String GROUP = "Группа:";
    private static final String SOCIAL_WORK = "Общественная работа";
    private static final String CAUNT_OF_SOCIAL_WORK = "Каличество общественной работы";
    private JTextField group;
    private JComboBox minCount;
    private JComboBox maxCount;
    private JFrame frame;
    private StudentTable searchStudentTable;
    private JTextField socialWork;

    public SearchDialog(StudentTable studentTable) {
        tableModel = studentTable.getTableModel();
        frame = createFrame();
        frame.pack();
        frame.setLocationRelativeTo(studentTable);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private JFrame createFrame(){
        JFrame frame = new JFrame("Поиск Студентов");
        JLabel labelText = new JLabel("Поиск Студентов");
        JPanel jPanelID = new JPanel();
        jPanelID.setLayout(new GridBagLayout());
        labelText.setHorizontalAlignment(JLabel.CENTER);
        AddComponent.add(jPanelID,labelText, 0, 0, 3, 1);

        String[] labelString = {LAST_NAME, GROUP,SOCIAL_WORK,CAUNT_OF_SOCIAL_WORK};
        labelText = new JLabel(labelString[0]);
        AddComponent.add(jPanelID,labelText, 0, 1, 1, 1);

        lastName = new JTextField(30);
        AddComponent.add(jPanelID, lastName, 1, 1, 3, 1);
        labelText = new JLabel(labelString[1]);
        AddComponent.add(jPanelID, labelText, 0, 2, 1, 1);

        group = new JTextField(30);
        AddComponent.add(jPanelID, group, 1, 2, 3, 1);
        labelText = new JLabel(labelString[2]);
        AddComponent.add(jPanelID, labelText, 0, 3, 1, 1);

        socialWork = new JTextField(30);
        AddComponent.add(jPanelID, socialWork, 1, 3, 3, 1);
        String[] markString = {"-","1","2","3", "4", "5", "6", "7", "8", "9", "10"};
        labelText = new JLabel(labelString[3]);
        labelText.setHorizontalAlignment(JLabel.CENTER);
        AddComponent.add(jPanelID,labelText, 0, 4, 1, 1);
        minCount = new JComboBox(markString);
        AddComponent.add(jPanelID, minCount, 1, 4, 1, 1);
        maxCount = new JComboBox(markString);
        AddComponent.add(jPanelID, maxCount, 2, 4, 1, 1);

        frame.add(jPanelID, BorderLayout.NORTH);
        JButton okButton = new JButton("Поиск");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudent();
            }
        });
        frame.add(okButton, BorderLayout.SOUTH);
        return frame;
    }

    private void searchStudent(){
        if (isAllCorrect()){
            if (searchStudentTable != null) frame.remove(searchStudentTable);
            searchStudentTable = new StudentTable();
            searchStudentTable.getTableModel().getStudents().clear();
            for (Student student: tableModel.getStudents()) {
                if (compliesTemplate(student)) {
                    searchStudentTable.getTableModel().getStudents().add(student);
                }
            }
            searchStudentTable.updateComponent();
            frame.add(searchStudentTable, BorderLayout.CENTER);
            frame.setSize(new Dimension(850,600));
            frame.revalidate();
            frame.repaint();
        } else {
            JOptionPane.showMessageDialog
                    (null, "Информация не корректна", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }


    private boolean compliesTemplate(Student student) {
        if (!lastName.getText().equals("") && !lastName.getText().equals(student.getLastName())) return false;
        if (!group.getText().equals("") && !group.getText().equals(student.getGroupNumber())) return false;
        if (!socialWork.getText().equals("") && !findSocialWork(socialWork.getText(),student.getSocialWork()) && minCount.getSelectedItem().equals("-") && maxCount.getSelectedItem().equals("-")) return false;
        if (!findSocialWorkBitweenMinAndMax(socialWork.getText(),student.getSocialWork())) return false;
        return true;
    }

    private boolean findSocialWorkBitweenMinAndMax(String searchSocialWork, List<String> student) {
        int min=0;
        if(!minCount.getSelectedItem().equals("-"))
            min=Integer.parseInt((String)minCount.getSelectedItem());
        int max=0;
        if(!maxCount.getSelectedItem().equals("-"))
            min=Integer.parseInt((String)maxCount.getSelectedItem());
        int count=0;
        for (String elOfSocialWork:student) {
            if(elOfSocialWork.equals(searchSocialWork))
                count++;
        }
        if(count>=min&&count<=max)
            return true;
        return false;
    }

    private boolean findSocialWork(String searchSocialWork, List<String> student) {
        for (String elOfSocialWork:student) {
            if(elOfSocialWork.equals(searchSocialWork))
                return true;
        }
        return false;
    }


    private boolean isAllCorrect() {
        return !(isNotCorrectLastName() && isNotCorrectSocialWork());
    }

    private boolean isNotCorrectLastName() {
        return (lastName.getText().length() > 0 && lastName.getText().charAt(0) == ' ');
    }
    private boolean isNotCorrectSocialWork() {
        return (socialWork.getText().length() > 0 && socialWork.getText().charAt(0) == ' ');
    }


}