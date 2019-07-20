package com.zte.zudp.modules.sys.actuals.dao;

import java.util.List;

import com.zte.zudp.common.persistence.dao.AbstractDao;
import com.zte.zudp.modules.sys.actuals.entity.Actuals;
import com.zte.zudp.modules.sys.user.entity.Officer;

/**
 * 数据字典
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-12-18.
 */
public interface ActualsDao extends AbstractDao<Actuals> {
	
	List<Actuals> getActuals(Actuals actuals);

	List<Actuals> getActualsInfo(String id);

	List<Actuals> findListByName(String name);

	List<Officer> getOfficers();

	List<Officer> getLinkOfficers(String id);

	Officer getPerson(String name);

	Officer getOfficer(String id);

}
