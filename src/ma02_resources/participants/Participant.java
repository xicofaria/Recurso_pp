//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ma02_resources.participants;

public interface Participant extends Cloneable {
    String getName();

    String getEmail();

    Contact getContact();

    Instituition getInstituition();

    void setInstituition(Instituition var1);

    void setContact(Contact var1);

    boolean equals(Object var1);
}
