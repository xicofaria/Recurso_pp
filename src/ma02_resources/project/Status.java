//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ma02_resources.project;

public enum Status {
    ACTIVE,
    INACTIVE,
    CANCELED,
    CLOSED;

    private Status() {
    }

    public String toString() {
        switch (this) {
            case ACTIVE:
                return "Active";
            case INACTIVE:
                return "Inactive";
            case CLOSED:
                return "Closed";
            case CANCELED:
                return "Canceled";
            default:
                return "Unknown";
        }
    }
}
