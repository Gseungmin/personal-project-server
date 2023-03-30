package personal.project.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import personal.project.domain.dto.JoinDto;
import personal.project.domain.dto.ReturnJoinDto;
import personal.project.domain.entity.Member;
import personal.project.web.service.MemberService;

import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    /**회원 가입*/
    @PostMapping("/auth/join")
    public ReturnJoinDto join(@RequestBody JoinDto memberJoin) {

        //회원이 이미 존재할 경우
        Optional<Member> findMember = memberService.findMember(memberJoin.getEmail());
        if (findMember.isPresent()) {
            return new ReturnJoinDto(false, memberJoin.getEmail());
        }

        Member member = new Member(memberJoin.getNickname(), memberJoin.getEmail(), memberJoin.getPassword());
        member.getRoles().add("USER"); //일반 유저

        //회원이 없는 경우 로그인
        memberService.join(member);
        return new ReturnJoinDto(true, memberJoin.getEmail());
    }

    @PostMapping("/auth/login")
    public ResponseEntity<Void> login() {
        return ResponseEntity.ok().build();
    }

    /**토큰 체크*/
    @GetMapping("/token/check")
    public ResponseEntity<Void> tokenCheck() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.ok().build();
    }
}



