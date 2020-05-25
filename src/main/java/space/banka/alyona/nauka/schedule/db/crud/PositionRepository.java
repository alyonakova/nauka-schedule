package space.banka.alyona.nauka.schedule.db.crud;

import org.springframework.data.repository.CrudRepository;
import space.banka.alyona.nauka.schedule.db.entities.Position;

public interface PositionRepository extends CrudRepository<Position, Integer> {
}
