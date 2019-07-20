package com.zte.zudp.modules.sys.common.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.zte.zudp.common.persistence.entity.DateEntity;
import com.zte.zudp.common.persistence.entity.ables.Weightable;
import com.zte.zudp.modules.sys.utils.json.View;

/**
 * 全国省市县数据对象
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-07-06.
 */
public class Area extends DateEntity implements Weightable<Area> {

    private static final long serialVersionUID = -5765570936306057828L;

    @JsonView(View.Default.class)
    private String type;        // 所在等级，0全国，1省，2市，3县

    @JsonView(View.Default.class)
    private String name;     // 城市名

    @JsonView(View.Default.class)
    private Area parent;    // 父级

    @JsonView(View.Default.class)
    private Integer weight;     // 权重

    public Area() {
        super();
    }

    public Area(String id) {
        super(id);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Area getParent() {
        return parent;
    }

    public void setParent(Area parent) {
        this.parent = parent;
    }

    @Override
    public Integer getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Area entity) {
        Integer other = entity.getWeight();
        Integer weight = this.getWeight();

        if (weight.equals(other)) {
            return 0;
        }

        return weight > other ? 1 : -1;
    }
}
