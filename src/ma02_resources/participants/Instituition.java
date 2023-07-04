//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ma02_resources.participants;

public interface Instituition extends Cloneable {
    String getName();

    String getEmail();

    InstituitionType getType();

    Contact getContact();

    String getWebsite();

    String getDescription();

    void setWebsite(String var1);

    void setDescription(String var1);

    void setContact(Contact var1);

    void setType(InstituitionType var1);

    boolean equals(Object var1);
}
