package edu.sjsu.cmpe.objects;

public class SensorObject {

	private int objectId;
	private int objectInstanceId;

	private boolean vehicleDetected;

	public SensorObject() {

		this.objectId = 10;
		this.objectInstanceId = 0;
	}

	public SensorObject(boolean preSet) {

		this.objectId = 10;
		this.objectInstanceId = 0;
		this.vehicleDetected = true;
	}

	public String getResourceDescription(int resourceId) {

		switch (resourceId) {
		case 0:
			return "vehicleDetected";
		default:
			return "error";
		}
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

	public int getObjectInstanceId() {
		return objectInstanceId;
	}

	public void setObjectInstanceId(int objectInstanceId) {
		this.objectInstanceId = objectInstanceId;
	}

	public boolean isVehicleDetected() {
		return vehicleDetected;
	}

	public void setVehicleDetected(boolean vehicleDetected) {
		this.vehicleDetected = vehicleDetected;
	}
}
