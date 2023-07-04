package ma02_resources.participants;

import java.util.Objects;

public abstract class ParticipantImpl implements Participant {
    private String name;
    private String email;
    private Contact contact;
    private Instituition instituition;

    public ParticipantImpl(String name, String email, Contact contact, Instituition instituition) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.instituition = instituition;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Contact getContact() {
        return contact;
    }

    public Instituition getInstituition() {
        return instituition;
    }

    public void setInstituition(Instituition instituition) {
        this.instituition = instituition;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipantImpl that = (ParticipantImpl) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(contact, that.contact) &&
                Objects.equals(instituition, that.instituition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, contact, instituition);
    }
}
