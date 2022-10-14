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
import entites.BulletinNote;
import entites.Coefficient;
import entites.DetailsBulletin;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author OBAMA
 */
public class DetailsBulletinJpaController implements Serializable {

    public DetailsBulletinJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetailsBulletin detailsBulletin) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BulletinNote idBulletin = detailsBulletin.getIdBulletin();
            if (idBulletin != null) {
                idBulletin = em.getReference(idBulletin.getClass(), idBulletin.getIdBulletin());
                detailsBulletin.setIdBulletin(idBulletin);
            }
            Coefficient idCoef = detailsBulletin.getIdCoef();
            if (idCoef != null) {
                idCoef = em.getReference(idCoef.getClass(), idCoef.getIdCoef());
                detailsBulletin.setIdCoef(idCoef);
            }
            em.persist(detailsBulletin);
            if (idBulletin != null) {
                idBulletin.getDetailsBulletinList().add(detailsBulletin);
                idBulletin = em.merge(idBulletin);
            }
            if (idCoef != null) {
                idCoef.getDetailsBulletinList().add(detailsBulletin);
                idCoef = em.merge(idCoef);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetailsBulletin(detailsBulletin.getIdDetailsBulletin()) != null) {
                throw new PreexistingEntityException("DetailsBulletin " + detailsBulletin + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetailsBulletin detailsBulletin) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetailsBulletin persistentDetailsBulletin = em.find(DetailsBulletin.class, detailsBulletin.getIdDetailsBulletin());
            BulletinNote idBulletinOld = persistentDetailsBulletin.getIdBulletin();
            BulletinNote idBulletinNew = detailsBulletin.getIdBulletin();
            Coefficient idCoefOld = persistentDetailsBulletin.getIdCoef();
            Coefficient idCoefNew = detailsBulletin.getIdCoef();
            if (idBulletinNew != null) {
                idBulletinNew = em.getReference(idBulletinNew.getClass(), idBulletinNew.getIdBulletin());
                detailsBulletin.setIdBulletin(idBulletinNew);
            }
            if (idCoefNew != null) {
                idCoefNew = em.getReference(idCoefNew.getClass(), idCoefNew.getIdCoef());
                detailsBulletin.setIdCoef(idCoefNew);
            }
            detailsBulletin = em.merge(detailsBulletin);
            if (idBulletinOld != null && !idBulletinOld.equals(idBulletinNew)) {
                idBulletinOld.getDetailsBulletinList().remove(detailsBulletin);
                idBulletinOld = em.merge(idBulletinOld);
            }
            if (idBulletinNew != null && !idBulletinNew.equals(idBulletinOld)) {
                idBulletinNew.getDetailsBulletinList().add(detailsBulletin);
                idBulletinNew = em.merge(idBulletinNew);
            }
            if (idCoefOld != null && !idCoefOld.equals(idCoefNew)) {
                idCoefOld.getDetailsBulletinList().remove(detailsBulletin);
                idCoefOld = em.merge(idCoefOld);
            }
            if (idCoefNew != null && !idCoefNew.equals(idCoefOld)) {
                idCoefNew.getDetailsBulletinList().add(detailsBulletin);
                idCoefNew = em.merge(idCoefNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = detailsBulletin.getIdDetailsBulletin();
                if (findDetailsBulletin(id) == null) {
                    throw new NonexistentEntityException("The detailsBulletin with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetailsBulletin detailsBulletin;
            try {
                detailsBulletin = em.getReference(DetailsBulletin.class, id);
                detailsBulletin.getIdDetailsBulletin();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detailsBulletin with id " + id + " no longer exists.", enfe);
            }
            BulletinNote idBulletin = detailsBulletin.getIdBulletin();
            if (idBulletin != null) {
                idBulletin.getDetailsBulletinList().remove(detailsBulletin);
                idBulletin = em.merge(idBulletin);
            }
            Coefficient idCoef = detailsBulletin.getIdCoef();
            if (idCoef != null) {
                idCoef.getDetailsBulletinList().remove(detailsBulletin);
                idCoef = em.merge(idCoef);
            }
            em.remove(detailsBulletin);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetailsBulletin> findDetailsBulletinEntities() {
        return findDetailsBulletinEntities(true, -1, -1);
    }

    public List<DetailsBulletin> findDetailsBulletinEntities(int maxResults, int firstResult) {
        return findDetailsBulletinEntities(false, maxResults, firstResult);
    }

    private List<DetailsBulletin> findDetailsBulletinEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetailsBulletin.class));
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

    public DetailsBulletin findDetailsBulletin(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetailsBulletin.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetailsBulletinCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetailsBulletin> rt = cq.from(DetailsBulletin.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
