package peaksoft.dao;

import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.Transaction;
import peaksoft.model.User;
import peaksoft.util.Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.sql.Connection;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private EntityManagerFactory entityManagerFactory = Util.createEntityManagerFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = entityManagerFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL)";

        Query query = session.createSQLQuery(sql).addEntity(User.class);

        transaction.commit();
        session.close();


//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.createQuery(query);
////        entityManager.createNamedQuery("name");
//        entityManager.getTransaction().commit();
//        entityManager.close();
        System.out.println("Successfully created Table...");
    }

    private SessionBuilder<SessionBuilder> entityManagerFactory() {
        return null;
    }

    @Override
    public void dropUsersTable() {
        Session session = new Util().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "DROP TABLE IF EXISTS users";

        Query query = session.createSQLQuery(sql).addEntity(User.class);

        transaction.commit();
        session.close();


//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.clear();
//        entityManager.getTransaction().commit();
//        entityManager.close();
        System.out.println("Successfully dropped Table...");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User user = new User(name, lastName, age);
        entityManager.persist(user);
//        entityManager.persist(name);
//        entityManager.persist(lastName);
//        entityManager.persist(age);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void removeUserById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User user = entityManager.createQuery("select l from User l where l.id = :id ", User.class).setParameter("id", id).getSingleResult();
        entityManager.remove(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<User> getAllUsers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<User> users = entityManager.createQuery("select l from User l ", User.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.clear();
        entityManager.getTransaction().commit();
        entityManager.close();
        System.out.println("Successfully cleaned all users...");
    }
}
