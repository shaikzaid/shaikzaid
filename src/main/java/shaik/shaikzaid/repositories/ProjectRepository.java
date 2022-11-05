package shaik.shaikzaid.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import shaik.shaikzaid.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long>{

    @Override
    Iterable<Project> findAllById(Iterable<Long> longs);

    Project findByProjectIdentifier(String projectId);

    @Override
    Iterable<Project> findAll();
}
