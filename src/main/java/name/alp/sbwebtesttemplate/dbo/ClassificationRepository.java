package name.alp.sbwebtesttemplate.dbo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassificationRepository extends CrudRepository<Classification, String> {
    List<Classification> findBySectorId(String sectorId);
}