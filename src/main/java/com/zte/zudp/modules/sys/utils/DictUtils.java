package com.zte.zudp.modules.sys.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zte.zudp.common.utils.SpringUtils;
import com.zte.zudp.common.utils.StringUtils;
import com.zte.zudp.modules.sys.dict.dao.DictDao;
import com.zte.zudp.modules.sys.dict.entity.Dict;
import com.zte.zudp.system.cache.CacheUtils;

/**
 * 数据字典工具类
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-08-03.
 */
public class DictUtils {

    private static Logger logger = LoggerFactory.getLogger(DictUtils.class);

    private static DictDao dictDao = SpringUtils.getBean(DictDao.class);

    public static Dict get(String id) {
        return dictDao.get(id);
    }

    /**
     * 获取指定 Dict 对象的 label 属性值
     * @param defaultLabel 默认的属性值
     * @return 当查找该 Dict 对象无果时返回默认的属性值
     */
    public static String getDictLabel(String type, String value, String defaultLabel) {
        Dict dict = getDict(type, value);

        return dict != null ? dict.getLabel() : defaultLabel;
    }

    /**
     * 获取指定的 Dict 对象
     * @return 如果查找不到，则会返回 null ，并发出一条警告日志
     */
    public static Dict getDict(String type, String value) {
        Dict result = null;

        List<Dict> dicts = getDict(type);
        for (Dict dict : dicts) {
            if (StringUtils.equals(value, dict.getValue())) {
                result = dict;
                break;
            }
        }

        if (result == null) {
            logger.warn("Not find the dict obj for type is {} and value is {}.", type, value);
        }

        return result;
    }

    /**
     * 获取指定类型的 字典集合
     */
    @SuppressWarnings("unchecked")
    public static List<Dict> getDict(String type) {
        Map<String, List<Dict>> container = (Map<String, List<Dict>>) CacheUtils.get(CacheUtils.DICT_MAP);
        if (container == null) {
            container = loadDicts();
        }

        List<Dict> dicts = container.get(type);
        return dicts != null ? dicts : new ArrayList<Dict>();
    }

    /**
     * 重新加载 字典数据 到缓存中
     * @return 所有字典数据
     */
    public static Map<String, List<Dict>> loadDicts() {
        Map<String, List<Dict>> container = new HashMap<>();

        List<Dict> dicts = dictDao.findList();
        List<Dict> typeDicts;
        for (Dict dict : dicts) {
            typeDicts = container.get(dict.getType());
            if (typeDicts != null){
                typeDicts.add(dict);
            }else{
                typeDicts = new ArrayList<>();
                typeDicts.add(dict);
                container.put(dict.getType(), typeDicts);
            }
        }

        CacheUtils.put(CacheUtils.DICT_MAP, container);

        return container;
    }
}
