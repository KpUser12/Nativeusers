package com.example.demo.dao;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.NativeUsers;
import com.example.demo.util.HashPassword;

import com.mongodb.client.MongoClients;


@Repository
public class NativeUsersDaoImpl implements NativeUsersDao{

	@Value("${spring.data.mongodb.host}")
	String host;

	@Value("${spring.data.mongodb.port}")
	String port;
	
	@Override
	public NativeUsers saveUser(String tenantID, NativeUsers nativeUser) {
		// TODO Auto-generated method stub
		
		MongoTemplate mongoTemplate = getConnection(tenantID);
		
		nativeUser.setId(nativeUser.getUserName());
		
		Criteria criteria1 = Criteria.where("tenantId").is(tenantID);
		Criteria criteria2 = Criteria.where("id").is(nativeUser.getUserName());
		Criteria criteria = criteria1.andOperator(criteria2);
		
		Query query = new Query();
		query.addCriteria(criteria);
		NativeUsers existing = mongoTemplate.findOne(query, NativeUsers.class);
		
		if(existing != null) {
			mongoTemplate.findAndReplace(query, nativeUser);
			return mongoTemplate.findOne(query, NativeUsers.class);
		}
		else {
			return mongoTemplate.save(nativeUser);
		}
	}

	@Override
	public boolean changeUserPassword(String tenantID, String userID, 
			String newEncPassword) {
		// TODO Auto-generated method stub
		
		MongoTemplate mongoTemplate = getConnection(tenantID);
		
		Criteria criteria1 = Criteria.where("tenantId").is(tenantID);
		Criteria criteria2 = Criteria.where("id").is(userID);
		Criteria criteria = criteria1.andOperator(criteria2);
		
		Query query = new Query();
		query.addCriteria(criteria);
		NativeUsers existing = mongoTemplate.findOne(query, NativeUsers.class);
		
		if(existing != null) {
				existing.setPassword(newEncPassword);
				mongoTemplate.findAndReplace(query, existing);
				return true;
		}
		
		return false;
	}

	@Override
	public boolean validateUserPassword(String tenantID, String userID, 
			String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// TODO Auto-generated method stub
		MongoTemplate mongoTemplate = getConnection(tenantID);
		
		Criteria criteria1 = Criteria.where("tenantId").is(tenantID);
		Criteria criteria2 = Criteria.where("id").is(userID);
		Criteria criteria = criteria1.andOperator(criteria2);
		System.out.println(" NativeUsersDaoImpl:: validateUserPassword tenantID = " + tenantID);
		System.out.println(" NativeUsersDaoImpl:: validateUserPassword userID = " + userID);
		
		
		
		Query query = new Query();
		query.addCriteria(criteria);
		NativeUsers existing = mongoTemplate.findOne(query, NativeUsers.class);
		
		if(existing != null) {
			System.out.println(" NativeUsersDaoImpl:: validateUserPassword User exist = true");
			System.out.println(" NativeUsersDaoImpl:: validateUserPassword existing password = " + existing.getPassword());
			
			HashPassword passObj = new HashPassword(userID);
			boolean encPassVerified = passObj.validatePassword(password, existing.getPassword());
						
			if(encPassVerified) {
				System.out.println(" NativeUsersDaoImpl:: validateUserPassword password matched");
				return true;
			}
		}
		System.out.println(" NativeUsersDaoImpl:: validateUserPassword password NOT matched");
		return false;
	}
	
	@Override
	public NativeUsers validatePasswordAndGetUser(String tenantID, String userID, String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		// TODO Auto-generated method stub
		MongoTemplate mongoTemplate = getConnection(tenantID);
		
		Criteria criteria1 = Criteria.where("tenantId").is(tenantID);
		Criteria criteria2 = Criteria.where("id").is(userID);
		Criteria criteria = criteria1.andOperator(criteria2);
		System.out.println(" NativeUsersDaoImpl:: validatePasswordAndGetUser tenantID = " + tenantID);
		System.out.println(" NativeUsersDaoImpl:: validatePasswordAndGetUser userID = " + userID);
		
		Query query = new Query();
		query.addCriteria(criteria);
		NativeUsers existing = mongoTemplate.findOne(query, NativeUsers.class);
		
		if(existing != null) {
			System.out.println(" NativeUsersDaoImpl:: validatePasswordAndGetUser User exist = true");
			System.out.println(" NativeUsersDaoImpl:: validatePasswordAndGetUser existing password = " + existing.getPassword());
			
			HashPassword passObj = new HashPassword(userID);
			boolean encPassVerified = passObj.validatePassword(password, existing.getPassword());
						
			if(encPassVerified) {
				System.out.println(" NativeUsersDaoImpl:: validatePasswordAndGetUser password matched");
				return existing;
			}
		}
		System.out.println(" NativeUsersDaoImpl:: validatePasswordAndGetUser password NOT matched");
		return null;
	}

	@Override
	public NativeUsers getUserDetails(String tenantID, String userID) {
		// TODO Auto-generated method stub
		MongoTemplate mongoTemplate = getConnection(tenantID);
		
		
		Criteria criteria1 = Criteria.where("tenantId").is(tenantID);
		Criteria criteria2 = Criteria.where("id").is(userID);
		Criteria criteria = criteria1.andOperator(criteria2);
		
		Query query = new Query();
		query.addCriteria(criteria);
		NativeUsers existing = mongoTemplate.findOne(query, NativeUsers.class);
		
		if(existing != null) {
			return existing;
		}
		else {
			return null;
		}
	}

	@Override
	public List<NativeUsers> getAllUsers(String tenantID) {
		// TODO Auto-generated method stub
		MongoTemplate mongoTemplate = getConnection(tenantID);
		return mongoTemplate.findAll(NativeUsers.class);
	}

	@Override
	public List<String> getAllAdminRolesOfUser(String tenantID, String userID) {
		// TODO Auto-generated method stub
		MongoTemplate mongoTemplate = getConnection(tenantID);
		
		
		Criteria criteria1 = Criteria.where("tenantId").is(tenantID);
		Criteria criteria2 = Criteria.where("id").is(userID);
		Criteria criteria = criteria1.andOperator(criteria2);
		
		Query query = new Query();
		query.addCriteria(criteria);
		NativeUsers existing = mongoTemplate.findOne(query, NativeUsers.class);
		
		if(existing != null) {
			return existing.getAdminRoles();
		}
		else {
			return null;
		}
	}

	@Override
	public void deleteUser(String tenantID, String userID) {
		// TODO Auto-generated method stub
		MongoTemplate mongoTemplate = getConnection(tenantID);
		
		
		Criteria criteria1 = Criteria.where("tenantId").is(tenantID);
		Criteria criteria2 = Criteria.where("id").is(userID);
		Criteria criteria = criteria1.andOperator(criteria2);
		
		Query query = new Query();
		query.addCriteria(criteria);
		mongoTemplate.findAndRemove(query, NativeUsers.class);
	}
	
	private MongoTemplate getConnection(String dbName) {
		System.out.println("RuleDaoImpl.getConnection()");
		String connectionURL = "mongodb://"+host+":"+port;
		System.out.println("RuleDaoImpl.getConnection() connectionURL = " + connectionURL);
		MongoTemplate mongoTemplate = new MongoTemplate(MongoClients.create(connectionURL), dbName);
		System.out.println(" NativeUsersDaoImpl:: getConnection() CONNECTED...");
		return mongoTemplate;
	}

	


}
