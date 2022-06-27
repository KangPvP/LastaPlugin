package fr.kangpvp.lastarria.titre;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Titres {
    //slot: slot get datachest, name: TitreDisplayName, Color: TitreDisplayColor, hours: Heures de jeu nécéssaire,price: prix, previousGrade: Titre nécéssaire, Aventages: list Aventage
    PAYSAN(new Titre(1, "Paysan", "2", 0, 370, 2, null, (List<String>) Arrays.asList(
            "Max d'items en vente : §f3", "Accès à §f2 homes" ))),

    COMMERCANT(new Titre(2, "Commercant", "2", 3, 980, Titres.PAYSAN.getGrade(), (List<String>) Arrays.asList(
            "Max d'items en vente : §f4", "ATM 1$/min" ) )),

    ARTISAN(new Titre(3, "Artisan", "2", 7, 1684,3, Titres.COMMERCANT.getGrade(), (List<String>) Arrays.asList(
            "Max d'items en vente : §f5", "Accès à §f3 homes", "Accès au /tpa: cooldown 3 min" ) )),

    ECUYER(new Titre(4, "Ecuyer", "2", 20, 2840, Titres.ARTISAN.getGrade(), (List<String>) Arrays.asList(
            "Max d'items en vente : §f6", "ATM 1.5$/min" ) )),

    VASSAL(new Titre(5, "Vassal", "9", 32, 3696, 4, Titres.ECUYER.getGrade(), (List<String>) Arrays.asList(
            "Max d'items en vente : §f7", "Accès à §f4 homes", "Acces au /tpahere (cooldown 10 min)" ) )),

    CHEVALIER(new Titre(6, "Chevalier", "9", 52,9856, Titres.VASSAL.getGrade(), (List<String>) Arrays.asList(
            "Max d'items en vente : §f8", "ATM 2$/min") )),

    CHATELAIN(new Titre(7, "Chatelain", "9", 82, 6244, 5, Titres.CHEVALIER.getGrade(), (List<String>) Arrays.asList(
            "Max d'items en vente : §f9", "Accès à §f5 homes", "Accès au /tpa : 30s de Cooldown") )),

    BARON(new Titre(8, "Baron", "e", 135, 15708, Titres.CHATELAIN.getGrade(), (List<String>) Arrays.asList(
            "Max d'items en vente : §f10", "ATM 2.5$/min") )),

    COMTE(new Titre(9, "Comte", "e", 216, 24948, 6, Titres.BARON.getGrade(), (List<String>) Arrays.asList(
            "Max d'items en vente : §f11", "Accès à §f6 homes", "Accès au /tpahere: 3min de cooldown") )),

    DUC(new Titre(10, "Duc", "e", 345, 39732, Titres.COMTE.getGrade(), (List<String>) Arrays.asList(
            "Max d'items en vente : §f12", "ATM 3$/min") )),

    SEIGNEUR(new Titre(11, "Seigneur", "c", 553, 62664,7, Titres.DUC.getGrade(), (List<String>) Arrays.asList(
            "Max d'items en vente : §f13", "Accès à §f7 homes") ));




    private Titre titre;
    Titres(Titre titre) {
        this.titre = titre;
    }

    public Titre getGrade() {
        return this.titre;
    }

    public static Titre getGradeFromName(String name) {

        Titre titreReturn = null;
        Titres[] gradeArray = Titres.values();

        for(int i = 0 ; i < gradeArray.length ; i ++) {
            System.out.println(name);
            if(gradeArray[i].getGrade().getNameWithoutColor().toLowerCase().equals(name)) {
                titreReturn = gradeArray[i].getGrade();
            }
        }

        return titreReturn;

    }

}
