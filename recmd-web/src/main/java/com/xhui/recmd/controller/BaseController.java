package com.xhui.recmd.controller;

import com.jfinal.core.Controller;
import com.xhui.recmd.utils.JsonUtil;
import com.xhui.recmd.web.Response;

/**
 * Created by littlehui on 2016/11/4.
 */
public class BaseController extends Controller {
    public void renderJson(Object value) {
        renderJson(JsonUtil.toJson(Response.getSuccessResponse(value)));
    }
}
