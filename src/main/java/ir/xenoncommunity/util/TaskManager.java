package ir.xenoncommunity.util;

import java.util.concurrent.*;

@SuppressWarnings("unused") public class TaskManager {
    private final ExecutorService executorService;
    private final ScheduledExecutorService scheduledExecutorService;
    public TaskManager(){
        this.executorService = new ThreadPoolExecutor(4,4,0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    }
    public synchronized void add(final Runnable runnable){
        executorService.submit(() -> {
            try{
                runnable.run();
            }catch(final Exception e){
                e.printStackTrace();
            }
        });
    }
    public synchronized void repeatingTask(final Runnable runnable, final int initialDelay, final int delay, final TimeUnit time){
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            try {
                runnable.run();
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }, initialDelay, delay, time);
    }
}
