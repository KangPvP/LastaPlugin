package fr.kangpvp.lastarria.grade;

import fr.kangpvp.lastarria.Main;
import fr.kangpvp.lastarria.utils.Grade;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class GradeManagment {

    private static File gradeFile = new File(Main.dataFolder, "player_grades.yml");
    private static FileConfiguration gradeConfig = YamlConfiguration.loadConfiguration(gradeFile);


    public static void setGrade(Player player, Grade grade) {

        gradeConfig.set(player.getUniqueId().toString(), grade.getNameWithoutColor());

    }

    public static Grade getGrade(Player player) {

        return Grades.getGradeFromName(gradeConfig.get(player.getUniqueId().toString()).toString());

    }

}
