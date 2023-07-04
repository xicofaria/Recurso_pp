package ma02_resources.participants;

import java.util.Objects;

public class InstituitionImpl implements Instituition {
    private String name;
    private String email;
    private InstituitionType type;
    private Contact contact;
    private String website;
    private String description;

    public InstituitionImpl(String name, String email, InstituitionType type, Contact contact, String website, String description) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.contact = contact;
        this.website = website;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public InstituitionType getType() {
        return type;
    }

    public Contact getContact() {
        return contact;
    }

    public String getWebsite() {
        return website;
    }

    public String getDescription() {
        return description;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public void setType(InstituitionType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstituitionImpl that = (InstituitionImpl) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                type == that.type &&
                Objects.equals(contact, that.contact) &&
                Objects.equals(website, that.website) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, type, contact, website, description);
    }
}
