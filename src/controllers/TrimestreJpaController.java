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
import entites.BulletinNote;
import entites.Trimestre;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author OBAMA
 */
public class TrimestreJpaController implements Serializable {

    public TrimestreJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Trimestre trimestre) throws PreexistingEntityException, Exception {
        if (trimestre.getBulletinNoteList() == null) {
            trimestre.setBulletinNoteList(new ArrayList<BulletinNote>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AnneeScolaire idAnneeScolaire = trimestre.getIdAnneeScolaire();
            if (idAnneeScolaire != null) {
                idAnneeScolaire = em.getReference(idAnneeScolaire.getClass(), idAnneeScolaire.getIdAnneeScolaire());
                trimestre.setIdAnneeScolaire(idAnneeScolaire);
            }
            List<BulletinNote> attachedBulletinNoteList = new ArrayList<BulletinNote>();
            for (BulletinNote bulletinNoteListBulletinNoteToAttach : trimestre.getBulletinNoteList()) {
                bulletinNoteListBulletinNoteToAttach = em.getReference(bulletinNoteListBulletinNoteToAttach.getClass(), bulletinNoteListBulletinNoteToAttach.getIdBulletin());
                attachedBulletinNoteList.add(bulletinNoteListBulletinNoteToAttach);
            }
            trimestre.setBulletinNoteList(attachedBulletinNoteList);
            em.persist(trimestre);
            if (idAnneeScolaire != null) {
                idAnneeScolaire.getTrimestreList().add(trimestre);
                idAnneeScolaire = em.merge(idAnneeScolaire);
            }
            for (BulletinNote bulletinNoteListBulletinNote : trimestre.getBulletinNoteList()) {
                Trimestre oldIdTrimesOfBulletinNoteListBulletinNote = bulletinNoteListBulletinNote.getIdTrimes();
                bulletinNoteListBulletinNote.setIdTrimes(trimestre);
                bulletinNoteListBulletinNote = em.merge(bulletinNoteListBulletinNote);
                if (oldIdTrimesOfBulletinNoteListBulletinNote != null) {
                    oldIdTrimesOfBulletinNoteListBulletinNote.getBulletinNoteList().remove(bulletinNoteListBulletinNote);
                    oldIdTrimesOfBulletinNoteListBulletinNote = em.merge(oldIdTrimesOfBulletinNoteListBulletinNote);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTrimestre(trimestre.getIdTrimes()) != null) {
                throw new PreexistingEntityException("Trimestre " + trimestre + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Trimestre trimestre) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Trimestre persistentTrimestre = em.find(Trimestre.class, trimestre.getIdTrimes());
            AnneeScolaire idAnneeScolaireOld = persistentTrimestre.getIdAnneeScolaire();
            AnneeScolaire idAnneeScolaireNew = trimestre.getIdAnneeScolaire();
            List<BulletinNote> bulletinNoteListOld = persistentTrimestre.getBulletinNoteList();
            List<BulletinNote> bulletinNoteListNew = trimestre.getBulletinNoteList();
            if (idAnneeScolaireNew != null) {
                idAnneeScolaireNew = em.getReference(idAnneeScolaireNew.getClass(), idAnneeScolaireNew.getIdAnneeScolaire());
                trimestre.setIdAnneeScolaire(idAnneeScolaireNew);
            }
            List<BulletinNote> attachedBulletinNoteListNew = new ArrayList<BulletinNote>();
            for (BulletinNote bulletinNoteListNewBulletinNoteToAttach : bulletinNoteListNew) {
                bulletinNoteListNewBulletinNoteToAttach = em.getReference(bulletinNoteListNewBulletinNoteToAttach.getClass(), bulletinNoteListNewBulletinNoteToAttach.getIdBulletin());
                attachedBulletinNoteListNew.add(bulletinNoteListNewBulletinNoteToAttach);
            }
            bulletinNoteListNew = attachedBulletinNoteListNew;
            trimestre.setBulletinNoteList(bulletinNoteListNew);
            trimestre = em.merge(trimestre);
            if (idAnneeScolaireOld != null && !idAnneeScolaireOld.equals(idAnneeScolaireNew)) {
                idAnneeScolaireOld.getTrimestreList().remove(trimestre);
                idAnneeScolaireOld = em.merge(idAnneeScolaireOld);
            }
            if (idAnneeScolaireNew != null && !idAnneeScolaireNew.equals(idAnneeScolaireOld)) {
                idAnneeScolaireNew.getTrimestreList().add(trimestre);
                idAnneeScolaireNew = em.merge(idAnneeScolaireNew);
            }
            for (BulletinNote bulletinNoteListOldBulletinNote : bulletinNoteListOld) {
                if (!bulletinNoteListNew.contains(bulletinNoteListOldBulletinNote)) {
                    bulletinNoteListOldBulletinNote.setIdTrimes(null);
                    bulletinNoteListOldBulletinNote = em.merge(bulletinNoteListOldBulletinNote);
                }
            }
            for (BulletinNote bulletinNoteListNewBulletinNote : bulletinNoteListNew) {
                if (!bulletinNoteListOld.contains(bulletinNoteListNewBulletinNote)) {
                    Trimestre oldIdTrimesOfBulletinNoteListNewBulletinNote = bulletinNoteListNewBulletinNote.getIdTrimes();
                    bulletinNoteListNewBulletinNote.setIdTrimes(trimestre);
                    bulletinNoteListNewBulletinNote = em.merge(bulletinNoteListNewBulletinNote);
                    if (oldIdTrimesOfBulletinNoteListNewBulletinNote != null && !oldIdTrimesOfBulletinNoteListNewBulletinNote.equals(trimestre)) {
                        oldIdTrimesOfBulletinNoteListNewBulletinNote.getBulletinNoteList().remove(bulletinNoteListNewBulletinNote);
                        oldIdTrimesOfBulletinNoteListNewBulletinNote = em.merge(oldIdTrimesOfBulletinNoteListNewBulletinNote);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = trimestre.getIdTrimes();
                if (findTrimestre(id) == null) {
                    throw new NonexistentEntityException("The trimestre with id " + id + " no longer exists.");
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
            Trimestre trimestre;
            try {
                trimestre = em.getReference(Trimestre.class, id);
                trimestre.getIdTrimes();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The trimestre with id " + id + " no longer exists.", enfe);
            }
            AnneeScolaire idAnneeScolaire = trimestre.getIdAnneeScolaire();
            if (idAnneeScolaire != null) {
                idAnneeScolaire.getTrimestreList().remove(trimestre);
                idAnneeScolaire = em.merge(idAnneeScolaire);
            }
            List<BulletinNote> bulletinNoteList = trimestre.getBulletinNoteList();
            for (BulletinNote bulletinNoteListBulletinNote : bulletinNoteList) {
                bulletinNoteListBulletinNote.setIdTrimes(null);
                bulletinNoteListBulletinNote = em.merge(bulletinNoteListBulletinNote);
            }
            em.remove(trimestre);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Trimestre> findTrimestreEntities() {
        return findTrimestreEntities(true, -1, -1);
    }

    public List<Trimestre> findTrimestreEntities(int maxResults, int firstResult) {
        return findTrimestreEntities(false, maxResults, firstResult);
    }

    private List<Trimestre> findTrimestreEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Trimestre.class));
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

    public Trimestre findTrimestre(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Trimestre.class, id);
        } finally {
            em.close();
        }
    }

    public int getTrimestreCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Trimestre> rt = cq.from(Trimestre.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
