package com.zte.zudp.modules.sys.user.dao;

import java.util.List;

import com.zte.zudp.common.persistence.dao.AbstractDao;
import com.zte.zudp.modules.sys.user.entity.Officer;

public interface OfficerDao extends AbstractDao<Officer>{

	List<Officer> getOfficerList();

}
