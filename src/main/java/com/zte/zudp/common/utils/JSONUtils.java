package com.zte.zudp.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zte.zudp.common.persistence.entity.ables.Propertyable;
import com.zte.zudp.common.exception.BaseException;
import com.zte.zudp.common.persistence.entity.TreeEntity;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-07-06.
 */
public class JSONUtils {

    public static String parse(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        throw new BaseException("fail parse object to json");
    }

    /**
     * 将一个拥有树结构的实体类集合转为一个 ztree 格式的 List 集合
     * @param entities 树结构的实体类
     * @return ztree 格式的List集合
     */
    public static List<Map<String, Object>>  ztree(List<? extends TreeEntity> entities) {
        List<Map<String, Object>> result = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(entities)) {
            Map<String, Object> map;
            for (TreeEntity entity : entities) {
                map = new HashMap<>();
                map.put("id", entity.getId());
                map.put("pId", entity.getParentId());
                map.put("pIds", entity.getParentIds());
                map.put("name", entity.getTreeName());

                result.add(map);
            }
        }

        return result;
    }

    /**
     * 将一个实现了 Propertyable 接口的对象转为一个 键值对 的集合，默认的键值对名字就是 key、value
     * @param list 实现了 Propertyable 接口的对象集合
     * @return 键值对 集合
     * @see JSONUtils#toKeyValue(List, String, String)
     */
    public static List<Map<String, Object>> toKeyValue(List<? extends Propertyable> list) {
        return toKeyValue(list, "key", "value");
    }

    /**
     * 将一个实现了 Propertyable 接口的对象转为一个 键值对 的集合
     * @param list 实现了 Propertyable 接口的对象集合
     * @param keyName 键名
     * @param valueName 值的名字
     * @return 键值对 集合
     */
    public static List<Map<String, Object>> toKeyValue(List<? extends Propertyable> list,
                                                       String keyName, String valueName) {
        List<Map<String, Object>> result = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(list)) {
            Map<String, Object> map;
            for (Propertyable property : list) {
                map = new HashMap<>();
                map.put(keyName, property.key());
                map.put(valueName, property.value());

                result.add(map);
            }
        }

        return result;
    }
}
