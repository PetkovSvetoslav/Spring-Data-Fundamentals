package com.resellerapp.model.entity;

import com.resellerapp.model.enums.ConditionName;
import jakarta.persistence.*;

@Entity
@Table(name = "conditions")
public class Condition extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private ConditionName conditionName;

    @Column(nullable = false)
    private String description;

    public Condition() {
    }

    public Condition(ConditionName conditionName) {
        this.conditionName = conditionName;
        setDescription(conditionName.name());
    }

    public ConditionName getConditionName() {
        return conditionName;
    }

    public Condition setConditionName(ConditionName conditionName) {
        this.conditionName = conditionName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Condition setDescription(String conditionName) {
        String description = switch (conditionName) {
            case "EXCELLENT" -> "In perfect condition";
            case "GOOD" -> "Some signs of wear and tear or minor defects";
            case "ACCEPTABLE" -> "The item is fairly worn but continues to function properly";
            default -> "";
        };

        this.description = description;

        return this;
    }
}
