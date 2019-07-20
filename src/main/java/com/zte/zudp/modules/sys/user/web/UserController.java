package com.zte.zudp.modules.sys.user.web;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.zte.zudp.common.annotation.HasPermission;
import com.zte.zudp.common.enums.Permission;
import com.zte.zudp.common.persistence.web.RESTfulController;
import com.zte.zudp.common.persistence.web.permission.Permissions;
import com.zte.zudp.common.utils.check.FirstCheck;
import com.zte.zudp.modules.sys.dict.entity.Dict;
import com.zte.zudp.modules.sys.user.entity.Officer;
import com.zte.zudp.modules.sys.user.entity.User;
import com.zte.zudp.modules.sys.user.service.UserService;
import com.zte.zudp.modules.sys.utils.json.View;

/**
 * 管理所有用户信息
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-10.
 */
@RestController
@RequestMapping("${sys.url.admin}/sys/user")
public class UserController extends RESTfulController<User> {

    @Override
    protected void init() {
        super.init();
        setResourceIdentity(Permissions.USER);
        setDefaultViewPath("admin/sys/user");
    }

    public UserService service() {
        return (UserService) service;
    }

    @Override
    @JsonView({View.Error.class})
    @HasPermission(Permission.UPDATE)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> update(@PathVariable("id") String id,
                                       @Validated(FirstCheck.class) @RequestBody User user,
                                       BindingResult result) {
        return super.update(id, user, result);
    }

    /**
     * 校验登录名是否存在
     *
     * @return 如果存在返回false，否则返回true
     */
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @HasPermission(Permission.VIEW)
    public boolean check(String loginName) {
        User user = service().getUserByLoginName(loginName);
        return user == null;
    }
    
    
    /*@Override
    protected void setCommonData(Dict dict) {
        super.setCommonData(dict);
        dict.setStatus(false);
    }*/
    
  
    
}
