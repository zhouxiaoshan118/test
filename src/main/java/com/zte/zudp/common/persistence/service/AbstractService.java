package com.zte.zudp.common.persistence.service;

import com.zte.zudp.common.persistence.dao.AbstractDao;
import com.zte.zudp.common.persistence.entity.AbstractEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>Service层接口的抽象实现类</p>
 * 增加 批量删除、统计、预插入、预更新方法
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-10.
 */
@Transactional(readOnly = true)
public abstract class AbstractService<T extends AbstractEntity> implements BaseService<T> {

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected AbstractDao<T> dao;

    @Override
    @Transactional()
    public int insert(T t) {
        return dao.insert(t);
    }

    @Override
    @Transactional()
    public void delete(String id) {
        dao.delete(id);
    }

    @Override
    @Transactional
    public int update(T t) {
        return dao.update(t);
    }

    @Override
    public T get(String id) {
        return dao.get(id);
    }

    @Override
    public List<T> findList() {
        return dao.findList();
    }

    @Transactional()
    public void delete(String[] ids) {
        dao.batchDelete(ids);
    }

    public T get(T t) {
        return dao.get(t.getId());
    }

    public List<T> findList(T searchable) {
    	List<T> findList = dao.findList(searchable);
        return findList;
    }

    /**
     * 统计数量
     * @param searchable 查询条件
     * @return 统计数量
     */
    public long count(T searchable) {
        return dao.count(searchable);
    }

    protected void preInsert(T t) {
    }

    protected void preUpdate(T t) {
    }
}
