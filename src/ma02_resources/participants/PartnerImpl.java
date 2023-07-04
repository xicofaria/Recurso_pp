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

    @Override
    public String getVat() {
        return vat;
    }

    @Override
    public String getWebsite() {
        return website;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        PartnerImpl partner = (PartnerImpl) obj;
        return vat.equals(partner.vat) && website.equals(partner.website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), vat, website);
    }
}
