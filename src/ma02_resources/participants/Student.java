package ma02_resources.participants;

public interface Student extends Participant {
    int getNumber();
    void addEvaluation(StudentEvaluation evaluation);
    double getFinalEvaluation();
}
