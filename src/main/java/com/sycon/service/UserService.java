package com.sycon.service;

import com.sycon.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
	public User encryptPass(User user);
}
