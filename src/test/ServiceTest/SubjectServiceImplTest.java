package ServiceTest;

import org.junit.Test;
import pojo.Subject;
import pojo.User;
import service.SubjectService;
import service.impl.SubjectServiceImpl;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author yaowenhao
 * @Title SubjectServiceImplTest
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/15 22:41
 */
public class SubjectServiceImplTest {

    SubjectService subjectService = new SubjectServiceImpl();

    @Test
    public void add() throws Exception {
        Subject subject = new Subject();
        subject.setTitle("这是一个测试类");
        subject.setStartTime(System.currentTimeMillis());
        subject.setEndTime(System.currentTimeMillis());
        subject.setNumber(1);
        User user = new User();
        user.setId((long) 10);
        subjectService.add(subject, user);
    }

    @Test
    public void list() throws Exception{
        List<Subject> list = subjectService.list();
        for (Subject subject:
             list) {
            System.out.println(subject.toString());
        }
    }
}