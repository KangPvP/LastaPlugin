package fr.kangpvp.lastarria.grade;

import fr.kangpvp.lastarria.utils.Grade;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Grades {

    PAYSAN(new Grade(1, "Paysan", "2", 0, 300, null, (List<String>) Arrays.asList(
            "3 items en vente", "2 homes" ) ));



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
            if(gradeArray.get(i).getGrade().getNameWithoutColor() == name) {
                gradeReturn = gradeArray.get(i).getGrade();
            }
        }

        return gradeReturn;

    }

}
