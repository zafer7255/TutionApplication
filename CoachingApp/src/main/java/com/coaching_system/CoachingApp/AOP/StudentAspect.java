package com.coaching_system.CoachingApp.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Aspect
@Component
public class StudentAspect {

    public static final Logger LOGGER = LoggerFactory.getLogger(StudentAspect.class);
    @Before("execution(* com.coaching_system.CoachingApp.Service.StudentService.*(..))")
    public void logmethodcall(JoinPoint jp)
    {
        String fileName = "/home/zafer/Projects/CoachingApp/src/main/java/com/coaching_system/CoachingApp/Monitoring/MonitoringEXE.txt";
        LocalDateTime now = LocalDateTime.now();
        String content = " Student Before Method called " + jp.getSignature().getName() + " CurrentTime " + now + "\n";
        try (FileWriter writer = new FileWriter(fileName,true)) {
            writer.write(content);
            System.out.println("File written successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    @AfterThrowing("execution(* com.coaching_system.CoachingApp.Service.StudentService.*(..))")
    public void logmethodexception(JoinPoint jp)
    {
        String fileName = "/home/zafer/Projects/CoachingApp/src/main/java/com/coaching_system/CoachingApp/Monitoring/MonitoringEXE.txt";
        LocalDateTime now = LocalDateTime.now();
        String content = " \nStudent(Method) problem with this " + jp.getSignature().getName() + " CurrentTime " + now + "\n";
        try (FileWriter writer = new FileWriter(fileName , true)) {
            writer.write(content);
            System.out.println("File written successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    @AfterReturning("execution(* com.coaching_system.CoachingApp.Service.StudentService.*(..))")
    public void logmethodExecuted(JoinPoint jp)
    {
        String fileName = "/home/zafer/Projects/CoachingApp/src/main/java/com/coaching_system/CoachingApp/Monitoring/MonitoringEXE.txt";
        LocalDateTime now = LocalDateTime.now();
        String content = " Student Method called " + jp.getSignature().getName() + " CurrentTime " + now + " Running Fine \n" ;
        try (FileWriter writer = new FileWriter(fileName , true)) {
            writer.write(content);
            System.out.println("File written successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }


}
