package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;

@Configuration
@EnableIntegration
public class AppConfig {

    private static final int MAXMESSAGESPERPOL = 2;
    private static final int CAPACITY = 2;
    private static final int PERIOD = 2;

    @Bean
    public QueueChannel cocoonChannel() {
        return MessageChannels.queue(CAPACITY).get();
    }

    @Bean
    public PublishSubscribeChannel evolveChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(PERIOD).maxMessagesPerPoll(MAXMESSAGESPERPOL).get();
    }

    @Bean
    public IntegrationFlow cookingFlow() {
        return IntegrationFlows.from("cocoonChannel")
                .split()
                .handle("evolveServiceImpl", "evolving")
                .aggregate()
                .channel("evolveChannel")
                .get();
    }
}