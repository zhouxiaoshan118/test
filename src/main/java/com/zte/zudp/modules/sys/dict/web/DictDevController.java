package com.zte.zudp.modules.sys.dict.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zte.zudp.common.persistence.web.RESTfulController;
import com.zte.zudp.common.persistence.web.permission.Permissions;
import com.zte.zudp.modules.sys.dict.entity.Dict;
import com.zte.zudp.modules.sys.dict.service.DictService;

/**
 * 系统字典表设置，非业务字典，只可查看与新增
 */
@RestController
@RequestMapping("${sys.url.admin}/sys/dict/dev")
public class DictDevController extends RESTfulController<Dict> {

    @Override
    protected void init() {
        super.init();
        this.setResourceIdentity(Permissions.DICT);
        setDefaultViewPath("admin/sys/dictDev");

        setListAlsoCommonData(true);
    }

    @Override
    protected void setCommonData(Dict dict) {
        super.setCommonData(dict);
        dict.setStatus(true);
    }

    public DictService service() {
        return (DictService) service;
    }

    // ---- 禁止以下 URL 的执行 ---------------------------------------------------------------------

    @Override
    public ResponseEntity<Dict> update(String id, Dict dict, BindingResult result) {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<Dict> delete(String t) {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<Dict> deleteInBatch(String[] ids) {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
