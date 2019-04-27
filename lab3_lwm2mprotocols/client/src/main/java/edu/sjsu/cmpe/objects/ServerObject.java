package edu.sjsu.cmpe.objects;

import java.net.UnknownHostException;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import edu.sjsu.cmpe.ParkingSpaceClient;

public class ServerObject {

	private int ShortServerId;
	private int LifeTime;
	
	public ServerObject()
	{
		ShortServerId=-1;
		LifeTime=-1;
	}
	
	public ServerObject(boolean isBootStrap){
		if (isBootStrap)
		{
			ShortServerId=0;
			LifeTime=99999;
		}
	}
	public int getShortServerId() {
		return ShortServerId;
	}
	public void setShortServerId(int shortServerId) {
		ShortServerId = shortServerId;
	}
	public int getLifeTime() {
		return LifeTime;
	}
	public void setLifeTime(int lifeTime) {
		LifeTime = lifeTime;
	}
	public void pushToDB(ParkingSpaceClient a1) throws UnknownHostException {

		Gson gson = new Gson();
		String myServer = gson.toJson(this);

		DBObject dbo = (DBObject) JSON.parse(myServer);
			a1.serverInfo.remove(new BasicDBObject());
		a1.serverInfo.insert(dbo);
	}
}
