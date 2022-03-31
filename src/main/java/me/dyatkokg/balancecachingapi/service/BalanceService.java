package me.dyatkokg.balancecachingapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.dyatkokg.balancecachingapi.client.SubscriberSystemClient;
import me.dyatkokg.balancecachingapi.entity.Balance;
import me.dyatkokg.balancecachingapi.mapper.BalanceMapper;
import me.dyatkokg.balancecachingapi.queue.QueueProcessor;
import me.dyatkokg.balancecachingapi.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BalanceService {

    private final BalanceRepository balanceRepository;

    private final BalanceMapper mapper;

    private final QueueProcessor queueProcessor;

    private final SubscriberSystemClient systemClient;

    @Value("${cache.condition}")
    private Double condition;

    @Scheduled(fixedDelayString = "${cache.fill-delay}", timeUnit = TimeUnit.SECONDS)
    public void save() {
        List<Balance> balances = systemClient.getBalances().stream().map(mapper::toEntity).collect(Collectors.toList());
        balanceRepository.saveAll(balances);
    }

    @Scheduled(fixedDelayString = "${cache.refresh-delay}", timeUnit = TimeUnit.SECONDS)
    public void queuePublisher() {
        List<Balance> balances = balanceRepository.findAllByBalanceLessThan(condition);
        for (Balance balance : balances) {
            queueProcessor.balance().send(MessageBuilder.withPayload(balance).build());
        }
    }
}
