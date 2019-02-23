package com.uoffice.shiro.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class ImageService {

    //保存图片，并且返回图片路径
    public static String imageSave(MultipartFile image, String path) throws IllegalStateException, IOException{
        //保存数据库的路径
        String sqlPath = null;
        //定义文件保存的本地路径
        String localPath=path+"static/imgs/loadimgs/";
        //定义 文件名
        String filename=null;
        if(!image.isEmpty()){
            //生成uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType=image.getContentType();
            //获得文件后缀名
            String suffixName=contentType.substring(contentType.indexOf("/")+1);
            //得到 文件名
            filename=uuid+"."+suffixName;
            System.out.println(filename);
            sqlPath = localPath+filename;
            System.out.println(sqlPath);
            //文件保存路径
            image.transferTo(new File(sqlPath));
        }
        //把图片的相对路径保存至数据库
        return filename;
    }

}
