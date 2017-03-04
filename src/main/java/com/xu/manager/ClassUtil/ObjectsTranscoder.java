package com.xu.manager.ClassUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
* @author Create By Xuguoqiang
* @date   2017年2月19日--下午7:22:10--
*
*/
public class ObjectsTranscoder <M extends Serializable> extends SerializeTranscoder{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8741537187504990524L;

	@SuppressWarnings("unchecked")
	  @Override
	  public byte[] serialize(Object value) {
	    if (value == null) {  
	      throw new NullPointerException("Can't serialize null");  
	    }  
	    byte[] result = null;  
	    ByteArrayOutputStream bos = null;  
	    ObjectOutputStream os = null;  
	    try {  
	      bos = new ByteArrayOutputStream();  
	      os = new ObjectOutputStream(bos);
	      M m = (M) value;
	      os.writeObject(m);  
	      os.close();  
	      bos.close();  
	      result = bos.toByteArray();  
	    } catch (IOException e) {  
	      throw new IllegalArgumentException("Non-serializable object", e);  
	    } finally {  
	      close(os);  
	      close(bos);  
	    }  
	    return result;  
	  }

	  @SuppressWarnings("unchecked")
	  @Override
	  public M deserialize(byte[] in) {
	    M result = null;  
	    ByteArrayInputStream bis = null;  
	    ObjectInputStream is = null;  
	    try {  
	      if (in != null) {  
	        bis = new ByteArrayInputStream(in);  
	        is = new ObjectInputStream(bis);  
	        result = (M) is.readObject();  
	        is.close();  
	        bis.close();  
	      }  
	    } catch (IOException e) {  
	     /* LoggerUtils.error(logger, String.format("Caught IOException decoding %d bytes of data",  
	          in == null ? 0 : in.length) + e); */ 
	    } catch (ClassNotFoundException e) {  
	     /* LoggerUtils.error(logger, String.format("Caught CNFE decoding %d bytes of data",  
	          in == null ? 0 : in.length) + e);*/  
	    } finally {  
	      close(is);  
	      close(bis);  
	    }  
	    return result;  
	  }

}
