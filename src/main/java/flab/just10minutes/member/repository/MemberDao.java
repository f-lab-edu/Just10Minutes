package flab.just10minutes.member.repository;

import flab.just10minutes.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface MemberDao {

    int save(Member member);
    Member findById(String id);
    String findId(String id);
    Member findByOpenId(Long id);

}
