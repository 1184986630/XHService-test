package cn.ffcs.xhService.core.dao;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface RedisDao {

	/**
	 * 设置值
	 * @param key 键
	 * @param value 值
	 */
	public void set(final String key, final String value);

	/**
	 * 获取值
	 * @param key 键
	 * @return 值
	 */
	public String get(final String key);
	
	/**
	 * 删除
	 * @param key 键
	 */
	public void delete(final String key);

	/**
	 * 删除全部相关键值
	 * @param pattern 前缀，方便统一删除
	 */
	public void clear(final String pattern);

	public Set<String> getKyes(final String pattern);
	
	/**
	 * 判断key是否存在
	 * */
	public boolean exists(final String key);
	
	
	/**
	 * 设置过期
	 * @param key 键
	 * @param date 过期时间
	 */
	public boolean expireAt(final String key, final Date date);
	
	/**
	 * 设置过期时长
	 * @param key 键
	 * @param Long 过期时长
	 */
	public boolean expireAt(final String key, final Long time, TimeUnit unit);
	
	/**
	 * 获取key的过期时长
	 * */
	public Long getExpire(String key, TimeUnit unit); 
	
	/**
	 * 获取key的过期时间
	 * */
	public Long getExpire(String key); 
}
