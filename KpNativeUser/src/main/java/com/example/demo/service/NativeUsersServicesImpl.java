package com.example.demo.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.bean.NativeUsers;
import com.example.demo.dao.NativeUsersDao;
import com.example.demo.util.HashPassword;

@Service
public class NativeUsersServicesImpl implements NativeUsersServices{


	@Autowired
	private NativeUsersDao daoService;
	
	@Override
	public NativeUsers insertUser(String tenantID, NativeUsers nativeUser) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// TODO Auto-generated method stub
		nativeUser.setTenantId(tenantID);
		String orgPassword = nativeUser.getPassword();
		HashPassword passObj = new HashPassword(nativeUser.getUserName());
		String encPass = passObj.getHashedPassword(orgPassword);
		nativeUser.setPassword(encPass);
		
		return daoService.saveUser(tenantID, nativeUser);
		
	}

	@Override
	public boolean changeUserPassword(String tenantID, String userID, String newPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// TODO Auto-generated method stub
		HashPassword passObj = new HashPassword(userID);
		String encPass = passObj.getHashedPassword(newPassword);
		
		return daoService.changeUserPassword(tenantID, userID, encPass);
		
	}

	@Override
	public boolean validateUserPassword(String tenantID, String userID, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// TODO Auto-generated method stub
//		HashPassword passObj = new HashPassword(userID);
//		String encPass = passObj.getHashedPassword(password);
//		System.out.println(" NativeUsersServicesImpl:: validateUserPassword password = " + password);
//		System.out.println(" NativeUsersServicesImpl:: validateUserPassword encPass = " + encPass);
//		
		return daoService.validateUserPassword(tenantID, userID, password);
	}
	
	@Override
	public NativeUsers validatePasswordAndGetUser(String tenantID, String userID, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// TODO Auto-generated method stub
		return daoService.validatePasswordAndGetUser(tenantID, userID, password);
	}

	@Override
	public NativeUsers getUserDetails(String tenantID, String userID) {
		// TODO Auto-generated method stub
		NativeUsers userDet = daoService.getUserDetails(tenantID, userID);
		if(userDet != null) {
			userDet.setPassword("******");
			return userDet;
		}
		return userDet;
	}

	@Override
	public List<NativeUsers> getAllUsers(String tenantID) {
		// TODO Auto-generated method stub
		List<NativeUsers> allUsers = new ArrayList<NativeUsers>();
		List<NativeUsers> listallUsers = daoService.getAllUsers(tenantID);
		for(NativeUsers nativeUser : listallUsers) {
			nativeUser.setPassword("******");
			allUsers.add(nativeUser);
		}
		return allUsers;
	}

	@Override
	public List<String> getAllAdminRolesOfUser(String tenantID, String userID) {
		// TODO Auto-generated method stub
		return daoService.getAllAdminRolesOfUser(tenantID, userID);
	}

	@Override
	public NativeUsers updateUser(String tenantID, NativeUsers nativeUser) {
		// TODO Auto-generated method stub
		nativeUser.setTenantId(tenantID);
		NativeUsers userDet = daoService.getUserDetails(tenantID, nativeUser.getId());
		nativeUser.setPassword(userDet.getPassword());
		return daoService.saveUser(tenantID, nativeUser);
	}

	@Override
	public boolean deleteUser(String tenantID, String userID) {
		// TODO Auto-generated method stub
		daoService.deleteUser(tenantID, userID);
		return true;
	}

}
