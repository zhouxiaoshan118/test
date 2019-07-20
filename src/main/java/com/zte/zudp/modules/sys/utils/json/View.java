package com.zte.zudp.modules.sys.utils.json;

/**
 * 控制 json 返回的实体字段
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-03-21.
 * @see com.fasterxml.jackson.annotation.JsonView
 */
public class View {
    /**
     * json会返回实体类的id属性
     * @see com.zte.zudp.common.persistence.entity.AbstractEntity
     */
    public interface Entity {}

    /**
     * json 会返回 Page 对象中的属性
     * @see com.zte.zudp.common.persistence.entity.page.Page
     */
    public interface Page extends Entity {}

    /**
     * json 会返回实体类的树形属性
     * @see com.zte.zudp.common.persistence.entity.TreeEntity
     */
    public interface Tree extends Page {}

    /**
     * json 会返回实体类中 默认返回的 属性，此一般在具体的某一个业务实体类中标注
     */
    public interface Default extends Tree {}


    /**
     * 二级过滤
     */
    public interface Second extends Default {}

    /**
     * json 会返回实体类中 错误字段 值
     * @see com.zte.zudp.common.persistence.entity.ErrorEntity
     */
    public interface Error extends Second {}


    /**
     * json 会返回实体类中 创建时间和修改时间 属性
     * @see com.zte.zudp.common.persistence.entity.DateEntity
     */
    public interface Date {}

    /**
     * json 会返回实体类中 创建者和修改者的 属性
     * @see com.zte.zudp.common.persistence.entity.DateEntity
     */
    public interface User {}

    public abstract class All implements Error, Date, User {}
    public abstract class officeDate implements Date,Default{}
}
