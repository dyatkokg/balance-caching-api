package me.dyatkokg.balancecachingapi.client;

import me.dyatkokg.balancecachingapi.dto.BalanceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "${api.external.subscriber-system.name}",
        url = "${api.external.subscriber-system.url}"
)
public interface SubscriberSystemClient {

    @GetMapping("all")
    List<BalanceDTO> getBalances();



}
