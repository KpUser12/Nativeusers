package com.example.demo.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.bean.NativeUsers;



public interface NativeUsersServices {
	
	public NativeUsers insertUser(String tenantID, NativeUsers nativeUser) throws NoSuchAlgorithmException, InvalidKeySpecException;
	public boolean changeUserPassword(String tenantID, String userID, String newPassword) throws NoSuchAlgorithmException, InvalidKeySpecException;
	public boolean validateUserPassword(String tenantID, String userID, String password) throws NoSuchAlgorithmException, InvalidKeySpecException;
	public NativeUsers validatePasswordAndGetUser(String tenantID, String userID, String password) throws NoSuchAlgorithmException, InvalidKeySpecException;
	public NativeUsers getUserDetails(String tenantID, String userID);
	public List<NativeUsers> getAllUsers(String tenantID);
	public List<String> getAllAdminRolesOfUser(String tenantID, String userID);
	public NativeUsers updateUser(String tenantID, NativeUsers nativeUser);
	public boolean deleteUser(String tenantID, String userID);

}
