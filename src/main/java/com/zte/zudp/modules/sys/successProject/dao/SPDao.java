package com.zte.zudp.modules.sys.successProject.dao;

import com.zte.zudp.common.persistence.dao.AbstractDao;
import com.zte.zudp.modules.sys.successProject.entity.GeneralUser;
import com.zte.zudp.modules.sys.successProject.entity.MiddleUser;
import com.zte.zudp.modules.sys.successProject.entity.RootUser;
import com.zte.zudp.modules.sys.successProject.entity.SuccessProject;

import java.util.List;

/**
 * 成功项目 的 Dao接口
 * Created by DW on 2017/12/16 17:00
 */
public interface SPDao extends AbstractDao<SuccessProject> {

    List<SuccessProject> getSPsByAppFiled(String applicationField);

    List<SuccessProject> getRecommendSPs();

	List<SuccessProject> findAll();

	void saveGeneralUser(GeneralUser generalUser);

	void saveMiddleUser(MiddleUser middleUser);

	void saveRootUser(RootUser rootUser);

	GeneralUser getGeneralUserById(String generalUserID);

	MiddleUser getMiddleUserById(String middleUserID);

	RootUser getRootUserById(String rootUserID);

	SuccessProject getProjectRootUserById(String roleID);

	SuccessProject getProjectMiddleUserById(String roleID);

	SuccessProject getProjectGeneralUserById(String roleID);


}
