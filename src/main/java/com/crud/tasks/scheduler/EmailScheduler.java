package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmailScheduler {
    private final SimpleEmailService simpleEmailService;
    private final TaskRepository taskRepository;
    private final AdminConfig adminConfig;
    private static final String SUBJECT = "Tasks: New Trello card";
    private static final String CALCULATE_SUBJECT = "Tasks Once a day email";

    //@Scheduled(fixedDelay = 10000)
    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        long size = taskRepository.count();
        String task = (size > 1) ? " tasks" : " task";
        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                "Currently in database you got: " + size + " " + task
        ));
    }

    @Scheduled(cron = "0 48 12 * * *")
    public void sendTasksQuantityInformationMail() {
        long size = taskRepository.count();
        if (size == 1) {
            simpleEmailService.sendTasksQuantityDailyMail(new Mail(
                    adminConfig.getAdminMail(),
                    CALCULATE_SUBJECT,
                    "Currently in database you got: " + size + " task")
            );
        } else {
            simpleEmailService.sendTasksQuantityDailyMail(new Mail(
                    adminConfig.getAdminMail(),
                    CALCULATE_SUBJECT,
                    "Currently in database you got: " + size + " tasks")
            );
        }
    }
}
