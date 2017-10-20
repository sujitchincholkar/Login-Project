package com.bridgelabz.controller;
import com.bridgelabz.model.User;

public class Validator {
static boolean signUpValidate(User user){
	boolean isValid=true;
	String emailPattern = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
	String  mobilePattern="^((\\+)?(\\d{2}[-]))?(\\d{10})?$";
	if(!user.getEmail().matches(emailPattern)){
		isValid=false;
	}
	if(!user.getMobileno().matches(mobilePattern)){
		isValid=false;
	}
	if(user.getPassword().length()<8){
		isValid=false;
	}
	return isValid;
}
}
