/**
 * 1125. 最小的必要团队
 *
 *
 * 思路：
 * 降people的能力分别记录在 技能表后面，然后dfs，一把梭。 之后做剪枝
 */

class Solution_1125 {

    var minTeamSize = 0
    val resTeam: HashSet<Int> = hashSetOf()
    fun smallestSufficientTeam(req_skills: Array<String>, people: List<List<String>>): IntArray {
        minTeamSize = people.size
        val map = mutableMapOf<String, MutableList<Int>>()
        req_skills.forEach {
            map[it] = mutableListOf()
        }

        var maxSizePeopleIndex = 0

        people.forEachIndexed { index, list ->
            if (list.size > people[maxSizePeopleIndex].size) {
                maxSizePeopleIndex = index
            }
            list.forEach {
                map[it]!!.add(index)
            }
        }
        dfs(map, req_skills, 0, hashSetOf())

        return resTeam.toIntArray()
    }


    /**
     * team 記錄people的index
     */
    fun dfs(map: MutableMap<String, MutableList<Int>>, req_skills: Array<String>, curIndex: Int, team: HashSet<Int>) {
        if (team.size > minTeamSize) return
        if (curIndex == req_skills.size) {
            if (team.size < minTeamSize) {
                minTeamSize = team.size
                resTeam.clear()
                resTeam.addAll(team)
            }
            return
        }

        map[req_skills[curIndex]]!!.forEachIndexed { index, person ->
            val isRemove = !team.contains(person)
            team.add(person)
            dfs(map, req_skills, curIndex + 1, team)
            if (isRemove) team.remove(person)
        }
    }
}