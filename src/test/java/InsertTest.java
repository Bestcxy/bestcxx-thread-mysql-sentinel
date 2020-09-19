import com.bestcxx.stu.springmybatis.App;
import com.bestcxx.stu.springmybatis.model.IndexStu;
import com.bestcxx.stu.springmybatis.service.IndexStuService;
import com.bestcxx.stu.springmybatis.threadpool.ThreadPoolFactory;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class InsertTest {

    @Autowired
    private IndexStuService indexStuService;

    // durid:https://blog.csdn.net/qq_27634797/article/details/100560118

    @Test
    public void test() {
        final IndexStu indexStu = new IndexStu();
        indexStu.setUpdateTime(new Date());
        indexStu.setType("A");
        indexStu.setStatus(0);
        indexStu.setCreateTime(new Date());

        for (int i = 0; i < 730447; i++) {
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ThreadPoolFactory.newTask(ThreadPoolFactory.getThreadFactory().newThread(new Thread(){
                @Override
                public void run() {
                    super.run();
                    indexStuService.insert(indexStu);

                }
            }));

        }


    }

    @Test
    public void testList() {
        final IndexStu indexStu = new IndexStu();
        indexStu.setUpdateTime(new Date());
        indexStu.setType("A");
        indexStu.setStatus(0);
        indexStu.setCreateTime(new Date());

        final List list = Lists.newArrayList();
        for(int i=0;i<50;i++){
            list.add(indexStu);
        }

        for (int i = 0; i < 816; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ThreadPoolFactory.newTask(ThreadPoolFactory.getThreadFactory().newThread(new Thread(){
                @Override
                public void run() {
                    super.run();
                    indexStuService.insertList(list);

                }
            }));

        }


    }
}
