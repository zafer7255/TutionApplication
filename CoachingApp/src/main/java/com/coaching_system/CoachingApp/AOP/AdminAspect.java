package com.coaching_system.CoachingApp.AOP;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Aspect
@Component
public class AdminAspect {
    public static final Logger LOGGER = LoggerFactory.getLogger(AdminAspect.class);

    @Before("execution(* com.coaching_system.CoachingApp.Service.AdminService.*(..))")
    public void logmethodcall(JoinPoint jp)
    {
        String fileName = "/home/zafer/Projects/CoachingApp/src/main/java/com/coaching_system/CoachingApp/Monitoring/MonitoringEXE.txt";
        LocalDateTime now = LocalDateTime.now();
        String content = " Before Admin call Method Name:-  " + jp.getSignature().getName() + " CurrentTime " + now + "\n";
        try (FileWriter writer = new FileWriter(fileName,true)) {
            writer.write(content);
            System.out.println("File written successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    @AfterReturning("execution(* com.coaching_system.CoachingApp.Service.AdminService.*(..))")
    public void logmethodcalled(JoinPoint jp)
    {
        String fileName = "/home/zafer/Projects/CoachingApp/src/main/java/com/coaching_system/CoachingApp/Monitoring/MonitoringEXE.txt";
        LocalDateTime now = LocalDateTime.now();
        String content = " After Everything(Fine) Admin call Method Name:-  " + jp.getSignature().getName() + " CurrentTime " + now + "\n";
        try (FileWriter writer = new FileWriter(fileName,true)) {
            writer.write(content);
            System.out.println("File written successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    @AfterThrowing("execution(* com.coaching_system.CoachingApp.Service.AdminService.*(..))")
    public void logmethodthrow(JoinPoint jp)
    {
        String fileName = "/home/zafer/Projects/CoachingApp/src/main/java/com/coaching_system/CoachingApp/Monitoring/MonitoringEXE.txt";
        LocalDateTime now = LocalDateTime.now();
        String content = " (Method throw exception) Admin call Method Name:-  " + jp.getSignature().getName() + " CurrentTime " + now + "\n";
        try (FileWriter writer = new FileWriter(fileName,true)) {
            writer.write(content);
            System.out.println("File written successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
