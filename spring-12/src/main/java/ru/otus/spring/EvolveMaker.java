package ru.otus.spring;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.domain.Caterpillar;

@MessagingGateway
public interface EvolveMaker {

    @Gateway(requestChannel = "cocoonChannel", replyChannel = "evolveChannel")
    Object evolving(Caterpillar caterpillar);
}