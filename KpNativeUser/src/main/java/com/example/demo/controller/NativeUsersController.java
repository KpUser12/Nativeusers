package com.example.demo.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.NativeUserPassword;
import com.example.demo.bean.NativeUsers;
import com.example.demo.service.NativeUsersServices;

@RestController
@RequestMapping("/nativeusers/api/v1/{tenantId}")
public class NativeUsersController {

	@Autowired
	NativeUsersServices service;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/createuser")
	public ResponseEntity<?> createUser(@PathVariable("tenantId") String tenantID,
			@RequestBody NativeUsers nativeUser) {
		NativeUsers response;
		try {
			response = service.insertUser(tenantID, nativeUser);
			return new ResponseEntity<>(response, null,  HttpStatus.OK);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSONObject res = new JSONObject();
		
			try {
				res.put("exception", e.getLocalizedMessage());
				return new ResponseEntity<>(res, null,  HttpStatus.EXPECTATION_FAILED);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		}
		
		
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/changepassword")
	public ResponseEntity<?> changeUserPassword(@PathVariable("tenantId") String tenantID, 
			@RequestBody NativeUserPassword nativeUserPassword) {
		
		boolean response;
		try {
			response = service.changeUserPassword(tenantID, 
					nativeUserPassword.getUserName(), nativeUserPassword.getPassword());
			return new ResponseEntity<>(response, null,  HttpStatus.OK);
			
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSONObject res = new JSONObject();
			
			try {
				res.put("exception", e.getLocalizedMessage());
				return new ResponseEntity<>(res, null,  HttpStatus.EXPECTATION_FAILED);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		}
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/validateandgetuser")
	public ResponseEntity<?> validatePasswordAndGetUser(@PathVariable("tenantId") String tenantID,
			@RequestBody NativeUserPassword nativeUserPassword) {
		NativeUsers response;
		try {
			System.out.println("validatePasswordAndGetUser .... ");
			response = service.validatePasswordAndGetUser(tenantID, 
					nativeUserPassword.getUserName(), nativeUserPassword.getPassword());
			return new ResponseEntity<>(response, null,  HttpStatus.OK);
			
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSONObject res = new JSONObject();
			
			try {
				res.put("exception", e.getLocalizedMessage());
				return new ResponseEntity<>(res, null,  HttpStatus.EXPECTATION_FAILED);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		}
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/validatepassword")
	public ResponseEntity<?> validateUserPassword(@PathVariable("tenantId") String tenantID,
			@RequestBody NativeUserPassword nativeUserPassword) {
		boolean response;
		try {
			System.out.println("validateUserPassword .... ");
			response = service.validateUserPassword(tenantID, 
					nativeUserPassword.getUserName(), nativeUserPassword.getPassword());
			System.out.println("validateUserPassword .... response = " + response);
			return new ResponseEntity<>(response, null,  HttpStatus.OK);
			
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSONObject res = new JSONObject();
		
			try {
				res.put("exception", e.getLocalizedMessage());
				return new ResponseEntity<>(res, null,  HttpStatus.EXPECTATION_FAILED);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		}
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/getuser/{userId}")
	public ResponseEntity<?> getUserDetails(@PathVariable("tenantId") String tenantID, 
			@PathVariable("userId") String userID) {
		
		NativeUsers response = service.getUserDetails(tenantID, userID);
		
		return new ResponseEntity<>(response, null,  HttpStatus.OK);
	}
	

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/getalluser")
	public ResponseEntity<?> getAllUsers(@PathVariable("tenantId") String tenantID) {
		
		List<NativeUsers> response = service.getAllUsers(tenantID);
		
		
		return new ResponseEntity<>(response, null,  HttpStatus.OK);
	}
	

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/getuseradminroles/{userId}")
	public ResponseEntity<?> getAllAdminRolesOfUser(@PathVariable("tenantId") String tenantID, 
			@PathVariable("userId") String userID) {
		
		List<String> response = service.getAllAdminRolesOfUser(tenantID, userID);
		
		return new ResponseEntity<>(response, null,  HttpStatus.OK);
	}
	

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/updateuser")
	public ResponseEntity<?> updateUser(@PathVariable("tenantId") String tenantID,
			@RequestBody NativeUsers nativeUser) {
		
		NativeUsers response = service.updateUser(tenantID, nativeUser);
		
		return new ResponseEntity<>(response, null,  HttpStatus.OK);		
	}
	
	

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping(value = "/deleteuser/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("tenantId") String tenantID, 
			@PathVariable("userId") String userID) {
		
		boolean response = service.deleteUser(tenantID, userID);
		
		return new ResponseEntity<>(response, null,  HttpStatus.OK);
		
	}

}
