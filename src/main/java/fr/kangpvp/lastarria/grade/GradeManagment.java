package fr.kangpvp.lastarria.grade;

import fr.kangpvp.lastarria.utils.Grade;
import org.bukkit.entity.Player;

public class GradeManagment {

    //uuid:
    //  nom_du_grade
    //autre uuid:
    //  nom_d_un_autre_grade


    //private static File gradeFile = new File(Main.INSTANCE.getDataFolder(), "player_grades.yml");
    //private static FileConfiguration gradeConfig = YamlConfiguration.loadConfiguration(gradeFile);


    public static void setGrade(Player player, Grade grade) {

        //ConfigManager.pgrades.set(player.getUniqueId().toString(), grade.getNameWithoutColor());

    }

   // public static Grade getGrade(Player player) {

      //  return Grades.getGradeFromName(ConfigManager.pgrades.get(player.getUniqueId().toString()).toString());

    //}

}
