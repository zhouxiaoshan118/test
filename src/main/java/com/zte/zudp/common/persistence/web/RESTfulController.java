package com.zte.zudp.common.persistence.web;

import com.zte.zudp.common.annotation.HasPermission;
import com.zte.zudp.common.enums.Permission;
import com.zte.zudp.common.persistence.entity.DateEntity;
import com.zte.zudp.common.persistence.entity.page.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * RESTful 请求方式的Controller
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-03-13.
 */
@RestController
public abstract class RESTfulController<T extends DateEntity> extends AbstractController<T> {

    /**
     * 列表页
     */
    @HasPermission(Permission.VIEW)
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list(T t, Model model) {
        return defaultView();
    }

    /**
     * 列表数据
     */
    // @JsonView(View.Default.class)
    @HasPermission(Permission.VIEW)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<Page<T>> index(T t) {
        if (isListAlsoCommonData()) {
            setCommonData(t);
        }

        Page<T> page = service.findPage(t);

        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    /**
     * 单条数据
     */
    // @JsonView(View.Default.class)
    @HasPermission(Permission.VIEW)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<T> get(@PathVariable("id") String id) {
        T t = service.get(id);

        if (t == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(t, HttpStatus.OK);
        }
    }

    /**
     * 创建数据
     */
    // @JsonView({View.Error.class})
    @HasPermission(Permission.CREATE)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<T> create(@Valid @RequestBody T t, BindingResult result) {
        if (hasError(t, result)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        if (isListAlsoCommonData()) {
            setCommonData(t);
        }
   
        service.save(t);
        return new ResponseEntity<>(t, HttpStatus.CREATED);
    }

    /**
     * 修改数据
     */
    // @JsonView({View.Error.class})
    @HasPermission(Permission.UPDATE)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<T> update(@PathVariable("id") String id, @Valid @RequestBody T t,
                                    BindingResult result) {
        if (hasError(t, result)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        t.setId(id);

        if (isListAlsoCommonData()) {
            setCommonData(t);
        }

        service.save(t);
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    /**
     * 删除指定数据
     */
    @HasPermission(Permission.DELETE)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<T> delete(@PathVariable("id") String t) {
        service.delete(t);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * 删除多条数据
     */
    @HasPermission(Permission.DELETE)
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<T> deleteInBatch(@RequestBody String[] ids) {
        service.delete(ids);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
