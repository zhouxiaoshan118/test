package com.zte.zudp.common.persistence.service;

import com.zte.zudp.common.persistence.entity.DateEntity;
import com.zte.zudp.common.persistence.entity.page.Page;
import com.zte.zudp.common.utils.Exceptions;
import com.zte.zudp.common.utils.IDUtils;
import com.zte.zudp.modules.sys.user.entity.Officer;
import com.zte.zudp.modules.sys.user.entity.User;
import com.zte.zudp.modules.sys.user.utils.UserUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 包含 delete、save、预插入更新及分页查找操作方法
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-10.
 */
@Transactional(readOnly = true)
public abstract class BusinessService<T extends DateEntity> extends AbstractService<T> {


    @Value("${localImagePath}")
    private String localImagePath;

    @Value("${sys.url.admin}")
    private String admin;

    @Transactional()
    public void delete(T t) {
        dao.delete(t.getId());
    }

    /**
     * <p>通用的保存方法</p>
     *
     * 覆写此方法以实现对 插入、更新 的完全控制
     *
     * @param t 要 插入 或 更新 的实体对象
     * @see BusinessService#pre(DateEntity)
     * @see BusinessService#preInsert(DateEntity)
     * @see BusinessService#preUpdate(DateEntity)
     */
    @Transactional()
    public void save(T t) {
        pre(t);

        if (t.isNewRecord()) {
            preInsert(t);
            insert(t);
            afterInsert(t);
        } else {
            preUpdate(t);
            update(t);
            afterUpdate(t);
        }
    }

    /**
     * 在更新实体对象后要执行的操作
     * @param t 已更新的实体对象
     */
    protected void afterUpdate(T t) {
    }

    /**
     * 在插入实体对象后要执行的操作
     * @param t 已插入的实体对象
     */
    protected void afterInsert(T t) {
    }

    /**
     * <p>在 保存 实体对象之前要执行的一些通用逻辑</p>
     *
     * 覆写此方法以控制对 保存 之前的逻辑
     *
     * @param t 要 保存 的实体对象
     */
    protected void pre(T t) {
    }

    /**
     * <p>在插入之前要做的一些通用逻辑</p>
     *
     * 覆写此方法以控制对 插入 之前的逻辑
     *
     * @param t 要 插入 的实体对象
     */
    @Override
    protected void preInsert(T t) {
        super.preInsert(t);
        t.setId(IDUtils.defaultUUID());
//        User user = UserUtils.getCurrentUser();
        User user = new User();
        user.setId("1");
        t.setCreateUser(user);
        t.setUpdateUser(user);
        t.setCreateDate(new Date());
        t.setUpdateDate(t.getCreateDate());
    }

    /**
     * <p>在更新之前要做的一些通用逻辑</p>
     *
     * 覆写此方法以控制对 更新 之前的逻辑
     *
     * @param t 要 更新 的实体对象
     */
    @Override
    protected void preUpdate(T t) {
        super.preUpdate(t);
        //User user = UserUtils.getCurrentUser();
        User user = new User();
        user.setId("1");
        t.setUpdateUser(user);
        t.setUpdateDate(new Date());
    }

    public Page<T> findPage(T t) {
        Page.start();
        return Page.end(findList(t));
    }

    /**
     *
     * @param multipartFile 上传的文件
     * @param path1 要上传的路径
     * @parm path2 访问路径前部分 http://<ip>/admin/<path2>/getimg/fileName+extName
     * @return 返回上传后直接课访问路径 http://.....
     */
    //保存文件到本地
    public String uploadimgs(MultipartFile multipartFile, String path1, String path2) {
//        上传service 返回图片路径
        String jarhome = System.getProperty("user.dir");
        String filePath = (jarhome+"../../upload"+path1).replaceAll("//","/");

        String fileName = UUID.randomUUID().toString().replaceAll("-","");
        String extName = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));

        File file = new File(filePath, fileName+extName);
        if(!file.getParentFile().exists()) file.getParentFile().mkdirs();

        try {
//
//            URL resource = SPService.class.getResource("/");
            String userhome = System.getProperty("user.dir");
//            logger.info(userhome);
//            logger.debug("zzq的getResource------>"+resource.getPath());
            multipartFile.transferTo(file);


//            logger.debug(fileName+extName+"写入---->/upload"+path+fileName+extName);
            return localImagePath+"/admin"+path2+"/getimg/"+fileName+extName;

        } catch (Exception e) {
            String errorMsg = Exceptions.getStackTraceAsString(e);
            logger.error(errorMsg);
            e.printStackTrace();
        }
        return null;

    }

    //保存文件到fastdfs图片服务器,需要配置要加入fastdfsUtils和导入fastdfsclient.jar,由于这里使用本地上传就不用了
    public String upload2imgServer(MultipartFile multipartFile) {
//        try {
//            String originalFilename = multipartFile.getOriginalFilename();
//            String extName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
//            FastDFSClient fastDFSClient = new FastDFSClient(imageServerConfLocation);
//            String filePath = fastDFSClient.uploadFile(multipartFile.getBytes(), extName);
//            return imageServeIp+filePath;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return null;
    }

    /**
     *
     * @param filePath 文件的目标路径 和uploadimgs中的 path 一致
     * @param fileName 文件名称
     * @param resourceLoader ResourceLoader 对象
     * @return 返回资源对象
     */
    //回显图片,/为项目部署外围路径
    public Resource getResource(String filePath, String fileName, ResourceLoader resourceLoader) {
        String ctx = System.getProperty("user.dir");
        String resourcePath = String.valueOf(Paths.get(ctx+"../../upload"+filePath,fileName));
        return resourceLoader.getResource("file:"+resourcePath);
    }

	

}
