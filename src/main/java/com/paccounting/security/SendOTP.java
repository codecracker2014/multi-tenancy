package com.paccounting.security;

import com.fasterxml.jackson.core.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.JSONObject;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;


/*
* This code is based on jersey-client library.
* For gradle based project use compile 'com.sun.jersey:jersey-client:1.18.4'
* You can also download the jar and add it to you project.
* */
public class SendOTP {

  //Base URL
  public static String baseUrl = "https://sendotp.msg91.com/api";

  // Your application key
  public static String applicationKey = "Iy3qS74dy5qYAE392kwFYRf6sn2ODCbHRxoO4UE-zj3whWaZ1ceAgG-5bph6hBcrbpAMsUmqrIoMvgu7MQJA5cg5wBqcXYi2jnGqp1bjlZSylbtCmIm5Q4SBcdK3ry2poaEHOanF2XG5uddmaYEhkE6MYHVMV0HK-RXShrL8yA0=";

  /*
  * This function is used to send OTP message on mobile number
  * */
  public static int generateOTP(String countryCode, String mobileNumber){
    try {
      Client client = Client.create();
      String Url  = baseUrl+"/generateOTP";
      WebResource webResource = client.resource(Url);

      HashMap<String, String> requestBodyMap = new HashMap();
      requestBodyMap.put("countryCode",countryCode);
      requestBodyMap.put("mobileNumber",mobileNumber);
      requestBodyMap.put("getGeneratedOTP","true");
      JSONObject requestBodyJsonObject = new JSONObject(requestBodyMap);
      String input = requestBodyJsonObject.toString();

      ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
        .header("application-Key", applicationKey)
        .post(ClientResponse.class, input);

      String json = response.getEntity(String.class);
     
      JSONObject jsonObject=new JSONObject(json);
      JSONObject jsonObject2=jsonObject.getJSONObject("response");
      System.out.println(jsonObject.toString()+","+jsonObject.getString("status")+jsonObject2.getString("code"));
      if("success".equals(jsonObject.getString("status"))&&"OTP_SENT_SUCCESSFULLY".equals(jsonObject2.getString("code")))
      return jsonObject2.getInt("oneTimePassword");
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
	return -1;
  }
  
  /*
  * This function is used to send OTP message on mobile number
  * */
  public static void verifyOTP(String oneTimePassword){
    try {
      //fetch your oneTimePassword from session or db
      //and compare it with the OTP sent from clien
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  

}
