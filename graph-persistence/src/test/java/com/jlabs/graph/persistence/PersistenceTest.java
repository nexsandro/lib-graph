package com.jlabs.graph.persistence;


import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

@SpringJUnitConfig(TestConfig.class)
public class PersistenceTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private PlatformTransactionManager tm;


    @Test
    public void testVertex() {

        final PersistentVertex vertex = new PersistentVertex();

        Integer vertexId = new TransactionTemplate(tm).execute(new TransactionCallback<Integer>() {

            @Override
            public Integer doInTransaction(TransactionStatus transactionStatus) {
                sessionFactory.getCurrentSession().save(vertex);
                return vertex.getId();
            }
        });

        Assert.notNull(vertexId);
    }


    @Test
    public void testEdge() {

        final PersistentVertex vertexA = new PersistentVertex();
        final PersistentVertex vertexB = new PersistentVertex();
        final PersistentEdge edge = new PersistentEdge();

        new TransactionTemplate(tm).execute(new TransactionCallbackWithoutResult() {

            @Override
            public void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                sessionFactory.getCurrentSession().save(vertexA);
                sessionFactory.getCurrentSession().save(vertexB);

                edge.setSource(vertexA);
                edge.setTarget(vertexB);
                sessionFactory.getCurrentSession().save(edge);
            }
        });

        Assert.notNull(edge.getId());
    }

}
