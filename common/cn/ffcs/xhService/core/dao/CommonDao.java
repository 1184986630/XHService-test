package cn.ffcs.xhService.core.dao;

import java.util.List;
import java.util.Map;

public interface CommonDao {
	
	public <T> List<T> selectInfo(String opType, Map<String, Object> index);
	
	public int selectInfoCount(String opType, Map<String, Object> index);
	
	public <T> int insertInfo(String opType, T instance);
	
	public <T> int insertInfos(String statement, List<T> list, boolean isRollBack);
	
    public int updateInfo(String opType, Map<String, Object> index);
    
    public int updateInfos(String opType, List<?> index);

    public int deleteInfo(String opType, Map<String, Object> index);
    
    public int deleteInfos(String opType, List<?> index);
}
