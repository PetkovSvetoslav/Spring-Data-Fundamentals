package com.resellerapp.service;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.enums.ConditionName;
import com.resellerapp.repository.ConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConditionService {
    private ConditionRepository conditionRepository;

    @Autowired
    public ConditionService(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    public boolean hasConditions() {
        return this.conditionRepository.count() > 0;
    }

    public void initConditions(ConditionName[] values) {
        if (this.hasConditions()){
            return;
        }

        List<Condition> styles = Arrays.stream(values)
                .map(Condition::new)
                .toList();

        this.conditionRepository.saveAll(styles);
    }

    public List<String> findAllConditionNames() {
        return this.conditionRepository.findAll()
                .stream()
                .map(c -> c.getConditionName().getValue())
                .collect(Collectors.toList());
    }

    public Condition findByConditionName(ConditionName conditionName) {
        return this.conditionRepository.findByConditionName(conditionName);
    }
}
