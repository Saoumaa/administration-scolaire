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
import entites.Enseignant;
import entites.Enseigner;
import entites.GroupPedag;
import entites.Matiere;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author OBAMA
 */
public class EnseignerJpaController implements Serializable {

    public EnseignerJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Enseigner enseigner) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AnneeScolaire idAnneeScolaire = enseigner.getIdAnneeScolaire();
            if (idAnneeScolaire != null) {
                idAnneeScolaire = em.getReference(idAnneeScolaire.getClass(), idAnneeScolaire.getIdAnneeScolaire());
                enseigner.setIdAnneeScolaire(idAnneeScolaire);
            }
            Enseignant idEns = enseigner.getIdEns();
            if (idEns != null) {
                idEns = em.getReference(idEns.getClass(), idEns.getIdEns());
                enseigner.setIdEns(idEns);
            }
            GroupPedag idGroupPedag = enseigner.getIdGroupPedag();
            if (idGroupPedag != null) {
                idGroupPedag = em.getReference(idGroupPedag.getClass(), idGroupPedag.getIdGroupPedag());
                enseigner.setIdGroupPedag(idGroupPedag);
            }
            Matiere idMatiere = enseigner.getIdMatiere();
            if (idMatiere != null) {
                idMatiere = em.getReference(idMatiere.getClass(), idMatiere.getIdMatiere());
                enseigner.setIdMatiere(idMatiere);
            }
            em.persist(enseigner);
            if (idAnneeScolaire != null) {
                idAnneeScolaire.getEnseignerList().add(enseigner);
                idAnneeScolaire = em.merge(idAnneeScolaire);
            }
            if (idEns != null) {
                idEns.getEnseignerList().add(enseigner);
                idEns = em.merge(idEns);
            }
            if (idGroupPedag != null) {
                idGroupPedag.getEnseignerList().add(enseigner);
                idGroupPedag = em.merge(idGroupPedag);
            }
            if (idMatiere != null) {
                idMatiere.getEnseignerList().add(enseigner);
                idMatiere = em.merge(idMatiere);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEnseigner(enseigner.getEnseignerCode()) != null) {
                throw new PreexistingEntityException("Enseigner " + enseigner + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Enseigner enseigner) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Enseigner persistentEnseigner = em.find(Enseigner.class, enseigner.getEnseignerCode());
            AnneeScolaire idAnneeScolaireOld = persistentEnseigner.getIdAnneeScolaire();
            AnneeScolaire idAnneeScolaireNew = enseigner.getIdAnneeScolaire();
            Enseignant idEnsOld = persistentEnseigner.getIdEns();
            Enseignant idEnsNew = enseigner.getIdEns();
            GroupPedag idGroupPedagOld = persistentEnseigner.getIdGroupPedag();
            GroupPedag idGroupPedagNew = enseigner.getIdGroupPedag();
            Matiere idMatiereOld = persistentEnseigner.getIdMatiere();
            Matiere idMatiereNew = enseigner.getIdMatiere();
            if (idAnneeScolaireNew != null) {
                idAnneeScolaireNew = em.getReference(idAnneeScolaireNew.getClass(), idAnneeScolaireNew.getIdAnneeScolaire());
                enseigner.setIdAnneeScolaire(idAnneeScolaireNew);
            }
            if (idEnsNew != null) {
                idEnsNew = em.getReference(idEnsNew.getClass(), idEnsNew.getIdEns());
                enseigner.setIdEns(idEnsNew);
            }
            if (idGroupPedagNew != null) {
                idGroupPedagNew = em.getReference(idGroupPedagNew.getClass(), idGroupPedagNew.getIdGroupPedag());
                enseigner.setIdGroupPedag(idGroupPedagNew);
            }
            if (idMatiereNew != null) {
                idMatiereNew = em.getReference(idMatiereNew.getClass(), idMatiereNew.getIdMatiere());
                enseigner.setIdMatiere(idMatiereNew);
            }
            enseigner = em.merge(enseigner);
            if (idAnneeScolaireOld != null && !idAnneeScolaireOld.equals(idAnneeScolaireNew)) {
                idAnneeScolaireOld.getEnseignerList().remove(enseigner);
                idAnneeScolaireOld = em.merge(idAnneeScolaireOld);
            }
            if (idAnneeScolaireNew != null && !idAnneeScolaireNew.equals(idAnneeScolaireOld)) {
                idAnneeScolaireNew.getEnseignerList().add(enseigner);
                idAnneeScolaireNew = em.merge(idAnneeScolaireNew);
            }
            if (idEnsOld != null && !idEnsOld.equals(idEnsNew)) {
                idEnsOld.getEnseignerList().remove(enseigner);
                idEnsOld = em.merge(idEnsOld);
            }
            if (idEnsNew != null && !idEnsNew.equals(idEnsOld)) {
                idEnsNew.getEnseignerList().add(enseigner);
                idEnsNew = em.merge(idEnsNew);
            }
            if (idGroupPedagOld != null && !idGroupPedagOld.equals(idGroupPedagNew)) {
                idGroupPedagOld.getEnseignerList().remove(enseigner);
                idGroupPedagOld = em.merge(idGroupPedagOld);
            }
            if (idGroupPedagNew != null && !idGroupPedagNew.equals(idGroupPedagOld)) {
                idGroupPedagNew.getEnseignerList().add(enseigner);
                idGroupPedagNew = em.merge(idGroupPedagNew);
            }
            if (idMatiereOld != null && !idMatiereOld.equals(idMatiereNew)) {
                idMatiereOld.getEnseignerList().remove(enseigner);
                idMatiereOld = em.merge(idMatiereOld);
            }
            if (idMatiereNew != null && !idMatiereNew.equals(idMatiereOld)) {
                idMatiereNew.getEnseignerList().add(enseigner);
                idMatiereNew = em.merge(idMatiereNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = enseigner.getEnseignerCode();
                if (findEnseigner(id) == null) {
                    throw new NonexistentEntityException("The enseigner with id " + id + " no longer exists.");
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
            Enseigner enseigner;
            try {
                enseigner = em.getReference(Enseigner.class, id);
                enseigner.getEnseignerCode();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enseigner with id " + id + " no longer exists.", enfe);
            }
            AnneeScolaire idAnneeScolaire = enseigner.getIdAnneeScolaire();
            if (idAnneeScolaire != null) {
                idAnneeScolaire.getEnseignerList().remove(enseigner);
                idAnneeScolaire = em.merge(idAnneeScolaire);
            }
            Enseignant idEns = enseigner.getIdEns();
            if (idEns != null) {
                idEns.getEnseignerList().remove(enseigner);
                idEns = em.merge(idEns);
            }
            GroupPedag idGroupPedag = enseigner.getIdGroupPedag();
            if (idGroupPedag != null) {
                idGroupPedag.getEnseignerList().remove(enseigner);
                idGroupPedag = em.merge(idGroupPedag);
            }
            Matiere idMatiere = enseigner.getIdMatiere();
            if (idMatiere != null) {
                idMatiere.getEnseignerList().remove(enseigner);
                idMatiere = em.merge(idMatiere);
            }
            em.remove(enseigner);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Enseigner> findEnseignerEntities() {
        return findEnseignerEntities(true, -1, -1);
    }

    public List<Enseigner> findEnseignerEntities(int maxResults, int firstResult) {
        return findEnseignerEntities(false, maxResults, firstResult);
    }

    private List<Enseigner> findEnseignerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Enseigner.class));
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

    public Enseigner findEnseigner(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Enseigner.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnseignerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Enseigner> rt = cq.from(Enseigner.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
