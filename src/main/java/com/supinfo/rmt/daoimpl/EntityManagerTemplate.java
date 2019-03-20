/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.daoimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author ccong
 */
public abstract class EntityManagerTemplate<T> implements Serializable {

    @PersistenceContext(name = "rmtPersistenceUnit")
    private EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }

    private Class<T> clazz;

    public EntityManagerTemplate(Class<T> clazz) {
        this.clazz = clazz;
    }

    public void create(T obj) {
        em.persist(obj);
    }

    public T find(Object id) {
        return em.find(clazz, id);
    }

    public int delete(T t) {
        try {
            em.remove(em.merge(t));
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    public T update(T t) {
        return em.merge(t);
    }

    public List<T> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        return em.createQuery(cq).getResultList();
    }

    public List<T> findWhereEquals(String key, Object value) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = builder.createQuery(clazz);
        Root<T> root = cq.from(clazz);
        Path<T> keyPath = root.get(key);
        cq.where(builder.equal(keyPath, value));
        return getEntityManager().createQuery(cq).getResultList();
    }

    /**
     * find with multiple query attributes
     *
     * @param entries
     * @param and
     * @return
     */
    /*
    public List<T> findWhereEqualsMultiAttributes(Set<Entry<SingularAttribute<T, String>,Object>> entries, boolean and) {
        
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = builder.createQuery(clazz);
        Root<T> root = cq.from(clazz);
        List<Predicate> options = new ArrayList<>();
        for(Entry<SingularAttribute<T, String>,Object> entry : entries) {
            options.add(builder.equal(root.get(entry.getKey()),entry.getValue()));
        }
        if(and) {
            cq.where(builder.and(options.toArray(new Predicate[options.size()])));
        } else {
            cq.where(builder.or(options.toArray(new Predicate[options.size()])));
        }
        return getEntityManager().createQuery(cq).getResultList();
    }
     */
    /**
     * find with multiple query attributes
     *
     * @param entries
     * @param and
     * @return
     */
    public List<T> findWhereEqualsMultiAttributes(Set<Entry<String, Object>> entries, boolean and) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = builder.createQuery(clazz);
        Root<T> root = cq.from(clazz);
        List<Predicate> options = new ArrayList<>();
        for (Entry<String, Object> entry : entries) {
            options.add(builder.equal(root.get(entry.getKey()), entry.getValue()));
        }
        if (and) {
            cq.where(builder.and(options.toArray(new Predicate[options.size()])));
        } else {
            cq.where(builder.or(options.toArray(new Predicate[options.size()])));
        }
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    public int countWithMultiFilter(Set<Map.Entry<List<String>, Object>> entries) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> root = cq.from(clazz);
        cq.select(getEntityManager().getCriteriaBuilder().count(root));

        if (entries != null && !entries.isEmpty()) {
            List<Predicate> preds = new LinkedList<>();
            for (Map.Entry<List<String>, Object> entry : entries) {
                List<String> multiKeyList = entry.getKey();
                if (multiKeyList != null && !multiKeyList.isEmpty()) {
                    Path<String> pathString = root.get(multiKeyList.get(0));
                    for (int i = 1; i < multiKeyList.size(); i++) {
                        pathString = pathString.get(multiKeyList.get(i));
                    }
                    //System.out.println("entry value : "+entry.getValue()+" is string ? "+(entry.getValue() instanceof String));
                    if (entry.getValue() instanceof String) {
                        preds.add(cb.like(pathString, entry.getValue().toString() + "%"));
                    } else {
                        preds.add(cb.equal(pathString, entry.getValue()));
                    }
                }
            }
            cq.where(cb.and(preds.toArray(new Predicate[preds.size()])));
        }
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    public List<T> findRangeWithMultiAttributeFilterAndSort(Set<Map.Entry<List<String>, Object>> entries, List<String> sortKeys, boolean desc, int first, int size) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root root = cq.from(clazz);
        if (entries != null && !entries.isEmpty()) {
            List<Predicate> preds = new LinkedList<>();
            for (Map.Entry<List<String>, Object> entry : entries) {
                List<String> queryAttributeList = entry.getKey();
                if (queryAttributeList != null && queryAttributeList.size() > 0) {
                    Path<String> pathString = root.get(queryAttributeList.get(0));
                    for (int i = 1; i < queryAttributeList.size(); i++) {
                        pathString = pathString.get(queryAttributeList.get(i));
                    }
                    if (entry.getValue() instanceof String) {
                        preds.add(cb.like(pathString, entry.getValue().toString() + "%"));
                    } else {//type boolean and int
                        preds.add(cb.equal(pathString, entry.getValue()));
                    }

                }
            }
            cq.where(cb.and(preds.toArray(new Predicate[preds.size()])));
        }
        Path<String> sortPathString = null;
        if (sortKeys != null && !sortKeys.isEmpty()) {
            sortPathString = root.get(sortKeys.get(0));
            for (int i = 1; i < sortKeys.size(); i++) {
                sortPathString = sortPathString.get(sortKeys.get(i));
            }
        }

        if (!desc) {
            if (sortPathString != null) {
                cq.orderBy(cb.asc(sortPathString));
            }
        } else if (sortPathString != null) {
            cq.orderBy(cb.desc(sortPathString));
        }
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(size);
        q.setFirstResult(first);
        return q.getResultList();
    }
}
