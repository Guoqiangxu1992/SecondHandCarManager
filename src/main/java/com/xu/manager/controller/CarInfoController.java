/**
 * 
 */
package com.xu.manager.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xu.manager.ClassUtil.EhCacheUtil;
import com.xu.manager.ClassUtil.ExportCarInformationUtils;
import com.xu.manager.Dto.CarInformationDto;
import com.xu.manager.Dto.DownLoadDto;
import com.xu.manager.bean.CarInformation;
import com.xu.manager.bean.LoginUser;
import com.xu.manager.bean.Pagination;
import com.xu.manager.bean.Price;
import com.xu.manager.bean.UploadParam;
import com.xu.manager.service.CarInformationService;
import com.xu.manager.service.OfficeDownloadService;
import com.xu.manager.service.UploadManager;

import net.sf.json.JSONObject;

/**
 * @author Create By Xuguoqiang
 * @date 2016年10月13日--下午10:01:34--
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/car")
public class CarInfoController extends BaseController {
    private String fileName = "";
	@Autowired
	private CarInformationService carInformationService;
	@Autowired 
	private UploadManager uploadManager;
	@Autowired
	private OfficeDownloadService officeDownloadService;

	@RequestMapping(value = "/EditCarManager.do")
	public ModelAndView initEditCarInfo() {
		return new ModelAndView("/system/car/carInfoList");

	}

	@RequestMapping(value = "/getCarInformation.do")
	@ResponseBody
	public String getCarInformation(CarInformationDto carInfoDto,HttpSession session) {
		//test11();
		LoginUser loginUser = (LoginUser) session.getAttribute("SESSION_LOGIN_USER");
		JSONObject jsonResult = new JSONObject();
		carInfoDto.setLimit(carInfoDto.getPgLimit());
		carInfoDto.setPgNumber(carInfoDto.getPgCurrentPage());
		Pagination carpagination = carInformationService.getCarInformation(carInfoDto);
		System.out.println(loginUser.getLoginName());
		jsonResult = com.xu.manager.ClassUtil.JsonUtils.toGridJson(carpagination.getTotalCount(),
				carpagination.getResultList());
		return jsonResult.toString();

	}

	@RequestMapping("/addCarInfo.do")
	public ModelAndView addCarInfo() {
		EhCacheUtil ehcacheUtil =  EhCacheUtil.getInstace();
		List<String> wordList = ehcacheUtil.getRuleWordInCache(ehcacheUtil.CACHE_NAME, ehcacheUtil.BASE_SCAN_WORD);
		if(CollectionUtils.isNotEmpty(wordList)){
			System.out.println("获取内存里面的缓存成功！");
			for(String word:wordList){
				System.out.println(word);
			}
		}
		return new ModelAndView("/system/car/addCarInfo");
		

	}

	// 保存车辆信息
	@RequestMapping(value = "/saveCarInfo.do")
	public ModelAndView saveCarInfo(HttpServletRequest request, CarInformation carInfo, HttpSession session) {
		String editorValue = request.getParameter("editorValue");
		Long id = com.xu.manager.ClassUtil.CarNumberCreateUtils.getCarNumber();
		carInfo.setCarId(id);
		carInfo.setCarInfoDetail(editorValue);
		LoginUser loginUser = (LoginUser) session.getAttribute("SESSION_LOGIN_USER");
		carInfo.setOwnerId(Long.parseLong(loginUser.getId().toString()));
		carInformationService.insertCarInformation(carInfo);
		return new ModelAndView("/system/car/carInfoList");

	}

	@RequestMapping(value = "/uploadCarInfoPic.do")
	public ModelAndView uploadCarInfoPic(@RequestParam("carId") String carId, Model model) {
		model.addAttribute("carId", carId);
		return new ModelAndView("/system/car/uploadCarInfoPic");
	}

	@RequestMapping(value = "/uploadImage.do")
	public void uploadToServer(@RequestParam("carId") Long carId,@RequestParam(value = "file", required = false) MultipartFile file, HttpSession session) {
		UploadParam param = new UploadParam();
		MultipartFile file1 = file; 
		String fileName=  file.getOriginalFilename();
		String bb = file.getContentType();
		System.out.println("aaa"+fileName+"b"+bb);
        CommonsMultipartFile cf= (CommonsMultipartFile)file; 
        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
        File f = fi.getStoreLocation();
        Long start = System.currentTimeMillis();
       	uploadManager.uploadFile(session, f, fileName, param,carId);
	}

	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/exportCarInfoExcel.do")  
	public void downloadExportlistLeave(HttpServletRequest request,
			HttpServletResponse response) {
		String fileName = "汽车信息.xls";
			//title.add(0,"姓名");
		CarInformationDto carInfoDto = new CarInformationDto();
		JSONObject jsonResult = new JSONObject();
		Pagination carpagination = carInformationService.getCarInformation(carInfoDto);
		List<CarInformation> list = carpagination.getResultList();
		List<String> titleList = ExportCarInformationUtils.getTitle();
		ExportCarInformationUtils.exportCarInformationUtils(list,titleList, request.getRealPath("/downloadExcel"), "/"+fileName);
		ImportExportAction.downloadExcel(request, response, fileName);
	}
	
	
	
	
	@RequestMapping(value = "/test.do")
	public void test(HttpServletRequest request,HttpServletResponse response,Model model){
		DownLoadDto downLoadDto = new DownLoadDto();
		downLoadDto.setFileName("汽车信息");
		downLoadDto.setOperatorId(1l);
		downLoadDto.setRequest(request);
		downLoadDto.setResponse(response);
		downLoadDto.setTaskId("NB"+System.currentTimeMillis());
		officeDownloadService.addTaskToQueue(downLoadDto);
	}

	

	// 写入测试数据

	public void test11() {
		String color[] = { "白色", "黑色", "黄色", "莹白", "红褐色", "灰色", "蓝色", "大黄色", "金色", "浅色", "褐色", "蓝黑", "大白" };
		String saleName[] = { "大众", "奥迪", "本田", "现代", "东风", "红旗", "雪佛兰", "凯迪拉克", "奇瑞", "标致", "路虎", "宝马", "奔驰", "jeep" };
		String country[] = { "美国", "日本", "中国", "英国", "意大利", "新西兰", "智力", "印度" };
		String email[] = { "27898@qq.com", "489589@qq.com", "xuguo@126.com", "34354@", "weewre", "2ewfwefw", "23rewfef",
				"wefef" };
		String detail = "<p>&nbsp; &nbsp;<span style=color: rgb(102, 102, 102); font-family: &quot;Microsoft Yahei&quot;; font-size: 14px; background-color: rgb(249, 249, 249);>虽然三四年迫击炮了，车况很好，正是好用的时候。公里数不算多，就跑了五万多公里。一直都是城市道路行驶，这辆车就是日常家用，上下班代步。一直都是在4S店做黄色的保养，加装倒车影像宅男系统、GPS导航。因个人原因AV出售爱车,炸弹雷管迫击炮有喜欢的朋友请尽快联系我吧，欢迎大家前来进行试驾，非诚勿扰呦。</span></p><p><span style=color: rgb(102, 102, 102); font-family: &quot;Microsoft Yahei&quot;; font-size: 14px; background-color: rgb(249, 249, 249);>&nbsp;&nbsp;&nbsp;&nbsp;<span style=color: rgb(102, 102, 102); font-family: &quot;Microsoft Yahei&quot;; font-size: 12px; background-color: rgb(249, 249, 249);>该车骨架完好，排除事故车，排除泡水车，排除火烧车，后部轻微追尾，轻微变形。</span></span></p><p><span style=color: rgb(102, 102, 102); font-family: &quot;Microsoft Yahei&quot;; font-size: 14px; background-color: rgb(249, 249, 249);>&nbsp;&nbsp;&nbsp;&nbsp;<span style=color: rgb(102, 102, 102); font-family: &quot;Microsoft Yahei&quot;; font-size: 14px; background-color: rgb(249, 249, 249);>这辆车买来刚不到一年，都是自己一直在用，内饰、外观都跟新车一样。用车的机会不多，开了一万多公里我的车平时就是城里开开，基本没跑过烂路。自家用车，正常使用。这车用的很爱惜，按时保养，整体车况还是不错的。加装倒车影像系统、GPS导航。准备换车了，所以出售此车。车况很好，欢迎喜欢的朋友来看车、试驾。</span></span></p>';";
		try {
			for (Long i = (long) 0; i < 1000000; i++) {
				LoginUser loginUser = new LoginUser();
          
				int Num = (int) (Math.random() * 12);
				double price1 = (long) (Math.random() * 100 + 1);
				CarInformation carInformation = new CarInformation();
				Long id = com.xu.manager.ClassUtil.CarNumberCreateUtils.getCarNumber();
				Price price = new Price();
				price.setCarId(id);
				price.setCarPrice(price1);
				carInformation.setPrice(price);
				// price.setCarPrice(1111d);
				carInformation.setCarAge(Num);
				carInformation.setCarDate(new Date());
				carInformation.setCarId(id);
				carInformation.setCarName(saleName[Num]);
				carInformation.setCarNameId((long) Num);
				carInformation.setColor(color[Num]);
				carInformation.setCarInfoDetail(detail);
				int Num1 = (int) (Math.random() * 7);

				carInformation.setCountry(country[Num1]);
				int Num2 = (int) (Math.random() * 7);
				// carInformation.setDisplaceMent(Num2);
				carInformation.setOwnerId(1l);
				carInformation.setStatus(1);
				carInformation.setVariableBox(1);
				double Num3 = (Math.random() * 20000 + 10000);
				carInformation.setTravelAge((long) Num3);
				carInformationService.insertCarInformation(carInformation);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
