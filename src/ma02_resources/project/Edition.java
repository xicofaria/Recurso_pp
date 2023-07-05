//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ma02_resources.project;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;

public interface Edition {
    String getName();

    LocalDate getStart();

    String getProjectTemplate();

    Status getStatus();

    void setStatus(Status var1);

    void addProject(String var1, String var2, String[] var3) throws IOException, ParseException;

    void removeProject(String var1);

    Project getProject(String var1);

    Project[] getProjects();

    Project[] getProjectsByTag(String var1);

    Project[] getProjectsOf(String var1);

    int getNumberOfProjects();

    LocalDate getEnd();

    boolean equals(Object var1);
}
