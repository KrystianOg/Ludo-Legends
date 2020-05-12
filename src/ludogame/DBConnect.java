package ludogame;

import Players.PlayerData;

import java.sql.*;
import java.util.List;

public class DBConnect {


        private Connection connection;
        private Statement statement;
        private ResultSet resultSet;
        private boolean connected;

        private final String url="jdbc:mysql://remotemysql.com:3306/e6FpSaeFOi?autoReconnect=true";
        private final String user="e6FpSaeFOi";
        private final String password="u1fHS7jJTy";

        public DBConnect(Handler handler) {

            connected=false;
            connection=null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                connection= DriverManager.getConnection(url,user,password);
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


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        public void addResults(PlayerData playerData){
            //select player_id from players
            int score,kills,wins;

            try {
                String query="SELECT score,kills,wins FROM players WHERE nickname='"+playerData.getNickname()+"';";

                resultSet= statement.executeQuery(query);

                score=playerData.getScore();
                kills=playerData.getKills();
                wins=playerData.getWins();

               if (resultSet.next()){
                   score += resultSet.getInt("score");
                   kills += resultSet.getInt("kills");
                   wins += resultSet.getInt("wins");
               }else{
                   System.out.println("ResultSet is empty");
                   addNewPlayer(playerData);
               }

                statement.executeUpdate(String.format("UPDATE players SET score=%d, kills=%d, wins=%d WHERE nickname='%s'",score,kills,wins,playerData.getNickname()));

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                if(resultSet!=null) {
                    try {
                        resultSet.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }

        }

        private void addNewPlayer(PlayerData playerData){

            String query="SELECT player_id FROM players ORDER BY player_id DESC limit 1";

            try {
                resultSet=statement.executeQuery(query);

                int player_id=1;
                while(resultSet.next())
                    player_id=resultSet.getInt("player_id")+1;

                statement.executeUpdate(String.format("INSERT INTO players(player_id,nickname,score,kills,wins) VALUES (%d, '%s', %d, %d, %d)",player_id,playerData.getNickname(),playerData.getScore(),playerData.getKills(),playerData.getWins()));

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        public boolean isConnected(){
            return connected;
        }

        public void close(){
            connected=false;
            try {
                if(!resultSet.isClosed())
                    resultSet.close();
                if(!connection.isClosed())
                    connection.close();
                this.finalize();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }

        }
}
