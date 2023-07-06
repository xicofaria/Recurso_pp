package ma02_resources.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TaskImpl implements Task {
    private LocalDate start;
    private LocalDate end;
    private int duration;
    private String title;
    private String description;
    private List<Submission> submissions;
    private int maximumNumberOfSubmissions;

    public TaskImpl(LocalDate start, int duration, String title, String description) {
        this.start = start;
        this.duration = duration;
        this.title = title;
        this.description = description;
        this.end = start.plusDays(duration);
        this.submissions = new ArrayList<>();
        this.maximumNumberOfSubmissions = maximumNumberOfSubmissions;
    }

    @Override
    public LocalDate getStart() {
        return start;
    }

    @Override
    public LocalDate getEnd() {
        return end;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Submission[] getSubmissions() {
        return submissions.toArray(new Submission[0]);
    }

    @Override
    public int getNumberOfSubmissions() {
        return submissions.size();
    }

    @Override
    public void addSubmission(Submission submission) {
        submissions.add(submission);
    }

    @Override
    public void extendDeadline(int days) {
        this.end = this.end.plusDays(days);
        this.duration += days;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TaskImpl task = (TaskImpl) obj;
        return duration == task.duration &&
                start.equals(task.start) &&
                end.equals(task.end) &&
                title.equals(task.title) &&
                description.equals(task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, duration, title, description);
    }

    @Override
    public int compareTo(Task o) {
        return this.start.compareTo(o.getStart());
    }
    // adicionado
    @Override
    public int getMaximumNumberOfSubmissions() {
        return maximumNumberOfSubmissions;
    }
}
