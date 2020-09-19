import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Del {
    public static void main(String [] args){

        ExecutorService threadPools = Executors.newFixedThreadPool(1);
        Thread t1 = new Thread("我的线程1"){
            @Override
            public void run() {
                super.run();
                int i=1;
                    while(i<10000000){
                        i=i+1;
                        //System.out.println("i="+i);
                    }
                System.out.println(i);

            }
        };
        threadPools.submit(t1);
        threadPools.submit(t1);

    }
}
