package mk.ukim.finki.lab2;

class Faculty {
    private String name;
    private Student[] students;

    public Faculty(String name, Student[] students) {
        this.name = name;
        this.students = students;
    }

    public int countStudentsFromCity(String cityName) {
        int counter = 0;
        for (Student student : students) {
            if (student.getCity().equals(cityName))
                ++counter;
        }
        return counter;
    }

    public Student getStudent(long index) {
        for (Student student : students) {
            if (student.getIndex() == index)
                return student;
        }
        return null;
    }

    public double getAverageNumberOfContacts() {
        double average = 0.0;
        for (Student student : students) {
            average += student.getEmailContacts().length + student.getPhoneContacts().length;
        }
        return average / students.length;
    }

    public Student getStudentWithMostContacts() {
        int currentContactNum = students[0].getEmailContacts().length + students[0].getPhoneContacts().length;
        int maxIndex = 0;
        for (int i = 1; i < students.length; ++i) {
            if (currentContactNum <= students[i].getEmailContacts().length + students[i].getPhoneContacts().length) {
                int lastContactNum = currentContactNum;
                currentContactNum = students[i].getEmailContacts().length + students[i].getPhoneContacts().length;
                if (lastContactNum == currentContactNum) {
                    if (students[maxIndex].getIndex() < students[i].getIndex())
                        maxIndex = i;
                } else
                    maxIndex = i;
            }
        }
        return students[maxIndex];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"fakultet\":");
        sb.append("\"" + name + "\", ");
        sb.append("\"studenti\":[");
        for (int i = 0; i < students.length; ++i) {
            sb.append(students[i]);
            if (i < students.length - 1)
                sb.append(", ");
        }
        sb.append("]}");
        return sb.toString();
    }
}
