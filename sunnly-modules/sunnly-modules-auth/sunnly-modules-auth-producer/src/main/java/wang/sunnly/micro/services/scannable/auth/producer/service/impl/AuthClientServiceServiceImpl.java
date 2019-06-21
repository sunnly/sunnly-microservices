package wang.sunnly.micro.services.scannable.auth.producer.service.impl;

import org.springframework.stereotype.Service;
import wang.sunnly.micro.services.scannable.auth.producer.mapper.AuthClientServiceMapper;
import wang.sunnly.micro.services.scannable.auth.producer.service.AuthClientServiceService;

import javax.annotation.Resource;

@Service
public class AuthClientServiceServiceImpl implements AuthClientServiceService {

    @Resource
    private AuthClientServiceMapper authClientServiceMapper;

}
