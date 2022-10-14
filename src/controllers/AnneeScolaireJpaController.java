/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entites.AnneeScolaire;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entites.Trimestre;
import java.util.ArrayList;
import java.util.List;
import entites.Enseigner;
import entites.Regime;
import entites.EleveGp;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author OBAMA
 */
public class AnneeScolaireJpaController implements Serializable {

    public AnneeScolaireJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AnneeScolaire anneeScolaire) throws PreexistingEntityException, Exception {
        if (anneeScolaire.getTrimestreList() == null) {
            anneeScolaire.setTrimestreList(new ArrayList<Trimestre>());
        }
        if (anneeScolaire.getEnseignerList() == null) {
            anneeScolaire.setEnseignerList(new ArrayList<Enseigner>());
        }
        if (anneeScolaire.getRegimeList() == null) {
            anneeScolaire.setRegimeList(new ArrayList<Regime>());
        }
        if (anneeScolaire.getEleveGpList() == null) {
            anneeScolaire.setEleveGpList(new ArrayList<EleveGp>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Trimestre> attachedTrimestreList = new ArrayList<Trimestre>();
            for (Trimestre trimestreListTrimestreToAttach : anneeScolaire.getTrimestreList()) {
                trimestreListTrimestreToAttach = em.getReference(trimestreListTrimestreToAttach.getClass(), trimestreListTrimestreToAttach.getIdTrimes());
                attachedTrimestreList.add(trimestreListTrimestreToAttach);
            }
            anneeScolaire.setTrimestreList(attachedTrimestreList);
            List<Enseigner> attachedEnseignerList = new ArrayList<Enseigner>();
            for (Enseigner enseignerListEnseignerToAttach : anneeScolaire.getEnseignerList()) {
                enseignerListEnseignerToAttach = em.getReference(enseignerListEnseignerToAttach.getClass(), enseignerListEnseignerToAttach.getEnseignerCode());
                attachedEnseignerList.add(enseignerListEnseignerToAttach);
            }
            anneeScolaire.setEnseignerList(attachedEnseignerList);
            List<Regime> attachedRegimeList = new ArrayList<Regime>();
            for (Regime regimeListRegimeToAttach : anneeScolaire.getRegimeList()) {
                regimeListRegimeToAttach = em.getReference(regimeListRegimeToAttach.getClass(), regimeListRegimeToAttach.getRegimeCode());
                attachedRegimeList.add(regimeListRegimeToAttach);
            }
            anneeScolaire.setRegimeList(attachedRegimeList);
            List<EleveGp> attachedEleveGpList = new ArrayList<EleveGp>();
            for (EleveGp eleveGpListEleveGpToAttach : anneeScolaire.getEleveGpList()) {
                eleveGpListEleveGpToAttach = em.getReference(eleveGpListEleveGpToAttach.getClass(), eleveGpListEleveGpToAttach.getIdEleveGp());
                attachedEleveGpList.add(eleveGpListEleveGpToAttach);
            }
            anneeScolaire.setEleveGpList(attachedEleveGpList);
            em.persist(anneeScolaire);
            for (Trimestre trimestreListTrimestre : anneeScolaire.getTrimestreList()) {
                AnneeScolaire oldIdAnneeScolaireOfTrimestreListTrimestre = trimestreListTrimestre.getIdAnneeScolaire();
                trimestreListTrimestre.setIdAnneeScolaire(anneeScolaire);
                trimestreListTrimestre = em.merge(trimestreListTrimestre);
                if (oldIdAnneeScolaireOfTrimestreListTrimestre != null) {
                    oldIdAnneeScolaireOfTrimestreListTrimestre.getTrimestreList().remove(trimestreListTrimestre);
                    oldIdAnneeScolaireOfTrimestreListTrimestre = em.merge(oldIdAnneeScolaireOfTrimestreListTrimestre);
                }
            }
            for (Enseigner enseignerListEnseigner : anneeScolaire.getEnseignerList()) {
                AnneeScolaire oldIdAnneeScolaireOfEnseignerListEnseigner = enseignerListEnseigner.getIdAnneeScolaire();
                enseignerListEnseigner.setIdAnneeScolaire(anneeScolaire);
                enseignerListEnseigner = em.merge(enseignerListEnseigner);
                if (oldIdAnneeScolaireOfEnseignerListEnseigner != null) {
                    oldIdAnneeScolaireOfEnseignerListEnseigner.getEnseignerList().remove(enseignerListEnseigner);
                    oldIdAnneeScolaireOfEnseignerListEnseigner = em.merge(oldIdAnneeScolaireOfEnseignerListEnseigner);
                }
            }
            for (Regime regimeListRegime : anneeScolaire.getRegimeList()) {
                AnneeScolaire oldIdAnneeScolaireOfRegimeListRegime = regimeListRegime.getIdAnneeScolaire();
                regimeListRegime.setIdAnneeScolaire(anneeScolaire);
                regimeListRegime = em.merge(regimeListRegime);
                if (oldIdAnneeScolaireOfRegimeListRegime != null) {
                    oldIdAnneeScolaireOfRegimeListRegime.getRegimeList().remove(regimeListRegime);
                    oldIdAnneeScolaireOfRegimeListRegime = em.merge(oldIdAnneeScolaireOfRegimeListRegime);
                }
            }
            for (EleveGp eleveGpListEleveGp : anneeScolaire.getEleveGpList()) {
                AnneeScolaire oldIdAnneeScolaireOfEleveGpListEleveGp = eleveGpListEleveGp.getIdAnneeScolaire();
                eleveGpListEleveGp.setIdAnneeScolaire(anneeScolaire);
                eleveGpListEleveGp = em.merge(eleveGpListEleveGp);
                if (oldIdAnneeScolaireOfEleveGpListEleveGp != null) {
                    oldIdAnneeScolaireOfEleveGpListEleveGp.getEleveGpList().remove(eleveGpListEleveGp);
                    oldIdAnneeScolaireOfEleveGpListEleveGp = em.merge(oldIdAnneeScolaireOfEleveGpListEleveGp);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAnneeScolaire(anneeScolaire.getIdAnneeScolaire()) != null) {
                throw new PreexistingEntityException("AnneeScolaire " + anneeScolaire + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AnneeScolaire anneeScolaire) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AnneeScolaire persistentAnneeScolaire = em.find(AnneeScolaire.class, anneeScolaire.getIdAnneeScolaire());
            List<Trimestre> trimestreListOld = persistentAnneeScolaire.getTrimestreList();
            List<Trimestre> trimestreListNew = anneeScolaire.getTrimestreList();
            List<Enseigner> enseignerListOld = persistentAnneeScolaire.getEnseignerList();
            List<Enseigner> enseignerListNew = anneeScolaire.getEnseignerList();
            List<Regime> regimeListOld = persistentAnneeScolaire.getRegimeList();
            List<Regime> regimeListNew = anneeScolaire.getRegimeList();
            List<EleveGp> eleveGpListOld = persistentAnneeScolaire.getEleveGpList();
            List<EleveGp> eleveGpListNew = anneeScolaire.getEleveGpList();
            List<Trimestre> attachedTrimestreListNew = new ArrayList<Trimestre>();
            for (Trimestre trimestreListNewTrimestreToAttach : trimestreListNew) {
                trimestreListNewTrimestreToAttach = em.getReference(trimestreListNewTrimestreToAttach.getClass(), trimestreListNewTrimestreToAttach.getIdTrimes());
                attachedTrimestreListNew.add(trimestreListNewTrimestreToAttach);
            }
            trimestreListNew = attachedTrimestreListNew;
            anneeScolaire.setTrimestreList(trimestreListNew);
            List<Enseigner> attachedEnseignerListNew = new ArrayList<Enseigner>();
            for (Enseigner enseignerListNewEnseignerToAttach : enseignerListNew) {
                enseignerListNewEnseignerToAttach = em.getReference(enseignerListNewEnseignerToAttach.getClass(), enseignerListNewEnseignerToAttach.getEnseignerCode());
                attachedEnseignerListNew.add(enseignerListNewEnseignerToAttach);
            }
            enseignerListNew = attachedEnseignerListNew;
            anneeScolaire.setEnseignerList(enseignerListNew);
            List<Regime> attachedRegimeListNew = new ArrayList<Regime>();
            for (Regime regimeListNewRegimeToAttach : regimeListNew) {
                regimeListNewRegimeToAttach = em.getReference(regimeListNewRegimeToAttach.getClass(), regimeListNewRegimeToAttach.getRegimeCode());
                attachedRegimeListNew.add(regimeListNewRegimeToAttach);
            }
            regimeListNew = attachedRegimeListNew;
            anneeScolaire.setRegimeList(regimeListNew);
            List<EleveGp> attachedEleveGpListNew = new ArrayList<EleveGp>();
            for (EleveGp eleveGpListNewEleveGpToAttach : eleveGpListNew) {
                eleveGpListNewEleveGpToAttach = em.getReference(eleveGpListNewEleveGpToAttach.getClass(), eleveGpListNewEleveGpToAttach.getIdEleveGp());
                attachedEleveGpListNew.add(eleveGpListNewEleveGpToAttach);
            }
            eleveGpListNew = attachedEleveGpListNew;
            anneeScolaire.setEleveGpList(eleveGpListNew);
            anneeScolaire = em.merge(anneeScolaire);
            for (Trimestre trimestreListOldTrimestre : trimestreListOld) {
                if (!trimestreListNew.contains(trimestreListOldTrimestre)) {
                    trimestreListOldTrimestre.setIdAnneeScolaire(null);
                    trimestreListOldTrimestre = em.merge(trimestreListOldTrimestre);
                }
            }
            for (Trimestre trimestreListNewTrimestre : trimestreListNew) {
                if (!trimestreListOld.contains(trimestreListNewTrimestre)) {
                    AnneeScolaire oldIdAnneeScolaireOfTrimestreListNewTrimestre = trimestreListNewTrimestre.getIdAnneeScolaire();
                    trimestreListNewTrimestre.setIdAnneeScolaire(anneeScolaire);
                    trimestreListNewTrimestre = em.merge(trimestreListNewTrimestre);
                    if (oldIdAnneeScolaireOfTrimestreListNewTrimestre != null && !oldIdAnneeScolaireOfTrimestreListNewTrimestre.equals(anneeScolaire)) {
                        oldIdAnneeScolaireOfTrimestreListNewTrimestre.getTrimestreList().remove(trimestreListNewTrimestre);
                        oldIdAnneeScolaireOfTrimestreListNewTrimestre = em.merge(oldIdAnneeScolaireOfTrimestreListNewTrimestre);
                    }
                }
            }
            for (Enseigner enseignerListOldEnseigner : enseignerListOld) {
                if (!enseignerListNew.contains(enseignerListOldEnseigner)) {
                    enseignerListOldEnseigner.setIdAnneeScolaire(null);
                    enseignerListOldEnseigner = em.merge(enseignerListOldEnseigner);
                }
            }
            for (Enseigner enseignerListNewEnseigner : enseignerListNew) {
                if (!enseignerListOld.contains(enseignerListNewEnseigner)) {
                    AnneeScolaire oldIdAnneeScolaireOfEnseignerListNewEnseigner = enseignerListNewEnseigner.getIdAnneeScolaire();
                    enseignerListNewEnseigner.setIdAnneeScolaire(anneeScolaire);
                    enseignerListNewEnseigner = em.merge(enseignerListNewEnseigner);
                    if (oldIdAnneeScolaireOfEnseignerListNewEnseigner != null && !oldIdAnneeScolaireOfEnseignerListNewEnseigner.equals(anneeScolaire)) {
                        oldIdAnneeScolaireOfEnseignerListNewEnseigner.getEnseignerList().remove(enseignerListNewEnseigner);
                        oldIdAnneeScolaireOfEnseignerListNewEnseigner = em.merge(oldIdAnneeScolaireOfEnseignerListNewEnseigner);
                    }
                }
            }
            for (Regime regimeListOldRegime : regimeListOld) {
                if (!regimeListNew.contains(regimeListOldRegime)) {
                    regimeListOldRegime.setIdAnneeScolaire(null);
                    regimeListOldRegime = em.merge(regimeListOldRegime);
                }
            }
            for (Regime regimeListNewRegime : regimeListNew) {
                if (!regimeListOld.contains(regimeListNewRegime)) {
                    AnneeScolaire oldIdAnneeScolaireOfRegimeListNewRegime = regimeListNewRegime.getIdAnneeScolaire();
                    regimeListNewRegime.setIdAnneeScolaire(anneeScolaire);
                    regimeListNewRegime = em.merge(regimeListNewRegime);
                    if (oldIdAnneeScolaireOfRegimeListNewRegime != null && !oldIdAnneeScolaireOfRegimeListNewRegime.equals(anneeScolaire)) {
                        oldIdAnneeScolaireOfRegimeListNewRegime.getRegimeList().remove(regimeListNewRegime);
                        oldIdAnneeScolaireOfRegimeListNewRegime = em.merge(oldIdAnneeScolaireOfRegimeListNewRegime);
                    }
                }
            }
            for (EleveGp eleveGpListOldEleveGp : eleveGpListOld) {
                if (!eleveGpListNew.contains(eleveGpListOldEleveGp)) {
                    eleveGpListOldEleveGp.setIdAnneeScolaire(null);
                    eleveGpListOldEleveGp = em.merge(eleveGpListOldEleveGp);
                }
            }
            for (EleveGp eleveGpListNewEleveGp : eleveGpListNew) {
                if (!eleveGpListOld.contains(eleveGpListNewEleveGp)) {
                    AnneeScolaire oldIdAnneeScolaireOfEleveGpListNewEleveGp = eleveGpListNewEleveGp.getIdAnneeScolaire();
                    eleveGpListNewEleveGp.setIdAnneeScolaire(anneeScolaire);
                    eleveGpListNewEleveGp = em.merge(eleveGpListNewEleveGp);
                    if (oldIdAnneeScolaireOfEleveGpListNewEleveGp != null && !oldIdAnneeScolaireOfEleveGpListNewEleveGp.equals(anneeScolaire)) {
                        oldIdAnneeScolaireOfEleveGpListNewEleveGp.getEleveGpList().remove(eleveGpListNewEleveGp);
                        oldIdAnneeScolaireOfEleveGpListNewEleveGp = em.merge(oldIdAnneeScolaireOfEleveGpListNewEleveGp);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = anneeScolaire.getIdAnneeScolaire();
                if (findAnneeScolaire(id) == null) {
                    throw new NonexistentEntityException("The anneeScolaire with id " + id + " no longer exists.");
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
            AnneeScolaire anneeScolaire;
            try {
                anneeScolaire = em.getReference(AnneeScolaire.class, id);
                anneeScolaire.getIdAnneeScolaire();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The anneeScolaire with id " + id + " no longer exists.", enfe);
            }
            List<Trimestre> trimestreList = anneeScolaire.getTrimestreList();
            for (Trimestre trimestreListTrimestre : trimestreList) {
                trimestreListTrimestre.setIdAnneeScolaire(null);
                trimestreListTrimestre = em.merge(trimestreListTrimestre);
            }
            List<Enseigner> enseignerList = anneeScolaire.getEnseignerList();
            for (Enseigner enseignerListEnseigner : enseignerList) {
                enseignerListEnseigner.setIdAnneeScolaire(null);
                enseignerListEnseigner = em.merge(enseignerListEnseigner);
            }
            List<Regime> regimeList = anneeScolaire.getRegimeList();
            for (Regime regimeListRegime : regimeList) {
                regimeListRegime.setIdAnneeScolaire(null);
                regimeListRegime = em.merge(regimeListRegime);
            }
            List<EleveGp> eleveGpList = anneeScolaire.getEleveGpList();
            for (EleveGp eleveGpListEleveGp : eleveGpList) {
                eleveGpListEleveGp.setIdAnneeScolaire(null);
                eleveGpListEleveGp = em.merge(eleveGpListEleveGp);
            }
            em.remove(anneeScolaire);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AnneeScolaire> findAnneeScolaireEntities() {
        return findAnneeScolaireEntities(true, -1, -1);
    }

    public List<AnneeScolaire> findAnneeScolaireEntities(int maxResults, int firstResult) {
        return findAnneeScolaireEntities(false, maxResults, firstResult);
    }

    private List<AnneeScolaire> findAnneeScolaireEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AnneeScolaire.class));
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

    public AnneeScolaire findAnneeScolaire(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AnneeScolaire.class, id);
        } finally {
            em.close();
        }
    }

    public int getAnneeScolaireCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AnneeScolaire> rt = cq.from(AnneeScolaire.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
