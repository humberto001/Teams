package infrateste.database

import com.example.wordcup.team.domain.model.Team
import org.assertj.core.util.Lists

class TeamDataSet {

    static def team1 = new Team(id: 10, name: "Vasco", numberOfPlayers: 10)
    static def team2 = new Team(id: 10, name: "Bota", numberOfPlayers: 9)
    static def team3 = new Team(id: 10, name: "Flu", numberOfPlayers: 8)
    static def team4 = new Team(id: 10, name: "Fla", numberOfPlayers: 7)

    static Collection<Team> teams = Lists.newArrayList()

    static {
        teams.add(team1)
        teams.add(team2)
        teams.add(team3)
        teams.add(team4)
    }

    static findAll(){
        return teams.findAll()
    }
}

