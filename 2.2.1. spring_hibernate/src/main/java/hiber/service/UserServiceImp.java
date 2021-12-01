package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Autowired
   private SessionFactory sessionFactory;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional(readOnly = true)
   @SuppressWarnings("unchecked")
   public List<User> listUsersByCar(String model, int series) {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User where car.model =: model AND car.series =: series");
      query.setParameter("model", model);
      query.setParameter("series", series);
      return query.getResultList();
   }
}
