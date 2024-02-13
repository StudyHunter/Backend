package inf.questpartner.repository.admin;

import inf.questpartner.domain.users.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long>, AdminRepositoryCustom {

}
