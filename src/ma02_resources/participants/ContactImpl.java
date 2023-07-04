package ma02_resources.participants;

public class ContactImpl implements Contact {
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String phone;

    public ContactImpl(String street, String city, String state, String zipCode, String country, String phone) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.phone = phone;
    }

    @Override
    public String getStreet() {
        return street;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public String getZipCode() {
        return zipCode;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ContactImpl contact = (ContactImpl) obj;
        return street.equals(contact.street) &&
                city.equals(contact.city) &&
                state.equals(contact.state) &&
                zipCode.equals(contact.zipCode) &&
                country.equals(contact.country) &&
                phone.equals(contact.phone);
    }

    @Override
    public int hashCode() {
        int result = street.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + zipCode.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + phone.hashCode();
        return result;
    }
}

