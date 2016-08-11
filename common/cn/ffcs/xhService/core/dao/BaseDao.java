package cn.ffcs.xhService.core.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.executor.BatchExecutorException;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.ffcs.xhService.utils.Constants;

public class BaseDao extends SqlSessionDaoSupport {
	
	private static final Logger logger = LoggerFactory.getLogger(Thread
            .currentThread().getStackTrace()[1].getClassName());
	
    @Resource(name = "sqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;
    
    @PostConstruct
    public void SqlSessionFactory() {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    
    public int batchInsert(String statement, List<?> list, boolean isRollBack) {
    	SqlSession batchSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
    	int i = 0;
    	int cntError = 0;
    	int startRow = 0;
    	for(int cnt = list.size(); i < cnt; i++) {
    		batchSession.insert(statement, list.get(i));
    		if((i + 1) % Constants.BATCH_DEAL_NUM == 0 || i == cnt - 1) {
    			try {
					batchSession.flushStatements();
					startRow = i + 1;
				} catch (BatchExecutorException e) {
					if(isRollBack) {
						logger.error("批量插入异常", e);
						batchSession.rollback();
						throw e;
					}
					cntError++;
					i = startRow + e.getSuccessfulBatchResults().size();
					startRow = i + 1;
					logger.debug("批量插入异常:" + e.toString());
				} catch (PersistenceException e) {
					if(isRollBack) {
						logger.error("批量插入异常", e);
						batchSession.rollback();
						throw e;
					}
					cntError++;
					BatchExecutorException ex = (BatchExecutorException) e.getCause();
					i = startRow + ex.getSuccessfulBatchResults().size();
					startRow = i + 1;
					logger.debug("批量插入异常:" + e.toString());
				} catch (Exception e) {
					if(isRollBack) {
						logger.error("批量插入异常", e);
						batchSession.rollback();
					}
					cntError++;
					BatchExecutorException ex = (BatchExecutorException) e.getCause();
					i = startRow + ex.getSuccessfulBatchResults().size();
					startRow = i + 1;
					logger.debug("批量插入异常:" + e.toString());
				}
    		}
    	}
    	batchSession.close();
    	return i - cntError;
    }
    
    public int batchUpdate(String statement, List<?> list) {
    	SqlSession batchSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
    	int i = 0;
    	for(int cnt = list.size(); i < cnt; i++) {
    		batchSession.update(statement, list.get(i));
    		if((i + 1) % Constants.BATCH_DEAL_NUM == 0) {
    			batchSession.flushStatements();
    		}
    	}
    	batchSession.flushStatements();
    	batchSession.close();
    	return i;
    }
    
    public int batchDelete(String statement, List<?> list) {
    	SqlSession batchSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
    	int i = 0;
    	for(int cnt = list.size(); i < cnt; i++) {
    		batchSession.delete(statement, list.get(i));
    		if((i + 1) % Constants.BATCH_DEAL_NUM == 0) {
    			batchSession.flushStatements();
    		}
    	}
    	batchSession.flushStatements();
    	batchSession.close();
    	return i;
    }
}
