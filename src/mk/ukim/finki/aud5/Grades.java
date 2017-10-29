package mk.ukim.finki.aud5;

final class Grades {
    private final int grade1;
    private final int grade2;
    private final int grade3;

    public Grades(int grade1, int grade2, int grade3) {
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.grade3 = grade3;
    }

    public int getGrade1() {
        String temp = Integer.toString(grade1);
        return Integer.parseInt(temp);
    }

    public int getGrade2() {
        String temp = Integer.toString(grade2);
        return Integer.parseInt(temp);
    }

    public int getGrade3() {
        String temp = Integer.toString(grade3);
        return Integer.parseInt(temp);
    }

    @Override
    public String toString() {
        return getGrade1() + " " + getGrade2() + " " + getGrade3();
    }
}
