package Table.Dialog;

import Table.Model.TableModel;
import Table.StudentTable;
import Window.Student;

import java.awt.*;
import java.util.List;

/**
 * Created by alex on 29.3.17.
 */
public class SearchContext {

    private SearchStrategy searchStrategy;

    public SearchContext(SearchStrategy searchStrategy) {
        this.searchStrategy=searchStrategy;
    }

    public List<Student> executeSearchStrategy(List<Student> students,Dialog dialog){
        return searchStrategy.execute(students,dialog);
    }



}
