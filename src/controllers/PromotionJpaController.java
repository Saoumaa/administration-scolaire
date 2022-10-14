/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entites.Cycles;
import entites.Promotion;
import entites.Serie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author OBAMA
 */
public class PromotionJpaController implements Serializable {

    public PromotionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Promotion promotion) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cycles idCycles = promotion.getIdCycles();
            if (idCycles != null) {
                idCycles = em.getReference(idCycles.getClass(), idCycles.getIdCycles());
                promotion.setIdCycles(idCycles);
            }
            Serie idSerie = promotion.getIdSerie();
            if (idSerie != null) {
                idSerie = em.getReference(idSerie.getClass(), idSerie.getIdSerie());
                promotion.setIdSerie(idSerie);
            }
            em.persist(promotion);
            if (idCycles != null) {
                idCycles.getPromotionList().add(promotion);
                idCycles = em.merge(idCycles);
            }
            if (idSerie != null) {
                idSerie.getPromotionList().add(promotion);
                idSerie = em.merge(idSerie);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPromotion(promotion.getIdPromo()) != null) {
                throw new PreexistingEntityException("Promotion " + promotion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Promotion promotion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Promotion persistentPromotion = em.find(Promotion.class, promotion.getIdPromo());
            Cycles idCyclesOld = persistentPromotion.getIdCycles();
            Cycles idCyclesNew = promotion.getIdCycles();
            Serie idSerieOld = persistentPromotion.getIdSerie();
            Serie idSerieNew = promotion.getIdSerie();
            if (idCyclesNew != null) {
                idCyclesNew = em.getReference(idCyclesNew.getClass(), idCyclesNew.getIdCycles());
                promotion.setIdCycles(idCyclesNew);
            }
            if (idSerieNew != null) {
                idSerieNew = em.getReference(idSerieNew.getClass(), idSerieNew.getIdSerie());
                promotion.setIdSerie(idSerieNew);
            }
            promotion = em.merge(promotion);
            if (idCyclesOld != null && !idCyclesOld.equals(idCyclesNew)) {
                idCyclesOld.getPromotionList().remove(promotion);
                idCyclesOld = em.merge(idCyclesOld);
            }
            if (idCyclesNew != null && !idCyclesNew.equals(idCyclesOld)) {
                idCyclesNew.getPromotionList().add(promotion);
                idCyclesNew = em.merge(idCyclesNew);
            }
            if (idSerieOld != null && !idSerieOld.equals(idSerieNew)) {
                idSerieOld.getPromotionList().remove(promotion);
                idSerieOld = em.merge(idSerieOld);
            }
            if (idSerieNew != null && !idSerieNew.equals(idSerieOld)) {
                idSerieNew.getPromotionList().add(promotion);
                idSerieNew = em.merge(idSerieNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = promotion.getIdPromo();
                if (findPromotion(id) == null) {
                    throw new NonexistentEntityException("The promotion with id " + id + " no longer exists.");
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
            Promotion promotion;
            try {
                promotion = em.getReference(Promotion.class, id);
                promotion.getIdPromo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The promotion with id " + id + " no longer exists.", enfe);
            }
            Cycles idCycles = promotion.getIdCycles();
            if (idCycles != null) {
                idCycles.getPromotionList().remove(promotion);
                idCycles = em.merge(idCycles);
            }
            Serie idSerie = promotion.getIdSerie();
            if (idSerie != null) {
                idSerie.getPromotionList().remove(promotion);
                idSerie = em.merge(idSerie);
            }
            em.remove(promotion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Promotion> findPromotionEntities() {
        return findPromotionEntities(true, -1, -1);
    }

    public List<Promotion> findPromotionEntities(int maxResults, int firstResult) {
        return findPromotionEntities(false, maxResults, firstResult);
    }

    private List<Promotion> findPromotionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Promotion.class));
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

    public Promotion findPromotion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Promotion.class, id);
        } finally {
            em.close();
        }
    }

    public int getPromotionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Promotion> rt = cq.from(Promotion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
