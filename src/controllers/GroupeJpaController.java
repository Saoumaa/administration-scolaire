/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entites.Groupe;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entites.Users;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author OBAMA
 */
public class GroupeJpaController implements Serializable {

    public GroupeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Groupe groupe) throws PreexistingEntityException, Exception {
        if (groupe.getUsersList() == null) {
            groupe.setUsersList(new ArrayList<Users>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Users> attachedUsersList = new ArrayList<Users>();
            for (Users usersListUsersToAttach : groupe.getUsersList()) {
                usersListUsersToAttach = em.getReference(usersListUsersToAttach.getClass(), usersListUsersToAttach.getUserCode());
                attachedUsersList.add(usersListUsersToAttach);
            }
            groupe.setUsersList(attachedUsersList);
            em.persist(groupe);
            for (Users usersListUsers : groupe.getUsersList()) {
                Groupe oldGroupeCodeOfUsersListUsers = usersListUsers.getGroupeCode();
                usersListUsers.setGroupeCode(groupe);
                usersListUsers = em.merge(usersListUsers);
                if (oldGroupeCodeOfUsersListUsers != null) {
                    oldGroupeCodeOfUsersListUsers.getUsersList().remove(usersListUsers);
                    oldGroupeCodeOfUsersListUsers = em.merge(oldGroupeCodeOfUsersListUsers);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGroupe(groupe.getGroupeCode()) != null) {
                throw new PreexistingEntityException("Groupe " + groupe + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Groupe groupe) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Groupe persistentGroupe = em.find(Groupe.class, groupe.getGroupeCode());
            List<Users> usersListOld = persistentGroupe.getUsersList();
            List<Users> usersListNew = groupe.getUsersList();
            List<Users> attachedUsersListNew = new ArrayList<Users>();
            for (Users usersListNewUsersToAttach : usersListNew) {
                usersListNewUsersToAttach = em.getReference(usersListNewUsersToAttach.getClass(), usersListNewUsersToAttach.getUserCode());
                attachedUsersListNew.add(usersListNewUsersToAttach);
            }
            usersListNew = attachedUsersListNew;
            groupe.setUsersList(usersListNew);
            groupe = em.merge(groupe);
            for (Users usersListOldUsers : usersListOld) {
                if (!usersListNew.contains(usersListOldUsers)) {
                    usersListOldUsers.setGroupeCode(null);
                    usersListOldUsers = em.merge(usersListOldUsers);
                }
            }
            for (Users usersListNewUsers : usersListNew) {
                if (!usersListOld.contains(usersListNewUsers)) {
                    Groupe oldGroupeCodeOfUsersListNewUsers = usersListNewUsers.getGroupeCode();
                    usersListNewUsers.setGroupeCode(groupe);
                    usersListNewUsers = em.merge(usersListNewUsers);
                    if (oldGroupeCodeOfUsersListNewUsers != null && !oldGroupeCodeOfUsersListNewUsers.equals(groupe)) {
                        oldGroupeCodeOfUsersListNewUsers.getUsersList().remove(usersListNewUsers);
                        oldGroupeCodeOfUsersListNewUsers = em.merge(oldGroupeCodeOfUsersListNewUsers);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = groupe.getGroupeCode();
                if (findGroupe(id) == null) {
                    throw new NonexistentEntityException("The groupe with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Groupe groupe;
            try {
                groupe = em.getReference(Groupe.class, id);
                groupe.getGroupeCode();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The groupe with id " + id + " no longer exists.", enfe);
            }
            List<Users> usersList = groupe.getUsersList();
            for (Users usersListUsers : usersList) {
                usersListUsers.setGroupeCode(null);
                usersListUsers = em.merge(usersListUsers);
            }
            em.remove(groupe);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Groupe> findGroupeEntities() {
        return findGroupeEntities(true, -1, -1);
    }

    public List<Groupe> findGroupeEntities(int maxResults, int firstResult) {
        return findGroupeEntities(false, maxResults, firstResult);
    }

    private List<Groupe> findGroupeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Groupe.class));
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

    public Groupe findGroupe(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Groupe.class, id);
        } finally {
            em.close();
        }
    }

    public int getGroupeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Groupe> rt = cq.from(Groupe.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
