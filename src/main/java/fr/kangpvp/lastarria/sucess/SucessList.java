package fr.kangpvp.lastarria.sucess;

import org.bukkit.Material;

import java.util.ArrayList;

public enum SucessList {

    PECHE(new Sucess("Pêche", 1000, 0, 10, Material.FISHING_ROD, "9", "Pêches 2000 poissons")),
    MINAGE(new Sucess("Minage", 0, 1, 2000, Material.DIAMOND_PICKAXE, "8", "Casses 2000 blocs de stone")),
    CHASSE(new Sucess("Chasse", 0, 1, 10000, Material.IRON_SWORD, "4", "Tues 10 000 mobs")),
    ELVAGE(new Sucess("Elvage", 0, 1, 1000, Material.STICK, "7", "Tues 1000 animaux")),
    JUMP(new Sucess("Saut", 1000, 0, 100000, Material.RABBIT_FOOT, "e", "Sautes 100 000 fois")),
    TRADE(new Sucess("Echange", 0, 2, 200, Material.VILLAGER_SPAWN_EGG, "c","Fais 200 échanges avec les Villagois")),
    TIME(new Sucess("Temps", 0,3, 30, Material.CLOCK, "e", "Atteint 30 jours de jeu (720h)")),
    SEIGNEUR(new Sucess("Titre", 2000, 0, 1, Material.BOOK, "9", "Atteint le Titre Seigneur"));


    private Sucess sucess;

    SucessList(Sucess sucess) {
        this.sucess = sucess;
    }

    public Sucess getSucess() {
        return sucess;
    }

    public static Sucess getSucessFromName(String name) {

        Sucess sucessReturn = null;
        SucessList[] sucessList = SucessList.values();

        for(int i = 0 ; i < sucessList.length ; i ++) {
            //System.out.println(name.substring(13));
            if(sucessList[i].getSucess().getName().toLowerCase().equals(name.toLowerCase().substring(13))) {
                sucessReturn = sucessList[i].getSucess();
            }
        }

        return sucessReturn;

    }

}
