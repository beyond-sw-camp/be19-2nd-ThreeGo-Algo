package com.threego.algomemberservice.member.query.infrastructure;

import com.threego.algomemberservice.member.query.dto.PostSummaryResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "algo-core-service",
        path = "/career-info",
        configuration = FeignClientConfig.class
)
public interface CareerInfoClient {

    @GetMapping("/members/{memberId}")
    List<PostSummaryResponseDto> getPostsByMember(@PathVariable("memberId") int memberId);
}

