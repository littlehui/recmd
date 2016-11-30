package chm.controller;

import chm.model.User;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;


public class UserController extends Controller{
    public void index() {
        System.out.println("xiaocui");
        Page<User> userPages=User.dao.paginate(1, 10, "select *", "from user_table order by id asc");
        setAttr("userList",userPages.getList() );
        render("/view/user.jsp");
    }
    public void delete(){
      System.out.println("ɾ��");
      User.dao.deleteById(getParaToInt());
      redirect("/user");
    }
    public void add(){
        System.out.println("����");
        getModel(User.class).save();
        redirect("/user");
    }
    public void edit(){
        setAttr("user", User.dao.findById(getParaToInt()));
        render("/view/userEdit.jsp");
    }
    public void update() {
        getModel(User.class).update();
        redirect("/user");
    }
}
