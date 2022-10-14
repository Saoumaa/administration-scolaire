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
import entites.AnneeScolaire;
import entites.Eleve;
import entites.Regime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author OBAMA
 */
public class RegimeJpaController implements Serializable {

    public RegimeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Regime regime) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AnneeScolaire idAnneeScolaire = regime.getIdAnneeScolaire();
            if (idAnneeScolaire != null) {
                idAnneeScolaire = em.getReference(idAnneeScolaire.getClass(), idAnneeScolaire.getIdAnneeScolaire());
                regime.setIdAnneeScolaire(idAnneeScolaire);
            }
            Eleve idEleve = regime.getIdEleve();
            if (idEleve != null) {
                idEleve = em.getReference(idEleve.getClass(), idEleve.getIdEleve());
                regime.setIdEleve(idEleve);
            }
            em.persist(regime);
            if (idAnneeScolaire != null) {
                idAnneeScolaire.getRegimeList().add(regime);
                idAnneeScolaire = em.merge(idAnneeScolaire);
            }
            if (idEleve != null) {
                idEleve.getRegimeList().add(regime);
                idEleve = em.merge(idEleve);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRegime(regime.getRegimeCode()) != null) {
                throw new PreexistingEntityException("Regime " + regime + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Regime regime) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Regime persistentRegime = em.find(Regime.class, regime.getRegimeCode());
            AnneeScolaire idAnneeScolaireOld = persistentRegime.getIdAnneeScolaire();
            AnneeScolaire idAnneeScolaireNew = regime.getIdAnneeScolaire();
            Eleve idEleveOld = persistentRegime.getIdEleve();
            Eleve idEleveNew = regime.getIdEleve();
            if (idAnneeScolaireNew != null) {
                idAnneeScolaireNew = em.getReference(idAnneeScolaireNew.getClass(), idAnneeScolaireNew.getIdAnneeScolaire());
                regime.setIdAnneeScolaire(idAnneeScolaireNew);
            }
            if (idEleveNew != null) {
                idEleveNew = em.getReference(idEleveNew.getClass(), idEleveNew.getIdEleve());
                regime.setIdEleve(idEleveNew);
            }
            regime = em.merge(regime);
            if (idAnneeScolaireOld != null && !idAnneeScolaireOld.equals(idAnneeScolaireNew)) {
                idAnneeScolaireOld.getRegimeList().remove(regime);
                idAnneeScolaireOld = em.merge(idAnneeScolaireOld);
            }
            if (idAnneeScolaireNew != null && !idAnneeScolaireNew.equals(idAnneeScolaireOld)) {
                idAnneeScolaireNew.getRegimeList().add(regime);
                idAnneeScolaireNew = em.merge(idAnneeScolaireNew);
            }
            if (idEleveOld != null && !idEleveOld.equals(idEleveNew)) {
                idEleveOld.getRegimeList().remove(regime);
                idEleveOld = em.merge(idEleveOld);
            }
            if (idEleveNew != null && !idEleveNew.equals(idEleveOld)) {
                idEleveNew.getRegimeList().add(regime);
                idEleveNew = em.merge(idEleveNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = regime.getRegimeCode();
                if (findRegime(id) == null) {
                    throw new NonexistentEntityException("The regime with id " + id + " no longer exists.");
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
            Regime regime;
            try {
                regime = em.getReference(Regime.class, id);
                regime.getRegimeCode();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The regime with id " + id + " no longer exists.", enfe);
            }
            AnneeScolaire idAnneeScolaire = regime.getIdAnneeScolaire();
            if (idAnneeScolaire != null) {
                idAnneeScolaire.getRegimeList().remove(regime);
                idAnneeScolaire = em.merge(idAnneeScolaire);
            }
            Eleve idEleve = regime.getIdEleve();
            if (idEleve != null) {
                idEleve.getRegimeList().remove(regime);
                idEleve = em.merge(idEleve);
            }
            em.remove(regime);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Regime> findRegimeEntities() {
        return findRegimeEntities(true, -1, -1);
    }

    public List<Regime> findRegimeEntities(int maxResults, int firstResult) {
        return findRegimeEntities(false, maxResults, firstResult);
    }

    private List<Regime> findRegimeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Regime.class));
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

    public Regime findRegime(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Regime.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegimeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Regime> rt = cq.from(Regime.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
