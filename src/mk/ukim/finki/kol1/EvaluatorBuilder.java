package mk.ukim.finki.kol1;

class EvaluatorBuilder {
    public static Evaluator build(Evaluator.TYPE type) throws InvalidEvaluation {
        // вашиот код овде
        switch (type) {
            case NO_CRIMINAL_RECORD:
                return applicant -> !(applicant.hasCriminalRecord());
            case MORE_EXPERIENCE:
                return applicant -> applicant.getEmploymentYears() >= 10;
            case MORE_CREDIT_SCORE:
                return applicant -> applicant.getCreditScore() >= 500;
            case NO_CRIMINAL_RECORD_AND_MORE_EXPERIENCE:
                return applicant -> build(Evaluator.TYPE.NO_CRIMINAL_RECORD).evaluate(applicant) &&
                        build(Evaluator.TYPE.MORE_EXPERIENCE).evaluate(applicant);
            case NO_CRIMINAL_RECORD_AND_MORE_CREDIT_SCORE:
                return applicant -> build(Evaluator.TYPE.NO_CRIMINAL_RECORD).evaluate(applicant) &&
                        build(Evaluator.TYPE.MORE_CREDIT_SCORE).evaluate(applicant);
            case MORE_EXPERIENCE_AND_MORE_CREDIT_SCORE:
                return applicant -> build(Evaluator.TYPE.MORE_EXPERIENCE).evaluate(applicant) &&
                        build(Evaluator.TYPE.MORE_CREDIT_SCORE).evaluate(applicant);
            case INVALID:
                throw new InvalidEvaluation();
            default:
                throw new InvalidEvaluation();
        }
    }
}