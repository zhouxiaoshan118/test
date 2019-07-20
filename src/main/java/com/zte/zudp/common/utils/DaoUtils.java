package com.zte.zudp.common.utils;

import java.util.ArrayList;
import java.util.List;

import com.zte.zudp.common.persistence.entity.AssociationEntity;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-09.
 */
public class DaoUtils {
    /**
     * 生成一个 一对多 的关联表对象的集合
     *
     * @param one    一 对应的主键
     * @param others 多集合 对应的主键
     * @return 生成一个关联表对象集合
     */
    public static List<AssociationEntity> generatedList(String one, String[] others) {
        List<AssociationEntity> result = new ArrayList<>();

        for (String other : others) {
            result.add(new AssociationEntity(one, other));
        }

        return result;
    }
}
