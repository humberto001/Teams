package com.example.wordcup.team.application.impl.validators;

import com.example.wordcup.team.domain.model.Team;
import com.example.wordcup.team.domain.model.TeamValidator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TeamServiceValidator implements TeamValidator {

    private Map<String, CheckRule> rules = new HashMap();

    {
        rules.put("Name nao pode ser vazio", team -> team.getName().equals(""));
        rules.put("Name nao pode ter mais que 30 caracteres", team -> team.getName().length()>30);
        rules.put("NumberOfPlayers nao pode ser 0 ou maior que 10", team -> team.getNumberOfPlayers()<=0 | team.getNumberOfPlayers()>10);
    }

    @Override
    public void checkRules(Team team) {
        Set<String> violations = rules.entrySet()
                .stream()
                .filter(rule -> rule.getValue().check(team))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        if (!violations.isEmpty()){
            throw new IllegalArgumentException("Algum valor é inválido" + violations);
        }
    }

    private interface CheckRule{
        Boolean check(Team team);
    }
}
