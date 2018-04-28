package com.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.domain.User;
import com.domain.UserData;

@Service
public class ApiServicesImpl implements ApiServices {

	private RestTemplate restTemplate;

	public ApiServicesImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<User> getUsers(Integer limit) {
		UserData userData = restTemplate.getForObject("http://apifaketory.com/api/user?limit"+limit,UserData.class);
		
		return userData.getData();
	}

}
