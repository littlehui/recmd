package com.xhui.recmd.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Title: Response.java 
* @Description: 前后端交互类
* @Company : cyou
* @author songchen  
* @date 2012-7-6 下午4:53:30 
* @version V1.0
 */
public class Response<T> implements Serializable {
	
	//成功
	public static final String RESULT_SUCCESS = "success";
	//未登录, 或者roleId获取失败, 典型的处理方式是显示完信息跳转到登录页
	public static final String RESULT_LOGIN = "login";
	//业务规则失败或者业务异常(自己在自己的程序各层抛出的异常), 比如扣款时余额不足
	public static final String RESULT_FAILURE = "failure";
	//表单格式验证失败, 表单业务规则验证失败, 典型的处理方式是显示完信息跳转回表单页
	public static final String RESULT_INPUT = "input";
	//可以预见但是不能处理的异常, 如SQLException, IOException等等
	public static final String RESULT_ERROR = "error";
	
	
	/**
	 * 获取成功的返回
	 * @return
	 */
	public static <M> Response<M> getSuccessResponse(){
		Response<M> response = new Response<M>();
		response.setResult(RESULT_SUCCESS);
		return response;
	}

    public static <M> Response<M> getSuccessResponse(M data){
        Response<M> response = new Response<M>();
        response.setData(data);
        response.setResult(RESULT_SUCCESS);
        return response;
    }

    public static <M> Response<M> getSuccessResponse(String message){
        Response<M> response = getSuccessResponse();
        response.setMessage(message);
        return response;
    }
	
	public static <M> Response<M> getInputFailedResponse(){
		Response<M> response = new Response<M>();
		response.setResult(RESULT_INPUT);
		return response;
	}
	
	public static <M> Response<M> getInputFailedResponse(String message) {
		Response<M> resp = Response.getInputFailedResponse();
		resp.setMessage(message);
		return resp;
	}

    public static <M> Response<M> getFailedResponse(M data){
        Response<M> response = new Response<M>();
        response.setData(data);
        response.setResult(RESULT_FAILURE);
        return response;
    }
	
	public static <M> Response<M> getFailedResponse(String msg) {
		Response<M> resp = getFailedResponse();
		resp.setMessage(msg);
		return resp;
	}
	public static <M> Response<M> getFailedResponse(){
		Response<M> response = new Response<M>();
		response.setResult(RESULT_FAILURE);
		return response;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -2049439550666128636L;

	private String result;
	private List<String> messages;
	private Map<String, String> fieldErrors;
	private List<String> errors;
	private T data;

	public Response(){
		messages = new ArrayList<String>(1);
		errors = new ArrayList<String>(1);
		fieldErrors = new HashMap<String, String>();
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<String> getMessages() {
		return messages;
	}
	/**
	 * 设置业务错误信息
	 * @param message
	 */
	public void setMessage(String message) {
		this.messages.clear();
		this.messages.add(message);
	}

	public Map<String, String> getFieldErrors() {
		return fieldErrors;
	}
	/**
	 * 添加字段错误
	 * @param key 
	 * @param value
	 */
	public void addFieldErrors(String key,String value) {
		this.fieldErrors.put(key, value);
	}

	public List<String> getErrors() {
		return errors;
	}
	/**
	 * 设置系统错误信息
	 * @param error
	 */
	public void setError(String error) {
		this.errors.clear();
		this.errors.add(error);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}


}
