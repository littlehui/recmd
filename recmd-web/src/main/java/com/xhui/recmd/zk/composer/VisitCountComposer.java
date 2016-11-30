package com.xhui.recmd.zk.composer;

import com.xhui.recmd.zk.bean.VisitCountVo;
import com.xhui.recmd.zk.services.VisitCountService;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by littlehui on 2016/11/20 0020.
 */
public class VisitCountComposer extends BindListenerForwardComposer {


    @Getter
    @Setter
    Window visitCountListWin;

    @Getter
    @Setter
    Textbox searchBox;

    @Getter
    @Setter
    Button searchButton;

    @Getter
    @Setter
    Listbox visitCountListbox;

    VisitCountService visitCountService = new VisitCountService();

    public void onCreate$visitCountListWin () {
        showAll();
    }

    public void onClick$searchButton() {
        if (searchBox.getValue() == null || searchBox.getValue().equals("")) {
            showAll();
            return;
        }
        List<VisitCountVo>  visitCountVos = visitCountService.queryByIP(searchBox.getValue());
        if (visitCountVos == null) {
            visitCountVos = new ArrayList<>();
        }
        show(visitCountVos);
    }

    private void showAll() {
        List<VisitCountVo>  visitCountVos = visitCountService.queryAll();
        show(visitCountVos);
    }

    private void show(List<VisitCountVo> visitCountVoList) {
        ListModelList value = new ListModelList(visitCountVoList);
        visitCountListbox.setModel(value);
        visitCountListbox.renderAll();
    }
}
