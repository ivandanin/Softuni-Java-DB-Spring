package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.Seller;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
}
