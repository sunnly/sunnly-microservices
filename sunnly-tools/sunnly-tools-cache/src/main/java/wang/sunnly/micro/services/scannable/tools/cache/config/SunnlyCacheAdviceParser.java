package wang.sunnly.micro.services.scannable.tools.cache.config;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * SunnlyCacheAdviceParser
 *
 * @author Sunnly
 * @create 2019/7/7 0007 22:22
 */
public class SunnlyCacheAdviceParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        super.doParse(element, parserContext, builder);
    }
}

