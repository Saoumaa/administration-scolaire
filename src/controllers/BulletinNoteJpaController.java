/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entites.BulletinNote;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entites.EleveGp;
import entites.Trimestre;
import entites.DetailsBulletin;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author OBAMA
 */
public class BulletinNoteJpaController implements Serializable {

    public BulletinNoteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BulletinNote bulletinNote) throws PreexistingEntityException, Exception {
        if (bulletinNote.getDetailsBulletinList() == null) {
            bulletinNote.setDetailsBulletinList(new ArrayList<DetailsBulletin>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EleveGp idEleveGp = bulletinNote.getIdEleveGp();
            if (idEleveGp != null) {
                idEleveGp = em.getReference(idEleveGp.getClass(), idEleveGp.getIdEleveGp());
                bulletinNote.setIdEleveGp(idEleveGp);
            }
            Trimestre idTrimes = bulletinNote.getIdTrimes();
            if (idTrimes != null) {
                idTrimes = em.getReference(idTrimes.getClass(), idTrimes.getIdTrimes());
                bulletinNote.setIdTrimes(idTrimes);
            }
            List<DetailsBulletin> attachedDetailsBulletinList = new ArrayList<DetailsBulletin>();
            for (DetailsBulletin detailsBulletinListDetailsBulletinToAttach : bulletinNote.getDetailsBulletinList()) {
                detailsBulletinListDetailsBulletinToAttach = em.getReference(detailsBulletinListDetailsBulletinToAttach.getClass(), detailsBulletinListDetailsBulletinToAttach.getIdDetailsBulletin());
                attachedDetailsBulletinList.add(detailsBulletinListDetailsBulletinToAttach);
            }
            bulletinNote.setDetailsBulletinList(attachedDetailsBulletinList);
            em.persist(bulletinNote);
            if (idEleveGp != null) {
                idEleveGp.getBulletinNoteList().add(bulletinNote);
                idEleveGp = em.merge(idEleveGp);
            }
            if (idTrimes != null) {
                idTrimes.getBulletinNoteList().add(bulletinNote);
                idTrimes = em.merge(idTrimes);
            }
            for (DetailsBulletin detailsBulletinListDetailsBulletin : bulletinNote.getDetailsBulletinList()) {
                BulletinNote oldIdBulletinOfDetailsBulletinListDetailsBulletin = detailsBulletinListDetailsBulletin.getIdBulletin();
                detailsBulletinListDetailsBulletin.setIdBulletin(bulletinNote);
                detailsBulletinListDetailsBulletin = em.merge(detailsBulletinListDetailsBulletin);
                if (oldIdBulletinOfDetailsBulletinListDetailsBulletin != null) {
                    oldIdBulletinOfDetailsBulletinListDetailsBulletin.getDetailsBulletinList().remove(detailsBulletinListDetailsBulletin);
                    oldIdBulletinOfDetailsBulletinListDetailsBulletin = em.merge(oldIdBulletinOfDetailsBulletinListDetailsBulletin);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBulletinNote(bulletinNote.getIdBulletin()) != null) {
                throw new PreexistingEntityException("BulletinNote " + bulletinNote + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BulletinNote bulletinNote) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BulletinNote persistentBulletinNote = em.find(BulletinNote.class, bulletinNote.getIdBulletin());
            EleveGp idEleveGpOld = persistentBulletinNote.getIdEleveGp();
            EleveGp idEleveGpNew = bulletinNote.getIdEleveGp();
            Trimestre idTrimesOld = persistentBulletinNote.getIdTrimes();
            Trimestre idTrimesNew = bulletinNote.getIdTrimes();
            List<DetailsBulletin> detailsBulletinListOld = persistentBulletinNote.getDetailsBulletinList();
            List<DetailsBulletin> detailsBulletinListNew = bulletinNote.getDetailsBulletinList();
            if (idEleveGpNew != null) {
                idEleveGpNew = em.getReference(idEleveGpNew.getClass(), idEleveGpNew.getIdEleveGp());
                bulletinNote.setIdEleveGp(idEleveGpNew);
            }
            if (idTrimesNew != null) {
                idTrimesNew = em.getReference(idTrimesNew.getClass(), idTrimesNew.getIdTrimes());
                bulletinNote.setIdTrimes(idTrimesNew);
            }
            List<DetailsBulletin> attachedDetailsBulletinListNew = new ArrayList<DetailsBulletin>();
            for (DetailsBulletin detailsBulletinListNewDetailsBulletinToAttach : detailsBulletinListNew) {
                detailsBulletinListNewDetailsBulletinToAttach = em.getReference(detailsBulletinListNewDetailsBulletinToAttach.getClass(), detailsBulletinListNewDetailsBulletinToAttach.getIdDetailsBulletin());
                attachedDetailsBulletinListNew.add(detailsBulletinListNewDetailsBulletinToAttach);
            }
            detailsBulletinListNew = attachedDetailsBulletinListNew;
            bulletinNote.setDetailsBulletinList(detailsBulletinListNew);
            bulletinNote = em.merge(bulletinNote);
            if (idEleveGpOld != null && !idEleveGpOld.equals(idEleveGpNew)) {
                idEleveGpOld.getBulletinNoteList().remove(bulletinNote);
                idEleveGpOld = em.merge(idEleveGpOld);
            }
            if (idEleveGpNew != null && !idEleveGpNew.equals(idEleveGpOld)) {
                idEleveGpNew.getBulletinNoteList().add(bulletinNote);
                idEleveGpNew = em.merge(idEleveGpNew);
            }
            if (idTrimesOld != null && !idTrimesOld.equals(idTrimesNew)) {
                idTrimesOld.getBulletinNoteList().remove(bulletinNote);
                idTrimesOld = em.merge(idTrimesOld);
            }
            if (idTrimesNew != null && !idTrimesNew.equals(idTrimesOld)) {
                idTrimesNew.getBulletinNoteList().add(bulletinNote);
                idTrimesNew = em.merge(idTrimesNew);
            }
            for (DetailsBulletin detailsBulletinListOldDetailsBulletin : detailsBulletinListOld) {
                if (!detailsBulletinListNew.contains(detailsBulletinListOldDetailsBulletin)) {
                    detailsBulletinListOldDetailsBulletin.setIdBulletin(null);
                    detailsBulletinListOldDetailsBulletin = em.merge(detailsBulletinListOldDetailsBulletin);
                }
            }
            for (DetailsBulletin detailsBulletinListNewDetailsBulletin : detailsBulletinListNew) {
                if (!detailsBulletinListOld.contains(detailsBulletinListNewDetailsBulletin)) {
                    BulletinNote oldIdBulletinOfDetailsBulletinListNewDetailsBulletin = detailsBulletinListNewDetailsBulletin.getIdBulletin();
                    detailsBulletinListNewDetailsBulletin.setIdBulletin(bulletinNote);
                    detailsBulletinListNewDetailsBulletin = em.merge(detailsBulletinListNewDetailsBulletin);
                    if (oldIdBulletinOfDetailsBulletinListNewDetailsBulletin != null && !oldIdBulletinOfDetailsBulletinListNewDetailsBulletin.equals(bulletinNote)) {
                        oldIdBulletinOfDetailsBulletinListNewDetailsBulletin.getDetailsBulletinList().remove(detailsBulletinListNewDetailsBulletin);
                        oldIdBulletinOfDetailsBulletinListNewDetailsBulletin = em.merge(oldIdBulletinOfDetailsBulletinListNewDetailsBulletin);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = bulletinNote.getIdBulletin();
                if (findBulletinNote(id) == null) {
                    throw new NonexistentEntityException("The bulletinNote with id " + id + " no longer exists.");
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
            BulletinNote bulletinNote;
            try {
                bulletinNote = em.getReference(BulletinNote.class, id);
                bulletinNote.getIdBulletin();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bulletinNote with id " + id + " no longer exists.", enfe);
            }
            EleveGp idEleveGp = bulletinNote.getIdEleveGp();
            if (idEleveGp != null) {
                idEleveGp.getBulletinNoteList().remove(bulletinNote);
                idEleveGp = em.merge(idEleveGp);
            }
            Trimestre idTrimes = bulletinNote.getIdTrimes();
            if (idTrimes != null) {
                idTrimes.getBulletinNoteList().remove(bulletinNote);
                idTrimes = em.merge(idTrimes);
            }
            List<DetailsBulletin> detailsBulletinList = bulletinNote.getDetailsBulletinList();
            for (DetailsBulletin detailsBulletinListDetailsBulletin : detailsBulletinList) {
                detailsBulletinListDetailsBulletin.setIdBulletin(null);
                detailsBulletinListDetailsBulletin = em.merge(detailsBulletinListDetailsBulletin);
            }
            em.remove(bulletinNote);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BulletinNote> findBulletinNoteEntities() {
        return findBulletinNoteEntities(true, -1, -1);
    }

    public List<BulletinNote> findBulletinNoteEntities(int maxResults, int firstResult) {
        return findBulletinNoteEntities(false, maxResults, firstResult);
    }

    private List<BulletinNote> findBulletinNoteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BulletinNote.class));
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

    public BulletinNote findBulletinNote(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BulletinNote.class, id);
        } finally {
            em.close();
        }
    }

    public int getBulletinNoteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BulletinNote> rt = cq.from(BulletinNote.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
