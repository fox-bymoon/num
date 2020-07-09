package com.example.demo.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

public class FileUploadAndFileDownload {
    public static boolean upload(MultipartFile file,String filePath,boolean isUUID){
        //获取文件的原始文件名
        String fileOldName = file.getOriginalFilename();
        //获取后缀名
        String sufferName = fileOldName.substring(fileOldName.lastIndexOf("."));
        //设置新的文件名
        String fileName;
        if (isUUID){//如果需要放重复，拼接uuid
            fileName = filePath+ UUID.randomUUID()+fileOldName;
        }else {
            fileName = filePath + fileOldName;
        }
        File dest = new File(fileName);//加入文件
        if (!dest.getParentFile().exists()){//如果文件在不存在 则创建
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest);//文件上传
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean download(HttpServletResponse response,String fileName,String newName){
        File file = new File(fileName);
        try {
            FileInputStream fis = new FileInputStream(file);//将文件放入输入流
            //设置下载头信息，newName为文件的新下载名。
            response.setContentType("application/force-download");
            response.addHeader("Content-disposition", "attachment;fileName=" + newName);
            //获取response的输出流
            ServletOutputStream os = response.getOutputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            //文件传输
            while ((len = fis.read(bytes)) != -1){
                os.write(bytes,0,len);
            }
            //关闭流
            fis.close();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
