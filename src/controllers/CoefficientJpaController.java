/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entites.Coefficient;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entites.Matiere;
import entites.DetailsBulletin;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author OBAMA
 */
public class CoefficientJpaController implements Serializable {

    public CoefficientJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Coefficient coefficient) throws PreexistingEntityException, Exception {
        if (coefficient.getDetailsBulletinList() == null) {
            coefficient.setDetailsBulletinList(new ArrayList<DetailsBulletin>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Matiere idMatiere = coefficient.getIdMatiere();
            if (idMatiere != null) {
                idMatiere = em.getReference(idMatiere.getClass(), idMatiere.getIdMatiere());
                coefficient.setIdMatiere(idMatiere);
            }
            List<DetailsBulletin> attachedDetailsBulletinList = new ArrayList<DetailsBulletin>();
            for (DetailsBulletin detailsBulletinListDetailsBulletinToAttach : coefficient.getDetailsBulletinList()) {
                detailsBulletinListDetailsBulletinToAttach = em.getReference(detailsBulletinListDetailsBulletinToAttach.getClass(), detailsBulletinListDetailsBulletinToAttach.getIdDetailsBulletin());
                attachedDetailsBulletinList.add(detailsBulletinListDetailsBulletinToAttach);
            }
            coefficient.setDetailsBulletinList(attachedDetailsBulletinList);
            em.persist(coefficient);
            if (idMatiere != null) {
                idMatiere.getCoefficientList().add(coefficient);
                idMatiere = em.merge(idMatiere);
            }
            for (DetailsBulletin detailsBulletinListDetailsBulletin : coefficient.getDetailsBulletinList()) {
                Coefficient oldIdCoefOfDetailsBulletinListDetailsBulletin = detailsBulletinListDetailsBulletin.getIdCoef();
                detailsBulletinListDetailsBulletin.setIdCoef(coefficient);
                detailsBulletinListDetailsBulletin = em.merge(detailsBulletinListDetailsBulletin);
                if (oldIdCoefOfDetailsBulletinListDetailsBulletin != null) {
                    oldIdCoefOfDetailsBulletinListDetailsBulletin.getDetailsBulletinList().remove(detailsBulletinListDetailsBulletin);
                    oldIdCoefOfDetailsBulletinListDetailsBulletin = em.merge(oldIdCoefOfDetailsBulletinListDetailsBulletin);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCoefficient(coefficient.getIdCoef()) != null) {
                throw new PreexistingEntityException("Coefficient " + coefficient + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Coefficient coefficient) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Coefficient persistentCoefficient = em.find(Coefficient.class, coefficient.getIdCoef());
            Matiere idMatiereOld = persistentCoefficient.getIdMatiere();
            Matiere idMatiereNew = coefficient.getIdMatiere();
            List<DetailsBulletin> detailsBulletinListOld = persistentCoefficient.getDetailsBulletinList();
            List<DetailsBulletin> detailsBulletinListNew = coefficient.getDetailsBulletinList();
            if (idMatiereNew != null) {
                idMatiereNew = em.getReference(idMatiereNew.getClass(), idMatiereNew.getIdMatiere());
                coefficient.setIdMatiere(idMatiereNew);
            }
            List<DetailsBulletin> attachedDetailsBulletinListNew = new ArrayList<DetailsBulletin>();
            for (DetailsBulletin detailsBulletinListNewDetailsBulletinToAttach : detailsBulletinListNew) {
                detailsBulletinListNewDetailsBulletinToAttach = em.getReference(detailsBulletinListNewDetailsBulletinToAttach.getClass(), detailsBulletinListNewDetailsBulletinToAttach.getIdDetailsBulletin());
                attachedDetailsBulletinListNew.add(detailsBulletinListNewDetailsBulletinToAttach);
            }
            detailsBulletinListNew = attachedDetailsBulletinListNew;
            coefficient.setDetailsBulletinList(detailsBulletinListNew);
            coefficient = em.merge(coefficient);
            if (idMatiereOld != null && !idMatiereOld.equals(idMatiereNew)) {
                idMatiereOld.getCoefficientList().remove(coefficient);
                idMatiereOld = em.merge(idMatiereOld);
            }
            if (idMatiereNew != null && !idMatiereNew.equals(idMatiereOld)) {
                idMatiereNew.getCoefficientList().add(coefficient);
                idMatiereNew = em.merge(idMatiereNew);
            }
            for (DetailsBulletin detailsBulletinListOldDetailsBulletin : detailsBulletinListOld) {
                if (!detailsBulletinListNew.contains(detailsBulletinListOldDetailsBulletin)) {
                    detailsBulletinListOldDetailsBulletin.setIdCoef(null);
                    detailsBulletinListOldDetailsBulletin = em.merge(detailsBulletinListOldDetailsBulletin);
                }
            }
            for (DetailsBulletin detailsBulletinListNewDetailsBulletin : detailsBulletinListNew) {
                if (!detailsBulletinListOld.contains(detailsBulletinListNewDetailsBulletin)) {
                    Coefficient oldIdCoefOfDetailsBulletinListNewDetailsBulletin = detailsBulletinListNewDetailsBulletin.getIdCoef();
                    detailsBulletinListNewDetailsBulletin.setIdCoef(coefficient);
                    detailsBulletinListNewDetailsBulletin = em.merge(detailsBulletinListNewDetailsBulletin);
                    if (oldIdCoefOfDetailsBulletinListNewDetailsBulletin != null && !oldIdCoefOfDetailsBulletinListNewDetailsBulletin.equals(coefficient)) {
                        oldIdCoefOfDetailsBulletinListNewDetailsBulletin.getDetailsBulletinList().remove(detailsBulletinListNewDetailsBulletin);
                        oldIdCoefOfDetailsBulletinListNewDetailsBulletin = em.merge(oldIdCoefOfDetailsBulletinListNewDetailsBulletin);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = coefficient.getIdCoef();
                if (findCoefficient(id) == null) {
                    throw new NonexistentEntityException("The coefficient with id " + id + " no longer exists.");
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
            Coefficient coefficient;
            try {
                coefficient = em.getReference(Coefficient.class, id);
                coefficient.getIdCoef();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The coefficient with id " + id + " no longer exists.", enfe);
            }
            Matiere idMatiere = coefficient.getIdMatiere();
            if (idMatiere != null) {
                idMatiere.getCoefficientList().remove(coefficient);
                idMatiere = em.merge(idMatiere);
            }
            List<DetailsBulletin> detailsBulletinList = coefficient.getDetailsBulletinList();
            for (DetailsBulletin detailsBulletinListDetailsBulletin : detailsBulletinList) {
                detailsBulletinListDetailsBulletin.setIdCoef(null);
                detailsBulletinListDetailsBulletin = em.merge(detailsBulletinListDetailsBulletin);
            }
            em.remove(coefficient);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Coefficient> findCoefficientEntities() {
        return findCoefficientEntities(true, -1, -1);
    }

    public List<Coefficient> findCoefficientEntities(int maxResults, int firstResult) {
        return findCoefficientEntities(false, maxResults, firstResult);
    }

    private List<Coefficient> findCoefficientEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Coefficient.class));
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

    public Coefficient findCoefficient(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Coefficient.class, id);
        } finally {
            em.close();
        }
    }

    public int getCoefficientCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Coefficient> rt = cq.from(Coefficient.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
