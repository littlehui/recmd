package com.xhui.recmd.zk.composer;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;

/**
 * Created by littlehui on 2016/10/15 0015.
 */
public class BindListenerForwardComposer<T extends Component, B extends ViewBean> extends GenericForwardComposer<T> {

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose((T) comp);
        bindListener(comp);
    }

    protected void bindListener(Component component) {
        // do nothing
    }

}
