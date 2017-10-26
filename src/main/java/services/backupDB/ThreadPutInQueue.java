package services.backupDB;

import classesFromXSD.Statistics;
import pojo.Puzzle;
import pojo.StatisticItem;
import pojo.User;
import services.ClassSerializeXML;

import javax.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class ThreadPutInQueue extends Thread {

    private static Statistics statistic;
    public static volatile boolean prodComplete;

    private BlockingQueue<Puzzle> queuePuzzle/* = new LinkedBlockingQueue()*/;
    private BlockingQueue<User> queueUser /*= new LinkedBlockingQueue<>()*/;
    private BlockingQueue<StatisticItem> queueStatItem /*= new LinkedBlockingQueue<>()*/;

    public ThreadPutInQueue(BlockingQueue<Puzzle> queuePuzzle,
                            BlockingQueue<User> queueUser,
                            BlockingQueue<StatisticItem> queueStatItem) {
        prodComplete = false;
        this.queuePuzzle = queuePuzzle;
        this.queueUser = queueUser;
        this.queueStatItem = queueStatItem;
    }

    @Override
    public void run() {
        try {
            Map<Integer, User> userMap = new HashMap<>();

            statistic =(Statistics) ClassSerializeXML.classFromXML("/home/sa/statistic.xml");
            for(classesFromXSD.Puzzle p :statistic.getItem()) {
                Puzzle puzzlePOJO = Puzzle.getPOJO(p);

                queuePuzzle.put(puzzlePOJO);
                //стат. по каждой загадке
                //пользователь по каждой статистике
                for(StatisticItem siPOJO :puzzlePOJO.getStatisticItemSet()){
                    queueStatItem.put(siPOJO);
                    User user = siPOJO.getUser();
                    if (userMap.containsKey(user.getId())){
                        siPOJO.setUser(userMap.get(user.getId()));
                    }
                    else
                    {
                        userMap.put(user.getId(), user);
                        queueUser.put(user);
                    }
                }
            }
            //пользователи без статистики
            for (classesFromXSD.User userBean : statistic.getUserWithoutStatistic()) {
                queueUser.put(User.getPOJO(userBean));
            }
            prodComplete = true;
            } catch (JAXBException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
