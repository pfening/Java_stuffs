package sqlite;

public class dbWrite {

    public static void main(String[] args) {

        String sql = "update person set name='john' where id=7";
        selectdb.write(sql);
    }
}