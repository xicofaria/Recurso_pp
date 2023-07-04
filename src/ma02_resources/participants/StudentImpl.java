package ma02_resources.participants;

import java.util.Objects;

public class StudentImpl extends ParticipantImpl implements Student {
    private int number;

    public StudentImpl(String name, String email, Contact contact, Instituition instituition, int number) {
        super(name, email, contact, instituition);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StudentImpl student = (StudentImpl) o;
        return number == student.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number);
    }
}
