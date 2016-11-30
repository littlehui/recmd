package com.xhui.recmd.zk.composer;

import com.alibaba.druid.sql.ast.statement.SQLCreateViewStatement;
import com.alibaba.druid.support.opds.udf.ExportSelectListColumns;
import com.xhui.recmd.core.service.RecmdCoreService;
import com.xhui.recmd.core.union.ColumnGoal;
import com.xhui.recmd.zk.bean.RecmdVo;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by littlehui on 2016/11/20 0020.
 */
public class RecmdComposer extends BindListenerForwardComposer {

    @Getter
    @Setter
    Window recmdListWin;

    @Getter
    @Setter
    Textbox searchBox;

    @Getter
    @Setter
    Button searchButton;

    @Getter
    @Setter
    Listbox recmdListbox;

    RecmdCoreService recmdCoreService = new RecmdCoreService();


    public void onClick$searchButton() {
        List<ColumnGoal> columnGoals = recmdCoreService.getRecmdInfo(searchBox.getValue());
        show(toRecmdVo(columnGoals));
    }

    private void show(List<RecmdVo> recmdVoList) {
        ListModelList listModelList = new ListModelList(recmdVoList);
        recmdListbox.setModel(listModelList);
        recmdListbox.renderAll();
    }

    private List<RecmdVo> toRecmdVo(List<ColumnGoal> columnGoals) {
        List<RecmdVo> result = new ArrayList<>();
        if (columnGoals != null) {
            for (ColumnGoal columnGoal : columnGoals) {
                RecmdVo recmdVo = new RecmdVo();
                recmdVo.setRecmdResource(columnGoal.getColumn());
                recmdVo.setRecmdValue(columnGoal.getGoal());
                result.add(recmdVo);
            }
        }
        return result;
    }
}
