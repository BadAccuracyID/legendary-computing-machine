package com.github.badaccuracyid.legendarycomputingmachine.utils;

import com.github.badaccuracyid.legendarycomputingmachine.objects.game.PlayerPosition;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.PlayerRole;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.Team;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.player.Player;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.player.impl.Attacker;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.player.impl.Defender;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.player.impl.Goalkeeper;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.player.impl.MidFielder;

import java.util.ArrayList;
import java.util.List;

public class GameUtils {

    private static final String[] nationalities = {
            "German", "Spanish", "Uruguayan", "Denmark", "France", "Ivory Coast", "Dutch", "Polish", "Brazilian"
    };
    private static final String[] firstNames = {
            "Lionel", "Cristiano", "Neymar", "Kylian", "Robert", "Luis", "Sergio", "Eden", "Kevin", "Paul", "Harry", "Mohamed", "Sadio", "Sergio", "Antoine", "Romelu", "Timo", "Leroy", "Thiago", "Toni",
    };
    private static final String[] lastNames = {
            "Stegen", "Casillas", "Navas", "De Gea", "Buffon", "Neuer", "Lloris", "Courtois", "Oblak", "Alisson", "Ederson", "Ter Stegen", "Pique", "Ramos", "Varane", "Marquinhos", "Kimpembe", "Koulibaly", "Bonucci", "Chiellini"
    };

    public static List<Player> generatePlayers() {
        List<Player> playerList = new ArrayList<>();
        // 1 goal keepers
        // 4 defenders
        // 3 midfielders
        // 3 attackers
        for (int i = 11; i > 0; i--) {
            // generate unique random shirt number
            int shirtNumber = (int) (Math.random() * 99 + 1);
            while (isDuplicateShirtNumber(playerList, shirtNumber)) {
                shirtNumber = (int) (Math.random() * 99 + 1);
            }

            // generate random name
            String playerName = firstNames[(int) (Math.random() * firstNames.length)] + " " + lastNames[(int) (Math.random() * lastNames.length)];

            // generate role and position
            PlayerRole playerRole;
            if (i > 10) {
                playerRole = PlayerRole.GOALKEEPER;
            } else if (i > 6) {
                playerRole = PlayerRole.DEFENDER;
            } else if (i > 3) {
                playerRole = PlayerRole.MIDFIELDER;
            } else {
                playerRole = PlayerRole.ATTACKER;
            }

            List<PlayerPosition> positionsByRole = PlayerPosition.getPositionsByRole(playerRole);
            PlayerPosition playerPosition = positionsByRole.get((int) (Math.random() * positionsByRole.size()));

            // generate random age 15-45
            int playerAge = (int) (Math.random() * 30 + 15);

            // generate random nationality
            String playerNationality = nationalities[(int) (Math.random() * nationalities.length)];

            Player player;
            switch (playerRole) {
                case GOALKEEPER:
                    player = new Goalkeeper(String.valueOf(shirtNumber), playerName, playerPosition, playerAge, playerNationality);
                    break;
                case DEFENDER:
                    player = new Defender(String.valueOf(shirtNumber), playerName, playerPosition, playerAge, playerNationality);
                    break;
                case MIDFIELDER:
                    player = new MidFielder(String.valueOf(shirtNumber), playerName, playerPosition, playerAge, playerNationality);
                    break;
                case ATTACKER:
                    player = new Attacker(String.valueOf(shirtNumber), playerName, playerPosition, playerAge, playerNationality);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + playerRole);
            }

            playerList.add(player);
        }

