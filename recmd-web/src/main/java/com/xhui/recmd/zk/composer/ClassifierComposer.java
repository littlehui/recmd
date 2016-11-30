package com.xhui.recmd.zk.composer;

import com.xhui.recmd.zk.bean.ClassifierVo;
import com.xhui.recmd.zk.bean.ResultPage;
import com.xhui.recmd.zk.services.ClassifierService;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.*;

import java.util.List;

/**
 * Created by littlehui on 2016/11/20 0020.
 */
public class ClassifierComposer extends BindListenerForwardComposer {

    @Getter
    @Setter
    Window classiferWin;

    @Getter
    @Setter
    Textbox searchBox;

    @Getter
    @Setter
    Button searchButton;

    @Setter
    @Getter
    Listbox classifierListBox;

    @Getter
    @Setter
    Button searchClassifierButton;

    @Getter
    @Setter
    Textbox searchKeyBox;

    private ClassifierService classifierService = ClassifierService.getInstance();


    protected void bindListener(Component component) {

    }

    public void onCreate$classiferWin() {
        showAll();
    }

    public void onClick$searchButton() {
        if ("".equals(searchBox.getValue()) || null == searchBox.getValue()) {
            showAll();
            return ;
        }
        ResultPage<ClassifierVo> resultPage = classifierService.queryByIp(searchBox.getValue());
        showClassifierPage(resultPage);
    }

    private void showClassifierPage(ResultPage<ClassifierVo> resultPage) {
        ListModelList classifierListModel = new ListModelList(resultPage.getResultList());
        classifierListBox.setModel(classifierListModel);
        classifierListBox.setPageSize(resultPage.getResultPageSize());
        classifierListBox.setActivePage(resultPage.getResultPageNumber());
        classifierListBox.setSelectedIndex(resultPage.getSelectedIndex());
        classifierListBox.renderAll();
        classifierListBox.setSelectedIndex(resultPage.getSelectedIndex());
    }

    public void showClassifiers(List<ClassifierVo> classifierVos) {
        ListModelList classifierListModel = new ListModelList(classifierVos);
        classifierListBox.setModel(classifierListModel);
        classifierListBox.renderAll();
    }

    private void showAll() {
        List<ClassifierVo> classifierVoList = classifierService.queryAllClassifiers();
        showClassifiers(classifierVoList);
    }

    public void onClick$searchClassifierButton() {
        if (searchKeyBox.getValue() == null || "".equals(searchKeyBox.getValue())) {
            showAll();
            return;
        }
        List<ClassifierVo> classifierVoList = classifierService.queryByRowKey(searchKeyBox.getValue());
        showClassifiers(classifierVoList);
    }
}

