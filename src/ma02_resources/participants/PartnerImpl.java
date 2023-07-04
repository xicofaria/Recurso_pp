package ma02_resources.participants;

import java.util.Objects;

public class PartnerImpl extends ParticipantImpl implements Partner {
    private String vat;
    private String website;

    public PartnerImpl(String name, String email, Contact contact, Instituition instituition, String vat, String website) {
        super(name, email, contact, instituition);
        this.vat = vat;
        this.website = website;
    }

    public String getVat() {
        return vat;
    }

    public String getWebsite() {
        return website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass```java
                ()) return false;
        if (!super.equals(o)) return false;
        PartnerImpl that = (PartnerImpl) o;
        return Objects.equals(vat, that.vat) &&
                Objects.equals(website, that.website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), vat, website);
    }
}
