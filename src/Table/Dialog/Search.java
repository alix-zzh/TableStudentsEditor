package Table.Dialog;

import Table.AddComponent;
import Table.Model.TableModel;
import Table.StudentTable;
import Window.Student;
import Window.SocialWork;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by alex on 29.3.17.
 */
public class Search {

    private TableModel tableModel;
    private JTextField lastName;
    private JTextField group;
    private JComboBox minCount;
    private JComboBox maxCount;
    private final String LAST_NAME = "Фамилия:";
    private final String GROUP = "Группа:";
    private final String SOCIAL_WORK = "Общественная работа:";
    private final String CAUNT_OF_SOCIAL_WORK = "Каличество общественной работы:";
    private JFrame frame;
    private JTextField socialWork;
    private SearchContext searchContext;

    public Search(TableModel tableModel,SearchStrategy searchStrategy) {
        searchContext=new SearchContext(searchStrategy);
        this.tableModel = tableModel;
        frame = createFrame();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }



    private JFrame createFrame(){
        JFrame frame = new JFrame("Поиск Студентов");
        JLabel labelText = new JLabel();
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
        JButton searchButton = new JButton("Поиск");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudent();
            }
        });
        frame.add(searchButton, BorderLayout.SOUTH);
        return frame;
    }

    private void searchStudent(){
//        if (isAllCorrect()){
//            List <Student> students = new ArrayList<Student>();
//            for (Student student: tableModel.getStudents()) {
//                if (compliesTemplate(student)) {
//                    students.add(student);
//                }
//            }
//            if(searchContext.getSearchStrategy() instanceof NameSearch) {
//                            if (searchStudentTable != null)
//                frame.remove(searchStudentTable);
//                frame.add(searchContext.executeSearchStrategy(students), BorderLayout.CENTER);
//                frame.setSize(new Dimension(850, 600));
//                frame.revalidate();
//                frame.repaint();
//            }
//            else
//                searchContext.executeSearchStrategy(students,tableModel);
//
//        } else {
//            JOptionPane.showMessageDialog
//                    (null, "Информация не корректна", "Ошибка", JOptionPane.ERROR_MESSAGE);
//        }
    }

    private boolean compliesTemplate(Student student) {
        if (!lastName.getText().equals(student.getLastName())) return false;
        if (!isTextEmpty(group.getText()) && !group.getText().equals(student.getGroupNumber())) return false;
        if (!isTextEmpty(socialWork.getText()) && !findSocialWork(socialWork.getText(), student.getSocialWork()) && minCount.getSelectedItem().equals("-") && maxCount.getSelectedItem().equals("-")) return false;
        if (!isTextEmpty(socialWork.getText()) && !findSocialWorkBitweenMinAndMax(socialWork.getText(),student.getSocialWork())) return false;
        return true;
    }

    private boolean findSocialWorkBitweenMinAndMax(String searchSocialWork, List<SocialWork> student) {
        if(minCount.getSelectedItem().equals("-") && maxCount.getSelectedItem().equals("-"))
            return true;

        int min=0;
        if(!minCount.getSelectedItem().equals("-"))
            min=Integer.parseInt((String)minCount.getSelectedItem());
        int max=0;
        if(!maxCount.getSelectedItem().equals("-"))
            max=Integer.parseInt((String)maxCount.getSelectedItem());

        int count=0;
        for (SocialWork elOfSocialWork:student) {
            if(elOfSocialWork.getWork().equals(searchSocialWork))
                count++;
        }
        if(count>=min&&count<=max)
            return true;
        return false;
    }

    private boolean findSocialWork(String searchSocialWork, java.util.List<SocialWork> student) {
        for (SocialWork elOfSocialWork:student) {
            if(elOfSocialWork.getWork().equals(searchSocialWork))
                return true;
        }
        return false;
    }

    private boolean isAllCorrect() {
        return !(isTextEmpty(lastName.getText()) || isNotCorrectText(lastName.getText()) || isNotCorrectText(group.getText()) || isNotCorrectText(socialWork.getText()) );
    }

    private boolean isTextEmpty(String text) {
        return text.equals("");
    }

    private boolean isNotCorrectText(String text) {
        return (text.length() > 0 && text.charAt(0) == ' ');
    }


}