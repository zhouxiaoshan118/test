package com.zte.zudp.modules.sys.dict.entity;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;
import com.zte.zudp.common.persistence.entity.DateEntity;
import com.zte.zudp.common.persistence.entity.ables.Weightable;
import com.zte.zudp.modules.sys.utils.json.View;

/**
 * 数据字典
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-07-06.
 */
public class Dict extends DateEntity implements Weightable<Dict> {

    private static final long serialVersionUID = 2937778114274390471L;

    @JsonView(View.Default.class)
    @Size(min = 1)
    private String type;        // 类型

    @JsonView(View.Default.class)
    private String label;     // 标签名

    @JsonView(View.Default.class)
    private String value;    // 值

    @JsonView(View.Default.class)
    private String mark;        // 描述

    @JsonView(View.Default.class)
    private Integer weight;     // 权重

    @JsonView(View.Default.class)
    private Boolean status;     // 状态, false 表示为普通数据字典, true 表示为开发数据字典

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public Integer getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public int compareTo(Dict entity) {
        Integer other = entity.getWeight();
        Integer weight = this.getWeight();

        if (weight.equals(other)) {
            return 0;
        }

        return weight > other ? 1 : -1;
    }
}
