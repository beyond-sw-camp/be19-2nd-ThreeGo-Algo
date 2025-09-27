package com.threego.algo.member.query.controller;

import com.threego.algo.member.query.dto.GetLoginUserResponseDTO;
import com.threego.algo.member.query.service.AuthQueryService;
import com.threego.algo.member.query.service.MemberQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "인증 API")
@RestController
@RequestMapping("/member")
public class AuthQueryController {
    private final AuthQueryService authQueryService;


    @Autowired
    public AuthQueryController(AuthQueryService authQueryService,
                               MemberQueryService memberQueryService) {
        this.authQueryService = authQueryService;
    }

    @Operation(
            summary = "이메일을 통한 회원 정보 조회",
            description = "로그인 과정에서 사용자가 입력한 이메일을 통해 해당 이메일을 가진 회원 데이터가 존재한다면 정보를 가져옵니다."
    )
    @GetMapping("/{email}")
    public ResponseEntity<GetLoginUserResponseDTO> findMemberByEmail(
            @PathVariable String email
    ){
        return ResponseEntity.ok(authQueryService.findMemberByEmail(email));
    }

}
