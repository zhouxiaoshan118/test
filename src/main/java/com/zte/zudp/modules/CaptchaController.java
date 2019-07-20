package com.zte.zudp.modules;

import com.zte.zudp.common.utils.ImageCode;
import com.zte.zudp.common.utils.MessageUtils;
import com.zte.zudp.system.CaptchaUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-11-15.
 */
@Controller
public class CaptchaController {


    @RequestMapping(value = "/img/captcha")
    public String imagecode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setDateHeader("Expires", 0L);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        Map<String,Object> map = ImageCode.getImageCode(80, 20);

        request.getSession().setAttribute(CaptchaUtils.CAPTCHA_KEY, map.get(ImageCode.CODE_KEY).toString().toLowerCase());
        request.getSession().setAttribute(CaptchaUtils.CODE_TIME_KEY, System.currentTimeMillis());

        try {
            OutputStream os = response.getOutputStream();
            ImageIO.write((BufferedImage) map.get(ImageCode.IMAGE_KEY), "JPEG", os);
        } catch (IOException e) {
            return "";
        }
        return null;
    }

    @RequestMapping(value = "/img/checkCode")
    @ResponseBody
    public String checkcode(HttpServletRequest request, @RequestParam("checkCode") String checkCode) {
        String result = CaptchaUtils.validateCaptcha(request, checkCode);
        if (result != null) {
            // todo 手机端验证
            String message = MessageUtils.message(result);
            // request.setAttribute("errorMsg", "验证码已失效，请重新输入！");
            return "验证码已失效，请重新输入！";
        } else {
            return "1";
        }
    }

    // @ResponseBody
    // @RequestMapping(value = "/jcaptcha-validate", method = RequestMethod.GET)
    // public Object jqueryValidationEngineValidate(HttpServletRequest request,
    //                                              @RequestParam(value = "fieldId", required = false) String fieldId,
    //                                              @RequestParam(value = "fieldValue", required = false) String fieldValue) {
    //
    //     List<Object> results = new ArrayList<>();
    //
    //     if (!com.zte.zudp.modules.sys.utils.CaptchaUtils.hasCaptcha(request, fieldValue)) {
    //         results.add(new Object[]{fieldId, 0, MessageUtils.message("jcaptcha.validate.error")});
    //     } else {
    //         results.add(new Object[]{fieldId, 1, MessageUtils.message("jcaptcha.validate.success")});
    //     }
    //
    //     if (results.size() == 1) {
    //         return results.get(0);
    //     }
    //     return results;
    // }
}
