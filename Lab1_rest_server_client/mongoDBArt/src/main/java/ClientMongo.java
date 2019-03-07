import java.net.UnknownHostException;
import java.util.Date;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class ClientMongo {
    public static void main (String args[]) {
        ServerMongo serverMongo = new ServerMongo();
        System.out.println("Table Entry");
        serverMongo.create();
        serverMongo.read();


        System.out.println("Post Update");
        //serverMongo.update();
        //serverMongo.read();
        //serverMongo.delete();
        serverMongo.read();

    }
}
