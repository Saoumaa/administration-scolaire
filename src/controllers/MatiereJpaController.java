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
import entites.Coefficient;
import java.util.ArrayList;
import java.util.List;
import entites.Enseigner;
import entites.Matiere;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author OBAMA
 */
public class MatiereJpaController implements Serializable {

    public MatiereJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Matiere matiere) throws PreexistingEntityException, Exception {
        if (matiere.getCoefficientList() == null) {
            matiere.setCoefficientList(new ArrayList<Coefficient>());
        }
        if (matiere.getEnseignerList() == null) {
            matiere.setEnseignerList(new ArrayList<Enseigner>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Coefficient> attachedCoefficientList = new ArrayList<Coefficient>();
            for (Coefficient coefficientListCoefficientToAttach : matiere.getCoefficientList()) {
                coefficientListCoefficientToAttach = em.getReference(coefficientListCoefficientToAttach.getClass(), coefficientListCoefficientToAttach.getIdCoef());
                attachedCoefficientList.add(coefficientListCoefficientToAttach);
            }
            matiere.setCoefficientList(attachedCoefficientList);
            List<Enseigner> attachedEnseignerList = new ArrayList<Enseigner>();
            for (Enseigner enseignerListEnseignerToAttach : matiere.getEnseignerList()) {
                enseignerListEnseignerToAttach = em.getReference(enseignerListEnseignerToAttach.getClass(), enseignerListEnseignerToAttach.getEnseignerCode());
                attachedEnseignerList.add(enseignerListEnseignerToAttach);
            }
            matiere.setEnseignerList(attachedEnseignerList);
            em.persist(matiere);
            for (Coefficient coefficientListCoefficient : matiere.getCoefficientList()) {
                Matiere oldIdMatiereOfCoefficientListCoefficient = coefficientListCoefficient.getIdMatiere();
                coefficientListCoefficient.setIdMatiere(matiere);
                coefficientListCoefficient = em.merge(coefficientListCoefficient);
                if (oldIdMatiereOfCoefficientListCoefficient != null) {
                    oldIdMatiereOfCoefficientListCoefficient.getCoefficientList().remove(coefficientListCoefficient);
                    oldIdMatiereOfCoefficientListCoefficient = em.merge(oldIdMatiereOfCoefficientListCoefficient);
                }
            }
            for (Enseigner enseignerListEnseigner : matiere.getEnseignerList()) {
                Matiere oldIdMatiereOfEnseignerListEnseigner = enseignerListEnseigner.getIdMatiere();
                enseignerListEnseigner.setIdMatiere(matiere);
                enseignerListEnseigner = em.merge(enseignerListEnseigner);
                if (oldIdMatiereOfEnseignerListEnseigner != null) {
                    oldIdMatiereOfEnseignerListEnseigner.getEnseignerList().remove(enseignerListEnseigner);
                    oldIdMatiereOfEnseignerListEnseigner = em.merge(oldIdMatiereOfEnseignerListEnseigner);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMatiere(matiere.getIdMatiere()) != null) {
                throw new PreexistingEntityException("Matiere " + matiere + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Matiere matiere) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Matiere persistentMatiere = em.find(Matiere.class, matiere.getIdMatiere());
            List<Coefficient> coefficientListOld = persistentMatiere.getCoefficientList();
            List<Coefficient> coefficientListNew = matiere.getCoefficientList();
            List<Enseigner> enseignerListOld = persistentMatiere.getEnseignerList();
            List<Enseigner> enseignerListNew = matiere.getEnseignerList();
            List<Coefficient> attachedCoefficientListNew = new ArrayList<Coefficient>();
            for (Coefficient coefficientListNewCoefficientToAttach : coefficientListNew) {
                coefficientListNewCoefficientToAttach = em.getReference(coefficientListNewCoefficientToAttach.getClass(), coefficientListNewCoefficientToAttach.getIdCoef());
                attachedCoefficientListNew.add(coefficientListNewCoefficientToAttach);
            }
            coefficientListNew = attachedCoefficientListNew;
            matiere.setCoefficientList(coefficientListNew);
            List<Enseigner> attachedEnseignerListNew = new ArrayList<Enseigner>();
            for (Enseigner enseignerListNewEnseignerToAttach : enseignerListNew) {
                enseignerListNewEnseignerToAttach = em.getReference(enseignerListNewEnseignerToAttach.getClass(), enseignerListNewEnseignerToAttach.getEnseignerCode());
                attachedEnseignerListNew.add(enseignerListNewEnseignerToAttach);
            }
            enseignerListNew = attachedEnseignerListNew;
            matiere.setEnseignerList(enseignerListNew);
            matiere = em.merge(matiere);
            for (Coefficient coefficientListOldCoefficient : coefficientListOld) {
                if (!coefficientListNew.contains(coefficientListOldCoefficient)) {
                    coefficientListOldCoefficient.setIdMatiere(null);
                    coefficientListOldCoefficient = em.merge(coefficientListOldCoefficient);
                }
            }
            for (Coefficient coefficientListNewCoefficient : coefficientListNew) {
                if (!coefficientListOld.contains(coefficientListNewCoefficient)) {
                    Matiere oldIdMatiereOfCoefficientListNewCoefficient = coefficientListNewCoefficient.getIdMatiere();
                    coefficientListNewCoefficient.setIdMatiere(matiere);
                    coefficientListNewCoefficient = em.merge(coefficientListNewCoefficient);
                    if (oldIdMatiereOfCoefficientListNewCoefficient != null && !oldIdMatiereOfCoefficientListNewCoefficient.equals(matiere)) {
                        oldIdMatiereOfCoefficientListNewCoefficient.getCoefficientList().remove(coefficientListNewCoefficient);
                        oldIdMatiereOfCoefficientListNewCoefficient = em.merge(oldIdMatiereOfCoefficientListNewCoefficient);
                    }
                }
            }
            for (Enseigner enseignerListOldEnseigner : enseignerListOld) {
                if (!enseignerListNew.contains(enseignerListOldEnseigner)) {
                    enseignerListOldEnseigner.setIdMatiere(null);
                    enseignerListOldEnseigner = em.merge(enseignerListOldEnseigner);
                }
            }
            for (Enseigner enseignerListNewEnseigner : enseignerListNew) {
                if (!enseignerListOld.contains(enseignerListNewEnseigner)) {
                    Matiere oldIdMatiereOfEnseignerListNewEnseigner = enseignerListNewEnseigner.getIdMatiere();
                    enseignerListNewEnseigner.setIdMatiere(matiere);
                    enseignerListNewEnseigner = em.merge(enseignerListNewEnseigner);
                    if (oldIdMatiereOfEnseignerListNewEnseigner != null && !oldIdMatiereOfEnseignerListNewEnseigner.equals(matiere)) {
                        oldIdMatiereOfEnseignerListNewEnseigner.getEnseignerList().remove(enseignerListNewEnseigner);
                        oldIdMatiereOfEnseignerListNewEnseigner = em.merge(oldIdMatiereOfEnseignerListNewEnseigner);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = matiere.getIdMatiere();
                if (findMatiere(id) == null) {
                    throw new NonexistentEntityException("The matiere with id " + id + " no longer exists.");
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
            Matiere matiere;
            try {
                matiere = em.getReference(Matiere.class, id);
                matiere.getIdMatiere();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The matiere with id " + id + " no longer exists.", enfe);
            }
            List<Coefficient> coefficientList = matiere.getCoefficientList();
            for (Coefficient coefficientListCoefficient : coefficientList) {
                coefficientListCoefficient.setIdMatiere(null);
                coefficientListCoefficient = em.merge(coefficientListCoefficient);
            }
            List<Enseigner> enseignerList = matiere.getEnseignerList();
            for (Enseigner enseignerListEnseigner : enseignerList) {
                enseignerListEnseigner.setIdMatiere(null);
                enseignerListEnseigner = em.merge(enseignerListEnseigner);
            }
            em.remove(matiere);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Matiere> findMatiereEntities() {
        return findMatiereEntities(true, -1, -1);
    }

    public List<Matiere> findMatiereEntities(int maxResults, int firstResult) {
        return findMatiereEntities(false, maxResults, firstResult);
    }

    private List<Matiere> findMatiereEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Matiere.class));
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

    public Matiere findMatiere(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Matiere.class, id);
        } finally {
            em.close();
        }
    }

    public int getMatiereCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Matiere> rt = cq.from(Matiere.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
