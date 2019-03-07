import com.mongodb.*;
import java.net.UnknownHostException;
import java.util.Date;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;


/**
 * Java + MongoDB Hello world Example
 *
 */
public class ServerMongo {
    /**** Connect to MongoDB ****/
    // Since 2.10.0, uses MongoClient
    private MongoClient mongo;
    private DB db;
    private DBCollection table;

    //constructor
    public ServerMongo() {
       try {
            /**** Connect to MongoDB ****/
            // Since 2.10.0, uses MongoClient
            mongo = new MongoClient("localhost", 27017);
            /**** Get database ****/
            // if database doesn't exists, MongoDB will create it for you
            this.db = mongo.getDB("CMPE273");
            System.out.println(db);
       } catch (UnknownHostException e) {
            e.printStackTrace();
       } catch (MongoException e) {
            e.printStackTrace();
       }
    }

    public void create() {
            System.out.println(this.db);

            /**** Get collection / table from 'testdb' ****/
            // if collection doesn't exists, MongoDB will create it for you
            table = this.db.getCollection("Parking");
            // create a document to store key and value
            BasicDBObject document = new BasicDBObject();
            document.put("name", "SJSU");
            document.put("Levels", 4);
            document.put("SpotsBus", 10);
            document.put("SpotsCar", 100);
            document.put("SpotsBike", 200);
            document.put("createdDate", new Date());
            table.insert(document);

    }

    public void read() {
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("name", "SJSU-update");
            DBCursor cursor = table.find(searchQuery);

            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
    }

    public void update() {
       BasicDBObject query = new BasicDBObject();
       query.put("name", "SJSU");
       BasicDBObject newDocument = new BasicDBObject();
       newDocument.put("name", "SJSU-update");
       BasicDBObject updateObj = new BasicDBObject();
       updateObj.put("$set", newDocument);
       table.update(query, updateObj);
    }


    public void delete() {
        DBObject doc = table.findOne();
        table.remove(doc);

    }

}


