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

            Connection connection=null;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection= DriverManager.getConnection(url, user, password);
                statement=connection.createStatement();

                if(connection!=null)
                    System.out.println("Successfully connected to MySQL players DB");

            } catch (SQLException throwables) {
                System.out.println("Error occurred while connecting MySQL database");
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        public void getData(String orderBy,int limit){

            //some commands:

            //example//
            // select 'columns' from 'table' order by 'column' asc/desc limit 10;

            // select * from players order by score desc limit 15;
            // select * from players order by kill desc limit 10;

            try {

                String query=String.format("SELECT * FROM players ORDER BY %s DESC limit %d",orderBy,limit);
                resultSet= statement.executeQuery(query);
                System.out.println("Records");

                while(resultSet.next()){
                    int player_id=resultSet.getInt("player_id");
                    String nickName=resultSet.getString("nickname");
                    int score=resultSet.getInt("score");
                    int kills=resultSet.getInt("kills");

                    System.out.println("Id: \t"+player_id+"\t"+nickName+"\t\t"+score+"\t\t"+kills);

                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }

        public void getUser(String nickname){

            String query="SELECT * FROM players WHERE nickname='"+nickname+"'";

            try {
                resultSet=statement.executeQuery(query);

                boolean exists=false;

                while(resultSet.next()){
                    exists=true;

                    int player_id=resultSet.getInt("player_id");
                    String nickName=resultSet.getString("nickname");
                    int score=resultSet.getInt("score");
                    int kills=resultSet.getInt("kills");

                    System.out.println("Id: \t"+player_id+"\t"+nickName+"\t\t"+score+"\t\t"+kills);
                }

                if(!exists)
                    addNewPlayer(nickname);


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        public void addResults(int player_id,int score,int kills){
            //select player_id from players

            try {
                String query="SELECT score,kills FROM players WHERE player_id="+player_id;

                resultSet= statement.executeQuery(query);

                while(resultSet.next()) {
                    score += resultSet.getInt("score");
                    kills += resultSet.getInt("kills");
                }

                statement.executeUpdate(String.format("UPDATE players SET score=%d, kills=%d WHERE player_id=%d",score,kills,player_id));


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        private void addNewPlayer(String nickname){

            String query="SELECT player_id FROM players ORDER BY player_id DESC limit 1";

            try {
                resultSet=statement.executeQuery(query);

                int player_id=1;
                while(resultSet.next())
                    player_id=resultSet.getInt("player_id")+1;

                statement.executeUpdate(String.format("INSERT INTO players(player_id,nickname,score,kills) VALUES (%d, '%s', 0, 0)",player_id,nickname));

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
}
