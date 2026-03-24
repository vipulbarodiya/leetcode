class Foo {
    Semaphore a, b, c;
    public Foo() {
        a = new Semaphore(1);
        b = new Semaphore(0);
        c = new Semaphore(0); 

    }

    public void first(Runnable printFirst) throws InterruptedException {
        
        // printFirst.run() outputs "first". Do not change or remove this line.
        a.acquire();
        printFirst.run();
        b.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        
        // printSecond.run() outputs "second". Do not change or remove this line.
        b.acquire();
        printSecond.run();
        c.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        
        // printThird.run() outputs "third". Do not change or remove this line.
        c.acquire();
        printThird.run();
        a.release();
    }
}