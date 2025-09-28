package com.threego.algo.auth.command.application.service;
import com.threego.algo.auth.command.application.dto.UserDTO;
import com.threego.algo.member.command.domain.aggregate.Member;
import com.threego.algo.member.command.domain.repository.MemberRepository;
import com.threego.algo.member.query.dao.AuthMapper;
import com.threego.algo.member.query.dto.LoginUserResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.threego.algo.member.command.domain.aggregate.Member.UserToMember;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    ModelMapper modelMapper;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    MemberRepository memberRepository;
    AuthMapper authMapper;
    MemberMapper memberMapper;

    @Autowired
    public AuthServiceImpl(MemberRepository memberRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           ModelMapper modelMapper,
                           AuthMapper authMapper,
                           MemberMapper memberMapper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
        this.authMapper = authMapper;
        this.memberMapper = memberMapper;
    }

    @Override
    public void registUser(UserDTO userDTO) {

        Member member = UserToMember(userDTO);

        member.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));

        memberRepository.save(member);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LoginUserResponseDTO loginUser = authMapper.selectMemberByEmail(email);
        if (loginUser == null) {
            throw new UsernameNotFoundException(email + "이메일 사용자는 존재하지 않습니다.");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(loginUser.getEmail(), loginUser.getPassword(), true, true, true, true, grantedAuthorities);
    }
}
