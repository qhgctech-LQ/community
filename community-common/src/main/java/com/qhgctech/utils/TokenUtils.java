package com.qhgctech.utils;


import java.util.UUID;

import com.qhgctech.contains.Constants;


public class TokenUtils {
 
	
	 public static String getToken(){
		 return Constants.USER_TOKEN+UUID.randomUUID();
	 }
	
	
}
