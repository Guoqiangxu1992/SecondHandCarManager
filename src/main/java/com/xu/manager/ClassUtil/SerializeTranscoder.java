package com.xu.manager.ClassUtil;

import java.io.Closeable;
import java.io.Serializable;

import jxl.common.Logger;

/**
* @author Create By Xuguoqiang
* @date   2017年2月19日--下午7:23:11--
*
*/
public abstract class SerializeTranscoder implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2222961029748931006L;
	protected static Logger logger = Logger.getLogger(SerializeTranscoder.class);
	  
	  public abstract byte[] serialize(Object value);
	  
	  public abstract Object deserialize(byte[] in);
	  
	  public void close(Closeable closeable) {
	    if (closeable != null) {
	      try {
	        closeable.close();
	      } catch (Exception e) {
	         logger.info("Unable to close " + closeable, e); 
	      }
	    }
	  }

}
