package com.zte.zudp.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import com.zte.zudp.common.persistence.entity.TreeEntity;

/**
 * 数组和集合的操作类
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-27.
 */
public class CollectionUtils {

    /**
     * 判断集合是否为空
     *
     * @return 如果传入的集合为 @{code null} 或者 其大小为0 则返回true，否则返回false
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }

    /**
     * 判断集合是否不为空
     *
     * @return 和 {@link CollectionUtils#isEmpty(Collection)}相反
     * @see CollectionUtils#isEmpty(Collection)
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断数组是否为空
     *
     * @return 如果传入的数组为 @{code null} 或者 其长度为0 则返回true，否则返回false
     */
    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否不为空
     *
     * @return 和 {@link CollectionUtils#isEmpty(Object[])} 相反
     * @see CollectionUtils#isEmpty(Object[])
     */
    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

    public static String[] toArray(List<String> list) {
        String[] result;

        if (isNotEmpty(list)) {
            result = new String[list.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = list.get(i);
            }
        } else {
            result = new String[0];
        }

        return result;
    }

    /**
     * 将一个 String 数组转为对应的list集合
     */
    public static List<String> toList(String[] headers) {
        List<String> result = new ArrayList<>(headers.length);

        if (isEmpty(headers)) {
            return result;
        }

        Collections.addAll(result, headers);

        return result;
    }

    /**
     * 对传入的 集合 进行排序
     *
     * @param before 要排序的 集合
     * @return 如果集合为空，返回一个空的Map对象，否则返回一个以父级id为key，子级TreeSet集合为value的map
     */
    public static <T extends TreeEntity> Map<String, TreeSet<T>> treeMap(List<T> before) {
        if (CollectionUtils.isEmpty(before)) {
            return new HashMap<>();
        }

        Map<String, TreeSet<T>> map = new HashMap<>();
        String parentId;
        TreeSet<T> children;
        for (T t : before) {
            parentId = StringUtils.get(t.getParentId());
            children = map.get(parentId);

            if (CollectionUtils.isEmpty(children)) {
                children = new TreeSet<>();
                map.put(parentId, children);
            }

            children.add(t);
        }

        return map;
    }

    /**
     * <p>对 集合 进行树形排序并返回</p>
     *
     * @return 排序后的集合
     */
    public static <T extends TreeEntity> List<T> treeList(List<T> before) {
        List<T> result = new LinkedList<>();
        Map<String, TreeSet<T>> tree = treeMap(before);

        TreeSet<T> root = pop(tree, getRoots(before).get(0));
        result.addAll(root);
        tree(result, tree);

        return result;
    }

    /**
     * 递归将 Map 中的数据全部排序插入到 List集合中
     *
     * @param result 要返回的List集合
     * @param tree   要排序的Map集合
     */
    private static <T extends TreeEntity> void tree(final List<T> result, Map<String, TreeSet<T>> tree) {
        for (int i = 0; i < result.size(); i++) {
            TreeSet<T> pop = pop(tree, result.get(i).getId());
            result.addAll(i + 1, pop);
        }

        if (!tree.isEmpty()) {
            tree(result, tree);
        }
    }

    /**
     * 获取 Map 中的键值，并在 Map 中删除该键值
     *
     * @param tree Map 集合
     * @param key  Map中的键
     * @return key 对应的值，如果为null则返回空的TreeSet实例对象
     */
    private static <T extends TreeEntity> TreeSet<T> pop(Map<String, TreeSet<T>> tree, String key) {
        TreeSet<T> value = tree.get(key);
        tree.remove(key);
        return value != null ? value : new TreeSet<T>();
    }

    /**
     * 将 List 参数的值全添加到排序的集合 result 中
     *
     * @return 将 result 作为 List 返回
     */
    private static <T extends TreeEntity> List<String> getRoots(List<T> list) {
        TreeSet<String> result = new TreeSet<>();

        for (T t : list) {
            result.add(t.root());
        }

        return new ArrayList<>(result);
    }
}
