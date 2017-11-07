package db;

public class DBSettings {
    public static final String DB_PATH = "jdbc:postgresql://localhost:5432/puzzles";
    public static final String DB_LOGIN = "postgres";
    public static final String DB_PASS = "";

    public static final String PUZZLE_GET_ALL = "select distinct pz.id as pid, pz.behavior, pz.question, pz.dlevel " +
            " from tbl_puzzle pz " +
            " order by pz.behavior";
    public static final String PUZZLE_GET_ALL_WITH_STAT = "select tp.id, tp.question, tp.behavior, td.id as did, td.name as dname, coalesce(t.cnt, 0) as cntStatistics " +
                                                             "from tbl_puzzle tp " +
                                                             "inner join tbl_diflevel td on td.id = tp.id_diflevel " +
                                                             "left join (select count(id) as cnt, id_puzzle " +
                                                             "          from tbl_statistic " +
                                                             "          group by id_puzzle) as t on t.id_puzzle = tp.id ";

    public static final String PUZZLE_GET_BY_ID = "select p.id , p.behavior, p.question, d.id as did, d.name as dname,   " +
                                                    " a.id as aid, a.answer, a.is_correct"+
                                                    " from tbl_puzzle p " +
                                                    " inner join tbl_diflevel d on d.id = p.id_diflevel "+
                                                    " left join tbl_answer a on a.id_puzzle = p.id "+
                                                    " where p.id = ? ";
    public static final String PUZZLE_INSERT = "insert into tbl_puzzle (behavior, question, id_diflevel) values(?,?,?)";
    public static final String PUZZLE_UPDATE = "update tbl_puzzle set behavior = ? ,question =  ? , id_diflevel = ? where id = ?";
    public static final String PUZZLE_DELETE = "delete from tbl_puzzle where id  = ?";
    public static final String PZZLE_GET_ALL_WITH_STAT_BY_USER = "select  p.id as pid, p.behavior, p.question, d.id as did, d.name as dname, "+
                                                                    " si.id as sid, coalesce(si.elapsed_time, 0) as elapsed_time, coalesce(si.attempts_count, 0) as attempts_count,"+
                                                                    " coalesce(si.is_solved,FALSE ) as is_solved "+
                                                                    " from tbl_puzzle p "+
                                                                    " inner join tbl_diflevel d on p.id_diflevel = d.id "+
                                                                    " LEFT JOIN  tbl_statistic si on "+
                                                                    " p.id = si.id_puzzle and si.id_user = ?";
    public static final String USER_GET_ALL = "select * from tbl_user order by first_name ";
    public static final String USER_GET_BY_ID = "select tu.*, case when(tr.role_name='admin') then true ELSE FALSE end as is_admin " +
                                                " from tbl_user tu " +
                                                " join tbl_user_role ur on ur.id_user = tu.id "+
                                                " join tbl_role tr on tr.id = ur.id_role "+
                                                " where tu.id = ?";
    public static final String USER_GET_BY_LOGIN = "select tu.*, case when(tr.role_name='admin') then true ELSE FALSE end as is_admin, role_name " +
            " from tbl_user tu " +
            " join tbl_user_role ur on ur.id_user = tu.id "+
            " join tbl_role tr on tr.id = ur.id_role "+
            " where tu.login = ?";


    public static final String USER_GET_LOGIN_PASSWORD = "select * from tbl_user where login = ? and password = ?";

    public static final String USER_INSERT = "insert into tbl_user (first_name, last_name, login, email, password) values(?,?,?,?,?)";
    public static final String USER_UPDATE = "update tbl_user set first_name = ?, last_name = ?, login = ?, "+
                                                                " email = ?, is_admin = ? where id = ?";
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
    public static final String STATISTICITEM_GET_BY_USER = "select * from statistic_item where id_user = ?";

    public static final String MENU_GET_ALL_BY_USER = "select * from tbl_menu where is_admin = ?";

    public static final String DIFFICULTYLEVEL_GET_ALL = "select * from tbl_diflevel";
    public static final String DIFFICULTYLEVEL_GET_BY_ID = "select * from tbl_diflevel where id = ?";

    public static final String ANSWER_GET_ALL_BY_PUZZLE = "select * from tbl_answer where id_puzzle = ?";

}
