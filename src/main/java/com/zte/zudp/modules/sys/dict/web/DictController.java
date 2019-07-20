package com.zte.zudp.modules.sys.dict.web;

import com.zte.zudp.common.persistence.web.RESTfulController;
import com.zte.zudp.common.persistence.web.permission.Permissions;
import com.zte.zudp.modules.sys.dict.entity.Dict;
import com.zte.zudp.modules.sys.dict.service.DictService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-12.
 */
@RestController
@RequestMapping("${sys.url.admin}/sys/dict")
public class DictController extends RESTfulController<Dict> {

    @Override
    protected void init() {
        super.init();
        this.setResourceIdentity(Permissions.DICT);
        setDefaultViewPath("/modules/sys/dict");

        setListAlsoCommonData(true);
    }

    public DictService service() {
        return (DictService) service;
    }

    @Override
    protected void setCommonData(Dict dict) {
        super.setCommonData(dict);
        dict.setStatus(false);
    }
    

  
}
