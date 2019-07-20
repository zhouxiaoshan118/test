package com.zte.zudp.modules.sys.previewUser.dao;


import java.util.List;

import com.zte.zudp.common.persistence.dao.AbstractDao;
import com.zte.zudp.modules.sys.previewUser.entity.PreviewUser;
import com.zte.zudp.modules.sys.previewUser.entity.PreviewUserResources;


public interface PreviewUserDao extends AbstractDao<PreviewUser>{

	void saveResource(PreviewUserResources previewUserResources);

	PreviewUser getBypreviewUserNameAndPwd(PreviewUser previewUser);

	PreviewUserResources getPreviewUserResourcesByIds(PreviewUserResources newPreviewUserResources);

	List<PreviewUserResources> getPuResourcesByPuID(String id);

	void deleteInTrue(String previewUserId);

	void qiyong(String id);

	PreviewUser checkPreviewUserName(String previewUserName);

}
