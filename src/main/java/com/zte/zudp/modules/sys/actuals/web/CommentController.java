package com.zte.zudp.modules.sys.actuals.web;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zte.zudp.common.persistence.entity.page.Page;
import com.zte.zudp.common.persistence.web.RESTfulController;
import com.zte.zudp.common.persistence.web.permission.Permissions;
import com.zte.zudp.modules.sys.actuals.entity.Comment;

@RestController
@RequestMapping("${sys.url.admin}/sys/comment")
public class CommentController extends RESTfulController<Comment>{

	@Override
    protected void init() {
        super.init();
        this.setResourceIdentity(Permissions.DICT);
        setDefaultViewPath("/modules/sys/comment");

        setListAlsoCommonData(true);
    }

	@Override
	public ResponseEntity<Page<Comment>> index(Comment t) {
		ResponseEntity<Page<Comment>> index = super.index(t);
		List<Comment> result = index.getBody().getResult();
		for (Comment comment : result) {
			long time = comment.getCommentDate().getTime();
			long currentTime = new Date().getTime();
			comment.setBeforeCommentTime(currentTime-time); 
		}
		
		
		return index;
	}
	
	
	
}
