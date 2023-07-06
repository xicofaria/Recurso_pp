package ma02_resources.project;

import ma02_resources.participants.Student;

import java.time.LocalDateTime;
import java.util.Objects;

public class SubmissionImpl implements Submission {
    private LocalDateTime date;
    private Student student;
    private String text;

    public SubmissionImpl(LocalDateTime date, Student student, String text) {
        this.date = date;
        this.student = student;
        this.text = text;
    }

    @Override
    public LocalDateTime getDate() {
        return date;
    }


    @Override
    public Student getStudent() {
        return student;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public int compareTo(Submission o) {
        return this.date.compareTo(o.getDate());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SubmissionImpl that = (SubmissionImpl) obj;
        return date.equals(that.date) &&
                student.equals(that.student) &&
                text.equals(that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, student, text);
    }
}
