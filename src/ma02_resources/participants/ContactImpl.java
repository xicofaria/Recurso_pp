package ma02_resources.participants;

import java.util.Objects;

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

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactImpl contact = (ContactImpl) o;
        return Objects.equals(street, contact.street) &&
                Objects.equals(city, contact.city) &&
                Objects.equals(state, contact.state) &&
                Objects.equals(zipCode, contact.zipCode) &&
                Objects.equals(country, contact.country) &&
                Objects.equals(phone, contact.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, state, zipCode, country, phone);
    }
}
