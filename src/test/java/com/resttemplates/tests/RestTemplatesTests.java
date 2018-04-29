package com.resttemplates.tests;

import static org.junit.Assert.assertNotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
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
	public void testCreateCustomer() { // method to test post if working fine.
		String api_url = ROOT_URL + "/customers/";
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> customer = new HashMap<>();
		customer.put("firstname", "ram");
		customer.put("lastname", "chandra");
		JsonNode jsonNode = restTemplate.postForObject(api_url, customer, JsonNode.class);
		System.out.println(jsonNode);
		assertNotNull(jsonNode);
	}

	@Test
	public void testUpdateCustomer() {
		String api_url = ROOT_URL + "/customers/";
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> customer = new HashMap<>();
		customer.put("firstname", "Pushkar");
		customer.put("lastname", "Rawat");
		JsonNode jsonNode = restTemplate.postForObject(api_url, customer, JsonNode.class);
		System.out.println("Respomse: " + jsonNode);
		String customerUrl = jsonNode.get("customer_url").textValue();
		String customerID = customerUrl.split("/")[3];

		customer.put("firstname", "Pushkar_updated");
		customer.put("lastname", "Rawat_updated");
		restTemplate.put(api_url + customerID, customer, JsonNode.class);
		System.out.println("Respomse: " + restTemplate.getForObject(api_url + customerID, JsonNode.class));
	}

	@Test
	public void testUpdateCustomerViaPatch() {
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		String api_url = ROOT_URL + "/customers/";
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		Map<String, Object> customerMap = new HashMap<>();
		customerMap.put("firstname", "Pushkar");
		customerMap.put("lastname", "Rawat");
		JsonNode jsonNode = restTemplate.postForObject(api_url, customerMap, JsonNode.class);
		System.out.println("Respomse: " + jsonNode);
		String customerUrl = jsonNode.get("customer_url").textValue();
		String customerID = customerUrl.split("/")[3];

		customerMap.put("firstname", "Pushkar_updated");
		customerMap.put("lastname", "Rawat_updated");

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(customerMap, headers);

		JsonNode updatedNode=restTemplate.patchForObject(api_url + customerID, httpEntity, JsonNode.class);
		System.out.println("response: "+updatedNode);
		
	}

}
