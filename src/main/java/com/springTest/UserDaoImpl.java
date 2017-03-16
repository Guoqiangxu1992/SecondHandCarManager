package com.springTest;

/**
* @author Create By Xuguoqiang
* @date   2017年3月16日--下午10:31:53--
*
*/
public class UserDaoImpl implements UserDao{
	private int daoCount = 0;

	public int getDaoCount() {
		return daoCount;
	}

	public void setDaoCount(int daoCount) {
		this.daoCount = daoCount;
	}

	@Override
	public void save() {
		System.out.println("-----save()"+daoCount);
	}
	
}
