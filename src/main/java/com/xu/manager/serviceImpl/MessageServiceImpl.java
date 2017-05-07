package com.xu.manager.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xu.manager.bean.Channel;
import com.xu.manager.dao.MessageDao;
import com.xu.manager.service.MessageService;

/**
* @author Create By Xuguoqiang
* @date   2017年5月7日--下午1:26:50--
*
*/
@Service
public class MessageServiceImpl implements MessageService{
	@Resource
	private MessageDao messageDao;

	@Override
	public List<Channel> getChannelList() {
		return messageDao.getChannelList();
	}

}
