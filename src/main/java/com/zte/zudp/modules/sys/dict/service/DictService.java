package com.zte.zudp.modules.sys.dict.service;

import com.zte.zudp.common.persistence.service.BusinessService;
import com.zte.zudp.modules.sys.dict.dao.DictDao;
import com.zte.zudp.modules.sys.dict.entity.Dict;
import com.zte.zudp.system.cache.CacheUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 数据字典
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-10.
 */
@Service
@Transactional(readOnly = true)
public class DictService extends BusinessService<Dict> {

    private DictDao dao() {
        return (DictDao) dao;
    }

    @Override
    @Transactional
    public void save(Dict dict) {
        super.save(dict);
        CacheUtils.remove(CacheUtils.DICT_MAP);
    }

    @Override
    @Transactional
    public void delete(String id) {
        super.delete(id);
        CacheUtils.remove(CacheUtils.DICT_MAP);
    }

    @Override
    @Transactional
    public void delete(String[] ids) {
        super.delete(ids);
        CacheUtils.remove(CacheUtils.DICT_MAP);
    }

    public String getDictLabel(String type, String value) {
        Dict dict = new Dict();
        dict.setValue(value);
        dict.setType(type);
        List<Dict> list = dao.findList(dict);
        if (list.size() > 0) {
            return list.get(0).getLabel();
        } else {
            return value;
        }
    }
}
