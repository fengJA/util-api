package com.fj.grpc.client.service;

import com.fj.grpc.GreeterGrpc;
import com.fj.grpc.HelloReply;
import com.fj.grpc.HelloRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloWorldClient {
    private static final Logger logger = Logger.getLogger(HelloWorldClient.class.getName());

    private final ManagedChannel channel;
//    private final GreeterGrpc.GreeterBlockingStub blockingStub;

    @GrpcClient("grpc-demo")
    private static GreeterGrpc.GreeterBlockingStub blockingStub;

    /** Construct client connecting to HelloWorld server at {@code host:port}. */
    public HelloWorldClient(String host, int port) {

        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build();
        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /** Say hello to server. */
   /* public void greet(String name) {
        logger.info("Will try to greet " + name + " ...");
        // 设置请求参数
        HelloRequest request = HelloRequest.newBuilder().setName(name).setSex(" 女").build();
        HelloReply response;
        try {
            // 接收到相应参数
            response = blockingStub.sayHello(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
        logger.info("Greeting: " + response.getMessage());
    }*/

    /**
     * Greet server. If provided, the first element of {@code args} is the name to use in the
     * greeting.
     */
    public static void main(String[] args) throws Exception {
        /*HelloWorldClient client = new HelloWorldClient("localhost", 8998);
        try {

            String user = "world";
            if (args.length > 0) {
                user = args[0];
            }
            client.greet(user);
        } finally {
            client.shutdown();
        }*/

        HelloRequest request = HelloRequest.newBuilder().setName("小明").setSex("女孩").build();

        String name = HelloRequest.getDescriptor().getName();
        System.out.printf("name is:", name);

        HelloReply response = blockingStub.sayHello(request);

        String message = response.getMessage();
        System.out.println("response message:"+message);
    }
}
