package fr.kangpvp.lastarria.sucess;

import org.bukkit.Material;

import java.util.ArrayList;

public enum SucessList {

    PECHE(new Sucess("Pêche", 1000, 0, 1000, Material.FISHING_ROD, "9", "Pêches 2000 poissons")),
    MINAGE(new Sucess("Minage", 0, 1, 2000, Material.DIAMOND_PICKAXE, "8", "Casse 2000 blocs de stone"));


    SucessList(Sucess sucess) {
        this.sucess = sucess;
    }


}
