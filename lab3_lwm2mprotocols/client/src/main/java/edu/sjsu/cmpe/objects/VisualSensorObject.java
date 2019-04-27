package edu.sjsu.cmpe.objects;

import java.net.UnknownHostException;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import edu.sjsu.cmpe.ParkingSpaceClient;

public class VisualSensorObject {

	private int objectId;

	private Boolean DeviceOn;
	private int HorizontalMovement;
	private int VerticalMovement;
	private int ObservationId;

	public VisualSensorObject() {
		this.objectId = 15;
		this.HorizontalMovement = 0;
		this.VerticalMovement = 0;
		this.ObservationId = 0;
		this.DeviceOn = false;
	}

	public void pushToDB(ParkingSpaceClient a1) throws UnknownHostException {

		Gson gson = new Gson();
		String myServer = gson.toJson(this);

		DBObject dbo = (DBObject) JSON.parse(myServer);
		a1.VisualInfo.remove(new BasicDBObject());
		a1.VisualInfo.insert(dbo);
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

	public Boolean getDeviceOn() {
		return DeviceOn;
	}

	public void setDeviceOn(Boolean deviceOn) {
		DeviceOn = deviceOn;
	}

	public int getHorizontalMovement() {
		return HorizontalMovement;
	}

	public void setHorizontalMovement(int horizontalMovement) {
		HorizontalMovement = horizontalMovement;
	}

	public int getVerticalMovement() {
		return VerticalMovement;
	}

	public void setVerticalMovement(int verticalMovement) {
		VerticalMovement = verticalMovement;
	}

	public int getObservationId() {
		return ObservationId;
	}

	public void setObservationId(int observationId) {
		ObservationId = observationId;
	}

}
