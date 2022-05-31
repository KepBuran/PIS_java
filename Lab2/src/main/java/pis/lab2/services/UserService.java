package pis.lab2.services;

import pis.lab2.dao.DaoFactory;
import pis.lab2.dao.IPackDao;
import pis.lab2.dao.IUserDao;
import pis.lab2.entities.Pack;
import pis.lab2.entities.User;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    public boolean checkAuthorization(String username, String password) {

        IUserDao userDao = DaoFactory.createUserDao();
        User user = userDao.findByUsername(username) ;
        userDao.closeConnection();

        if (user == null)
            return false;

        if(password.equals(user.getPasswordHash()))
            return true;
        return false;

    }

    public boolean registerUser(String username, String password) {

        IUserDao userDao = DaoFactory.createUserDao();

        Long id = userDao.save(new User(username, password)) ;
        userDao.closeConnection();

        if (id == null || id == 0)
            return false;

        return true;

    }

    public Long getUserIdByUsername(String username) {

        IUserDao userDao = DaoFactory.createUserDao();
        User user = userDao.findByUsername(username) ;
        userDao.closeConnection();

        if (user == null)
            return null;

        return user.getId();

    }


}
