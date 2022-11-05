package shaik.shaikzaid.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shaik.shaikzaid.domain.Backlog;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long> {
      Backlog findByProjectIdentifier(String Identifier);
}
