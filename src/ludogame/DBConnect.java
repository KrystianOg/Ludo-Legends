package ludogame;

import java.sql.*;

public class DBConnect {


        private Connection connection;
        private Statement statement;
        private ResultSet resultSet;

        String url="jdbc:mysql://26.246.252.18:3306/ludo";
        String user="magento";
        String password="";

        public DBConnect() {

            try {
                connection= DriverManager.getConnection(url, user, password);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                statement=connection.createStatement();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        public void getData(String command){

            //some commands:

            //example//
            // select 'columns' from 'table' order by 'column' asc/desc limit 10;

            // select * from players order by score desc limit 15;
            //select * from players order by kill desc limit 10;




            try {
                String query = command;
                statement.addBatch(query);
                resultSet= statement.executeQuery(query);
                System.out.println("Records");

                while(resultSet.next()){
                    int player_id=resultSet.getInt("player_id");
                    String nickName=resultSet.getString("nickname");
                    int score=resultSet.getInt("score");
                    int kills=resultSet.getInt("kills");

                    System.out.println("Id: "+player_id+" "+nickName+" "+score+" "+kills);

                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }

        public void setNewUser(){





        }


}
