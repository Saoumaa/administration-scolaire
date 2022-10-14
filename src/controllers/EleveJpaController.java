/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entites.Eleve;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entites.EleveGp;
import entites.Regime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author OBAMA
 */
public class EleveJpaController implements Serializable {

    public EleveJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Eleve eleve) throws PreexistingEntityException, Exception {
        if (eleve.getRegimeList() == null) {
            eleve.setRegimeList(new ArrayList<Regime>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EleveGp eleveGp = eleve.getEleveGp();
            if (eleveGp != null) {
                eleveGp = em.getReference(eleveGp.getClass(), eleveGp.getIdEleveGp());
                eleve.setEleveGp(eleveGp);
            }
            List<Regime> attachedRegimeList = new ArrayList<Regime>();
            for (Regime regimeListRegimeToAttach : eleve.getRegimeList()) {
                regimeListRegimeToAttach = em.getReference(regimeListRegimeToAttach.getClass(), regimeListRegimeToAttach.getRegimeCode());
                attachedRegimeList.add(regimeListRegimeToAttach);
            }
            eleve.setRegimeList(attachedRegimeList);
            em.persist(eleve);
            if (eleveGp != null) {
                Eleve oldEleveOfEleveGp = eleveGp.getEleve();
                if (oldEleveOfEleveGp != null) {
                    oldEleveOfEleveGp.setEleveGp(null);
                    oldEleveOfEleveGp = em.merge(oldEleveOfEleveGp);
                }
                eleveGp.setEleve(eleve);
                eleveGp = em.merge(eleveGp);
            }
            for (Regime regimeListRegime : eleve.getRegimeList()) {
                Eleve oldIdEleveOfRegimeListRegime = regimeListRegime.getIdEleve();
                regimeListRegime.setIdEleve(eleve);
                regimeListRegime = em.merge(regimeListRegime);
                if (oldIdEleveOfRegimeListRegime != null) {
                    oldIdEleveOfRegimeListRegime.getRegimeList().remove(regimeListRegime);
                    oldIdEleveOfRegimeListRegime = em.merge(oldIdEleveOfRegimeListRegime);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEleve(eleve.getIdEleve()) != null) {
                throw new PreexistingEntityException("Eleve " + eleve + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Eleve eleve) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Eleve persistentEleve = em.find(Eleve.class, eleve.getIdEleve());
            EleveGp eleveGpOld = persistentEleve.getEleveGp();
            EleveGp eleveGpNew = eleve.getEleveGp();
            List<Regime> regimeListOld = persistentEleve.getRegimeList();
            List<Regime> regimeListNew = eleve.getRegimeList();
            List<String> illegalOrphanMessages = null;
            if (eleveGpOld != null && !eleveGpOld.equals(eleveGpNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain EleveGp " + eleveGpOld + " since its eleve field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (eleveGpNew != null) {
                eleveGpNew = em.getReference(eleveGpNew.getClass(), eleveGpNew.getIdEleveGp());
                eleve.setEleveGp(eleveGpNew);
            }
            List<Regime> attachedRegimeListNew = new ArrayList<Regime>();
            for (Regime regimeListNewRegimeToAttach : regimeListNew) {
                regimeListNewRegimeToAttach = em.getReference(regimeListNewRegimeToAttach.getClass(), regimeListNewRegimeToAttach.getRegimeCode());
                attachedRegimeListNew.add(regimeListNewRegimeToAttach);
            }
            regimeListNew = attachedRegimeListNew;
            eleve.setRegimeList(regimeListNew);
            eleve = em.merge(eleve);
            if (eleveGpNew != null && !eleveGpNew.equals(eleveGpOld)) {
                Eleve oldEleveOfEleveGp = eleveGpNew.getEleve();
                if (oldEleveOfEleveGp != null) {
                    oldEleveOfEleveGp.setEleveGp(null);
                    oldEleveOfEleveGp = em.merge(oldEleveOfEleveGp);
                }
                eleveGpNew.setEleve(eleve);
                eleveGpNew = em.merge(eleveGpNew);
            }
            for (Regime regimeListOldRegime : regimeListOld) {
                if (!regimeListNew.contains(regimeListOldRegime)) {
                    regimeListOldRegime.setIdEleve(null);
                    regimeListOldRegime = em.merge(regimeListOldRegime);
                }
            }
            for (Regime regimeListNewRegime : regimeListNew) {
                if (!regimeListOld.contains(regimeListNewRegime)) {
                    Eleve oldIdEleveOfRegimeListNewRegime = regimeListNewRegime.getIdEleve();
                    regimeListNewRegime.setIdEleve(eleve);
                    regimeListNewRegime = em.merge(regimeListNewRegime);
                    if (oldIdEleveOfRegimeListNewRegime != null && !oldIdEleveOfRegimeListNewRegime.equals(eleve)) {
                        oldIdEleveOfRegimeListNewRegime.getRegimeList().remove(regimeListNewRegime);
                        oldIdEleveOfRegimeListNewRegime = em.merge(oldIdEleveOfRegimeListNewRegime);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = eleve.getIdEleve();
                if (findEleve(id) == null) {
                    throw new NonexistentEntityException("The eleve with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Eleve eleve;
            try {
                eleve = em.getReference(Eleve.class, id);
                eleve.getIdEleve();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eleve with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            EleveGp eleveGpOrphanCheck = eleve.getEleveGp();
            if (eleveGpOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Eleve (" + eleve + ") cannot be destroyed since the EleveGp " + eleveGpOrphanCheck + " in its eleveGp field has a non-nullable eleve field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Regime> regimeList = eleve.getRegimeList();
            for (Regime regimeListRegime : regimeList) {
                regimeListRegime.setIdEleve(null);
                regimeListRegime = em.merge(regimeListRegime);
            }
            em.remove(eleve);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Eleve> findEleveEntities() {
        return findEleveEntities(true, -1, -1);
    }

    public List<Eleve> findEleveEntities(int maxResults, int firstResult) {
        return findEleveEntities(false, maxResults, firstResult);
    }

    private List<Eleve> findEleveEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Eleve.class));
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

    public Eleve findEleve(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Eleve.class, id);
        } finally {
            em.close();
        }
    }

    public int getEleveCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Eleve> rt = cq.from(Eleve.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
