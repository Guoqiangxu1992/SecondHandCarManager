package com.xu.manager.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Redis.RedisHashOperations;
import com.Redis.RedisListOperations;
import com.Redis.RedisValueOperations;
import com.xu.manager.bean.CarInformation;

/**
* @author Create By Xuguoqiang
* @date   2017年5月9日--下午9:40:23--
*
*/
@RequestMapping("/testController")
@Controller
public class TestController {
	@Autowired
	private RedisValueOperations redisValueOperations;
	@Autowired
	private RedisListOperations redisListOperations;
	
	@Autowired
	private RedisHashOperations redisHashOperations;
	
	@RequestMapping("/getValue.do")
	public void getValue(){
		CarInformation carInformation=new CarInformation();
		carInformation.setCarName("hahahhahahaha哈哈哈");
		redisValueOperations.saveValue("redisValueOperations",carInformation, 60l);
		CarInformation carInformation1 = (CarInformation) redisValueOperations.getValue("redisValueOperations");
		System.out.println(carInformation1.getCarName());
		CarInformation carInformation2=new CarInformation();
		carInformation2.setCarName("hahahhahahaha哈哈哈");
		List<CarInformation> list = new ArrayList<>();
		list.add(carInformation2);
		redisListOperations.saveList("carInformation2List", list);
		List<Object> list2  = redisListOperations.getList("carInformation2List", 0l, -1l);
		
		redisHashOperations.saveHash("carInformation2", "carInformation2", carInformation2);
		CarInformation carInformation3  =(CarInformation) redisHashOperations.getHash("carInformation2", "carInformation2");
	}

}
