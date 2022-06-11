package fr.kangpvp.lastarria.grade;

import fr.kangpvp.lastarria.utils.Grade;
import org.bukkit.Bukkit;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Grades {

    PAYSAN(new Grade(1, "Paysan", "2", 0, 300, null, (List<String>) Arrays.asList(
            "3 items en vente", "2 homes" ) )),
    COMMERCANT(new Grade(2, "Commercant", "2", 0, 300, Grades.PAYSAN.getGrade(), (List<String>) Arrays.asList(
            "ATM 1$/min", "ATM 1$/min" ) )),
    ARTISAN(new Grade(3, "Artisan", "2", 7, 1400, Grades.COMMERCANT.getGrade(), (List<String>) Arrays.asList(
            "5 items en vente", "3 homes", "5 items en vente" ) )),
    ECUYER(new Grade(4, "Ecuyer", "2", 20, 1400, Grades.ARTISAN.getGrade(), (List<String>) Arrays.asList(
            "6 items en vente", "ATM 1$/min" ) )),
    VASSAL(new Grade(5, "Vassal", "9", 32, 1400, Grades.ECUYER.getGrade(), (List<String>) Arrays.asList(
            "7 items en vente", "+ 1 home", "Acces au /tpahere (cooldown 10 min)" ) )),
    CHEVALIER(new Grade(6, "Chevalier", "9", 52, 1400, Grades.VASSAL.getGrade(), (List<String>) Arrays.asList(
            "8 items en vente", "ATM 2$/min") )),
    CHATELAIN(new Grade(7, "Chatelain", "9", 52, 1400, Grades.CHEVALIER.getGrade(), (List<String>) Arrays.asList(
            "8 items en vente", "ATM 2$/min") ));



    private Grade grade;
    Grades(Grade grade) {
        this.grade = grade;
    }

    public Grade getGrade() {
        return this.grade;
    }

    public static Grade getGradeFromName(String name) {

        Grade gradeReturn = null;
        List<Grades> gradeArray = Arrays.stream(Grades.class.getEnumConstants()).collect(Collectors.toList());


        for(int i = 0 ; i < gradeArray.size() ; i ++) {

            if(gradeArray.get(i).getGrade().getNameWithoutColor().toLowerCase().equals(name.toLowerCase().substring(4))) {
                System.out.println("test42");
                System.out.println(gradeArray.get(i).getGrade());
                gradeReturn = gradeArray.get(i).getGrade();
            }
        }
        System.out.println("Test 686");
        return gradeReturn;

    }

}
