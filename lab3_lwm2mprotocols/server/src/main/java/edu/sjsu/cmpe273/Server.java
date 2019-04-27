package edu.sjsu.cmpe273;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import edu.sjsu.cmpe273.functions.BootStrapClient;
import edu.sjsu.cmpe273.functions.DeleteClientRegistration;
import edu.sjsu.cmpe273.functions.RegisterClient;
import edu.sjsu.cmpe273.functions.UpdateClientRegistration;

@Path("/lwm2m")
public class Server {

	public static MongoClient mongo = null;
	public static DB db;
	public static DBCollection bootStrapInfo;
	public static DBCollection registrationInfo;
	public static DBCollection server_lwm2mResourceInfo;

	public static void connectDatabase() throws UnknownHostException {

		String textUri = "mongodb://127.0.0.1/test";
		MongoClientURI uri = new MongoClientURI(textUri);
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		bootStrapInfo = db.getCollection("bootStrapInfo");
		registrationInfo = db.getCollection("registrationInfo");
		server_lwm2mResourceInfo = db.getCollection("server_lwm2mResourceInfo");
	}

	static Map<String, List<String>> map = new HashMap<String, List<String>>();

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public String sayHello() throws UnknownHostException, JSONException {

		String temp1 = new String("Hello");
		return temp1;
	}

	@POST
	@Path("/bs")
	@Consumes(MediaType.APPLICATION_JSON)
	public String bootStrap(@QueryParam("ep") String endPointName) throws JSONException, UnknownHostException {

		System.out.println("Connecting to DB");
		connectDatabase();

		System.out.println("Start Bootstrapping the client for EndPointName = " + endPointName);
		BootStrapClient bs = new BootStrapClient();
        System.out.println("Created Bootstrap client object the client for EndPointName = " + endPointName);

		return bs.bootStrapInDB(endPointName).replace("\\", "");
	}

	@POST
	@Path("/rd")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response register(String myParameters, @QueryParam("ep") String endPointName,
			@QueryParam("lt") String lifeTime) throws JSONException, UnknownHostException {

		connectDatabase();

		System.out.println("Registering Client with EndPointName = " + endPointName + " and LifeTime = " + lifeTime);

		System.out.println("Registration data: " + myParameters);

		RegisterClient rd = new RegisterClient();

		String registrationId = new String();
		registrationId = rd.registerAtDatabase(endPointName, lifeTime);

		return Response.status(201).entity(registrationId).build();
	}

	@PUT
	@Path("/rd")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateRegistration(@QueryParam("registrationId") String registrationId,
			@QueryParam("lt") String lifeTime) throws JSONException, UnknownHostException {

		connectDatabase();

		UpdateClientRegistration ud = new UpdateClientRegistration();

		System.out.println(
				"Updating Client Registration : Registration Id = " + registrationId + " LifeTime = " + lifeTime);

		ud.updateRegistrationInDB(registrationId, lifeTime);

		return Response.status(201).entity(registrationId).build();

	}

	@DELETE
	@Path("/rd")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteRegistration(@QueryParam("registrationId") String registrationId)
			throws JSONException, UnknownHostException {

		connectDatabase();

		DeleteClientRegistration dd = new DeleteClientRegistration();

		System.out.println("Deleting Client Registration : Registration Id = " + registrationId);

		dd.deleteRegistrationInDB(registrationId);

		return Response.status(201).entity(registrationId).build();

	}
	
	@GET
	@Path("/notify")
	@Produces(MediaType.TEXT_PLAIN)
	public String notify(@QueryParam("tokenNo") String tokenNo, @QueryParam("value") String value) throws JSONException, UnknownHostException {

		System.out.println("Notification Received: tokenNo: " + tokenNo + " value: "+value);
		return "OK";
	}

}
