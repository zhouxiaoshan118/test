package com.zte.zudp.modules.sys.actuals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zte.zudp.common.persistence.service.BusinessService;
import com.zte.zudp.modules.sys.actuals.dao.ActualsOfficerDao;
import com.zte.zudp.modules.sys.actuals.entity.ActualsOfficer;
@Service
@Transactional(readOnly = true)
public class ActualsOfficerService extends BusinessService<ActualsOfficer>{

	@Autowired
	private ActualsOfficerDao actualsOfficerDao;
	
	 @Transactional(readOnly = false)
	public void deleteInTure(String actual_id) {
		actualsOfficerDao.deleteInTure(actual_id);
	}
		
		
}
