package com.fj.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class HelloWorldServer {
    private static final Logger logger = Logger.getLogger(HelloWorldServer.class.getName());

    /* The port on which the server should run */
    private int port = 8998;
    private Server server;

    private void start() throws IOException {
        server = ServerBuilder.forPort(port)
                .addService(GreeterGrpc.bindService(new GreeterImpl()))
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                HelloWorldServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     * 设置守护线程
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        final HelloWorldServer server = new HelloWorldServer();
        server.start();
        server.blockUntilShutdown();
    }

    private class GreeterImpl implements GreeterGrpc.Greeter {
        /** 原子Integer */
        public AtomicInteger count = new AtomicInteger(0);

        @Override
        public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
            System.out.println("call sayHello");
            //处理接受到的消息
            HelloReply reply = HelloReply.newBuilder().setMessage("Hello I am " + req.getName() + req.getSex()).build();

            String message = reply.getMessage();
            System.out.println("接收到的消息："+message);

            responseObserver.onNext(reply);
            responseObserver.onCompleted();
            System.out.println(count.incrementAndGet() + Thread.currentThread().getName());
        }
    }

}
