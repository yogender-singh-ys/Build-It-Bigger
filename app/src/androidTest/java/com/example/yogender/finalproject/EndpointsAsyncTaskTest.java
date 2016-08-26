package com.example.yogender.finalproject;

import android.test.InstrumentationTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class EndpointsAsyncTaskTest extends InstrumentationTestCase implements OnJokeLoad {

    private static String jokeTextResult;
    private static boolean called;
    private CountDownLatch signal;
    final String localEndpoint = "http://10.0.2.2:8080/_ah/api/";
    final String testEndpoint = "https://builditbigger-1baef.appspot.com/_ah/api/";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        signal = new CountDownLatch(1);
    }

    @Override
    public void jokeLoadTaskHandler(String intentText) {
        called = true;
        jokeTextResult = intentText;
        signal.countDown();
    }

    public void testEndpointsAsyncTask() throws InterruptedException {
        new EndpointsAsyncTask(this).execute(testEndpoint);
        signal.await(30, TimeUnit.SECONDS);
        assertTrue(called);
        assertNotNull(jokeTextResult);
        Boolean stringIsEmpty = jokeTextResult.equals("");
        assertFalse(stringIsEmpty);
    }

    public void testEndpointsAsyncTaskLocal() throws InterruptedException {
        new EndpointsAsyncTask(this).execute(localEndpoint);
        signal.await(30, TimeUnit.SECONDS);
        assertTrue(called);
        assertNotNull(jokeTextResult);
        Boolean stringIsEmpty = jokeTextResult.equals("");
        assertFalse(stringIsEmpty);
    }

    @Override
    protected void runTest() throws Throwable {
        super.runTest();
    }
}
