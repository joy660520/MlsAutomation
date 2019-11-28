package model;

public class UserModel {

	String name;
	String password;
	String url;
	String browser;
	String propertyurl;
	
	public String getPropertyurl() {
		return propertyurl;
	}
	public void setPropertyurl(String propertyurl) {
		this.propertyurl = propertyurl;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
