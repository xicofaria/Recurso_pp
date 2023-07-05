//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ma02_resources.participants;

public enum InstituitionType {
    UNIVERSITY,
    COMPANY,
    NGO,
    OTHER;

    private InstituitionType() {
    }

    public String toString() {
        switch (this) {
            case UNIVERSITY:
                return "Universitary";
            case COMPANY:
                return "Company";
            case NGO:
                return "NGO";
            case OTHER:
                return "Other";
            default:
                return "Unknown";
        }
    }
}
