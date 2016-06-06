import com.spring.ioc.diy.manager.ClassPathXmlApplicationContext;
import com.spring.ioc.diy.model.Bean;
import com.spring.ioc.diy.model.Student;
import com.spring.ioc.diy.model.Teacher;
import org.junit.Test;

import java.util.Map;

/**
 * <p>测试用例</p>
 *
 * @author zongxin.xzx
 * @version 1.0
 * @date 创建时间 ：2016/6/5 12:57
 */
public class DiySpringIOC {

    public void testParseXml() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext();
        String path = "/applicationContext.xml";
        Map<String, Bean> beanMap = classPathXmlApplicationContext.parseXml(path);
        System.out.println(beanMap);
    }

    @Test
    public void testDiyIOC() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext();
        Teacher teacher = (Teacher) classPathXmlApplicationContext.getBean("teacher");
        System.out.println("teacher相关的属性输出:" + teacher);
        Student student = (Student) classPathXmlApplicationContext.getBean("student");
        System.out.println("student相关的属性输出:" + student);
    }
}
