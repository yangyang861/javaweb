package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import vo.Download;

/*import com.sun.corba.se.pept.transport.Connection;*/

public class DownloadDao{
	//��ѯt_ downloadList������м�¼����ѯ�������ڼ����У�������Ԫ��������Download����
	public ArrayList <Download> query() {
	ArrayList<Download> list = new ArrayList<Download>();
	try{
	// 1.��������
	Class.forName("com.mysql.jdbc.Driver");
	// 2.���������ݿ������
	Connection con =DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/exercise?useunicode=true&character=utf-8", "root", "root");

	// 3. �������
	String sql = "select * from t_downloadlist ";
	PreparedStatement pst=con.prepareStatement(sql);
	// 4.ִ�����
	ResultSet rs = pst . executeQuery();
	// 5.�������
	while (rs.next()) {
	Download download = new Download();
	download. setId(rs .getInt("id")); //��Դid
	download . setName(rs . getString( "name")); //��Դ����
	download . setPath(rs . getString("path")); //��Դ������·��
	download . setDescription(rs . getString("description")); //��Դ����
	long size=rs. getLong("size"); //��Դ��С
	String sizeStr=fileSizeTransfer(size); //ת��ΪB, KB, MB, GB����ʽ
	download.setSizeStr(sizeStr);
	download.setStar(rs . getInt("star")); //�Ǽ�
	download . setImage(rs. getString("image")); //��Դͼ�������·��
	download . setTime(rs. getString("time")); //��Դ �ϴ�ʱ��
	list . add(download); //���������ڼ�����

	}
	// 6.�ر�����
	con.close();
	} catch (Exception e) {
	e.printStackTrace();
	}
	return list;
	}
	//������Դid��ѯ��¼������¼ת��Ϊdown1 oad������з���
	public Download get(int id) {
	Download download = null;
		try {
		// 1.��������
	//	Class . forName("com.mysql.jdbc.Driver");
		// 2.���������ݿ������
	//	Connection con =DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/exercise?useunicode=true&character=utf-8",
	//	"root","root");
			config.DBUtils db=new config.DBUtils();
			Connection con=db.get();
		// 3.�ִ������
		String sql = "select * from t_downloadlist where id=? ";
		PreparedStatement pst =con.prepareStatement(sql);
		pst. setInt(1, id);
		// 4.ִ�����
		ResultSet rs = pst . executeQuery(); 
		// 5.�������
		if (rs.next()) {
		download = new Download();
		download . setId(rs . getInt("id"));
		download. setName(rs . getString("name"));
		download. setPath(rs . getString("path"));
		download. setDescription(rs . getString("description"));
		long size=rs. getLong("size");
		String sizeStr=fileSizeTransfer(size);
		download. setStar(rs. getInt("star"));
		download. setImage(rs . getString("image"));
		download. setTime(rs . getString("time"));
		download. setSizeStr(sizeStr);
		}
		// 6.�ر�����
		//con.close();
		db.close(con, pst);
		} 
		catch (Exception e) {
	e. printStackTrace();
		}
	return download;
	}
	//�Զ��巽��������Դ��Сת��Ϊ***G, **MB , **KB, **B����ʽ���ַ���
	public String fileSizeTransfer(long fileSize) {
	String mFileSize;
	DecimalFormat df = new DecimalFormat("#####0.00");
	double size = (double) fileSize;
	if (size > 1024 * 1024 *1024) {
	size = size / (1024 * 1024 * 1024);
	mFileSize = df . format(size) + "G";
	} else if (size > 1024*1024) {
	size = size / (1024 * 1024);
	mFileSize = df. format(size) +"MB";
	} else if (size > 1024) {
	size = size / 1024;
	mFileSize = df. format(size) +"KB";
	} else {
	mFileSize = df. format(size) +"B";
	}
	return mFileSize;
	}
}




