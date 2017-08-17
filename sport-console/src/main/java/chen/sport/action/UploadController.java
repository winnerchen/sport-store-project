package chen.sport.action;

import chen.sport.core.tools.Constants;
import chen.sport.core.tools.FastDFSTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

/**
 * @Author: Yiheng Chen
 * @Description: 上传文件控制器
 * @Date: Created in 17:01 2017/8/16
 * @Modified by:
 */
@Controller
public class UploadController {
    @RequestMapping(value = "/uploadFile.do")
    @ResponseBody
    public HashMap<String,String> uploadFile(MultipartFile mpf) throws Exception{
        System.out.println(mpf.getOriginalFilename());
        // 将文件上传到分布式文件系统，并返回文件的存储路径及名称
        String uploadFile = FastDFSTool.uploadFile(mpf.getBytes(),
                mpf.getOriginalFilename());
        // 返回json格式的字符串（图片的绝对网络存放地址）
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("path", Constants.FDFS_SERVER + uploadFile);
        return hashMap;

    }
}
