package mk.ukim.finki.kol1;

class EvaluatorBuilder {
    public static Evaluator build(Evaluator.TYPE type) throws InvalidEvaluation {
        // вашиот код овде
        switch (type) {
            case NO_CRIMINAL_RECORD:
                return new NO_CRIMINAL_RECORD();
            case MORE_EXPERIENCE:
                return new MORE_EXPERIENCE();
            case MORE_CREDIT_SCORE:
                return new MORE_CREDIT_SCORE();
            case NO_CRIMINAL_RECORD_AND_MORE_EXPERIENCE:
                return new NO_CRIMINAL_RECORD_AND_MORE_EXPERIENCE();
            case NO_CRIMINAL_RECORD_AND_MORE_CREDIT_SCORE:
                return new NO_CRIMINAL_RECORD_AND_MORE_CREDIT_SCORE();
            case MORE_EXPERIENCE_AND_MORE_CREDIT_SCORE:
                return new MORE_EXPERIENCE_AND_MORE_CREDIT_SCORE();
            case INVALID:
                throw new InvalidEvaluation();
            default:
                throw new InvalidEvaluation();
        }
    }

    static class NO_CRIMINAL_RECORD implements Evaluator {
        @Override
        public boolean evaluate(Applicant applicant) {
            return !(applicant.hasCriminalRecord());
        }
    }

    static class MORE_EXPERIENCE implements Evaluator {
        @Override
        public boolean evaluate(Applicant applicant) {
            return applicant.getEmploymentYears() >= 10;
        }
    }

    static class MORE_CREDIT_SCORE implements Evaluator {
        @Override
        public boolean evaluate(Applicant applicant) {
            return applicant.getCreditScore() >= 500;
        }
    }

    static class NO_CRIMINAL_RECORD_AND_MORE_EXPERIENCE implements Evaluator {
        @Override
        public boolean evaluate(Applicant applicant) {
            return new NO_CRIMINAL_RECORD().evaluate(applicant) && new MORE_EXPERIENCE().evaluate(applicant);
        }
    }

    static class MORE_EXPERIENCE_AND_MORE_CREDIT_SCORE implements Evaluator {
        @Override
        public boolean evaluate(Applicant applicant) {
            return new MORE_EXPERIENCE().evaluate(applicant) && new MORE_CREDIT_SCORE().evaluate(applicant);
        }
    }

    static class NO_CRIMINAL_RECORD_AND_MORE_CREDIT_SCORE implements Evaluator {
        @Override
        public boolean evaluate(Applicant applicant) {
            return new NO_CRIMINAL_RECORD().evaluate(applicant) && new MORE_CREDIT_SCORE().evaluate(applicant);
        }
    }
}