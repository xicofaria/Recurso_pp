//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ma02_resources.project;

import java.time.LocalDate;

public interface Task extends Cloneable, Comparable<Task> {
    LocalDate getStart();

    LocalDate getEnd();

    int getDuration();

    String getTitle();

    String getDescription();

    Submission[] getSubmissions();

    int getNumberOfSubmissions();

    void addSubmission(Submission var1);

    void extendDeadline(int var1);

    boolean equals(Object var1);

    int compareTo(Task var1);

    // adicionada
    int getMaximumNumberOfSubmissions();
}
