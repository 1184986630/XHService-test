package cn.ffcs.xhService.core.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class CommonDaoImpl extends BaseDao implements CommonDao {

	public <T> int insertInfo(String opType, T instance) {
		return this.getSqlSession().insert(opType, instance);
	}
	
	public int deleteInfo(String opType, Map<String, Object> index) {
		return this.getSqlSession().delete(opType, index);
	}
	
    public int updateInfo(String opType, Map<String, Object> index) {
    	return this.getSqlSession().update(opType, index);
    }
	
	public <T> List<T> selectInfo(String opType, Map<String, Object> index) {
		return this.getSqlSession().selectList(opType, index);
	}
	
	public int selectInfoCount(String opType, Map<String, Object> index) {
		return (Integer)this.getSqlSession().selectOne(opType, index);
	}

	public <T> int insertInfos(String opType, List<T> list,
			boolean isRollBack) {
		return super.batchInsert(opType, list, isRollBack);
	}

	public int updateInfos(String opType, List<?> index) {
		return super.batchUpdate(opType, index);
	}

	public int deleteInfos(String opType, List<?> list) {
		return super.batchDelete(opType, list);
	}
	
}
