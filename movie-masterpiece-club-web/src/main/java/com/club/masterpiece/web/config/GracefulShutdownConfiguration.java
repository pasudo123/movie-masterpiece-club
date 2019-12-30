//package com.club.masterpiece.web.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.catalina.Executor;
//import org.apache.catalina.connector.Connector;
//import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.event.ContextClosedEvent;
//
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
///**
// * [ 우아한 종료 ]
// * - executable.jar 로 웹 컨테이너를 띄운 후에 업데이트 등의 이슈로 서버를 재시작 하는 경우가 있다.
// * - 서버를 바로 종료 시, 작업하던 것을 응답하지 못한 채로 종료된다.
// * - 이에 따라 처리 중인 작업이 있다면 이를 마무리하고 종료시키는 처리가 필요하다.
// * => GracefulShutdown
// *
// * Created by pasudo123 on 2019-11-12
// * Email: oraedoa@gmail.com
// **/
//@Configuration
//@Slf4j
//public class GracefulShutdownConfiguration implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {
//
//    private static final int TIMEOUT = 30;
//
//    private volatile Connector connector;
//
//    @Override
//    public void customize(Connector connector) {
//        this.connector = connector;
//    }
//
//    @Override
//    public void onApplicationEvent(ContextClosedEvent event) {
//
//        this.connector.pause();
//
//        Executor executor = (Executor) this.connector.getProtocolHandler().getExecutor();
//
//        if(!(executor instanceof ThreadPoolExecutor)) {
//            return;
//        }
//
//        try{
//
//            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
//            threadPoolExecutor.shutdown();
//
//            if (threadPoolExecutor.awaitTermination(TIMEOUT, TimeUnit.SECONDS)) {
//                log.info("Tomcat thread pool has been gracefully shutdown");
//                return;
//            }
//
//            log.warn("Tomcat thread pool did not shut down gracefully within {} seconds. Processing with forceful shutdown", TIMEOUT);
//
//            threadPoolExecutor.shutdownNow();
//
//            if (!threadPoolExecutor.awaitTermination(TIMEOUT, TimeUnit.SECONDS)) {
//                log.error("Tomcat thread pool did not terminate");
//            }
//
//        } catch (InterruptedException e) {
//
//            Thread.currentThread().interrupt();
//        }
//    }
//}
