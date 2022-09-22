package ou.info.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String birth;
    private String address;
    private String passportNumber;
    private String passport;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getName(), person.getName()) &&
                Objects.equals(getBirth(), person.getBirth()) &&
                Objects.equals(getAddress(), person.getAddress()) &&
                Objects.equals(getPassportNumber(), person.getPassportNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getName(),
                getAddress(),
                getPassportNumber());
    }
}
