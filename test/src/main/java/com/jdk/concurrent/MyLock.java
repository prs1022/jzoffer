package com.jdk.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * 基于juc，CAS ,Park unPark 实现 独占锁、乐观锁
 *
 * @author purensong
 * @date 2019/4/9 13:09
 */

public class MyLock implements Lock {

    /**
     * 利用CAS实现自旋锁
     */
    private AtomicReference<Thread> owner = new AtomicReference<Thread>();

    private BlockingQueue<Thread> waiter = new LinkedBlockingQueue<Thread>();

    @Override
    public void lock() {
        //不断加取 乐观锁
        while (!(owner.compareAndSet(null, Thread.currentThread()))) {
            //进入等待队列
            waiter.add(Thread.currentThread());
            LockSupport.park();
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        //抢到锁
        if (owner.compareAndSet(Thread.currentThread(), null)) {
            //移除当前线程，[很关键]
            waiter.remove(Thread.currentThread());
            //通知等锁队列
            Thread t = null;
            while ((t = waiter.poll()) != null) {
                LockSupport.unpark(t);
            }
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
