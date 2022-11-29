package com.bitacademy.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.jblog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
}
