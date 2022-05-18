package com.atguigu.boot.springbootprofilegroup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

@SpringBootApplication
public class SpringBootProfileGroupApplication {

    /**
     * 1、外部配置源
     * 常用：Java属性文件、YAML文件、环境变量、命令行参数；
     *
     * 2、配置文件查找位置
     * (1) classpath 根路径
     * (2) classpath 根路径下config目录
     * (3) jar包当前目录
     * (4) jar包当前目录的config目录
     * (5) /config子目录的直接子目录
     * -------------------------------------------------------------------
     * 3、创建 SpringApplication  --> new SpringApplication(primarySources)
     *   3.1、保存一些信息。resourceLoader
     *   3.2、判定当前应用的类型。ClassUtils --> Servlet
     *   3.3、 bootstrappers：初始启动引导器（List<Bootstrapper>）：去spring.factories文件中找 org.springframework.boot.Bootstrapper
     *   3.4、 找 ApplicationContextInitializer；去spring.factories找 ApplicationContextInitializer
     *     ■ List<ApplicationContextInitializer<?>> initializers
     *   3.5、 找 ApplicationListener ,应用监听器。去spring.factories找 ApplicationListener
     *     ■ List<ApplicationListener<?>> listeners
     * 4、运行 SpringApplication  --> new SpringApplication(primarySources).run(args)
     *   4.1、StopWatch
     *   4.2、记录应用的启动时间 StopWatch.start
     *   4.3、创建引导上下文（Context环境）createBootstrapContext()
     *         ■ 获取到所有之前的 bootstrappers 挨个执行 intitialize() 来完成对引导启动器上下文环境设置
     *   4.4、让当前应用进入headless模式。java.awt.headless
     *   4.5、获取所有 RunListener（运行监听器）【为了方便所有Listener进行事件感知】
     *         ■ getSpringFactoriesInstances 去spring.factories找 SpringApplicationRunListener --> EventPublishingRunListener
     *   4.6、遍历 SpringApplicationRunListener 调用 starting 方法；
     *        ■ 相当于通知所有感兴趣系统正在启动过程的人，项目正在 starting。
     *   4.7、保存命令行参数；ApplicationArguments
     *   4.8、准备环境 prepareEnvironment（）;
     *        ■ 返回或者创建基础环境信息对象。StandardServletEnvironment
     *        ■ 配置环境信息对象。
     *          ● 读取所有的配置源的配置属性值。
     *        ■ 绑定环境信息
     *        ■ 监听器调用 listener.environmentPrepared()；通知所有的监听器当前环境准备完成
     *   4.9、创建IOC容器（createApplicationContext（））
     *         ■ 根据项目类型（Servlet）创建容器，
     *         ■ 当前会创建 AnnotationConfigServletWebServerApplicationContext
     *   4.10、准备ApplicationContext IOC容器的基本信息   prepareContext()
     *         ■ 保存环境信息
     *         ■ IOC容器的后置处理流程。
     *         ■ 应用初始化器；applyInitializers；
     *             ● 遍历所有的 ApplicationContextInitializer 。调用 initialize方法。来对ioc容器进行初始化扩展功能
     *         ■ 遍历所有的 listener(EventPublishRunListenr) 调用 contextPrepared; 通知所有的监听器contextPrepared
     *         ■ 所有的监听器 调用 contextLoaded。通知所有的监听器 contextLoaded；
     *   4.11、刷新IOC容器。refreshContext
     *         ■ 创建容器中的所有组件（Spring注解）
     *   4.12、容器刷新完成后工作？afterRefresh
     *   4.13、所有监听器调用 listeners.started(context); 通知所有的监听器 started
     *   4.14、调用所有runners；callRunners()
     *         ■ 获取容器中的 ApplicationRunner
     *         ■ 获取容器中的  CommandLineRunner
     *         ■ 合并所有runner并且按照@Order进行排序
     *         ■ 遍历所有的runner,调用 run 方法
     *   4.15、如果以上有异常，
     *         ■ 调用Listener 的 failed
     *   4.16、调用所有监听器的 running 方法  listeners.running(context); 通知所有的监听器 running
     *   4.17、 running如果有问题。继续通知 failed 。调用所有 Listener 的 failed；通知所有的监听器 failed
     * @param args
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringBootProfileGroupApplication.class, args);
        ConfigurableEnvironment environment = run.getEnvironment();
        Map<String, Object> systemEnvironment = environment.getSystemEnvironment();
        Map<String, Object> systemProperties = environment.getSystemProperties();
        System.out.println("环境变量为："+systemEnvironment);
        System.out.println("系统属性为："+systemProperties);
    }

}
