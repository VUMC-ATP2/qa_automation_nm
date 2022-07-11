package getset;

import lombok.Getter;
import lombok.Setter;

public class Employee {
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String surname;
    private int age;
}
