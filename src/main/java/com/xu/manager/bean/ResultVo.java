package com.xu.manager.bean;

import java.util.List;

/**
* @author Create By Xuguoqiang
* @date   2016年12月14日--下午8:27:43--
*
*/
public class ResultVo {
	private boolean hasSensitive;
	private List<String> wordList;
	public boolean isHasSensitive() {
		return hasSensitive;
	}
	public void setHasSensitive(boolean hasSensitive) {
		this.hasSensitive = hasSensitive;
	}
	public List<String> getWordList() {
		return wordList;
	}
	public void setWordList(List<String> wordList) {
		this.wordList = wordList;
	}

}
