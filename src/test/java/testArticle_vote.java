import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xu.manager.ClassUtil.ObjectsTranscoder;
import com.xu.manager.ClassUtil.RedisClient;
import com.xu.manager.bean.MenuBean;

import redis.clients.jedis.Jedis;

/**
* @author Create By Xuguoqiang
* @date   2017年2月19日--下午2:19:32--
*
*/
public class testArticle_vote implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2491502352176476678L;
	private static final int ONE_WEEK_IN_SECONDS = 7 * 86400;
    private static final int VOTE_SCORE = 432;
    private static final int ARTICLES_PER_PAGE = 25;
    Jedis jedis = null;
    
    public void articleVote(Jedis conn, String user, String article) {
    	System.out.println(conn.get("name"));
        long cutoff = (System.currentTimeMillis() / 1000) - ONE_WEEK_IN_SECONDS;
        if (conn.zscore("time:", article) < cutoff){
            return;
        }

        String articleId = article.substring(article.indexOf(':') + 1);
        if (conn.sadd("voted:" + articleId, user) == 1) {
            conn.zincrby("score:", VOTE_SCORE, article);
            conn.hincrBy(article, "votes", 1l);
        }
    }
    
    
    public String postArticle(Jedis conn, String user, String title, String link) {
        String articleId = String.valueOf(conn.incr("article:"));

        String voted = "voted:" + articleId;
        conn.sadd(voted, user);
        conn.expire(voted, ONE_WEEK_IN_SECONDS);

        long now = System.currentTimeMillis() / 1000;
        String article = "article:" + articleId;
        HashMap<String,String> articleData = new HashMap<String,String>();
        articleData.put("title", title);
        articleData.put("link", link);
        articleData.put("user", user);
        articleData.put("now", String.valueOf(now));
        articleData.put("votes", "1");
        conn.hmset(article, articleData);
        conn.zadd("score:", now + VOTE_SCORE, article);
        conn.zadd("time:", now, article);

        return articleId;
    }

	@Test
	public void test() throws Exception {
		 jedis = new Jedis("192.168.5.130", 6379);
		 List<MenuBean> list = new ArrayList<>();
		 
		 MenuBean menuBean = new MenuBean();
		 menuBean.setId("hahahahah");
		 menuBean.setLev(1);
		 menuBean.setMenuName("哟嘻嘻嘻");
		 list.add(menuBean);
		 RedisClient.set("xuxu1111", "menuBean");
		 RedisClient.set("xuxu1111", menuBean);
		 MenuBean menuBean1= (MenuBean) RedisClient.get("xuxu");
		 ObjectsTranscoder<MenuBean> objTranscoder =  new ObjectsTranscoder<>();
		 byte[] result1 = objTranscoder.serialize(objTranscoder);
		// MenuBean menuBean1 = objTranscoder.deserialize(result1);
		 String objectJson = JSON.toJSONString(menuBean);
		 jedis.set("menu1", objectJson);
		 MenuBean menu = JSON.parseObject(jedis.get("menu1"), MenuBean.class);
		    
		 jedis.set("menu".getBytes(), result1);
		 byte[] result2 = jedis.get("menu".getBytes());
		// MenuBean menuBean222 = objTranscoder.deserialize(result2);
		    
		    //System.out.println(menuBean1.getMenuName());
		// jedis.get(key)
		 this.articleVote(jedis, "xuguoqiang111", "article:4");
		 this.postArticle(jedis, "xuguoqiang", "article", "xuguoqiang");
		 Object a = jedis.hgetAll("article:4");
		 Object a1 = jedis.zrangeWithScores("score:", 0, -1).toArray();
		 //Object a1 = jedis.getrange("article:7", 0, 4);
		 this.articleVote(jedis, "xuguoqiang", "article:4");
		
	}
	
	
	

}
