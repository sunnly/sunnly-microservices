package wang.sunnly.micro.services.scannable.tools.fastdfs.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import wang.sunnly.micro.services.scannable.tools.fastdfs.service.StorageService;
import wang.sunnly.micro.services.scannable.tools.fastdfs.service.impl.FastDFSStorageServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * StorageFactory
 *
 * @author Sunnly
 * @since 2019/6/28 0028 22:41
 */
public class StorageFactory implements FactoryBean<StorageService> {

    @Autowired
    private AutowireCapableBeanFactory acbf;

    /**
     * 存储服务的类型，目前仅支持fastdfs
     */
    private static final String TYPE = "fastdfs";

    private Map<String, Class<? extends StorageService>> classMap;

    public StorageFactory() {
        classMap = new HashMap<>();
        classMap.put("fastdfs", FastDFSStorageServiceImpl.class);
    }

    @Override
    public StorageService getObject() throws Exception {
        Class<? extends StorageService> clazz = classMap.get(StorageFactory.TYPE);
        if (clazz == null) {
            throw new RuntimeException("Unsupported storage type [" + StorageFactory.TYPE + "], valid are " + classMap.keySet());
        }

        StorageService bean = clazz.newInstance();
        acbf.autowireBean(bean);
        acbf.initializeBean(bean, bean.getClass().getSimpleName());
        return bean;
    }

    @Override
    public Class<?> getObjectType() {
        return StorageService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
