package cn.tedu.controller;

import cn.tedu.dao.ImageDao;
import cn.tedu.entity.Image;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@MultipartConfig
@WebServlet(name = "UploadServlet",urlPatterns = "/upload")
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String title=request.getParameter("title");
        Part part=request.getPart("pic");
        String info=part.getHeader("content-disposition");
        String suffix=info.substring(info.lastIndexOf("."),info.length()-1);
        System.out.println("title:"+title);
        System.out.println("后缀名:"+suffix);
        String fileName= UUID.randomUUID()+suffix;
        String path=request.getServletContext().getRealPath("images/");
        new File(path).mkdirs();
        part.write(path+fileName);
//        把图片信息封装到Image对象中
        Image image=new Image(0,title,"images/"+fileName);
//        创建Dao并调用添加方法
        ImageDao dao=new ImageDao();
        dao.add(image);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw=response.getWriter();
        pw.println("发布完成！");
        pw.close();


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
