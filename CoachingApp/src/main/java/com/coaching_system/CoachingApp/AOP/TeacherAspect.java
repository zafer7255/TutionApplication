package com.coaching_system.CoachingApp.AOP;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Aspect
@Component
public class TeacherAspect {

    @Before("execution(* com.coaching_system.CoachingApp.Service.TeacherService.*(..))")
    public void logmethodcall(JoinPoint jp)
    {
        String fileName = "/home/zafer/Projects/CoachingApp/src/main/java/com/coaching_system/CoachingApp/Monitoring/MonitoringEXE.txt";
        LocalDateTime now = LocalDateTime.now();
        String content = " Teacher Before Method called " + jp.getSignature().getName() + " CurrentTime " + now +"\n";
        try (FileWriter writer = new FileWriter(fileName,true)) {
            writer.write(content);
            System.out.println("File written successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    @AfterThrowing("execution(* com.coaching_system.CoachingApp.Service.TeacherService.*(..))")
    public void logmethodexception(JoinPoint jp)
    {
        String fileName = "/home/zafer/Projects/CoachingApp/src/main/java/com/coaching_system/CoachingApp/Monitoring/MonitoringEXE.txt";
        LocalDateTime now = LocalDateTime.now();
        String content = " Teacher(Method) problem with this " + jp.getSignature().getName() + " CurrentTime " + now + "\n";
        try (FileWriter writer = new FileWriter(fileName,true)) {
            writer.write(content);
            System.out.println("File written successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    @AfterReturning("execution(* com.coaching_system.CoachingApp.Service.TeacherService.*(..))")
    public void logmethodExecuted(JoinPoint jp)
    {
        String fileName = "/home/zafer/Projects/CoachingApp/src/main/java/com/coaching_system/CoachingApp/Monitoring/MonitoringEXE.txt";
        LocalDateTime now = LocalDateTime.now();
        String content = " Teacher Method called " + jp.getSignature().getName() + " CurrentTime " + now + " Running Fine \n";
        try (FileWriter writer = new FileWriter(fileName,true)) {
            writer.write(content);
            System.out.println("File written successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

}
