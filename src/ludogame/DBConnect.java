package ludogame;

import Players.PlayerData;

import java.sql.*;
import java.util.List;

public class DBConnect {


        private Connection connection;
        private Statement statement;
        private ResultSet resultSet;
        private boolean connected;

        private final String url="jdbc:mysql://26.246.252.18:3306/ludo";
        private final String user="magenta";
        private final String password="";

        private final String connectionString=url+"?user="+user+"&password="+password+"&useUnicode=true&characterEncoding=UTF-8";

        public DBConnect() {

            connected=false;
            connection=null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                connection= DriverManager.getConnection(connectionString);
                statement=connection.createStatement();

                if(connection!=null) {
                    System.out.println("Successfully connected to MySQL players DB");
                    connected=true;
                }
            } catch (SQLException e) {
                System.out.println("Error occurred while connecting MySQL database");
                System.out.println("Could not connect to the database "+e.getMessage());
                connected=false;
            } catch (ClassNotFoundException e) {
                System.out.println("Could not find the database driver "+e.getMessage());
            } catch (IllegalAccessException e) {
                System.out.println("Error: "+e.getMessage());
                e.printStackTrace();
            } catch (InstantiationException e) {
                System.out.println("Error: "+e.getMessage());
                e.printStackTrace();
            }
        }

        public void getData(String orderBy, int limit, List<PlayerData> playerData){

/**
            some commands:

            orderBy: score, kills
            limit - Integer (ilość pobieranych danych)

             example:

             select 'columns' from 'table' order by 'column' asc/desc limit 10;
             select * from players order by score desc limit 15;
             select * from players order by kill desc limit 10;

*/

            try {

                String query=String.format("SELECT * FROM players ORDER BY %s DESC limit %d",orderBy,limit);
                resultSet= statement.executeQuery(query);

                while(resultSet.next()){
                    PlayerData temporary=new PlayerData();
                    temporary.setPlayerId(resultSet.getInt("player_id"));
                    temporary.setNickname(resultSet.getString("nickname"));
                    temporary.setScore(resultSet.getInt("score"));
                    temporary.setKills(resultSet.getInt("kills"));
                    temporary.setWins(resultSet.getInt("wins"));

                    playerData.add(temporary);
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

        public void addResults(String nickname,int score,int kills,int wins){
            //select player_id from players

            try {
                String query="SELECT score,kills FROM players WHERE nickname='"+nickname+"'";

                resultSet= statement.executeQuery(query);

                while(resultSet.next()) {
                    score += resultSet.getInt("score");
                    kills += resultSet.getInt("kills");
                    wins += resultSet.getInt("wins");
                }

                statement.executeUpdate(String.format("UPDATE players SET score=%d, kills=%d WHERE nickname='%s'",score,kills,nickname));


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

                statement.executeUpdate(String.format("INSERT INTO players(player_id,nickname,score,kills,wins) VALUES (%d, '%s', 0, 0, 0)",player_id,nickname));

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        public boolean isConnected(){
            return connected;
        }
}
