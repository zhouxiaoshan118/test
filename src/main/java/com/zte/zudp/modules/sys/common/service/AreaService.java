package com.zte.zudp.modules.sys.common.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zte.zudp.common.persistence.service.BusinessService;
import com.zte.zudp.common.utils.StringUtils;
import com.zte.zudp.modules.sys.common.dao.AreaDao;
import com.zte.zudp.modules.sys.common.entity.Area;

/**
 * 全国省市县数据对象
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-10.
 */
@Service
@Transactional(readOnly = true)
public class AreaService extends BusinessService<Area> {

    private AreaDao dao() {
        return (AreaDao) dao;
    }

    /**
     * 地址转换，将后台id转换为对应的地区名称
     *
     * @param address 格式：province&city[[&country]&other]
     * @return id转换后的名字
     */
    public String dealAddress(String address) {
        StringBuilder result = new StringBuilder();

        if (StringUtils.isNotEmpty(address)) {
            String[] split = address.split("&");

            Area province = null;
            Area city = null;
            Area country = null;
            String other = null;

            switch (split.length) {
                case 4:
                    other = split[3];
                case 3:
                    if (other == null) {
                        other = split[2];
                    } else {
                        country = get(split[2]);
                    }
                case 2:
                    city = get(split[1]);
                case 1:
                    province = get(split[0]);
            }
            if (province != null) {
                result.append(province.getName());
            }
            if (city != null) {
                result.append(city.getName());
            }
            if (country != null) {
                result.append(country.getName());
            }
            if (StringUtils.isNotEmpty(other)) {
                result.append(other);
            }
        }

        return result.toString();
    }
}
