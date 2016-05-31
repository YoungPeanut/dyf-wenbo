package com.dyf.wenbo.scm;

import com.dyf.wenbo.model.TypeWrapper;
import com.hyphenate.easeui.domain.EaseEmojicon;
import com.zhonghua.dileber.mvp.scm.INetListener;

/**
 * Created by  on 16/1/17.
 */
public interface IActiveScm {

    void setType(Integer id, Integer leixing, final INetListener<Integer> iNetListener) ;
    void getType(Integer id, INetListener<TypeWrapper> iNetListener);

}
