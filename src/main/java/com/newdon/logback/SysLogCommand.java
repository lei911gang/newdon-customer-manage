package com.newdon.logback;

import com.netflix.hystrix.*;
import com.newdon.entity.SystemLog;
import com.newdon.mapper.SysLogMapper;

public class SysLogCommand extends HystrixCommand<Boolean> {
    private SysLogMapper sysLogMapper;

    public static final HystrixCommandKey COMMAND_KEY = HystrixCommandKey.Factory.asKey("SysLogCommand");
    private SystemLog sysLog;

    public SysLogCommand(SysLogMapper sysLogMapper, SystemLog sysLog) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SysLog"))
                .andCommandKey(COMMAND_KEY)
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("SysLogPool"))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(6)//默认10
                        .withMaxQueueSize(7)//默认关闭
                        .withQueueSizeRejectionThreshold(8)
                )//默认5
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withCircuitBreakerRequestVolumeThreshold(30)//默认20
                        .withCircuitBreakerErrorThresholdPercentage(40)//默认50
                        .withCircuitBreakerSleepWindowInMilliseconds(3000)//默认5000
                        .withExecutionTimeoutInMilliseconds(200)
                        .withFallbackIsolationSemaphoreMaxConcurrentRequests(30)
                )

        );
        this.sysLog = sysLog;
        this.sysLogMapper = sysLogMapper;
    }

    @Override
    protected Boolean run() {
        int insert = this.sysLogMapper.insert(sysLog);
        if (1 == insert) {
            return true;
        }
        return false;
    }

    @Override
    protected Boolean getFallback() {
        return false;
    }
}
