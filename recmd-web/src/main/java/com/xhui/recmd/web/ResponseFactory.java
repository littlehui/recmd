package com.xhui.recmd.web;


/**
 * 常用Response工厂.
 * @Company : cyou
 * @author yangz
 * @date   2012-9-29 下午02:29:23
 */
public class ResponseFactory {
	/**
	 * 获取默认操作成功Response.
	 * @return
	 * @author yangz
	 * @date 2012-9-29 下午02:30:07
	 */
	public static <T> Response<T> getDefaultSuccessResponse(){
		Response<T> response = new Response<T>();
		response.setResult(Response.RESULT_SUCCESS);
		return response;
	}


    public static<T> Response<T> getDefaultLoginResponse() {
        Response<T> response = new Response<T>();
        response.setResult(Response.RESULT_LOGIN);
        response.setMessage("请先登陆。");
        return response;
    }

	public static <T> Response<T> getDefaultSuccessResponse(String msg) {
		Response<T> response = getDefaultSuccessResponse() ;
		response.setMessage(msg) ;
		return response ;
	}

    public static<T> Response<T> getDefaultNeedLoginResponse() {
        Response<T> response = new Response<T>();
        response.setResult("login");
        response.setMessage("需要重新登录。");
        return response;
    }


	/**
	 * 获取默认操作失败Response.
	 * @return
	 * @author yangz
	 * @date 2012-11-14 下午04:37:39
	 */
	public static <T> Response<T> getDefaultFailureResponse(){
		Response<T> response = new Response<T>();
		response.setResult(Response.RESULT_FAILURE);
		return response;
	}
	
	public static <T> Response<T> getDefaultFailureResponse(String msg) {
		Response<T> response= getDefaultFailureResponse() ;
		response.setMessage(msg) ;
		return response ;
	}
	
	/**
	 * 获取默认输入操作失败Response.
	 * @return
	 */
	public static <T> Response<T> getDefaultInputFailureResponse() {
		Response<T> response = new Response<T>() ;
		response.setResult(Response.RESULT_INPUT) ;
		return response ;
	}
	
	public static <T> Response<T> getDefaultInputFailureResponse(String msg) {
		Response<T> response = getDefaultInputFailureResponse() ;
		response.setMessage(msg) ;
		return response ;
	}
}
