package com.xu.manager.bean;

public class PicConstant {

    public final static int productPicType = 3;  //图片类型(产品图片)
    public final static int productResourceType = 0;  //对应的业务类型
    
    public static String postUrl="http://upload.yihaodian.com/upload/UploadAction";
    public static String ftpIp;
    
    public static String       sessionLogin;           //后台传送，不需要验证

    public static String       temporaryFolder;
    public static int          timeout=10;
	
	public String getSessionLogin() {
        return sessionLogin;
    }

    public static void setSessionLogin(String sessionLogin) {
        PicConstant.sessionLogin = sessionLogin;
    }

    public int getTimeout() {
        return timeout;
    }

    public static void setTimeout(int timeout) {
        PicConstant.timeout = timeout;
    }

    public String getTemporaryFolder() {
		return temporaryFolder;
	}

	public static void setTemporaryFolder(String temporaryFolder) {
		PicConstant.temporaryFolder = temporaryFolder;
	}
    public String getPostUrl() {
        return postUrl;
    }

    public static void setPostUrl(String postUrl) {
        PicConstant.postUrl = postUrl;
    }

    public String getFtpIp() {
        return ftpIp;
    }

    public static void setFtpIp(String ftpIp) {
        PicConstant.ftpIp = ftpIp;
    }

}