        return playerList;
    }

    public static List<Player> generatePlayersForTwo() {
        List<Player> playerList = new ArrayList<>();
        // 2 goal keepers
        // 8 defenders
        // 6 midfielders
        // 6 attackers
        for (int i = 22; i > 0; i--) {
            // generate unique random shirt number
            int shirtNumber = (int) (Math.random() * 99 + 1);
            while (isDuplicateShirtNumber(playerList, shirtNumber)) {
                shirtNumber = (int) (Math.random() * 99 + 1);
            }

            // generate random name
            String playerName = firstNames[(int) (Math.random() * firstNames.length)] + " " + lastNames[(int) (Math.random() * lastNames.length)];

            // generate role and position
            PlayerRole playerRole;
            if (i > 20) {
                playerRole = PlayerRole.GOALKEEPER;
            } else if (i > 12) {
                playerRole = PlayerRole.DEFENDER;
            } else if (i > 6) {
                playerRole = PlayerRole.MIDFIELDER;
            } else {
                playerRole = PlayerRole.ATTACKER;
            }

            List<PlayerPosition> positionsByRole = PlayerPosition.getPositionsByRole(playerRole);
            PlayerPosition playerPosition = positionsByRole.get((int) (Math.random() * positionsByRole.size()));

            // generate random age 15-45
            int playerAge = (int) (Math.random() * 30 + 15);

            // generate random nationality
            String playerNationality = nationalities[(int) (Math.random() * nationalities.length)];

            Player player;
            switch (playerRole) {
                case GOALKEEPER:
                    player = new Goalkeeper(String.valueOf(shirtNumber), playerName, playerPosition, playerAge, playerNationality);
                    break;
                case DEFENDER:
                    player = new Defender(String.valueOf(shirtNumber), playerName, playerPosition, playerAge, playerNationality);
                    break;
                case MIDFIELDER:
                    player = new MidFielder(String.valueOf(shirtNumber), playerName, playerPosition, playerAge, playerNationality);
                    break;
                case ATTACKER:
                    player = new Attacker(String.valueOf(shirtNumber), playerName, playerPosition, playerAge, playerNationality);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + playerRole);
            }

            playerList.add(player);
        }

        return playerList;
    }

    public static void displayTeamMembers(Team team) {
        System.out.println(team.getTeamName());
        System.out.println("================");
        System.out.println();

        System.out.println("Goalkeeper");
        System.out.println("================================");
        System.out.printf("| %-3s %-24s |\n", "REF", team.getGoalKeepers().get(0).getAttribute().getAtt1Value());
        System.out.printf("| %-3s %-24s |\n", "CAT", team.getGoalKeepers().get(0).getAttribute().getAtt2Name());
        System.out.printf("| %-3s %-24s |\n", "SHI", team.getGoalKeepers().get(0).getShirtNumber());
        System.out.printf("| %-3s %-24s |\n", "NAM", team.getGoalKeepers().get(0).getName());
        System.out.println("================================");

        System.out.println();
        System.out.println("Defender");
        System.out.print("================================ ");
        System.out.print("================================ ");
        System.out.print("================================ ");
        System.out.print("================================\n");

        System.out.printf("| %-3s %-24s | ", "DEF", team.getDefenders().get(0).getAttribute().getAtt1Value());
        System.out.printf("| %-3s %-24s | ", "DEF", team.getDefenders().get(1).getAttribute().getAtt1Value());
        System.out.printf("| %-3s %-24s | ", "DEF", team.getDefenders().get(2).getAttribute().getAtt1Value());
        System.out.printf("| %-3s %-24s |\n", "DEF", team.getDefenders().get(3).getAttribute().getAtt1Value());

        System.out.printf("| %-3s %-24s | ", "TAC", team.getDefenders().get(0).getAttribute().getAtt2Name());
        System.out.printf("| %-3s %-24s | ", "TAC", team.getDefenders().get(1).getAttribute().getAtt2Name());
        System.out.printf("| %-3s %-24s | ", "TAC", team.getDefenders().get(2).getAttribute().getAtt2Name());
        System.out.printf("| %-3s %-24s |\n", "TAC", team.getDefenders().get(3).getAttribute().getAtt2Name());

        System.out.printf("| %-3s %-24s | ", "SHI", team.getDefenders().get(0).getShirtNumber());
        System.out.printf("| %-3s %-24s | ", "SHI", team.getDefenders().get(1).getShirtNumber());
        System.out.printf("| %-3s %-24s | ", "SHI", team.getDefenders().get(2).getShirtNumber());
        System.out.printf("| %-3s %-24s |\n", "SHI", team.getDefenders().get(3).getShirtNumber());

        System.out.printf("| %-3s %-24s | ", "NAM", team.getDefenders().get(0).getName());
        System.out.printf("| %-3s %-24s | ", "NAM", team.getDefenders().get(1).getName());
        System.out.printf("| %-3s %-24s | ", "NAM", team.getDefenders().get(2).getName());
        System.out.printf("| %-3s %-24s |\n", "NAM", team.getDefenders().get(3).getName());

        System.out.print("================================ ");
        System.out.print("================================ ");
        System.out.print("================================ ");
        System.out.print("================================\n");

        System.out.println();
        System.out.println("Midfielder");
        System.out.print("================================ ");
        System.out.print("================================ ");
        System.out.print("================================\n");

        System.out.printf("| %-3s %-24s | ", "LOW", team.getMidfielders().get(0).getAttribute().getAtt1Value());
        System.out.printf("| %-3s %-24s | ", "LOW", team.getMidfielders().get(1).getAttribute().getAtt1Value());
        System.out.printf("| %-3s %-24s |\n", "LOW", team.getMidfielders().get(2).getAttribute().getAtt1Value());

        System.out.printf("| %-3s %-24s | ", "LOF", team.getMidfielders().get(0).getAttribute().getAtt2Name());
        System.out.printf("| %-3s %-24s | ", "LOF", team.getMidfielders().get(1).getAttribute().getAtt2Name());
        System.out.printf("| %-3s %-24s |\n", "LOF", team.getMidfielders().get(2).getAttribute().getAtt2Name());

        System.out.printf("| %-3s %-24s | ", "SHI", team.getMidfielders().get(0).getShirtNumber());
        System.out.printf("| %-3s %-24s | ", "SHI", team.getMidfielders().get(1).getShirtNumber());
        System.out.printf("| %-3s %-24s |\n", "SHI", team.getMidfielders().get(2).getShirtNumber());

        System.out.printf("| %-3s %-24s | ", "NAM", team.getMidfielders().get(0).getName());
        System.out.printf("| %-3s %-24s | ", "NAM", team.getMidfielders().get(1).getName());
        System.out.printf("| %-3s %-24s |\n", "NAM", team.getMidfielders().get(2).getName());

        System.out.print("================================ ");
        System.out.print("================================ ");
        System.out.print("================================\n");

        System.out.println();
        System.out.println("Attacker");
        System.out.print("================================ ");
        System.out.print("================================ ");
        System.out.print("================================\n");

        System.out.printf("| %-3s %-24s | ", "FIN", team.getAttackers().get(0).getAttribute().getAtt1Value());
        System.out.printf("| %-3s %-24s | ", "FIN", team.getAttackers().get(1).getAttribute().getAtt1Value());
        System.out.printf("| %-3s %-24s |\n", "FIN", team.getAttackers().get(2).getAttribute().getAtt1Value());

        System.out.printf("| %-3s %-24s | ", "KIC", team.getAttackers().get(0).getAttribute().getAtt2Name());
        System.out.printf("| %-3s %-24s | ", "KIC", team.getAttackers().get(1).getAttribute().getAtt2Name());
        System.out.printf("| %-3s %-24s |\n", "KIC", team.getAttackers().get(2).getAttribute().getAtt2Name());

        System.out.printf("| %-3s %-24s | ", "SHI", team.getAttackers().get(0).getShirtNumber());
        System.out.printf("| %-3s %-24s | ", "SHI", team.getAttackers().get(1).getShirtNumber());
        System.out.printf("| %-3s %-24s |\n", "SHI", team.getAttackers().get(2).getShirtNumber());

        System.out.printf("| %-3s %-24s | ", "NAM", team.getAttackers().get(0).getName());
        System.out.printf("| %-3s %-24s | ", "NAM", team.getAttackers().get(1).getName());
        System.out.printf("| %-3s %-24s |\n", "NAM", team.getAttackers().get(2).getName());

        System.out.print("================================ ");
        System.out.print("================================ ");
        System.out.print("================================\n");
    }

    public static void printBothTeams(Team firstTeam, Team backupTeam) {
        System.out.println("=====================================================================");
        System.out.printf("| Not in First Team               | First Team                      |\n");
        System.out.println("=====================================================================");
        for (int i = 0; i < Math.max(firstTeam.getPlayerList().size(), backupTeam.getPlayerList().size()); i++) {
            Player player1 = null;
            try {
                player1 = backupTeam.getPlayerList().get(i);
            } catch (Exception e) {
            }
            Player player2 = null;
            try {
                player2 = firstTeam.getPlayerList().get(i);
            } catch (Exception e) {
            }

            if (player1 == null) {
                assert player2 != null;
                System.out.printf("| %-2s %-21s %-3s %-2s | %-2s %-21s %-3s %-2s |\n",
                        " ", " ", " ", " ",
                        player2.getShirtNumber(), player2.getName(), player2.getPosition().toString(), (int) player2.getOverallRating());
            } else if (player2 == null) {
                System.out.printf("| %-2s %-21s %-3s %-2s | %-2s %-21s %-3s %-2s |\n",
                        player1.getShirtNumber(), player1.getName(), player1.getPosition().toString(), (int) player1.getOverallRating(),
                        " ", " ", " ", " ");
            } else {
                System.out.printf("| %-2s %-21s %-3s %-2s | %-2s %-21s %-3s %-2s |\n",
                        player1.getShirtNumber(), player1.getName(), player1.getPosition().toString(), (int) player1.getOverallRating(),
                        player2.getShirtNumber(), player2.getName(), player2.getPosition().toString(), (int) player2.getOverallRating());
            }
        }
        System.out.println("=====================================================================");
    }

    private static boolean isDuplicateShirtNumber(List<Player> playerList, int shirtNumber) {
        return playerList.stream().anyMatch(player -> Integer.parseInt(player.getShirtNumber()) == shirtNumber);
    }
}
