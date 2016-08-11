package cn.ffcs.xhService.core.dao;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisDaoImpl implements RedisDao {

	@Autowired
	private StringRedisTemplate redisTemplate;

	/**
	 * 设置key
	 * */
	public void set(final String key, final String value) {
		if(StringUtils.isEmpty(key)) {
			return;
		}
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
				redisConnection.set(key.getBytes(), value.getBytes());
				return null;
			}
		});
	}

	/**
	 * 获取key对应值
	 * */
	public String get(final String key) {
		if(StringUtils.isEmpty(key)) {
			return "";
		}
		return redisTemplate.execute(new RedisCallback<Object>() {
			public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
				try{
					byte[] retBytes = redisConnection.get(key.getBytes());
					if(retBytes == null || retBytes.length <= 0) {
						return "";
					}
					return new String(retBytes, "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				return "";
			}
		}).toString();
	}

	/**
	 * 设置到期时间
	 * */
	public boolean expireAt(final String key, final Date date) {
		if(StringUtils.isEmpty(key) || date == null) {
			return false;
		}
		return redisTemplate.expireAt(key, date);
	}
	
	/**
	 * 设置到期时长
	 * */
	public boolean expireAt(String key, Long time, TimeUnit unit) {
		if(StringUtils.isEmpty(key) || unit == null) {
			return false;
		}
		return redisTemplate.expire(key, time, unit);
	}

	/**
	 * 删除key
	 * */
	public void delete(final String key) {
		if(StringUtils.isEmpty(key)) {
			return;
		}
		redisTemplate.delete(key);
	}

	/**
	 * 清除满足前缀的key
	 * */
	public void clear(final String pattern) {
		if(StringUtils.isEmpty(pattern)) {
			return;
		}
		Set<String> keys = redisTemplate.keys(pattern + "*");
		if(!keys.isEmpty()) {
			redisTemplate.delete(keys);
		}
	}

	/**
	 * 获取满足前缀的key
	 * */
	public Set<String> getKyes(final String pattern) {
		if(StringUtils.isEmpty(pattern)) {
			return null;
		}
		return redisTemplate.keys(pattern + "*");
	}

	/**
	 * 是否存在key
	 * */
	public boolean exists(final String key) {
		if(StringUtils.isEmpty(key)) {
			return false;
		}
		return redisTemplate.hasKey(key);
	}
	
	
	/**
	 * 获取过期时长
	 * */
	public Long getExpire(String key, TimeUnit unit) {
		if (StringUtils.isEmpty(key) || unit == null) {  
            return null;
        }  
        return redisTemplate.getExpire(key, unit);
	}

	/**
	 * 获取过期时间
	 * */
	public Long getExpire(String key) {
		if (StringUtils.isEmpty(key)) {  
            return null;
        }  
        return redisTemplate.getExpire(key);
	}

}
