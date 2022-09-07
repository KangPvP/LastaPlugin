package fr.kangpvp.lastarria.utils.database;

public class MongoManager {
    MongoConnection mongoConnection;

    private final String linkConnect = "mongodb+srv://Admin4786:znmdnB5MJSme4Mn@cluster0.cspx28h.mongodb.net/?retryWrites=true&w=majority";

    public MongoManager(){
        this.mongoConnection = new MongoConnection(linkConnect, "PlayerData");
    }

    public MongoConnection getMongoConnection(){
        return this.mongoConnection;
    }






}
