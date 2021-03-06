package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {
    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message){
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_goal", companyConfig.getCompanyGoal());
        context.setVariable("company_email", companyConfig.getCompanyMail());
        context.setVariable("company_phone", companyConfig.getCompanyPhone());
        context.setVariable("goodbye_message", "Thank you very much for created new card !");
        context.setVariable("preview_message", "Mail contains information about trello card which has been created.");
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildQuantityOfTrelloTasksInformationDailyMail(String message) {
        long tasksQuantity = taskRepository.count();

        List<String> noTaskInApplication = new ArrayList<>();
        noTaskInApplication.add("You can only create tasks, and after that manipulate it !");

        List<String> taskInApplication = new ArrayList<>();
        taskInApplication.add("You can manipulate created tasks !");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("show_button", true);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_goal", companyConfig.getCompanyGoal());
        context.setVariable("company_email", companyConfig.getCompanyMail());
        context.setVariable("company_phone", companyConfig.getCompanyPhone());
        context.setVariable("goodbye_message", "We'll send for You next email tomorrow :)");
        context.setVariable("preview_message", "Mail contains information about tasks quantity");
        context.setVariable("tasks_quantity", tasksQuantity);
        context.setVariable("no_task_in_application", noTaskInApplication);
        context.setVariable("task_in_application", taskInApplication);
        if (tasksQuantity == 0) {
            context.setVariable("is_zero_tasks", true);
        } else {
            context.setVariable("is_zero_tasks", false);
        }
        return templateEngine.process("mail/calculate-trello-tasks-mail", context);
    }
}
