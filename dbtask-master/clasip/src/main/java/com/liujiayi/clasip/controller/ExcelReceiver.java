package com.liujiayi.clasip.controller;

import com.liujiayi.clasip.dao.ClassStudentDao;
import com.liujiayi.clasip.dao.StudentDao;
import com.liujiayi.clasip.pojo.Student;
import com.liujiayi.clasip.pojo.association.ClassStudent;
import com.liujiayi.clasip.util.ErrorEnum;
import com.liujiayi.clasip.util.Result;
//import org.apache.catalina.User;
//import org.jumpmind.symmetric.csv.CsvReader;
//import org.junit.Test;
//import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.Csv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
//import java.nio.charset.Charset;
import java.util.List;
import java.util.Vector;

/**
 * 创建时间: 2020-12-08 19:13
 * 文件备注: 接收csv，存入数据库
 * 编写人员: 杨伯益
 */

@Controller
public class ExcelReceiver {

    @Autowired
    StudentDao studentDao;

    @Autowired
    ClassStudentDao classStudentDao;

    //接收csv格式的文件
    @ResponseBody
    @PostMapping("/uploadcsv")
    public Result getCsv(@RequestParam("csvFile")MultipartFile multipartFile) throws Exception {
        System.out.println("开始处理。。。");
        if(multipartFile==null) {
            System.out.println("空文件！");
            return Result.failure(ErrorEnum.E_90003);
        }
        System.out.println("不是空文件！");


        String fileName = multipartFile.getOriginalFilename();
        File file = new File(fileName);
        OutputStream out = null;
        try{
            //获取文件流，以文件流的方式输出到新文件
//    InputStream in = multipartFile.getInputStream();
            out = new FileOutputStream(file);
            byte[] ss = multipartFile.getBytes();
            for(int i = 0; i < ss.length; i++){
                out.write(ss[i]);
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


//        File csvFile = transferToFile(file);
        test1(file);
        System.out.println("上传成功");
        return Result.successs("csv上传数据成功！");
    }

    //插入数据库
    public void test1(File csv) throws Exception {
//        File file = new File("D:\\apps\\git\\test\\dbtask\\clasip\\src\\main\\java\\com\\liujiayi\\clasip\\test\\test1.csv");
        File file = csv;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = "";
        Vector<String> sids = new Vector<String>();
        Vector<String> pwds = new Vector<String>();
        Vector<String> names = new Vector<String>();
        Vector<String> majors = new Vector<String>();
        Vector<String> grades = new Vector<String>();
        while((line = bufferedReader.readLine())!=null){
            String[] lines = line.split(",");
            sids.add(lines[0]);
            pwds.add(lines[1]);
            names.add(lines[2]);
            majors.add(lines[3]);
            grades.add(lines[4]);
        }
        List<String> sidList = studentDao.selectSid();
        int n = sids.size();
        for (int i=1;i<n;i++){
            //判断主键重复
            if(!sidList.contains(sids.get(i)))
                studentDao.insert(new Student(sids.get(i),pwds.get(i),names.get(i),majors.get(i),grades.get(i)));
        }
    }

    public File transferToFile(MultipartFile multipartFile) {
//        选择用缓冲区来实现这个转换即使用java 创建的临时文件 使用 MultipartFile.transferto()方法 。
        File file = null;
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String[] filename = originalFilename.split(".");
            file=File.createTempFile(filename[0], filename[1]);
            multipartFile.transferTo(file);
            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    //插入数据库（选课的）
    public void test2(File csv) throws Exception {
//        File file = new File("D:\\apps\\git\\test\\dbtask\\clasip\\src\\main\\java\\com\\liujiayi\\clasip\\test\\test1.csv");
        File file = csv;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = "";
        Vector<String> sids = new Vector<String>();
        Vector<String> cids = new Vector<String>();
        while((line = bufferedReader.readLine())!=null){
            String[] lines = line.split(",");
            sids.add(lines[0]);
            cids.add(lines[1]);
        }
//        List<String> sidList = classStudentDao.selectSid();
        int n = sids.size();
        for (int i=1;i<n;i++){
            //判断主键重复
//            if(!sidList.contains(sids.get(i)))
                classStudentDao.insert(new ClassStudent(sids.get(i),cids.get(i)));
        }
    }

    //接收csv格式的文件
    @ResponseBody
    @PostMapping("/uploadcsv2")
    public Result getCsv2(@RequestParam("csvFile")MultipartFile multipartFile) throws Exception {
        System.out.println("开始处理。。。");
        if(multipartFile==null) {
            System.out.println("空文件！");
            return Result.failure(ErrorEnum.E_90003);
        }
        System.out.println("不是空文件！");


        String fileName = multipartFile.getOriginalFilename();
        File file = new File(fileName);
        OutputStream out = null;
        try{
            //获取文件流，以文件流的方式输出到新文件
//    InputStream in = multipartFile.getInputStream();
            out = new FileOutputStream(file);
            byte[] ss = multipartFile.getBytes();
            for(int i = 0; i < ss.length; i++){
                out.write(ss[i]);
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


//        File csvFile = transferToFile(file);
        test2(file);
        System.out.println("上传成功");
        return Result.successs("csv上传数据成功！");
    }

}
