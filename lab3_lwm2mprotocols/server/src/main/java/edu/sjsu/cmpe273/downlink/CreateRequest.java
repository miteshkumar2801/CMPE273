package edu.sjsu.cmpe273.downlink;

import java.net.URI;
import java.net.URISyntaxException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class CreateRequest {

	private int objectId;
	private int ACLobjectId;
	private int objectInstanceId;
	private int ACL;
	private int owner;
	
	public CreateRequest(){
		this.objectId=10;
		this.ACLobjectId=10;
		this.objectInstanceId=0;
		this.ACL=11010;
		this.owner=101;
	}
	
	public int getObjectId() {
		return objectId;
	}
	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}
	public int getACLObjectId() {
		return ACLobjectId;
	}
	public void setACLObjectId(int ACLobjectId) {
		this.ACLobjectId = ACLobjectId;
	}
	public int getObjectInstanceId() {
		return objectInstanceId;
	}
	public void setObjectInstanceId(int objectInstanceId) {
		this.objectInstanceId = objectInstanceId;
	}
	public int getACL() {
		return ACL;
	}
	public void setACL(int ACL) {
		this.ACL = ACL;
	}
	public int getowner() {
		return owner;
	}
	public void setowner(int owner) {
		this.owner = owner;
	}
		void sendCreateRequest() throws URISyntaxException{

		Client client = Client.create();
		String output = new String();

		String payload="/"+objectId+"/create"+"?ACLobjectId="+ACLobjectId+
				"&objectInstanceId="+objectInstanceId+"&ACL="+ACL+"&owner="+owner;
		String clientURI="http://localhost:8082/client-0.0.1-SNAPSHOT/lwm2m";
		String uri_temp=clientURI+payload;
		
		URI uri1 = new URI(uri_temp);

		System.out.println("Create Request to Client: " + uri1);

		WebResource webResource = client.resource(uri1);

		ClientResponse response = webResource.type("application/json").post(ClientResponse.class);

		output = response.getEntity(String.class);
		System.out.println("Create Response : " + output);
		
		if (response.getStatus() == 201) {
			System.out.println("create Testing Successful");
		} else {
			throw new RuntimeException("Error while Discovering : HTTP error code : " + response.getStatus());
		}
	}
}
	