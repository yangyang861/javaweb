package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vo.Download;
import vo.Resouce;
import vo.User;

public class RoleDao {
	public ArrayList<Resouce> get(String userName) {
		ArrayList<Resouce> list=null;
		try {
			// 1.������
	//		Class.forName("com.mysql.jdbc.Driver");
			// 2.�������ݿ�����
	//		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/exercise?useunicode=true&character=utf-8", "root", "root");
			config.DBUtils db=new config.DBUtils();
			Connection con=db.get();
			// 3.�������
			String sql = "select * from t_resouce where resouceId IN"
					+ " ( select resouceId from t_role_resouce where roleId IN"
					+ " ( select roleId from t_user_role where userName=? ) ) ";
			PreparedStatement pst = con.prepareStatement(sql);
			// 4.ִ�����
			pst.setString(1,userName);
			ResultSet rs = pst.executeQuery();
			// 5.��Ӧ����
			while(rs.next()) {
				Resouce resouce = new Resouce();
				resouce.setResouceId(rs .getInt("resouceId")); //��Դid
				resouce.setResouceName(rs.getString( "resouceName")); //��Դ����
				resouce.setUrl(rs . getString("url")); //��Դ������·��
			   list.add(resouce);
			}
			for(Resouce r:list)System.out.println(r.toString());

			// 6.�ر�����
			//con.close();
			db.close(con, pst);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
