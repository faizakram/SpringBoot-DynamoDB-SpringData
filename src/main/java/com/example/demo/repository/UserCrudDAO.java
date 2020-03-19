package com.example.demo.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.example.demo.User;
@Repository
public class UserCrudDAO {
	@Autowired
	private DynamoDBMapper dynamoDBMapper;

	public List<User> getFilterData(String data) {
		Map<String, AttributeValue> eav = new HashMap<>();
		eav.put(":val1", new AttributeValue().withS(data));
		DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<User>()
				.withKeyConditionExpression("userName =:val1")
				.withExpressionAttributeValues(eav);
		return dynamoDBMapper.query(User.class, queryExpression);
	}
}
