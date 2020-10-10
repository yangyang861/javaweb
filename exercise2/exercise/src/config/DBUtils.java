package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.omg.CORBA.portable.InputStream;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;


public class DBUtils {	
	public static java.sql.Connection get() throws Exception {
	// 1.读取配置文件中的4个基本信息
	java.io.InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

	Properties pros = new Properties();
	pros.load(is);

	String user = pros.getProperty("DBUser");
	String password = pros.getProperty("DBPassword");
	String url = pros.getProperty("DBUrl");
	String driverClass = pros.getProperty("DBDriver");

	// 2.加载驱动
	Class.forName(driverClass);

	// 3.获取连接
	java.sql.Connection conn = DriverManager.getConnection(url,user,password);
	return conn;
}
/**
 * 
 * @Description 关闭资源
 */
public static void close(java.sql.Connection con,java.sql.PreparedStatement pst){
	try {
		if(pst != null)
			pst.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	try {
		if(con != null)
			con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
}
