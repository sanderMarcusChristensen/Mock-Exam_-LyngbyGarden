package dat.dao;

import dat.dto.ResellerDTO;
import dat.entities.Reseller;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class ResellerDAO {

    private EntityManagerFactory emf;

    public ResellerDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public ResellerDTO create (ResellerDTO dto) {

        Reseller reseller = dto.getResellerAsEntity();

        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(reseller);

            ResellerDTO resellerDTO = reseller.getResellerAsDTO();
            em.getTransaction().commit();

            return resellerDTO;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Reseller findById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Reseller.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}



