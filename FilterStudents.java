import java.util.*;
import java.util.stream.Collectors;

public class FilterStudents {
    static class Student {
        private String name;
        private double marks;

        public Student(String name, double marks) {
            this.name = name;
            this.marks = marks;
        }

        public String getName() { return name; }
        public double getMarks() { return marks; }
    }

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Alice", 88),
            new Student("Bob", 72),
            new Student("Charlie", 91),
            new Student("Dave", 60)
        );

        List<String> filteredNames = students.stream()
                .filter(s -> s.getMarks() > 75)
                .sorted(Comparator.comparingDouble(Student::getMarks).reversed())
                .map(Student::getName)
                .collect(Collectors.toList());

        System.out.println("Students scoring above 75%:");
        filteredNames.forEach(System.out::println);
    }
}
