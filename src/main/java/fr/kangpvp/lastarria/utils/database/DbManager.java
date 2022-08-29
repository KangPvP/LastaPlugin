package fr.kangpvp.lastarria.utils.database;

import java.sql.SQLException;

public class DbManager {
    private DbConnection playerConnection;

    public DbManager(){
        this.playerConnection = new DbConnection(new DbCredentials("151.80.13.0", "u44_4lgRWJLRuj", "ol=iDaEzVtcCpEmmL=3dix.6", "s44_DataLastarria", 3306));
    }

    public DbConnection getPlayerConnection(){
        return playerConnection;
    }


    public void close() {
        try{
            this.playerConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
