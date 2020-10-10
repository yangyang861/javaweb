package vo;

public class Resouce {
	private int resouceId;
	private String resouceName;
	private String url;
	public Resouce() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Resouce(int resouceId, String resouceName, String url) {
		super();
		this.resouceId = resouceId;
		this.resouceName = resouceName;
		this.url = url;
	}
	public int getResouceId() {
		return resouceId;
	}
	public void setResouceId(int resouceId) {
		this.resouceId = resouceId;
	}
	public String getResouceName() {
		return resouceName;
	}
	public void setResouceName(String resouceName) {
		this.resouceName = resouceName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Resouce [resouceId=" + resouceId + ", resouceName="
				+ resouceName + ", url=" + url + "]";
	}
	
}
