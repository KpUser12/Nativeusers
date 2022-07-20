package com.example.demo.dao;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import com.example.demo.bean.NativeUsers;



public interface NativeUsersDao {

	public NativeUsers saveUser(String tenantID, NativeUsers nativeUser);
	public boolean changeUserPassword(String tenantID, String userID, String newEncPassword);
	public boolean validateUserPassword(String tenantID, String userID, String password)throws NoSuchAlgorithmException, InvalidKeySpecException;
	public NativeUsers validatePasswordAndGetUser(String tenantID, String userID, String password) throws NoSuchAlgorithmException, InvalidKeySpecException;
	public NativeUsers getUserDetails(String tenantID, String userID);
	public List<NativeUsers> getAllUsers(String tenantID);
	public List<String> getAllAdminRolesOfUser(String tenantID, String userID);
	public void deleteUser(String tenantID, String userID);

}
