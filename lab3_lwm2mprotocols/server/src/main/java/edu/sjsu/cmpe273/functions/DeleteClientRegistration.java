package edu.sjsu.cmpe273.functions;

import com.mongodb.BasicDBObject;

import edu.sjsu.cmpe273.Server;

public class DeleteClientRegistration {

	public String deleteRegistrationInDB(String registrationId) {

		BasicDBObject query = new BasicDBObject();
		query.append("registrationId", registrationId);

		Server.registrationInfo.remove(query);

		return "Success";
	}

}
