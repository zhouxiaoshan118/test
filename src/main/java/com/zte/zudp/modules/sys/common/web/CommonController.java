package com.zte.zudp.modules.sys.common.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.zte.zudp.common.persistence.web.PathController;
import com.zte.zudp.modules.sys.common.entity.Area;
import com.zte.zudp.modules.sys.common.service.AreaService;
import com.zte.zudp.modules.sys.utils.json.View;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-07-27.
 */
@RestController
@RequestMapping("${sys.url.admin}/sys/common")
public class CommonController extends PathController<Area> {

    @Autowired
    private AreaService areaService;

    /**
     * 根据 Area 的类型查询
     * @param type 为地区的类型，0 中国，1省，2市，3县
     * @return 返回某一个类型的地区，如 type=1，则返回所有省份
     */
    @JsonView(View.Default.class)
    @RequestMapping(value = "/area/{type}", method = RequestMethod.GET)
    public List<Area> area(@PathVariable("type") String type) {
        Area area = new Area();
        area.setType(type);
        return areaService.findList(area);
    }

    /**
     * 如 type = 2 id = 10，返回10下面从属2级地区的数据
     * @param type 为地区的类型，0 中国，1省，2市，3县
     * @param id 父级id
     * @return 某地区的下一级地区
     */
    @JsonView(View.Default.class)
    @RequestMapping(value = "/area/{type}/{id}", method = RequestMethod.GET)
    public List<Area> areaId(@PathVariable("type") String type, @PathVariable("id") String id) {
        Area area = new Area();
        area.setType(type);
        area.setParent(new Area(id));
        return areaService.findList(area);
    }
}
