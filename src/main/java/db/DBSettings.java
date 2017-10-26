package db;

public class DBSettings {
    public static final String DB_PATH = "jdbc:postgresql://localhost:5432/puzzles";
    public static final String DB_LOGIN = "postgres";
    public static final String DB_PASS = "";

    public static final String PUZZLE_GET_ALL = "select distinct pz.id as pid, pz.behavior, pz.question, pz.answer, pz.dlevel " +
            " from puzzle pz " +
            " order by pz.behavior";
    public static final String PUZZLE_GET_ALL_WITH_STAT = "select tp.id, tp.question, tp.behavior, td.name as dlevel, coalesce(t.cnt, 0) as cntStatistics " +
                                                             "from tbl_puzzle tp " +
                                                             "inner join tbl_diflevel td on td.id = tp.id_diflevel " +
                                                             "left join (select count(id) as cnt, id_puzzle " +
                                                             "          from tbl_statistic " +
                                                             "          group by id_puzzle) as t on t.id_puzzle = tp.id ";

    public static final String PUZZLE_GET_BY_ID = "select id, behavior, question, answer, dlevel from puzzle where id = ?";
    public static final String PUZZLE_INSERT = "insert into puzzle (behavior, question, answer, dlevel) values(?,?,?,?)";
    public static final String PUZZLE_UPDATE = "update puzzle set behavior = ? ,question =  ? , answer = ? , dlevel = ? where id = ?";
    public static final String PUZZLE_DELETE = "delete from puzzle where id  = ?";
    public static final String PZZLE_GET_ALL_WITH_STAT_BY_USER = "select  p.id as pid, p.behavior, p.question, p.answer, p.dlevel, "+
                                                                 "  si.id as sid, si.elasped_time, si.attempts_count, si.is_solved "+
                                                                 "   from puzzle p "+
                                                                 "       left join statistic_item si on "+
                                                                 "               p.id = si.id_puzzle and si.id_user = ? ";

    public static final String USER_GET_ALL = "select * from tbl_user order by first_name ";
    public static final String USER_GET_BY_ID = "select * from tbl_user where id = ?";
    public static final String USER_GET_LOGIN_PASSWORD = "select * from tbl_user where login = ? and password = ?";

    public static final String USER_INSERT = "insert into tbl_user (first_name, last_name, login, email, password) values(?,?,?,?,?)";
    public static final String USER_UPDATE = "update tbl_user set first_name = ?, last_name = ?, login = ?, "+
                                                                        " email = ? where id = ?";
    public static final String USER_DELETE = "delete from tbl_user where id  = ? ";
    public static final String USER_GET_NOT_STATISTIC = "select * from tbl_user "+
                                                                " where id not in (select id_user from statistic_item)" +
                                                                " order by first_name ";

    public static final String STATISTICITEM_GET_ALL = "select * from statistic_item";
    public static final String STATISTICITEM_GET_BY_ID = "select * from statistic_item where id = ?";
    public static final String STATISTICITEM_INSERT = "insert into statistic_item (id_puzzle, attempts_count, elasped_time, is_solved, id_user) values(?,?,?,?,?)";
    public static final String STATISTICITEM_UPDATE = "update statistic_item set " +
                                                        "id_puzzle = ? , elasped_time =  ? , attempts_count = ?, is_solved = ? , id_user = where id = ?";
    public static final String STATISTICITEM_DELETE = "delete from statistic_item where id  = ? ";
    public static final String STATISTICITEM_GET_BY_PUZZLE = "select * from statistic_item where id_puzzle = ?";

    public static final String MENU_GET_ALL_BY_USER = "select * from tbl_menu where is_admin = ?";

}
