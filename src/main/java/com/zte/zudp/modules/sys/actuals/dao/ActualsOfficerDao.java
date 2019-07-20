package com.zte.zudp.modules.sys.actuals.dao;

import com.zte.zudp.common.persistence.dao.AbstractDao;
import com.zte.zudp.modules.sys.actuals.entity.ActualsOfficer;

public interface ActualsOfficerDao extends AbstractDao<ActualsOfficer>{

	void deleteInTure(String actual_id);

}
