package ma02_resources.participants;

public class StudentEvaluation {
    private Student student;
    private double selfEvaluation;
    private double peerEvaluation;

    public StudentEvaluation(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public double getSelfEvaluation() {
        return selfEvaluation;
    }

    public void setSelfEvaluation(double selfEvaluation) {
        this.selfEvaluation = selfEvaluation;
    }

    public double getPeerEvaluation() {
        return peerEvaluation;
    }

    public void setPeerEvaluation(double peerEvaluation) {
        this.peerEvaluation = peerEvaluation;
    }

    public double getFinalEvaluation() {
        return (selfEvaluation + peerEvaluation) / 2;
    }
}
