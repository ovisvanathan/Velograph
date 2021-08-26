package com.horus.velograph.service;

public class UserServiceFactory<T> {

	public static UserService getUserService() {
		return new UserService();
	}

}
