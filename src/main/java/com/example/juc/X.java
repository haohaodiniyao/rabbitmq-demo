package com.example.juc;

import java.util.concurrent.locks.ReentrantLock;

class X { private final ReentrantLock lock = new ReentrantLock();
// ... public void m() {
// lock.lock();
// block until condition holds try {
// ... method body } finally { lock.unlock() } } }