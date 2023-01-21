package exames;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Especial22 {

    /**
     * Considere a seguinte class Student e respetivos métodos de acesso às variáveis.
     * Implemente um método que recebe uma lista de objetos Student e devolve num map a média das notas de
     * exame agregada por turma.
     * Map<String, Double> avScoreClass (List<Student> ls)
     */
    public static class Student {
        String student_id;
        // id do estudante
        String class_id;
        // id da turma
        Double exam_score;
        // nota de exame
        Double freq_score;
        // nota de frequência
        private boolean [] presences; // identifica se um aluno teve presente na lista de aulas da UC

        String getClassId(){return class_id;}
        Double getExamScore() {return exam_score;}

        Student(String class_id, Double exam_score){
            this.class_id = class_id;
            this.exam_score = exam_score;
        }
    }

    static Map<String, Double> avScoreClass(List<Student> ls){
        Map<String, Double> map = new HashMap<>();
        Map<String, Integer> nrStu = new HashMap<>();

        for (Student stu : ls){
            String classId = stu.getClassId();
            Double score = stu.getExamScore();

            if (!map.containsKey(classId)){
                map.put(classId, score);
                nrStu.put(classId, 1);
            }

            else{
                Double currentAvg = map.get(classId);
                int nrStudentsInClass = nrStu.get(classId);
                Double newAvg = (currentAvg * nrStudentsInClass + score)/(nrStudentsInClass + 1);
                map.put(classId, newAvg);
                nrStu.put(classId, nrStudentsInClass + 1);
            }
        }
        return map;
    }

    public static void main(String[] args) {
        System.out.println("Exame 2022-09-08 - Especial 22\n");

        System.out.println(" Questão 1");
        Student st1 = new Student("1", 10.0);
        Student st2 = new Student("1", 16.0);
        Student st3 = new Student("2", 10.0);
        Student st4 = new Student("2", 15.0);
        Student st5 = new Student("2", 20.0);

        List<Student> list = new ArrayList<>();
        list.add(st1);
        list.add(st2);
        list.add(st3);
        list.add(st4);
        list.add(st5);

        System.out.println(
                avScoreClass (list)
        );
    }
}
