package com.xhui.recmd.cfg;

import com.xhui.recmd.controller.RecmdController;
import com.jfinal.config.*;
import com.jfinal.render.ViewType;
import com.xhui.recmd.controller.HelloController;

/**
 * Created by littlehui on 2016/8/24.
 */
public class RecmdConfig extends JFinalConfig {
    public void configConstant(Constants me) {
        me.setDevMode(true);
        me.setViewType(ViewType.FREE_MARKER);
        me.setUrlParaSeparator("_");
    }

    public void configRoute(Routes me) {
        me.add("/hello", HelloController.class);
        me.add("/recmd", RecmdController.class);
    }

    public void configPlugin(Plugins me) {
        loadPropertyFile("recmd_service_cfg.properties");
/*        DruidPlugin druidPlugin = new DruidPlugin(getProperty("db.url"),getProperty("db.user"),getProperty("db.password"));
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        me.add(arp);*/
        //arp.addMapping("user", User.class);
    }

    public void configInterceptor(Interceptors me) {

    }

    public void configHandler(Handlers me) {

    }

}
