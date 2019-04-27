package edu.sjsu.cmpe273.functions;


import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

import edu.sjsu.cmpe273.Server;

public class BootStrapClient {

    private String regServerURI;
	private String regServerID;
    private String regServerLifeTimeInSec;
    
    public String getRegServerURI() {
		return regServerURI;
	}
	public void setRegServerURI(String regServerURI) {
		this.regServerURI = regServerURI;
	}
	public String getRegServerID() {
		return regServerID;
	}
	public void setRegServerID(String regServerID) {
		this.regServerID = regServerID;
	}
	public String getRegServerLifeTimeInSec() {
		return regServerLifeTimeInSec;
	}
	public void setRegServerLifeTimeInSec(String regServerLifeTimeInSec) {
		this.regServerLifeTimeInSec = regServerLifeTimeInSec;
	}  
	
	
	public String bootStrapInDB(String endPointName) throws JSONException{
			
		int objectId;
		int objectInstanceId;
		int resourceId;
		
		BasicDBObject fields = new BasicDBObject().append("serverId", 1); // SELECT name
		fields.append("URI",1);
		fields.append("lifeTime",1);
        System.out.println("serverId created");

		BasicDBObject query = new BasicDBObject().append("endPointName", endPointName); // WHERE name = "Jon"
		DBCursor results = Server.bootStrapInfo.find(query, fields);
		System.out.println("serverId queried");
		System.out.println (results);

		BasicDBObject resultField = (BasicDBObject) results.next();
//		System.out.println ("resultfield", resultField);
		setRegServerID(resultField.getString("serverId"));
		setRegServerURI(resultField.getString("URI"));
		setRegServerLifeTimeInSec(resultField.getString("lifeTime"));


		objectId=0;	//security object
		objectInstanceId=1;	//REGISTRATION SERVER
		resourceId=0;	//URI
		
		String item[] = new String[3];
		String value[] = new String[3];

		item[0]="/"+objectId+"/"+objectInstanceId+"/"+resourceId;
		value[0]=regServerURI;

		objectId=1;	//server object
		objectInstanceId=1;	//1st server
		resourceId=0;	//ServerId
		item[1]="/"+objectId+"/"+objectInstanceId+"/"+resourceId;
		value[1]=regServerID;
	
		objectId=1;	//server object
		objectInstanceId=1;	//1st server
		resourceId=1;	//life time
		item[2]="/"+objectId+"/"+objectInstanceId+"/"+resourceId;
		value[2]=regServerLifeTimeInSec;

		JSONArray j1 = new JSONArray();
		for(int i=0;i<3;i++)
		{
			j1.put(new JSONObject()
            	.put("resource", item[i])
				.put("value",value[i]));
		}
		System.out.println("j1.toString()");
		System.out.println(j1.toString());
		return j1.toString() ;
		
	}
			
}
