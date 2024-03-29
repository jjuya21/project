package project.board.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.board.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
    List<Member> findAll();
}
