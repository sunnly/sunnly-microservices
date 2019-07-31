package wang.sunnly.micro.services.scannable.tools.cache.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import wang.sunnly.micro.services.scannable.tools.cache.enums.SunnlyCacheMode;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * SunnlyAdviceModeImportSelector
 *
 * @author Sunnly
 * @since 2019/7/7 0007 20:30
 */
public abstract class AbstractCacheModeImportSelector<A extends Annotation> implements ImportSelector {

    @Override
    public final String[] selectImports(AnnotationMetadata importingClassMetadata) {

        Class<?> annType = GenericTypeResolver.resolveTypeArgument(getClass(), AbstractCacheModeImportSelector.class);

        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(annType.getName(), false);
        AnnotationAttributes attributes;
        if (annotationAttributes != null){
            if (annotationAttributes instanceof AnnotationAttributes){
                attributes =  (AnnotationAttributes) annotationAttributes;
            }else {
                attributes = new AnnotationAttributes(annotationAttributes);
            }
        }else{
            throw new IllegalArgumentException(String.format(
                    "@%s is not present on importing class '%s' as expected",
                    annType.getSimpleName(), importingClassMetadata.getClassName()));
        }
        SunnlyCacheMode sunnlyCacheMode = attributes.getEnum("value");
        String[] imports = selectImports(sunnlyCacheMode);
        if (imports == null){
            throw new IllegalArgumentException("Unknown value: " + sunnlyCacheMode);
        }
        return imports;
    }

    protected abstract String[] selectImports(SunnlyCacheMode cacheMode);
}
