package mk.ukim.finki.aud5;

class Student implements Comparable<Student> {
    private String firstName;
    private String lastName;
    private Grades grades;
    private double totalPoints;
    private char letterGrade;

    public Student(String lastName, String firstName, Grades grades) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grades = grades;
        this.totalPoints = grades.getGrade1() * .25 + grades.getGrade2() * .3 + grades.getGrade3() * .45;
        processTotal(this.totalPoints);
    }

    private void processTotal(final double total) {
        if (total > 91) this.letterGrade = 'A';
        else if (total > 81) this.letterGrade = 'B';
        else if (total > 71) this.letterGrade = 'C';
        else if (total > 61) this.letterGrade = 'D';
        else if (total > 51) this.letterGrade = 'E';
        else this.letterGrade = 'F';
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getTotalPoints() {
        return totalPoints;
    }

    public char getLetterGrade() {
        return letterGrade;
    }

    public static Student parseFromString(final String str) {
        String[] parts = str.split(":");
        Grades grades = new Grades(Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
        return new Student(parts[0], parts[1], grades);
    }

    @Override
    public int compareTo(Student o) {
        return Double.compare(this.totalPoints, o.totalPoints);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %.0f %c", lastName, firstName, grades.toString(), totalPoints, letterGrade);
    }
}
