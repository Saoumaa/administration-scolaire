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
import entites.Enseigner;
import entites.GroupPedag;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author OBAMA
 */
public class GroupPedagJpaController implements Serializable {

    public GroupPedagJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GroupPedag groupPedag) throws PreexistingEntityException, Exception {
        if (groupPedag.getEnseignerList() == null) {
            groupPedag.setEnseignerList(new ArrayList<Enseigner>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Enseigner> attachedEnseignerList = new ArrayList<Enseigner>();
            for (Enseigner enseignerListEnseignerToAttach : groupPedag.getEnseignerList()) {
                enseignerListEnseignerToAttach = em.getReference(enseignerListEnseignerToAttach.getClass(), enseignerListEnseignerToAttach.getEnseignerCode());
                attachedEnseignerList.add(enseignerListEnseignerToAttach);
            }
            groupPedag.setEnseignerList(attachedEnseignerList);
            em.persist(groupPedag);
            for (Enseigner enseignerListEnseigner : groupPedag.getEnseignerList()) {
                GroupPedag oldIdGroupPedagOfEnseignerListEnseigner = enseignerListEnseigner.getIdGroupPedag();
                enseignerListEnseigner.setIdGroupPedag(groupPedag);
                enseignerListEnseigner = em.merge(enseignerListEnseigner);
                if (oldIdGroupPedagOfEnseignerListEnseigner != null) {
                    oldIdGroupPedagOfEnseignerListEnseigner.getEnseignerList().remove(enseignerListEnseigner);
                    oldIdGroupPedagOfEnseignerListEnseigner = em.merge(oldIdGroupPedagOfEnseignerListEnseigner);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGroupPedag(groupPedag.getIdGroupPedag()) != null) {
                throw new PreexistingEntityException("GroupPedag " + groupPedag + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GroupPedag groupPedag) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GroupPedag persistentGroupPedag = em.find(GroupPedag.class, groupPedag.getIdGroupPedag());
            List<Enseigner> enseignerListOld = persistentGroupPedag.getEnseignerList();
            List<Enseigner> enseignerListNew = groupPedag.getEnseignerList();
            List<Enseigner> attachedEnseignerListNew = new ArrayList<Enseigner>();
            for (Enseigner enseignerListNewEnseignerToAttach : enseignerListNew) {
                enseignerListNewEnseignerToAttach = em.getReference(enseignerListNewEnseignerToAttach.getClass(), enseignerListNewEnseignerToAttach.getEnseignerCode());
                attachedEnseignerListNew.add(enseignerListNewEnseignerToAttach);
            }
            enseignerListNew = attachedEnseignerListNew;
            groupPedag.setEnseignerList(enseignerListNew);
            groupPedag = em.merge(groupPedag);
            for (Enseigner enseignerListOldEnseigner : enseignerListOld) {
                if (!enseignerListNew.contains(enseignerListOldEnseigner)) {
                    enseignerListOldEnseigner.setIdGroupPedag(null);
                    enseignerListOldEnseigner = em.merge(enseignerListOldEnseigner);
                }
            }
            for (Enseigner enseignerListNewEnseigner : enseignerListNew) {
                if (!enseignerListOld.contains(enseignerListNewEnseigner)) {
                    GroupPedag oldIdGroupPedagOfEnseignerListNewEnseigner = enseignerListNewEnseigner.getIdGroupPedag();
                    enseignerListNewEnseigner.setIdGroupPedag(groupPedag);
                    enseignerListNewEnseigner = em.merge(enseignerListNewEnseigner);
                    if (oldIdGroupPedagOfEnseignerListNewEnseigner != null && !oldIdGroupPedagOfEnseignerListNewEnseigner.equals(groupPedag)) {
                        oldIdGroupPedagOfEnseignerListNewEnseigner.getEnseignerList().remove(enseignerListNewEnseigner);
                        oldIdGroupPedagOfEnseignerListNewEnseigner = em.merge(oldIdGroupPedagOfEnseignerListNewEnseigner);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = groupPedag.getIdGroupPedag();
                if (findGroupPedag(id) == null) {
                    throw new NonexistentEntityException("The groupPedag with id " + id + " no longer exists.");
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
            GroupPedag groupPedag;
            try {
                groupPedag = em.getReference(GroupPedag.class, id);
                groupPedag.getIdGroupPedag();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The groupPedag with id " + id + " no longer exists.", enfe);
            }
            List<Enseigner> enseignerList = groupPedag.getEnseignerList();
            for (Enseigner enseignerListEnseigner : enseignerList) {
                enseignerListEnseigner.setIdGroupPedag(null);
                enseignerListEnseigner = em.merge(enseignerListEnseigner);
            }
            em.remove(groupPedag);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GroupPedag> findGroupPedagEntities() {
        return findGroupPedagEntities(true, -1, -1);
    }

    public List<GroupPedag> findGroupPedagEntities(int maxResults, int firstResult) {
        return findGroupPedagEntities(false, maxResults, firstResult);
    }

    private List<GroupPedag> findGroupPedagEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GroupPedag.class));
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

    public GroupPedag findGroupPedag(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GroupPedag.class, id);
        } finally {
            em.close();
        }
    }

    public int getGroupPedagCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GroupPedag> rt = cq.from(GroupPedag.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
