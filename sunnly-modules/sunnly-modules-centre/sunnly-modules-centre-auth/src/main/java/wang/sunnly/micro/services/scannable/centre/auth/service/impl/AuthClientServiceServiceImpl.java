package wang.sunnly.micro.services.scannable.centre.auth.service.impl;

import org.springframework.stereotype.Service;
import wang.sunnly.micro.services.scannable.centre.auth.mapper.AuthClientServiceMapper;
import wang.sunnly.micro.services.scannable.centre.auth.service.AuthClientServiceService;

import javax.annotation.Resource;

@Service
public class AuthClientServiceServiceImpl implements AuthClientServiceService {

    @Resource
    private AuthClientServiceMapper authClientServiceMapper;

}
