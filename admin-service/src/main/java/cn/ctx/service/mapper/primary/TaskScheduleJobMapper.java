package cn.ctx.service.mapper.primary;

import cn.ctx.service.model.ScheduleJob;

public interface TaskScheduleJobMapper {
    int deleteByPrimaryKey(Long jobId);

    int insert(ScheduleJob record);

    int insertSelective(ScheduleJob record);

    ScheduleJob selectByPrimaryKey(Long jobId);

    int updateByPrimaryKeySelective(ScheduleJob record);

    int updateByPrimaryKey(ScheduleJob record);
}