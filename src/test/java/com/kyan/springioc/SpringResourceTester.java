package com.kyan.springioc;

import org.junit.Test;
import org.springframework.core.io.*;

/**
 * @author kyan
 * @date 2019/10/31
 */
public class SpringResourceTester {

    @Test
    public void testResourceLoader() {
        ResourceLoader resourceLoader = new DefaultResourceLoader();

        Resource r1 = resourceLoader.getResource("/Users/kyan/Workspace/myworkspace/spring-ioc/src/test/resources/test.txt");
        System.out.println("r1 is ClassPathResource: " + (r1 instanceof ClassPathResource));
        System.out.println(r1);
        //r1 = ClassPathContextResource

        Resource r2 = resourceLoader.getResource("file: /Users/kyan/Workspace/myworkspace/spring-ioc/src/test/resources/test.txt");
        System.out.println("r2 is UrlResource: " + (r2 instanceof UrlResource));
        System.out.println(r2);
        //r2 = FileUrlResource

        Resource r3 = resourceLoader.getResource("http://www.baidu.com");
        System.out.println("r3 is UrlResource: " + (r3 instanceof UrlResource));
        System.out.println(r3);
        //r3 = UrlResource

        Resource r4 = resourceLoader.getResource("D:/Users/kyan/Workspace/myworkspace/spring-ioc/src/test/resources/test.tx");
        System.out.println("r4 is FileSystemResource: " + (r4 instanceof FileSystemResource));
        System.out.println("r4 is ClassPathResource: " + (r4 instanceof ClassPathResource));
        System.out.println(r4);

        FileSystemResourceLoader fsrl = new FileSystemResourceLoader();
        r4 = fsrl.getResource("D:/Users/kyan/Workspace/myworkspace/spring-ioc/src/test/resources/test.tx");
        System.out.println("r4 is FileSystemResource: " + (r4 instanceof FileSystemResource));

        //r1 is ClassPathResource: true
        //class path resource [Users/kyan/Workspace/myworkspace/spring-ioc/src/test/resources/test.txt]
        //r2 is UrlResource: true
        //URL [file: /Users/kyan/Workspace/myworkspace/spring-ioc/src/test/resources/test.txt]
        //r3 is UrlResource: true
        //URL [http://www.baidu.com]
    }
}
