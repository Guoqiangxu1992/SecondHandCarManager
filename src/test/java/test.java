import java.io.Closeable;
import java.io.IOException;

import org.junit.Test;

import junit.framework.TestCase;
import redis.clients.jedis.Jedis;

/**
* @author Create By Xuguoqiang
* @date   2017年2月18日--下午10:25:22--
*
*/
public class test extends TestCase {
	@Test  
    public void test() {  
        String key = "name";  
        Jedis jedis = null;  
          
        try {  
            jedis = new Jedis("192.168.5.130", 6379);
            System.out.println(jedis.get(key));  
            System.out.println(jedis.type(key));  
              
            System.out.println("key:" + key + ", tyep:" + jedis.type(key) + "; value:" + jedis.get(key));  
            jedis.del(key);     //del  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } finally {  
            if (jedis != null) {  
                try {
					((Closeable) jedis).close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
            }  
              
        }  
	

}}