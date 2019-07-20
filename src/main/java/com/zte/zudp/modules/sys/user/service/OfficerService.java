package com.zte.zudp.modules.sys.user.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.zte.zudp.common.persistence.service.BusinessService;
import com.zte.zudp.common.utils.Exceptions;
import com.zte.zudp.modules.sys.user.dao.OfficerDao;
import com.zte.zudp.modules.sys.user.entity.Officer;


@Service
@Transactional
public class OfficerService extends BusinessService<Officer>{

	@Autowired
	private OfficerDao officerDao;
	
	public List<Officer> getOfficerList() {
		
		List<Officer> officers=officerDao.getOfficerList();
		
		return officers;
	}
	
	

	
	

}
