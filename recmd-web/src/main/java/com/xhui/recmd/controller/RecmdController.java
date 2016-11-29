package com.xhui.recmd.controller;

import com.xhui.recmd.core.service.RecmdCoreService;
import com.xhui.recmd.core.union.ColumnGoal;

import java.util.List;

/**
 * Created by littlehui on 2016/11/4.
 */
public class RecmdController extends BaseController {

    RecmdCoreService recmdCoreService = new RecmdCoreService();

    public void index() {
        renderText("Hello JFinal World.");
    }

    public void getRecmdInfo() {
        String ip = this.getPara("ip");
        try {
            List<ColumnGoal> result = recmdCoreService.getRecmdInfo(ip);
            renderJson(result);
        } catch (Exception e) {
            e.printStackTrace();
            renderJson("");
        }
    }
}
