package fr.kangpvp.lastarria.grade;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Titres {
    //slot: slot get datachest, name: TitreDisplayName, Color: TitreDisplayColor, hours: Heures de jeu nécéssaire,price: prix, previousGrade: Titre nécéssaire, Aventages: list Aventage
    PAYSAN(new Titre(1, "Paysan", "2", 0, 370, null, (List<String>) Arrays.asList(
            "3 items en vente", "2 homes" ) )),

    COMMERCANT(new Titre(2, "Commercant", "2", 0, 980, Titres.PAYSAN.getGrade(), (List<String>) Arrays.asList(
            "4 items en vente", "ATM 1$/min" ) )),

    ARTISAN(new Titre(3, "Artisan", "2", 7, 1684, Titres.COMMERCANT.getGrade(), (List<String>) Arrays.asList(
            "5 items en vente", "3 homes", "/tpa: cooldown 3 min" ) )),

    ECUYER(new Titre(4, "Ecuyer", "2", 20, 2840, Titres.ARTISAN.getGrade(), (List<String>) Arrays.asList(
            "6 items en vente", "ATM 1.5$/min" ) )),

    VASSAL(new Titre(5, "Vassal", "9", 32, 3696, Titres.ECUYER.getGrade(), (List<String>) Arrays.asList(
            "7 items en vente", "4 homes", "Acces au /tpahere (cooldown 10 min)" ) )),

    CHEVALIER(new Titre(6, "Chevalier", "9", 52,9856, Titres.VASSAL.getGrade(), (List<String>) Arrays.asList(
            "8 items en vente", "ATM 2$/min") )),

    CHATELAIN(new Titre(7, "Chatelain", "9", 82, 6244, Titres.CHEVALIER.getGrade(), (List<String>) Arrays.asList(
            "9 items en vente", "5 homes", "/tpa: 30s de Cooldown") )),

    BARON(new Titre(8, "Baron", "e", 82, 15708, Titres.CHATELAIN.getGrade(), (List<String>) Arrays.asList(
            "10 items en vente", "ATM 2.5$/min") )),

    COMTE(new Titre(9, "Comte", "e", 216, 24948, Titres.BARON.getGrade(), (List<String>) Arrays.asList(
            "11 items en vente", "6 homes", "/tpahere: 3min de cooldown") )),

    DUC(new Titre(10, "Duc", "e", 345, 39732, Titres.COMTE.getGrade(), (List<String>) Arrays.asList(
            "12 items en vente", "ATM 3$/min") )),

    SEIGNEUR(new Titre(11, "Seigneur", "c", 553, 62664, Titres.DUC.getGrade(), (List<String>) Arrays.asList(
            "13 items en vente", "7 homes") ));




    private Titre titre;
    Titres(Titre titre) {
        this.titre = titre;
    }

    public Titre getGrade() {
        return this.titre;
    }

    public static Titre getGradeFromName(String name) {

        Titre titreReturn = null;
        List<Titres> gradeArray = Arrays.stream(Titres.class.getEnumConstants()).collect(Collectors.toList());


        for(int i = 0 ; i < gradeArray.size() ; i ++) {

            if(gradeArray.get(i).getGrade().getNameWithoutColor().toLowerCase().equals(name.toLowerCase().substring(4))) {
                titreReturn = gradeArray.get(i).getGrade();
            }
        }
        return titreReturn;

    }

}
