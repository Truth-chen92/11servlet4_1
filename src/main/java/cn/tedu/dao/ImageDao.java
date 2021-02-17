package cn.tedu.dao;

import cn.tedu.entity.Image;
import cn.tedu.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ImageDao {
    public void add(Image image) {
        try (Connection conn= DBUtils.getConn()
        ){
             String sql="insert into images values(null,?,?)";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1, image.getTitle());
            ps.setString(2, image.getUrl());
            ps.executeUpdate();
            System.out.println("添加完成！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Image> findAll() {
        ArrayList<Image> list = new ArrayList<Image>();
        try (Connection conn= DBUtils.getConn()
        ){
            String sql="select id,title,url from images";
            Statement s=conn.createStatement();
            ResultSet rs=s.executeQuery(sql);
            while (rs.next()) {
                int id=rs.getInt(1);
                String title=rs.getString(2);;
                String url=rs.getString(3);
                list.add(new Image(id,title, url));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteById(String id) {
        try (Connection conn= DBUtils.getConn()
        ){
            String sql="delete from images where id=?";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(id));
            ps.executeUpdate();
            System.out.println("删除完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String findUrlById(String id) {
        try (Connection conn= DBUtils.getConn()
        ){
            String sql="select url from images where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
