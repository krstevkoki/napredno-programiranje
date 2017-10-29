package mk.ukim.finki.aud5;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalculateGrades {
    private static final String str =
            "Doe:John:100:100:100\n" +
                    "Pantz:Smartee:80:90:80\n" +
                    "Torko:Torkovski:65:74:90\n" +
                    "Drakura:Drakuroski:80:55:85";

    private static void writeToFile(final String fileName) throws FileNotFoundException {
        try (PrintWriter in = new PrintWriter(new FileOutputStream(new File(fileName)))) {
            in.print(str);
        }
    }

    private static ArrayList<Student> readFromFile(final InputStream fileName) throws IOException {
        ArrayList<Student> students = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(fileName))) {
            String line;
            while ((line = bf.readLine()) != null && !(line.length() < 1)) {
                students.add(Student.parseFromString(line));
            }
        }
        return students;
    }

    private static int[] gradeDistribution(List<Student> students) {
        int[] gradeDistribuiton = new int[6];
        for (int i = 0; i < students.size(); ++i)
            gradeDistribuiton[students.get(i).getLetterGrade() - 'A']++;
        return gradeDistribuiton;
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Two arguments must be provided!");
            return;
        }
        writeToFile(args[0]);
        List<Student> students = readFromFile(new FileInputStream(new File(args[0])));
        students.sort(Collections.reverseOrder());
        // Collections.sort(students);  // natural order of the elements
        for (Student student : students)
            System.out.println(String.format("%s %s %c", student.getLastName(), student.getFirstName(), student.getLetterGrade()));
        int[] gradeDistribution = gradeDistribution(students);
        try (PrintWriter in = new PrintWriter(new FileWriter(new File(args[1])))) {
            for (Student student : students)
                in.println(student.toString());
            for (int i = 0; i < gradeDistribution.length; ++i) {
                in.println(String.format("%c: %d", 'A' + i, gradeDistribution[i]));
            }
        }
    }
}
