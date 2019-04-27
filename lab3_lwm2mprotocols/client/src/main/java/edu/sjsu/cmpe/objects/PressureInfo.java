package edu.sjsu.cmpe.objects;

import java.net.UnknownHostException;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import edu.sjsu.cmpe.ParkingSpaceClient;

public class PressureInfo {

	private int objectId;
	
	private Boolean TypeOfVehicle;
	private double Pressure;
	private Boolean PressureDetected;
	private int observationId;
	
	public PressureInfo(){
		this.objectId=10;
		this.Pressure=0.0;
		this.PressureDetected=false;
		this.TypeOfVehicle=false;
		this.observationId=0;
	}

	public int getObservationId() {
		return observationId;
	}

	public void setObservationId(int observationId) {
		this.observationId = observationId;
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

	public Boolean getTypeOfVehicle() {
		return TypeOfVehicle;
	}

	public void setTypeOfVehicle(Boolean typeOfVehicle) {
		TypeOfVehicle = typeOfVehicle;
	}

	public double getPressure() {
		return Pressure;
	}

	public void setPressure(double pressure) {
		Pressure = pressure;
	}

	public Boolean getPressureDetected() {
		return PressureDetected;
	}

	public void setPressureDetected(Boolean pressureDetected) {
		PressureDetected = pressureDetected;
	}
	
	public void pushToDB(ParkingSpaceClient a1) throws UnknownHostException {

		Gson gson = new Gson();
		String myServer = gson.toJson(this);

		DBObject dbo = (DBObject) JSON.parse(myServer);
			a1.PressureInfo.remove(new BasicDBObject());
		a1.PressureInfo.insert(dbo);
	}
	
}
