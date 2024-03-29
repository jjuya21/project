package project.board.Service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.board.Repository.MemberRepository;
import project.board.domain.Member;
import project.board.domain.Role;
import project.board.dto.MemberDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    @Transactional
    public Long joinUser(MemberDto memberDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        return memberRepository.save(memberDto.toEntity()).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> findName = memberRepository.findByUsername(username);
        Member member = findName.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if(("admin").equals(username)){
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        }else {
            authorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
        }
        return new User(member.getUsername(), member.getPassword() , authorities);
    }
}