package pis.lab2.services;

import pis.lab2.dao.DaoFactory;
import pis.lab2.dao.IPackDao;
import pis.lab2.dao.IQuestionsDao;
import pis.lab2.entities.Pack;

import java.util.List;

public class PackService {


    public List<Pack> findUserPacks(Long userID) {
        IPackDao packDao = DaoFactory.createPackDao();
        List<Pack> packs = packDao.findByUserId(userID);
        packDao.closeConnection();

        return packs;
    }

    public List<Pack> findAllPacks() {
        IPackDao packDao = DaoFactory.createPackDao();
        List<Pack> packs = packDao.findAll();
        packDao.closeConnection();

        return packs;
    }

    public boolean createPack(String packName, Long userId) {
        IPackDao packDao = DaoFactory.createPackDao();
        Pack pack = new Pack(packName, userId);
        Long id = packDao.save(pack);
        packDao.closeConnection();

        if (id == 0 && id == null)
                return false;
        return true;
    }

    public void deletePack(Long packId) {
        IQuestionsDao questionDao = DaoFactory.createQuestionDao();
        questionDao.deleteByPackId(packId);
        questionDao.closeConnection();
        IPackDao packDao = DaoFactory.createPackDao();
        packDao.delete(packId);
        packDao.closeConnection();
    }

    public boolean changePackName(Long packId, String packName, Long userId) {
        IPackDao packDao = DaoFactory.createPackDao();
        if (packDao.update(packId, packName, userId)) {
            packDao.closeConnection();
            return true;
        }

        return false;
    }

    public String getPackName(Long packId) {
        IPackDao packDao = DaoFactory.createPackDao();
        String packName = packDao.getPackNameById(packId);
        packDao.closeConnection();
        return packName;

    }
}
