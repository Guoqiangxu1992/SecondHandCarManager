/**
 * 
 */
package com.xu.manager.serviceImpl;

import java.io.File;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import com.xu.manager.ClassUtil.CarNumberCreateUtils;
import com.xu.manager.bean.LoginUser;
import com.xu.manager.bean.PictureInformation;
import com.xu.manager.bean.UploadParam;
import com.xu.manager.dao.CarInformationDao;
import com.xu.manager.service.UploadManager;

/**
* @author Create By Xuguoqiang
* @date   2016年10月20日--下午8:24:26--
*
*/
/**
 * @author summer
 *
 */
@Service("uploadManager")
@Scope("prototype")
public class UploadManagerImpl implements UploadManager {
	@Autowired 
	private CarInformationDao carInformationDao;

	@Override
	public UploadParam uploadFile(HttpSession session, File file, String filename, UploadParam param,Long carId) {
		UploadParam param1 = new UploadParam();
		HttpClient client = new HttpClient();
		PostMethod method = null;
		String filePath = file.getAbsolutePath();
		HttpServletResponse response = null;
		File file1 = new File(filePath);
		// 设置相关参数
		String url = "http://localhost:8080/SecondHandCarManager/upload/uploadfile.do";
		client.getHttpConnectionManager().getParams().setConnectionTimeout(100000);
		client.getHttpConnectionManager().getParams().setSoTimeout(100000);
		try {
			Part[] parts = { new FilePart("file", file1) };
			method = new PostMethod(url);
			method.setRequestHeader("fileName", filename);
			StringPart aa = new StringPart("file", file.getName());
			method.setRequestEntity(new MultipartRequestEntity(parts, method.getParams()));
			int status = client.executeMethod(method);
			if (status == HttpStatus.SC_OK) {
				byte[] responseBody = method.getResponseBody();
				Header headerResponse = method.getResponseHeader("url");
				LoginUser loginUser = (LoginUser) session.getAttribute("SESSION_LOGIN_USER");
                PictureInformation picInfo = new PictureInformation();
                picInfo.setCreateTime(new Date());
                picInfo.setUploaderId(Long.parseLong(loginUser.getId().toString()));
                picInfo.setCarId(carId);
                picInfo.setOriginalName(filename);
                picInfo.setSize(file.getTotalSpace());
                picInfo.setUrl(headerResponse.getValue());
                picInfo.setPictureId(CarNumberCreateUtils.getCarNumber());
                carInformationDao.savePictureInfo(picInfo);
				System.out.println(headerResponse.getValue());
				System.out.println("上传成功");
			} else {
				System.out.println("上传失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return null;
	}

}
