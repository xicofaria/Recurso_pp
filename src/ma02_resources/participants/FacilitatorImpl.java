package ma02_resources.participants;

import java.util.Objects;

public class FacilitatorImpl extends ParticipantImpl implements Facilitator {
    private String areaOfExpertise;

    public FacilitatorImpl(String name, String email, Contact contact, Instituition instituition, String areaOfExpertise) {
        super(name, email, contact, instituition);
        this.areaOfExpertise = areaOfExpertise;
    }

    public String getAreaOfExpertise() {
        return areaOfExpertise;
    }

    public void setAreaOfExpertise(String areaOfExpertise) {
        this.areaOfExpertise = areaOfExpertise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FacilitatorImpl that = (FacilitatorImpl) o;
        return Objects.equals(areaOfExpertise, that.areaOfExpertise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), areaOfExpertise);
    }
}
