package com.zte.zudp.modules.sys.actuals.dao;

import com.zte.zudp.common.persistence.dao.AbstractDao;
import com.zte.zudp.modules.sys.actuals.entity.Comment;
import com.zte.zudp.modules.sys.user.entity.Officer;

public interface CommentDao extends AbstractDao<Comment> {

	Officer getOfficerByUserId(String user_id);
	
}
