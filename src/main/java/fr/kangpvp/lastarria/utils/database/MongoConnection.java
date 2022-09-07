package fr.kangpvp.lastarria.utils.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.logging.Logger;

public class MongoConnection {

    private String linkConnect;
    private String dbName;

    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public MongoConnection(String linkConnect, String dbname){
        this.linkConnect = linkConnect;
        this.dbName = dbname;
        this.connect();
    }

    private void connect() {
        try{
            this.client = MongoClients.create(linkConnect);
            this.database = client.getDatabase(dbName);
            Logger.getLogger("Minecraft").info("Successfully connect Mongo DataBase");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public MongoClient getClient(){
        return this.client;
    }

    public MongoDatabase getDatabase(){
        return database;
    }

    public MongoCollection<Document> getCollection(String colName){
        return this.database.getCollection(colName);
    }


}
