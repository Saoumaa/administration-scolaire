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
import entites.Promotion;
import entites.Serie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author OBAMA
 */
public class SerieJpaController implements Serializable {

    public SerieJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Serie serie) throws PreexistingEntityException, Exception {
        if (serie.getPromotionList() == null) {
            serie.setPromotionList(new ArrayList<Promotion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Promotion> attachedPromotionList = new ArrayList<Promotion>();
            for (Promotion promotionListPromotionToAttach : serie.getPromotionList()) {
                promotionListPromotionToAttach = em.getReference(promotionListPromotionToAttach.getClass(), promotionListPromotionToAttach.getIdPromo());
                attachedPromotionList.add(promotionListPromotionToAttach);
            }
            serie.setPromotionList(attachedPromotionList);
            em.persist(serie);
            for (Promotion promotionListPromotion : serie.getPromotionList()) {
                Serie oldIdSerieOfPromotionListPromotion = promotionListPromotion.getIdSerie();
                promotionListPromotion.setIdSerie(serie);
                promotionListPromotion = em.merge(promotionListPromotion);
                if (oldIdSerieOfPromotionListPromotion != null) {
                    oldIdSerieOfPromotionListPromotion.getPromotionList().remove(promotionListPromotion);
                    oldIdSerieOfPromotionListPromotion = em.merge(oldIdSerieOfPromotionListPromotion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSerie(serie.getIdSerie()) != null) {
                throw new PreexistingEntityException("Serie " + serie + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Serie serie) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Serie persistentSerie = em.find(Serie.class, serie.getIdSerie());
            List<Promotion> promotionListOld = persistentSerie.getPromotionList();
            List<Promotion> promotionListNew = serie.getPromotionList();
            List<Promotion> attachedPromotionListNew = new ArrayList<Promotion>();
            for (Promotion promotionListNewPromotionToAttach : promotionListNew) {
                promotionListNewPromotionToAttach = em.getReference(promotionListNewPromotionToAttach.getClass(), promotionListNewPromotionToAttach.getIdPromo());
                attachedPromotionListNew.add(promotionListNewPromotionToAttach);
            }
            promotionListNew = attachedPromotionListNew;
            serie.setPromotionList(promotionListNew);
            serie = em.merge(serie);
            for (Promotion promotionListOldPromotion : promotionListOld) {
                if (!promotionListNew.contains(promotionListOldPromotion)) {
                    promotionListOldPromotion.setIdSerie(null);
                    promotionListOldPromotion = em.merge(promotionListOldPromotion);
                }
            }
            for (Promotion promotionListNewPromotion : promotionListNew) {
                if (!promotionListOld.contains(promotionListNewPromotion)) {
                    Serie oldIdSerieOfPromotionListNewPromotion = promotionListNewPromotion.getIdSerie();
                    promotionListNewPromotion.setIdSerie(serie);
                    promotionListNewPromotion = em.merge(promotionListNewPromotion);
                    if (oldIdSerieOfPromotionListNewPromotion != null && !oldIdSerieOfPromotionListNewPromotion.equals(serie)) {
                        oldIdSerieOfPromotionListNewPromotion.getPromotionList().remove(promotionListNewPromotion);
                        oldIdSerieOfPromotionListNewPromotion = em.merge(oldIdSerieOfPromotionListNewPromotion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = serie.getIdSerie();
                if (findSerie(id) == null) {
                    throw new NonexistentEntityException("The serie with id " + id + " no longer exists.");
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
            Serie serie;
            try {
                serie = em.getReference(Serie.class, id);
                serie.getIdSerie();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The serie with id " + id + " no longer exists.", enfe);
            }
            List<Promotion> promotionList = serie.getPromotionList();
            for (Promotion promotionListPromotion : promotionList) {
                promotionListPromotion.setIdSerie(null);
                promotionListPromotion = em.merge(promotionListPromotion);
            }
            em.remove(serie);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Serie> findSerieEntities() {
        return findSerieEntities(true, -1, -1);
    }

    public List<Serie> findSerieEntities(int maxResults, int firstResult) {
        return findSerieEntities(false, maxResults, firstResult);
    }

    private List<Serie> findSerieEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Serie.class));
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

    public Serie findSerie(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Serie.class, id);
        } finally {
            em.close();
        }
    }

    public int getSerieCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Serie> rt = cq.from(Serie.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
