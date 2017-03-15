package Window;

import java.util.List;

/**
 * Created by alex on 15.3.17.
 */
public class Student {

    private String lastName;
    private String firstName;
    private String middleName;
    private String groupNumber;
    private List<String> socialWork;

    public Student(String lastName, String firstName, String middleName,
                   String groupNumber, List<String> socialWork) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.groupNumber = groupNumber;
        this.socialWork = socialWork;

    }
    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public List<String> getSocialWork() {
        return socialWork;
    }
}
