/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entites.Cycles;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entites.Promotion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author OBAMA
 */
public class CyclesJpaController implements Serializable {

    public CyclesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cycles cycles) throws PreexistingEntityException, Exception {
        if (cycles.getPromotionList() == null) {
            cycles.setPromotionList(new ArrayList<Promotion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Promotion> attachedPromotionList = new ArrayList<Promotion>();
            for (Promotion promotionListPromotionToAttach : cycles.getPromotionList()) {
                promotionListPromotionToAttach = em.getReference(promotionListPromotionToAttach.getClass(), promotionListPromotionToAttach.getIdPromo());
                attachedPromotionList.add(promotionListPromotionToAttach);
            }
            cycles.setPromotionList(attachedPromotionList);
            em.persist(cycles);
            for (Promotion promotionListPromotion : cycles.getPromotionList()) {
                Cycles oldIdCyclesOfPromotionListPromotion = promotionListPromotion.getIdCycles();
                promotionListPromotion.setIdCycles(cycles);
                promotionListPromotion = em.merge(promotionListPromotion);
                if (oldIdCyclesOfPromotionListPromotion != null) {
                    oldIdCyclesOfPromotionListPromotion.getPromotionList().remove(promotionListPromotion);
                    oldIdCyclesOfPromotionListPromotion = em.merge(oldIdCyclesOfPromotionListPromotion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCycles(cycles.getIdCycles()) != null) {
                throw new PreexistingEntityException("Cycles " + cycles + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cycles cycles) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cycles persistentCycles = em.find(Cycles.class, cycles.getIdCycles());
            List<Promotion> promotionListOld = persistentCycles.getPromotionList();
            List<Promotion> promotionListNew = cycles.getPromotionList();
            List<Promotion> attachedPromotionListNew = new ArrayList<Promotion>();
            for (Promotion promotionListNewPromotionToAttach : promotionListNew) {
                promotionListNewPromotionToAttach = em.getReference(promotionListNewPromotionToAttach.getClass(), promotionListNewPromotionToAttach.getIdPromo());
                attachedPromotionListNew.add(promotionListNewPromotionToAttach);
            }
            promotionListNew = attachedPromotionListNew;
            cycles.setPromotionList(promotionListNew);
            cycles = em.merge(cycles);
            for (Promotion promotionListOldPromotion : promotionListOld) {
                if (!promotionListNew.contains(promotionListOldPromotion)) {
                    promotionListOldPromotion.setIdCycles(null);
                    promotionListOldPromotion = em.merge(promotionListOldPromotion);
                }
            }
            for (Promotion promotionListNewPromotion : promotionListNew) {
                if (!promotionListOld.contains(promotionListNewPromotion)) {
                    Cycles oldIdCyclesOfPromotionListNewPromotion = promotionListNewPromotion.getIdCycles();
                    promotionListNewPromotion.setIdCycles(cycles);
                    promotionListNewPromotion = em.merge(promotionListNewPromotion);
                    if (oldIdCyclesOfPromotionListNewPromotion != null && !oldIdCyclesOfPromotionListNewPromotion.equals(cycles)) {
                        oldIdCyclesOfPromotionListNewPromotion.getPromotionList().remove(promotionListNewPromotion);
                        oldIdCyclesOfPromotionListNewPromotion = em.merge(oldIdCyclesOfPromotionListNewPromotion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cycles.getIdCycles();
                if (findCycles(id) == null) {
                    throw new NonexistentEntityException("The cycles with id " + id + " no longer exists.");
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
            Cycles cycles;
            try {
                cycles = em.getReference(Cycles.class, id);
                cycles.getIdCycles();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cycles with id " + id + " no longer exists.", enfe);
            }
            List<Promotion> promotionList = cycles.getPromotionList();
            for (Promotion promotionListPromotion : promotionList) {
                promotionListPromotion.setIdCycles(null);
                promotionListPromotion = em.merge(promotionListPromotion);
            }
            em.remove(cycles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cycles> findCyclesEntities() {
        return findCyclesEntities(true, -1, -1);
    }

    public List<Cycles> findCyclesEntities(int maxResults, int firstResult) {
        return findCyclesEntities(false, maxResults, firstResult);
    }

    private List<Cycles> findCyclesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cycles.class));
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

    public Cycles findCycles(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cycles.class, id);
        } finally {
            em.close();
        }
    }

    public int getCyclesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cycles> rt = cq.from(Cycles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
