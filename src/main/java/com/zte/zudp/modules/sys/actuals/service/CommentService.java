package com.zte.zudp.modules.sys.actuals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zte.zudp.common.persistence.entity.AbstractEntity;
import com.zte.zudp.common.persistence.service.BusinessService;
import com.zte.zudp.modules.sys.actuals.dao.CommentDao;
import com.zte.zudp.modules.sys.actuals.entity.Comment;
import com.zte.zudp.modules.sys.user.entity.Officer;

@Service
@Transactional(readOnly = true)
public class CommentService extends BusinessService<Comment>{
	
	@Autowired
	private CommentDao commentDao;
	
	public Officer getOfficerByUserId(String user_id) {
		
		return commentDao.getOfficerByUserId(user_id);
	}

	
	
	
	
}
