package com.resttemplates.tests;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

public class RestTemplatesTests {
	// will see some common http method tests for users api

	public static final String ROOT_URL = "https://api.predic8.de:443/shop";

	@Test
	public void testGetCustomers() {
		String api_url = ROOT_URL + "/customers/";
		RestTemplate restTemplate = new RestTemplate();
		JsonNode jsonNode = restTemplate.getForObject(api_url, JsonNode.class);
		System.out.println(jsonNode);
		assertNotNull(jsonNode);
	}
	
	@Test
	public void testCreateCustomer() { //method to test post if working fine.
		String api_url = ROOT_URL + "/customers/";
		RestTemplate restTemplate = new RestTemplate();
		Map<String,Object> customer=new HashMap<>();
		customer.put("firstname","ram");
		customer.put("lastname","chandra");
		JsonNode jsonNode = restTemplate.postForObject(api_url,customer,JsonNode.class);
		System.out.println(jsonNode);
		assertNotNull(jsonNode);	
	}
	
	@Test
	public void testUpdateCustomer(){
		
	}
	
}
