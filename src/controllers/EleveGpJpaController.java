/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entites.AnneeScolaire;
import entites.Eleve;
import entites.BulletinNote;
import entites.EleveGp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author OBAMA
 */
public class EleveGpJpaController implements Serializable {

    public EleveGpJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EleveGp eleveGp) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (eleveGp.getBulletinNoteList() == null) {
            eleveGp.setBulletinNoteList(new ArrayList<BulletinNote>());
        }
        List<String> illegalOrphanMessages = null;
        Eleve eleveOrphanCheck = eleveGp.getEleve();
        if (eleveOrphanCheck != null) {
            EleveGp oldEleveGpOfEleve = eleveOrphanCheck.getEleveGp();
            if (oldEleveGpOfEleve != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Eleve " + eleveOrphanCheck + " already has an item of type EleveGp whose eleve column cannot be null. Please make another selection for the eleve field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AnneeScolaire idAnneeScolaire = eleveGp.getIdAnneeScolaire();
            if (idAnneeScolaire != null) {
                idAnneeScolaire = em.getReference(idAnneeScolaire.getClass(), idAnneeScolaire.getIdAnneeScolaire());
                eleveGp.setIdAnneeScolaire(idAnneeScolaire);
            }
            Eleve eleve = eleveGp.getEleve();
            if (eleve != null) {
                eleve = em.getReference(eleve.getClass(), eleve.getIdEleve());
                eleveGp.setEleve(eleve);
            }
            List<BulletinNote> attachedBulletinNoteList = new ArrayList<BulletinNote>();
            for (BulletinNote bulletinNoteListBulletinNoteToAttach : eleveGp.getBulletinNoteList()) {
                bulletinNoteListBulletinNoteToAttach = em.getReference(bulletinNoteListBulletinNoteToAttach.getClass(), bulletinNoteListBulletinNoteToAttach.getIdBulletin());
                attachedBulletinNoteList.add(bulletinNoteListBulletinNoteToAttach);
            }
            eleveGp.setBulletinNoteList(attachedBulletinNoteList);
            em.persist(eleveGp);
            if (idAnneeScolaire != null) {
                idAnneeScolaire.getEleveGpList().add(eleveGp);
                idAnneeScolaire = em.merge(idAnneeScolaire);
            }
            if (eleve != null) {
                eleve.setEleveGp(eleveGp);
                eleve = em.merge(eleve);
            }
            for (BulletinNote bulletinNoteListBulletinNote : eleveGp.getBulletinNoteList()) {
                EleveGp oldIdEleveGpOfBulletinNoteListBulletinNote = bulletinNoteListBulletinNote.getIdEleveGp();
                bulletinNoteListBulletinNote.setIdEleveGp(eleveGp);
                bulletinNoteListBulletinNote = em.merge(bulletinNoteListBulletinNote);
                if (oldIdEleveGpOfBulletinNoteListBulletinNote != null) {
                    oldIdEleveGpOfBulletinNoteListBulletinNote.getBulletinNoteList().remove(bulletinNoteListBulletinNote);
                    oldIdEleveGpOfBulletinNoteListBulletinNote = em.merge(oldIdEleveGpOfBulletinNoteListBulletinNote);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEleveGp(eleveGp.getIdEleveGp()) != null) {
                throw new PreexistingEntityException("EleveGp " + eleveGp + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EleveGp eleveGp) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EleveGp persistentEleveGp = em.find(EleveGp.class, eleveGp.getIdEleveGp());
            AnneeScolaire idAnneeScolaireOld = persistentEleveGp.getIdAnneeScolaire();
            AnneeScolaire idAnneeScolaireNew = eleveGp.getIdAnneeScolaire();
            Eleve eleveOld = persistentEleveGp.getEleve();
            Eleve eleveNew = eleveGp.getEleve();
            List<BulletinNote> bulletinNoteListOld = persistentEleveGp.getBulletinNoteList();
            List<BulletinNote> bulletinNoteListNew = eleveGp.getBulletinNoteList();
            List<String> illegalOrphanMessages = null;
            if (eleveNew != null && !eleveNew.equals(eleveOld)) {
                EleveGp oldEleveGpOfEleve = eleveNew.getEleveGp();
                if (oldEleveGpOfEleve != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Eleve " + eleveNew + " already has an item of type EleveGp whose eleve column cannot be null. Please make another selection for the eleve field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idAnneeScolaireNew != null) {
                idAnneeScolaireNew = em.getReference(idAnneeScolaireNew.getClass(), idAnneeScolaireNew.getIdAnneeScolaire());
                eleveGp.setIdAnneeScolaire(idAnneeScolaireNew);
            }
            if (eleveNew != null) {
                eleveNew = em.getReference(eleveNew.getClass(), eleveNew.getIdEleve());
                eleveGp.setEleve(eleveNew);
            }
            List<BulletinNote> attachedBulletinNoteListNew = new ArrayList<BulletinNote>();
            for (BulletinNote bulletinNoteListNewBulletinNoteToAttach : bulletinNoteListNew) {
                bulletinNoteListNewBulletinNoteToAttach = em.getReference(bulletinNoteListNewBulletinNoteToAttach.getClass(), bulletinNoteListNewBulletinNoteToAttach.getIdBulletin());
                attachedBulletinNoteListNew.add(bulletinNoteListNewBulletinNoteToAttach);
            }
            bulletinNoteListNew = attachedBulletinNoteListNew;
            eleveGp.setBulletinNoteList(bulletinNoteListNew);
            eleveGp = em.merge(eleveGp);
            if (idAnneeScolaireOld != null && !idAnneeScolaireOld.equals(idAnneeScolaireNew)) {
                idAnneeScolaireOld.getEleveGpList().remove(eleveGp);
                idAnneeScolaireOld = em.merge(idAnneeScolaireOld);
            }
            if (idAnneeScolaireNew != null && !idAnneeScolaireNew.equals(idAnneeScolaireOld)) {
                idAnneeScolaireNew.getEleveGpList().add(eleveGp);
                idAnneeScolaireNew = em.merge(idAnneeScolaireNew);
            }
            if (eleveOld != null && !eleveOld.equals(eleveNew)) {
                eleveOld.setEleveGp(null);
                eleveOld = em.merge(eleveOld);
            }
            if (eleveNew != null && !eleveNew.equals(eleveOld)) {
                eleveNew.setEleveGp(eleveGp);
                eleveNew = em.merge(eleveNew);
            }
            for (BulletinNote bulletinNoteListOldBulletinNote : bulletinNoteListOld) {
                if (!bulletinNoteListNew.contains(bulletinNoteListOldBulletinNote)) {
                    bulletinNoteListOldBulletinNote.setIdEleveGp(null);
                    bulletinNoteListOldBulletinNote = em.merge(bulletinNoteListOldBulletinNote);
                }
            }
            for (BulletinNote bulletinNoteListNewBulletinNote : bulletinNoteListNew) {
                if (!bulletinNoteListOld.contains(bulletinNoteListNewBulletinNote)) {
                    EleveGp oldIdEleveGpOfBulletinNoteListNewBulletinNote = bulletinNoteListNewBulletinNote.getIdEleveGp();
                    bulletinNoteListNewBulletinNote.setIdEleveGp(eleveGp);
                    bulletinNoteListNewBulletinNote = em.merge(bulletinNoteListNewBulletinNote);
                    if (oldIdEleveGpOfBulletinNoteListNewBulletinNote != null && !oldIdEleveGpOfBulletinNoteListNewBulletinNote.equals(eleveGp)) {
                        oldIdEleveGpOfBulletinNoteListNewBulletinNote.getBulletinNoteList().remove(bulletinNoteListNewBulletinNote);
                        oldIdEleveGpOfBulletinNoteListNewBulletinNote = em.merge(oldIdEleveGpOfBulletinNoteListNewBulletinNote);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = eleveGp.getIdEleveGp();
                if (findEleveGp(id) == null) {
                    throw new NonexistentEntityException("The eleveGp with id " + id + " no longer exists.");
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
            EleveGp eleveGp;
            try {
                eleveGp = em.getReference(EleveGp.class, id);
                eleveGp.getIdEleveGp();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eleveGp with id " + id + " no longer exists.", enfe);
            }
            AnneeScolaire idAnneeScolaire = eleveGp.getIdAnneeScolaire();
            if (idAnneeScolaire != null) {
                idAnneeScolaire.getEleveGpList().remove(eleveGp);
                idAnneeScolaire = em.merge(idAnneeScolaire);
            }
            Eleve eleve = eleveGp.getEleve();
            if (eleve != null) {
                eleve.setEleveGp(null);
                eleve = em.merge(eleve);
            }
            List<BulletinNote> bulletinNoteList = eleveGp.getBulletinNoteList();
            for (BulletinNote bulletinNoteListBulletinNote : bulletinNoteList) {
                bulletinNoteListBulletinNote.setIdEleveGp(null);
                bulletinNoteListBulletinNote = em.merge(bulletinNoteListBulletinNote);
            }
            em.remove(eleveGp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EleveGp> findEleveGpEntities() {
        return findEleveGpEntities(true, -1, -1);
    }

    public List<EleveGp> findEleveGpEntities(int maxResults, int firstResult) {
        return findEleveGpEntities(false, maxResults, firstResult);
    }

    private List<EleveGp> findEleveGpEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EleveGp.class));
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

    public EleveGp findEleveGp(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EleveGp.class, id);
        } finally {
            em.close();
        }
    }

    public int getEleveGpCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EleveGp> rt = cq.from(EleveGp.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
