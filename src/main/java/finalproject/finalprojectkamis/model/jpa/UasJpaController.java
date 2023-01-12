/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.finalprojectkamis.model.jpa;

import finalproject.finalprojectkamis.model.entity.Uas;
import finalproject.finalprojectkamis.model.jpa.exceptions.NonexistentEntityException;
import finalproject.finalprojectkamis.model.jpa.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Reza Ramadhani
 */
public class UasJpaController implements Serializable {

    public UasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("finalproject_finalprojectkamis_jar_0.0.1-SNAPSHOTPU");

    public UasJpaController() {
    }
    
    

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Uas uas) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUas(uas.getId()) != null) {
                throw new PreexistingEntityException("Uas " + uas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Uas uas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uas = em.merge(uas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uas.getId();
                if (findUas(id) == null) {
                    throw new NonexistentEntityException("The uas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Uas uas;
            try {
                uas = em.getReference(Uas.class, id);
                uas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uas with id " + id + " no longer exists.", enfe);
            }
            em.remove(uas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Uas> findUasEntities() {
        return findUasEntities(true, -1, -1);
    }

    public List<Uas> findUasEntities(int maxResults, int firstResult) {
        return findUasEntities(false, maxResults, firstResult);
    }

    private List<Uas> findUasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Uas.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Uas findUas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Uas.class, id);
        } finally {
            em.close();
        }
    }

    public int getUasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Uas> rt = cq.from(Uas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
