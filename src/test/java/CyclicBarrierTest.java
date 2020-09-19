import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    /**循环栅栏*/
    private static CyclicBarrier c1=new CyclicBarrier(3,new Thread() {

        @Override
        public void run() {
            super.run();
            System.out.println(System.currentTimeMillis()+":乘务员：请大家系好安全带，即将出发");
        }

    });

    public static void main(String[] args) {
        //测试循环栅栏的效果
        for(int i=1;i<=6;i++) {
            Thread t=new Thread("name"+i) {
                @Override
                public void run() {
                    super.run();
                    try {
                        Thread.sleep(1000);
                        System.out.println(System.currentTimeMillis()+" "+Thread.currentThread().getName()+":已上车");
                        c1.await();
                        System.out.println(System.currentTimeMillis()+" "+Thread.currentThread().getName()+":系好安全带了");
                    } catch (InterruptedException | BrokenBarrierException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            };
            t.start();

        }

    }

}
