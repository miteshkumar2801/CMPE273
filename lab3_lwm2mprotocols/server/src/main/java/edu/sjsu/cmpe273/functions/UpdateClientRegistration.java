package edu.sjsu.cmpe273.functions;

import com.mongodb.BasicDBObject;

import edu.sjsu.cmpe273.Server;

public class UpdateClientRegistration {

	public String updateRegistrationInDB(String registrationId, String lifeTime) {

		BasicDBObject searchQuery = new BasicDBObject();

		searchQuery.put("registrationId", registrationId);

		BasicDBObject newDocument = new BasicDBObject();
		newDocument.append("$set", new BasicDBObject().append("lifeTime", lifeTime));

		Server.registrationInfo.update(searchQuery, newDocument);

		return "Success";
	}

}
