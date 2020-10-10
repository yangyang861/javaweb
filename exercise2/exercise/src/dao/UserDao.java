package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.User;

public class UserDao {
	public User get(String userName) {
		User user = null;
		try {
			// 1.加载类
		/*	Class.forName("com.mysql.jdbc.Driver");
			// 2.建立数据库连接
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/exercise?useunicode=true&character=utf-8", "root", "root");*/
			config.DBUtils db=new config.DBUtils();
			Connection con=db.get();
			
			// 3.创建语句
			String sql = "select * from t_user where userName=?";
			PreparedStatement pst = con.prepareStatement(sql);
			// 4.执行语句
			pst.setString(1,userName);
			ResultSet rs = pst.executeQuery();
			// 5.响应处理
			if (rs.next()) {
				user = new User(rs.getString("userName"), rs.getString("password"),
						rs.getString("chrName"));
			}
			// 6.关闭连接
			//con.close();
			db.close(con, pst);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

}
