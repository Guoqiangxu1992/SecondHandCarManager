/**
 * 
 */
package com.xu.manager.bean;

import java.io.Serializable;

/**
* @author Create By Xuguoqiang
* @date   2016年9月25日--下午5:35:00--
*
*/
/**
 * @author summer
 *
 */
public abstract class BaseDto implements Serializable{
	public final static String DIRECTION_DESC = "DESC";
    public final static String DIRECTION_ASC  = "ASC";

    private int                start = 0;

    /** 每页显示的限制条数 */
    private int                limit          = 20;

    /**
     * 数据库字段名称
     */
    private String             sort;

    /**
     * 方向
     */
    private String             dir;

    private boolean            needCount;

    private int                totalCount;

    final public int getTotalCount() {
        return totalCount;
    }

    final public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    final public void calStart() {
        if (start >= totalCount) {
            start = ((int) ((totalCount - 1) / limit)) * limit;
        }
    }

    final public void setPgNumber(int pgNumber) {
        if (pgNumber < 1)
            pgNumber = 1;

        start = (pgNumber - 1) * limit;
    }

    final public int getPgNumber() {
        return start / limit + 1;
    }

    final public int getEnd() {
        return this.start + this.limit;
    }

    final public int getStart() {
        return start;
    }

    final public void setStart(int start) {
        this.start = start;
    }

    final public int getLimit() {
        return limit;
    }

    final public void setLimit(int limit) {
        this.limit = limit;
    }

    final public boolean isNeedCount() {
        return needCount;
    }

    final public void setNeedCount(boolean needCount) {
        this.needCount = needCount;
    }

    final public String getSort() {
        return sort;
    }

    final public void setSort(String sort) {
        this.sort = sort;
    }

    final public String getDir() {
        return dir;
    }

    final public void setDir(String dir) {
        this.dir = dir;
    }


}
