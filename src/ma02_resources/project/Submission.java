    //
    // Source code recreated from a .class file by IntelliJ IDEA
    // (powered by FernFlower decompiler)
    //

    package ma02_resources.project;

    import java.time.LocalDateTime;
    import ma02_resources.participants.Student;

    public interface Submission extends Comparable<Submission> {
        LocalDateTime getDate();

        Student getStudent();

        String getText();

        int compareTo(Submission var1);
    }
