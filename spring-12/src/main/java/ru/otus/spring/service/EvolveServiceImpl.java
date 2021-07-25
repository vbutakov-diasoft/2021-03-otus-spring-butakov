package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Butterfly;
import ru.otus.spring.domain.Caterpillar;
import ru.otus.spring.domain.DeadBody;

@Service
public class EvolveServiceImpl implements EvolveService {

    @Override
    public Object evolving(Caterpillar caterpillar) {
        System.out.println("Caterpillar is wrapped in a cocoon");
        System.out.println("Evolving is success: "+ (caterpillar.isEvolvingSuccess() ? " Yes " : " No "));
        System.out.println("Evolving " + (caterpillar.isEvolvingSuccess() ? "into butterfly " : "failed, caterpillar died"));
        return caterpillar.isEvolvingSuccess() ? new Butterfly() : new DeadBody();
    }
}