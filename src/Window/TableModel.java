package Window;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 15.3.17.
 */
public class TableModel {
    public final int SEMESTER_NUMBER = 10;
    private List<Student> students;

    public TableModel() {
        students = new ArrayList<Student>();
        List<String> socialWork=new ArrayList<String>();
        for(int i=0;i<10;i++)
            socialWork.add(i+"");
        for(int i=0;i<117;i++)
            students.add(new Student((i+1)+"","s","a","eee",socialWork));
    }

    public List<Student> getStudents() {
        return students;
    }
}