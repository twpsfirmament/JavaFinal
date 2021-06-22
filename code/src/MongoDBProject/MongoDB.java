package MongoDBProject;

import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import com.mongodb.MongoClientSettings;

import static com.mongodb.client.model.Filters.*;


import com.mongodb.client.MongoClient;
import org.bson.Document; //for MongoCollection<Document>


import static com.mongodb.client.model.Updates.*;


import com.mongodb.*;

import java.util.function.Consumer;


import org.bson.types.ObjectId;

import javax.swing.*;

public class MongoDB {
    //connect string根據自己本地端的做修改
    String url="mongodb+srv://user:user@cluster0.sebh5.mongodb.net/test?authSource=admin&replicaSet=atlas-ivpyt7-shard-0&readPreference=primary&appname=MongoDB%20Compass&ssl=true";
    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> coll;



    public MongoDB() {
        connect();
    }

    //連接到資料庫
    public void connect() {

        ConnectionString connString = new ConnectionString(url);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .retryWrites(true)
                .build();
        mongoClient = MongoClients.create(settings);
        //根據要用的資料庫的名稱做修改
        database = mongoClient.getDatabase("test");
        //根據要用的collection做修改
        coll = database.getCollection("test");
    }

    //新增
    public void insert(String account , String pas,int amount,String name,String gender,String mobile,String email) {

        Document doc = new Document("account" ,account)
                .append("password", pas)
                .append("amount", amount)
                .append("name",name)
                .append("gender", gender)
                .append("contact", new Document("mobile", mobile).append("e-mail", email));
        coll.insertOne(doc);
    }

    //刪除
    public void deleteOne(String id) {

        //coll.deleteOne(eq("_id",id));
        //ObjectId objId = new ObjectId(id);
        coll.deleteOne(eq("_id",transferID(id)));

    }

    public void login(String account,String password)
    {
        BasicDBObject doc = new BasicDBObject();
        doc.put("account",account);
        doc.put("password",password);
        FindIterable<Document> find = coll.find(doc);
        System.out.println(find.first().toJson());
    }

    //修改
    public int balance(String id)
    {
        return coll.find(eq("account",id)).first().getInteger("amount");
    }
    public void transferOut(String id,int amount) {
        coll.updateOne(eq("_id", transferID(id)), set("amount",this.balance(id) - amount));
    }
    public void transferIn(String id,int amount) {
        coll.updateOne(eq("_id", transferID(id)), set("amount",this.balance(id) + amount));
    }
    public ObjectId transferID(String id)
    {
        return coll.find(eq("account",id)).first().getObjectId("_id");
    }

    public String findPassword(String id) {
        Document myDoc = coll.find(eq("account", id)).first();
        return myDoc.getString("password");
        /*try {
            Document myDoc2 = coll.find(eq("_id", new ObjectId(id))).first();
            System.out.println(myDoc2.getString("password"));}catch(Exception e){
            //do nothing
        }
        */
    }

    public void changePassword(String id,String password){
        coll.updateOne(eq("_id",transferID(id)),set("password",password));
    }

    /*
    public void updateMany(int amount) {

        UpdateResult updateResult = coll.updateMany(lt("amount", amount), set("amount", 100));
        System.out.println(updateResult.getModifiedCount());

    }
    */

    //for findMany
    Consumer<Document> printConsumer = new Consumer<Document>() {
        @Override
        public void accept(final Document document) {
            System.out.println(document.getString("name"));
        }
    };
    public void findMany(int amount) {
        //gt >  lt <  lte <=
        coll.find(gt("amount", amount))
                .forEach(printConsumer);
    }
        /*try {
            Document myDoc2 = coll.find(eq("_id", new ObjectId(id))).first();
            System.out.println(myDoc2.getString("password"));}catch(Exception e){
            //do nothing
        }
        */

    public static void main(String args[]) {
        MongoDB mongoDB = new MongoDB();
        Account account = new Account();
        account.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        account.setSize(250, 350);
        account.setLocation(550, 300);
        account.setVisible(true);
    }
}
