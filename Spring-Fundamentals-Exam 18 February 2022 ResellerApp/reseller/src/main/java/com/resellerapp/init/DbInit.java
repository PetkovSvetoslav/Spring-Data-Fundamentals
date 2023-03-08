package com.resellerapp.init;


import com.resellerapp.model.enums.ConditionName;
import com.resellerapp.service.ConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbInit implements CommandLineRunner {
    private ConditionService conditionService;

    @Autowired
    public DbInit(ConditionService conditionService) {
        this.conditionService = conditionService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.conditionService.initConditions(ConditionName.values());
    }
}
