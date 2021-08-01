package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.IntegrationComponentScan;
import ru.otus.spring.domain.Caterpillar;

import java.util.concurrent.ForkJoinPool;


@IntegrationComponentScan
@ComponentScan
public class Main {

    public static void main(String[] args) throws InterruptedException {

        ApplicationContext context = SpringApplication.run(Main.class, args);

        EvolveMaker em = context.getBean(EvolveMaker.class);

        ForkJoinPool pool = ForkJoinPool.commonPool();

        while (true) {
            pool.execute(() -> {
                System.out.println("Caterpillar climbs a tree ");
                Caterpillar caterpillar = getCaterpillar();
                em.evolving(caterpillar);
                System.out.println();
            });

            Thread.sleep(2500);
        }
    }

    private static Caterpillar getCaterpillar() {
        return new Caterpillar(Math.random() < 0.5);
    }

}