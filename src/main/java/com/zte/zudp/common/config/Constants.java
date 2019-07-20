package com.zte.zudp.common.config;

/**
 * 系统全局的配置参数
 *
 * @author piumnl b
 * @version 1.0.0
 * @since on 2017-02-15.
 */
public interface Constants {

    // ------其他----------------------------------------------------------------------

    String SYSTEM_ROOT_PATH = "sys.url.root";

    String WEB_VIEW_PREFIX = "sys.web.view.prefix";
    String WEB_VIEW_SUFFIX = "sys.web.view.suffix";

    String CAPTCHA_URL = "/jcaptcha.jpg";

    String CAPTCHA_VALIDATE_ERROR = "captcha.validate.error";

    String MESSAGE = "message";

    String MODEL = "m";

    // -------日志----------------------------------------------------------------------------------

    String SYSTEM_LOG = "com.zte.zudp.sys";

    String SYSTEM_SESSION_LOG = "com.zte.zudp.sys.session";

    String INFO_LOG = "com.zte.zudp.info";

    String ERROR_LOG = "com.zte.zudp.error";

    String ACCESS_LOG = "com.zte.zudp.access";

    // ------标识-----------------------------------------------------------------------------------

    String ERROR = "error";

    String SUCCESS = "success";

    String FALSE = "false";

    String TRUE = "true";

    // -----男女------------------------------------------------------------------------------------

    Boolean GENDER_FEMALE = Boolean.FALSE;

    Boolean GENDER_MALE = Boolean.TRUE;

    // -----URL-------------------------------------------------------------------------------------

    String LOGIN_URL = "admin/sys/index/login";

    String REGISTER_URL = "admin/sys/index/register";

    String AUTHORIZED_URL = "error/403";

    String BIND_URL = "error/400";

    String ERROR_URL = "error/500";

    // ----正则表达式--------------------------------------------------------------------------------

    /**
     * 登录名的正则表达式
     *      匹配规则：3-20 个任意字母、数字和下划线
     */
    String LOGINNAME_PATTERN = "^[\\w]{3,20}$";

    /**
     * 昵称的正则表达式
     *      匹配规则：2-20，首字符为中文或字母或下划线，之后的字符可为任意中文和 字母+数字+下划线
     */
    String NICKNAME_PATTERN = "^[\\u4E00-\\u9FA5\\uf900-\\ufa2d\\w]{2,20}$";

    /**
     * 邮箱的正则表达式
     */
    String EMAIL_PATTERN =
            "^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?";

    /**
     * 手机号码的正则表达式
     *      匹配规则：首字符可有 0，之后两位可为 13、15、14、18，然后 0-9 9位
     */
    String MOBILE_PHONE_NUMBER_PATTERN = "^0?(13[0-9]|15[0-9]|14[0-9]|18[0-9])[0-9]{8}$";

    /**
     * 简单的身份证正则
     *      匹配规则：首字符非零，之后16位纯数字，最后一位为 数字 或 X 或 x。
     */
    String IDCARD_PATTERN = "^[1-9][0-9]{16}[0-9Xx]$";
}
