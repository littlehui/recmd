package com.xhui.recmd.zk.composer;

import com.xhui.recmd.zk.bean.IpToIpDis;
import com.xhui.recmd.zk.bean.VisitCountVo;
import com.xhui.recmd.zk.services.IpToIpDisService;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.zul.*;

import java.util.List;

/**
 * Created by littlehui on 2016/11/20 0020.
 */
public class IpToIpDisComposer extends BindListenerForwardComposer {

    @Getter
    @Setter
    Window ipToIpDisListWin;

    @Getter
    @Setter
    Textbox searchBox;

    @Getter
    @Setter
    Button searchButton;

    @Getter
    @Setter
    Listbox ipToIpListBox;

    IpToIpDisService ipToIpDisService = new IpToIpDisService();

    public void onCreate$ipToIpDisListWin() {

    }

    public void onClick$searchButton() {
        List<IpToIpDis> ipToIpDises = ipToIpDisService.queryByIp(searchBox.getValue());
        showIpToIpDis(ipToIpDises);
    }

    private void showIpToIpDis(List<IpToIpDis> ipToIpDisList) {
        ListModelList listModelList = new ListModelList(ipToIpDisList);
        ipToIpListBox.setModel(listModelList);
        ipToIpListBox.renderAll();
    }

}
