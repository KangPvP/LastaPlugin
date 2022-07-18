package fr.kangpvp.lastarria.sucess;

import org.bukkit.Material;

import java.util.ArrayList;

public enum SucessList {

    PECHE(new Sucess("Pêche", 1000, 0, 10, Material.FISHING_ROD, "9", "Pêches 2000 poissons")),
    MINAGE(new Sucess("Minage", 0, 1, 2000, Material.DIAMOND_PICKAXE, "8", "Casse 2000 blocs de stone"));

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
            System.out.println(name.substring(13));
            if(sucessList[i].getSucess().getName().toLowerCase().equals(name.toLowerCase().substring(13))) {
                sucessReturn = sucessList[i].getSucess();
            }
        }

        return sucessReturn;

    }

}
