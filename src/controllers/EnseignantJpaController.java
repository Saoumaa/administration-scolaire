/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entites.Enseignant;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entites.Enseigner;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author OBAMA
 */
public class EnseignantJpaController implements Serializable {

    public EnseignantJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Enseignant enseignant) throws PreexistingEntityException, Exception {
        if (enseignant.getEnseignerList() == null) {
            enseignant.setEnseignerList(new ArrayList<Enseigner>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Enseigner> attachedEnseignerList = new ArrayList<Enseigner>();
            for (Enseigner enseignerListEnseignerToAttach : enseignant.getEnseignerList()) {
                enseignerListEnseignerToAttach = em.getReference(enseignerListEnseignerToAttach.getClass(), enseignerListEnseignerToAttach.getEnseignerCode());
                attachedEnseignerList.add(enseignerListEnseignerToAttach);
            }
            enseignant.setEnseignerList(attachedEnseignerList);
            em.persist(enseignant);
            for (Enseigner enseignerListEnseigner : enseignant.getEnseignerList()) {
                Enseignant oldIdEnsOfEnseignerListEnseigner = enseignerListEnseigner.getIdEns();
                enseignerListEnseigner.setIdEns(enseignant);
                enseignerListEnseigner = em.merge(enseignerListEnseigner);
                if (oldIdEnsOfEnseignerListEnseigner != null) {
                    oldIdEnsOfEnseignerListEnseigner.getEnseignerList().remove(enseignerListEnseigner);
                    oldIdEnsOfEnseignerListEnseigner = em.merge(oldIdEnsOfEnseignerListEnseigner);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEnseignant(enseignant.getIdEns()) != null) {
                throw new PreexistingEntityException("Enseignant " + enseignant + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Enseignant enseignant) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Enseignant persistentEnseignant = em.find(Enseignant.class, enseignant.getIdEns());
            List<Enseigner> enseignerListOld = persistentEnseignant.getEnseignerList();
            List<Enseigner> enseignerListNew = enseignant.getEnseignerList();
            List<Enseigner> attachedEnseignerListNew = new ArrayList<Enseigner>();
            for (Enseigner enseignerListNewEnseignerToAttach : enseignerListNew) {
                enseignerListNewEnseignerToAttach = em.getReference(enseignerListNewEnseignerToAttach.getClass(), enseignerListNewEnseignerToAttach.getEnseignerCode());
                attachedEnseignerListNew.add(enseignerListNewEnseignerToAttach);
            }
            enseignerListNew = attachedEnseignerListNew;
            enseignant.setEnseignerList(enseignerListNew);
            enseignant = em.merge(enseignant);
            for (Enseigner enseignerListOldEnseigner : enseignerListOld) {
                if (!enseignerListNew.contains(enseignerListOldEnseigner)) {
                    enseignerListOldEnseigner.setIdEns(null);
                    enseignerListOldEnseigner = em.merge(enseignerListOldEnseigner);
                }
            }
            for (Enseigner enseignerListNewEnseigner : enseignerListNew) {
                if (!enseignerListOld.contains(enseignerListNewEnseigner)) {
                    Enseignant oldIdEnsOfEnseignerListNewEnseigner = enseignerListNewEnseigner.getIdEns();
                    enseignerListNewEnseigner.setIdEns(enseignant);
                    enseignerListNewEnseigner = em.merge(enseignerListNewEnseigner);
                    if (oldIdEnsOfEnseignerListNewEnseigner != null && !oldIdEnsOfEnseignerListNewEnseigner.equals(enseignant)) {
                        oldIdEnsOfEnseignerListNewEnseigner.getEnseignerList().remove(enseignerListNewEnseigner);
                        oldIdEnsOfEnseignerListNewEnseigner = em.merge(oldIdEnsOfEnseignerListNewEnseigner);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enseignant.getIdEns();
                if (findEnseignant(id) == null) {
                    throw new NonexistentEntityException("The enseignant with id " + id + " no longer exists.");
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
            Enseignant enseignant;
            try {
                enseignant = em.getReference(Enseignant.class, id);
                enseignant.getIdEns();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enseignant with id " + id + " no longer exists.", enfe);
            }
            List<Enseigner> enseignerList = enseignant.getEnseignerList();
            for (Enseigner enseignerListEnseigner : enseignerList) {
                enseignerListEnseigner.setIdEns(null);
                enseignerListEnseigner = em.merge(enseignerListEnseigner);
            }
            em.remove(enseignant);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Enseignant> findEnseignantEntities() {
        return findEnseignantEntities(true, -1, -1);
    }

    public List<Enseignant> findEnseignantEntities(int maxResults, int firstResult) {
        return findEnseignantEntities(false, maxResults, firstResult);
    }

    private List<Enseignant> findEnseignantEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Enseignant.class));
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

    public Enseignant findEnseignant(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Enseignant.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnseignantCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Enseignant> rt = cq.from(Enseignant.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
