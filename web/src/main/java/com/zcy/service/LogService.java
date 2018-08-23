package com.zcy.service;

import com.zcy.dao.LogDao;
import com.zcy.domain.LogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zcy on 2018/8/23.
 */
@Service
public class LogService {
    @Autowired
    private LogDao logDao;

    //插入行为轨迹
    public int insertTrack(LogInfo logInfo){
        int i = logDao.insertTrack(logInfo);
        return i;
    }
}
