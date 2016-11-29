package chm.init;

import chm.controller.UserController;
import chm.model.User;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;

public class ProjectConfig extends JFinalConfig {

    /**
     * ???ó???
     */
    @Override
    public void configConstant(Constants me) {
        loadPropertyFile("datasource.properties");                // ??????????????????????getProperty(...)????
        me.setDevMode(getPropertyToBoolean("devMode", false));
        me.setViewType(ViewType.JSP);   

    }

    /**
     * ????·??
     */
    public void configRoute(Routes me) {
        me.add("/user", UserController.class);

    }

    @Override
    public void configPlugin(Plugins me) {
     // ????C3p0????????????
        C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password").trim());
        me.add(c3p0Plugin);
        
        // ????ActiveRecord???
        ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
        me.add(arp);
        arp.addMapping("user_table", User.class); // ???blog ?? Blog???

    }

    @Override
    public void configInterceptor(Interceptors me) {
        // TODO Auto-generated method stub

    }

    @Override
    public void configHandler(Handlers me) {
        // TODO Auto-generated method stub

    }

}
