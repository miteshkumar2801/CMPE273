package edu.sjsu.cmpe273.downlink;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class DownlinkClient {

	static final String endPointName = "6d26bbc3=a66a=455e=b089=3bb2f8ac915e"; // Pre
																				// Installed
																				// EndPointName
																				// UUID.randomUUID()
	static String registrationId; // Fetched from server during bootstrap

	public static void main(String[] args) throws URISyntaxException, InterruptedException {

		// ===============Discover=====================//
		Client client = Client.create();
		String output = new String();

		System.out.println("\nWaiting for discovery to start...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		URI uri1 = new URI("http://localhost:8080/server-0.0.1-SNAPSHOT/lwm2m/3/0/discover");
		//URI uri1 = new URI("http://localhost:8080/server-0.0.1-SNAPSHOT/lwm2m/get");
		//URI uri1 = new URI("http://localhost:8080/server-0.0.1-SNAPSHOT/lwm2m/bs");


		System.out.println("Discover request : " + uri1);

		WebResource webResource = client.resource(uri1);

		ClientResponse response = webResource.type("text/plain").get(ClientResponse.class);

		output = response.getEntity(String.class);
		System.out.println("Successfully Discovered : " + output);

		if (response.getStatus() == 200) {
		} else {
			throw new RuntimeException("Error while Discovering : HTTP error code : " + response.getStatus());
		}
		

		
		// ===============Read=====================//


		
		ReadRequest r1 = new ReadRequest(10, 1, 2);
		ReadRequest r2 = new ReadRequest(6, 1);

		System.out.println("\nWaiting for read request #1 to start...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		r1.sendReadRequest();
		System.out.println("Read Request #1 successful");

		System.out.println("\nWaiting for read request #2 to start...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		r2.sendReadRequest();

		System.out.println("Read Request #2 successful");
		
		// ================ Write Request ==============//
		
		System.out.println("\nWaiting for write request to start...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		new WriteRequest(1, 1, 0).sendWriteRequest();
		System.out.println("Write Request successful");

		
		// ===============Execute==============//
		System.out.println("\nWaiting for execute request to start...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Client client1 = Client.create();
		String output1 = new String();

		URI uri11 = new URI("http://localhost:8082/client-0.0.1-SNAPSHOT/lwm2m/10/0/5/execute");

		System.out.println("Execute request : " + uri11);

		WebResource webResource1 = client1.resource(uri11);

		ClientResponse response1 = webResource1.type("application/json").post(ClientResponse.class);

		output1 = response1.getEntity(String.class);
		System.out.println("Successfully Executed : " + output1);

		if (response1.getStatus() == 201) {
		} else {
			throw new RuntimeException("Error while Executing : HTTP error code : " + response.getStatus());
		}
		
		// ===============create=====================//

		System.out.println("\nWaiting for create request to start...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		CreateRequest cr1 = new CreateRequest();
		cr1.sendCreateRequest();

		System.out.println("Create Request successful");

		// ===============Delete=====================//
		System.out.println("\nWaiting for delete request to start...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		DeleteRequest dr1 = new DeleteRequest();
		dr1.sendDeleteRequest();
		System.out.println("Delete Request successful");

		// =================== Write Attribute ====================//
		System.out.println("\nWaiting for Write Attribute request to start...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		WriteAttributeRequest ob1 = new WriteAttributeRequest(10, 0, 0, 5); // resource:/10/0/0
																			// pmin=5
																			// seconds
		ob1.sendWriteAttributeRequest();

		System.out.println("Write Attribute Request successful");

		// =================== Observation Request ==================//
		System.out.println("\nWaiting for Observation request to start...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		URI uri_observe = new URI("http://localhost:8082/client-0.0.1-SNAPSHOT/lwm2m/10/0/0/observe?tokenNo=777");

		System.out.println("Observation Request at uri : " + uri_observe);

		Client client_observe = Client.create();
		WebResource webresource_observe = client_observe.resource(uri_observe);

		ClientResponse resp_observe = webresource_observe.type("application/json").get(ClientResponse.class);

		String output_observe = resp_observe.getEntity(String.class);
		System.out.println("Observe Request reply : " + output_observe);

		if (response.getStatus() == 200) {
			System.out.println("Observe Testing Successful");
		} else {
			throw new RuntimeException("Error while Observing : HTTP error code : " + response.getStatus());
		}

		System.out.println("Observation Request successful");

		
		// =================== Cancel Observation ==================//

		System.out.println("\nWaiting for Cancel Observation request to start...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		URI uri2 = new URI("http://localhost:8082/client-0.0.1-SNAPSHOT/lwm2m/10/0/0/cancel");

		System.out.println("Cancel request started at uri : " + uri2);

		WebResource webResource2 = client.resource(uri2);

		ClientResponse response2 = webResource2.type("application/json").post(ClientResponse.class);

		output = response2.getEntity(String.class);
		System.out.println("Cancel Response : " + output);

		if (response.getStatus() == 200) {
		} else {
			throw new RuntimeException("Error while Discovering : HTTP error code : " + response.getStatus());
		}

		System.out.println("Cancel Observation Request successful");

		
	}

}
