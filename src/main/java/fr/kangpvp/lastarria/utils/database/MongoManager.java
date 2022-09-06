package fr.kangpvp.lastarria.utils.database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.sql.SQLException;

public class MongoManager {

        public MongoManager(){

            MongoClient client = (MongoClient) MongoClients.create("mongodb+srv://Admin4786:znmdnB5MJSme4Mn@cluster0.cspx28h.mongodb.net/?retryWrites=true&w=majority");

            MongoDatabase database = client.getDatabase("PlayerData");

            MongoCollection<Document> collection = database.getCollection("PlayerHomes");

            Document document = new Document("_id", 1).append("name", "Jhon");

            collection.insertOne(document);

        }






}
