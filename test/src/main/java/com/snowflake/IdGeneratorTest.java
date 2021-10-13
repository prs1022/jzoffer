//package com.snowflake;
//
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * @author rensong.pu
// * @date 2020/8/4 11:02 星期二
// **/
//public class IdGeneratorTest {
//    /** logger */
//    private static final Logger log = LoggerFactory.getLogger(IdGeneratorTest.class);
//    @Autowired
//    private IdGenerator idGenerator;
//
//    @Test
//    public void testBatchId() {
//        for (int i = 0; i < 100; i++) {
//            String batchId = idGenerator.batchId(1001, 100);
//            log.info("批次号: {}", batchId);
//        }
//    }
//
//    @Test
//    public void testSimpleUUID() {
//        for (int i = 0; i < 100; i++) {
//            String simpleUUID = idGenerator.simpleUUID();
//            log.info("simpleUUID: {}", simpleUUID);
//        }
//    }
//
//    @Test
//    public void testRandomUUID() {
//        for (int i = 0; i < 100; i++) {
//            String randomUUID = idGenerator.randomUUID();
//            log.info("randomUUID: {}", randomUUID);
//        }
//    }
//
//    @Test
//    public void testObjectID() {
//        for (int i = 0; i < 100; i++) {
//            String objectId = idGenerator.objectId();
//            log.info("objectId: {}", objectId);
//        }
//    }
//
//    @Test
//    public void testSnowflakeId() {
//        ExecutorService executorService = Executors.newFixedThreadPool(20);
//        for (int i = 0; i < 20; i++) {
//            executorService.execute(() -> {
//                log.info("分布式 ID: {}", idGenerator.snowflakeId());
//            });
//        }
//        executorService.shutdown();
//    }
//
//}
//
