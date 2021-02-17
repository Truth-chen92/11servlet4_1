package cn.tedu.controller;

import cn.tedu.dao.ImageDao;
import cn.tedu.entity.Image;
import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FindAllServlet",urlPatterns = "/findall")
public class FindAllServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ImageDao dao=new ImageDao();
        List<Image>list=dao.findAll();
        Context context = new Context();
        context.setVariable("list",list);
        ThUtils.print("list.html",context,response);
    }
}
