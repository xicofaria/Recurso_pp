//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ma02_resources.participants;

public interface Contact extends Cloneable {
    String getStreet();

    String getCity();

    String getState();

    String getZipCode();

    String getCountry();

    String getPhone();

    boolean equals(Object var1);
}
