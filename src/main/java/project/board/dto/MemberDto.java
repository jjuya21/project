package project.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.board.domain.Member;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {

    private Long id;
    private String username;
    private String password;
    private String email;

    @Builder
    public MemberDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Member toEntity(){
        return Member.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();
    }
}