package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.Member;
import java.sql.Connection;
import java.util.List;
import org.junit.jupiter.api.Test;

class MemberDaoTest {

    @Test
    void connection() {
        final MemberDao memberDao = new MemberDao();
        final Connection connection = memberDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    void save() {
        final MemberDao memberDao = new MemberDao();
        memberDao.save(new Member("jason", "박재성"));
    }

    @Test
    void findById() {
        final MemberDao memberDao = new MemberDao();
        final Member member = memberDao.findById("jason");
        assertThat(member.getName()).isEqualTo("박재성");
    }

    @Test
    void findAll() {
        final MemberDao memberDao = new MemberDao();
        final List<Member> members = memberDao.findAll();
        assertThat(members).isNotEmpty();
    }
}