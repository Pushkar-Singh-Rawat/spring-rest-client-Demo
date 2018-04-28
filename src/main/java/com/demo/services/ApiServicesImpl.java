package com.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.domain.User;
import com.domain.UserData;

@Service
public class ApiServicesImpl implements ApiServices {

	private RestTemplate restTemplate;
	private String url;

	public ApiServicesImpl(RestTemplate restTemplate, @Value("${user.api.url}") String url) {
		this.restTemplate = restTemplate;
		this.url = url;
	}

	@Override
	public List<User> getUsers(Integer limit) {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(url).queryParam("limit", limit);
		
		UserData userData = restTemplate.getForObject(uriBuilder.toUriString(), UserData.class);

		return userData.getData();
	}

}
