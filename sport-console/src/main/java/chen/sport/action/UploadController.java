package chen.sport.action;

import chen.sport.core.tools.Constants;
import chen.sport.core.tools.FastDFSTool;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

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
    public HashMap<String, String> uploadFile(MultipartFile mpf) throws Exception {
        System.out.println(mpf.getOriginalFilename());
        // 将文件上传到分布式文件系统，并返回文件的存储路径及名称
        String uploadFile = FastDFSTool.uploadFile(mpf.getBytes(),
                mpf.getOriginalFilename());
        // 返回json格式的字符串（图片的绝对网络存放地址）
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("path", Constants.FDFS_SERVER + uploadFile);
        return hashMap;
    }

    @RequestMapping(value = "console/product/uploadFck.do")
    @ResponseBody
    public HashMap<String, Object> uploadFck(HttpServletRequest request,
                                             HttpServletResponse response)
            throws FileNotFoundException, IOException, Exception {

        // 将request强转为spring提供的MultipartRequest
        MultipartRequest multipartRequest = (MultipartRequest) request;

        // 获得MultipartRequest里面的所有文件
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        Set<Map.Entry<String, MultipartFile>> entries = fileMap.entrySet();

        for (Map.Entry<String, MultipartFile> entry : entries) {
            // 将文件上传到分布式文件系统，并返回文件的存储路径及名称
            MultipartFile file = entry.getValue();
            String uploadFile = FastDFSTool.uploadFile(file.getBytes(), file.getOriginalFilename());
            // 返回json格式的字符串（图片的绝对网络存放地址）
            HashMap<String, Object> map = new HashMap<>();
            map.put("url", Constants.FDFS_SERVER + uploadFile);
            // error和url名字都是固定死的
            map.put("error", 0);
            return map;
        }
        return null;
    }
    /**
     * 同时上传多个文件
     * @param mpfs
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws Exception
     */
    @RequestMapping(value = "/console/product/uploadPics.do")
    @ResponseBody
    public List<String> uploadFiles(@RequestParam MultipartFile[] mpfs)
            throws FileNotFoundException, IOException, Exception {

        // 上传文件返回的路径集合
        List<String> arrayList = new ArrayList<String>();

        for (MultipartFile mpf : mpfs) {

            // 将文件上传到分布式文件系统，并返回文件的存储路径及名称
            String uploadFile = FastDFSTool.uploadFile(mpf.getBytes(),
                    mpf.getOriginalFilename());

            System.out.println(uploadFile);

            // 返回json格式的字符串（图片的绝对网络存放地址）
            arrayList.add(Constants.FDFS_SERVER + uploadFile);
        }
        return arrayList;
    }
}
